package org.travel.core;

import java.util.ArrayList;
import java.util.List;

public class TravelPackage {
    private String name;
    private int capacity;
    private List<Destination> destinations;
    private List<Passenger> passengers;

    public TravelPackage(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
        this.destinations = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public void addPassenger(Passenger passenger){
        //TODO
    }

    public void addDestination(Destination destination){
        //TODO
    }

    public void printItinerary(){
        //TODO
    }

    public void printPassengerList(){
        //TODO
    }

    public void printAvailableActivities(){
        // TODO
    }

}
