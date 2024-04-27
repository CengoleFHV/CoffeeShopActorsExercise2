package org.cengole;

public class CoffeeOrder {
    private final int time;
    private final String coffeeType;

    public CoffeeOrder(int time, String coffeeType) {
        this.time = time;
        this.coffeeType = coffeeType;
    }

    public int getTime() {
        return time;
    }

    public String getCoffeeType() {
        return coffeeType;
    }
}

