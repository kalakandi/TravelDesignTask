package org.travel.core;

import org.travel.exceptions.ActivityCapacityIsFullException;
import org.travel.exceptions.DestinationNotFound;
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

    public void signUpForActivity(Activity activity) throws InsufficientBalanceException, ActivityCapacityIsFullException, DestinationNotFound {
        // Check if the activity is already full.
        if (activity.getCapacity() <= 0) {
            throw new ActivityCapacityIsFullException("The activity " + activity.getName() + "is already full");
        }

        // Sign up the passenger for the activity based on their type.
        switch (type) {
            case STANDARD -> signUpStandardPassenger(activity);
            case GOLD -> signUpGoldPassenger(activity);
            case PREMIUM -> signUpPremiumPassenger(activity);
            default -> throw new IllegalArgumentException("Unknown passenger type: " + type);
        }
    }

    private void signUpStandardPassenger(Activity activity) throws InsufficientBalanceException {
        if (balance < activity.getCost()) {
            throw new InsufficientBalanceException("Not enough balance to sign up for the activity: " + activity.getName());
        }

        activityList.add(activity);
        balance -= activity.getCost();
    }

    private void signUpGoldPassenger(Activity activity) throws InsufficientBalanceException, DestinationNotFound {
        if (balance >= activity.getDiscountedCost()) {
            activityList.add(activity);
            balance -= activity.getDiscountedCost();
        } else {
            throw new InsufficientBalanceException("Not enough balance to sign up for the activity: " + activity.getName());
        }
    }

    private void signUpPremiumPassenger(Activity activity) {
        activityList.add(activity);
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
