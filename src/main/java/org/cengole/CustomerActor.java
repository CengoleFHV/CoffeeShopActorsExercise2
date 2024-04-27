package org.cengole;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import java.util.Random;

public class CustomerActor extends AbstractActor {

    private final ActorRef barista;

    public CustomerActor(ActorRef barista) {
        this.barista = barista;
        System.out.println("Customer Actor created");
    }

    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, message -> {

                    // randomly choose coffee type and time
                    Random random = new Random();
                    String[] coffeeTypes = {"Latte", "Cappuccino", "Espresso"};
                    String coffeeType = coffeeTypes[random.nextInt(coffeeTypes.length)];
                    int time = random.nextInt(24);

                    // send order to Barista
                    barista.tell(new CoffeeOrder(time, coffeeType), getSender());
                })
                .build();
    }
}
