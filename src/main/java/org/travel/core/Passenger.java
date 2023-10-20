package org.travel.core;

import org.travel.exceptions.ActivityCapacityIsFullException;
import org.travel.exceptions.InsufficientBalanceException;

import java.util.ArrayList;
import java.util.List;

public class Passenger {
    private final String name;
    private final int number;
    private double balance;
    private final PassengerType type;
    private final List<Activity> activityList;

    public Passenger(String name, int number, PassengerType type, double balance){
        this.name = name;
        this.number = number;
        this.type = type;
        this.balance = balance;
        this.activityList = new ArrayList<>();
    }

    public void signUpForActivity(Activity activity) throws InsufficientBalanceException, ActivityCapacityIsFullException {
        if(activity.getCapacity() <= 0){
            throw new ActivityCapacityIsFullException("The activity " + activity.getName() + "is already full");
        }
        if (type == PassengerType.STANDARD) {
            if (balance >= activity.getCost()) {
                activityList.add(activity);
                balance -= activity.getCost();
            } else {
                throw new InsufficientBalanceException("Not enough balance to sign up for the activity: " + activity.getName());
            }
        } else if (type == PassengerType.GOLD) {
            if (balance >= activity.getDiscountedCost()) {
                activityList.add(activity);
                balance -= activity.getDiscountedCost();
            } else {
                throw new InsufficientBalanceException("Not enough balance to sign up for the activity: " + activity.getName());
            }
        } else if (type == PassengerType.PREMIUM) {
            activityList.add(activity);
        }
    }

    public void printDetails() {
        System.out.println("Name: " + name);
        System.out.println("Number: " + number);
        System.out.println("Balance: " + balance);
        for (Activity activity : activityList) {
            System.out.println("Activity: " + activity.getName());
            System.out.println("Destination: " + activity.getDestination().getName());
            System.out.println("Price Paid: " + (activity.getCost() - activity.getDiscountedCost()));
        }
    }
    public String getName() {
        return name;
    }

    public double getBalance(){
        return balance;
    }

    public int getNumber() {
        return number;
    }
}
