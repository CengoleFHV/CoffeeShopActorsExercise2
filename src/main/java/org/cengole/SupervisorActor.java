package org.cengole;

import akka.actor.*;

public class SupervisorActor extends AbstractActor {
    private int coffeesServed = 0;
    private int maxCoffees;
    private final ActorRef customer;
    private final ActorRef barista;

    public SupervisorActor(int maxCoffees) {
        this.maxCoffees = maxCoffees;
        this.barista = getContext().actorOf(Props.create(BaristaActor.class), "barista");
        this.customer = getContext().actorOf(Props.create(CustomerActor.class, this.barista), "customer");
        System.out.println("SupervisorActor created");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("start", message -> {
                    // start serving customers
                    customer.tell("order", getSelf());
                })
                .match(CoffeeReady.class, message -> {
                    // increase count for served coffee
                    coffeesServed++;

                    // print out the currently served amount
                    System.out.println("Coffees Served: " + coffeesServed);

                    // Stop the system after serving over 10
                    if (coffeesServed >= maxCoffees) {
                        getContext().getSystem().terminate();
                    } else {
                        // otherwise serve next customer
                        customer.tell("order", getSelf());
                    }
                })
                .build();
    }
}
