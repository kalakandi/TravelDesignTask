package org.travel.core;

import java.util.ArrayList;
import java.util.List;

public class Passenger {
    private String name;
    private int number;
    private double balance;
    private PassengerType type;
    private List<Activity> activityList;

    public Passenger(String name, int number, PassengerType type, double balance){
        this.name = name;
        this.number = number;
        this.type = type;
        this.balance = balance;
        this.activityList = new ArrayList<>();
    }
}
