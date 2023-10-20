package org.travel.core;

import org.travel.exceptions.TravelPackageFullException;

import java.util.ArrayList;
import java.util.List;

public class TravelPackage {
    private final String name;
    private final int capacity;
    private final List<Destination> destinations;
    private final List<Passenger> passengers;

    public TravelPackage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.destinations = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public void addPassenger(Passenger passenger) throws TravelPackageFullException {
        if (passengers.size() < capacity) {
            passengers.add(passenger);
        } else {
            throw new TravelPackageFullException("Package is full. Cannot add more members");
        }
    }

    public void addDestination(Destination destination) {
        destinations.add(destination);
    }

    public void printItinerary() {
        System.out.println("Travel Package: " + name);
        destinations.stream()
                .flatMap(destination -> destination.getActivities().stream())
                .forEach(activity -> {
                    System.out.println("Destination: " + activity.getDestination().getName());
                    System.out.println("Activity: " + activity.getName());
                    System.out.println("Description: " + activity.getDescription());
                    System.out.println("Cost: " + activity.getCost());
                    System.out.println("Capacity: " + activity.getCapacity());
                });
    }

    public void printPassengerList() {
        System.out.println("Travel Package: " + name);
        System.out.println("Capacity: " + capacity);
        System.out.println("Number of Passengers: " + passengers.size());
        for (Passenger passenger : passengers) {
            System.out.println("Name: " + passenger.getName());
            System.out.println("Number: " + passenger.getNumber());
        }
    }

    public void printAvailableActivities() {
        System.out.println("Available Activities:");
        destinations.stream()
                .flatMap(destination -> destination.getActivities().stream())
                .filter(activity -> activity.getAvailableSpace() > 0)
                .forEach(activity -> {
                    System.out.println("Activity: " + activity.getName());
                    System.out.println("Available Space: " + activity.getAvailableSpace());
                });
    }

}
