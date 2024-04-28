package org.cengole;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import java.util.Random;

public class BaristaActor extends AbstractActor {

    public BaristaActor() {
        System.out.println("Barista Actor created");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(CoffeeOrder.class, order -> {
                    // If the order is for a cappuccino after 12, the barista should die
                    if (order.getCoffeeType().equals("Cappuccino") && order.getTime() > 12){
                        sender().tell("ready", getSelf());
                        throw new RuntimeException("Barista killed!");
                    }

                    System.out.println("Preparing " + order.getCoffeeType() + " for " + order.getTime() + " o'clock");
                    //Simulate the Barista not knowing every coffee type and taking some time
                    Thread.sleep(new Random().nextInt(3000) + 1000);

                    //Notify the Customer that coffee is ready
                    sender().tell("ready", getSelf());
                })
                .build();
    }
}
