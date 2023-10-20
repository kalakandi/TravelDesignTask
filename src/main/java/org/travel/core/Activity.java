package org.travel.core;

public class Activity {
    private final String name;
    private final String description;
    private final double cost;
    private final int capacity;
    private Destination destination;

    public Activity(String name, String description, double cost, int capacity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Destination getDestination() {
        return destination;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSpace() {
        return capacity - destination.getSignedUpPassengersCount(this);
    }

    public double getDiscountedCost() {
        if (destination != null) {
                return cost * 0.9;
        }
        else{
            throw new NullPointerException("Destination is Null");
        }
    }

}
