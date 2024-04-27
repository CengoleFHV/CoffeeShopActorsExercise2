package org.cengole;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Coffee World!");
        ActorSystem system = ActorSystem.create("coffeeShop");
        System.out.println("Created CoffeeShop ActorSystem");
    ActorRef supervisor = system.actorOf(Props.create(SupervisorActor.class, 10), "supervisor");

        // Start the simulation
        supervisor.tell("start", ActorRef.noSender());
    }
}
