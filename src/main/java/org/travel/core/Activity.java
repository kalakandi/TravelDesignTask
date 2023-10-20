package org.travel.core;

public class Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private Destination destination;

    public Activity(String name, String description, double cost, int capacity){
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
    }

}
