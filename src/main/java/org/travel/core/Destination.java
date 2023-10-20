package org.travel.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Destination {
    private final String name;
    private final List<Activity> activities;

    public Destination(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addActivity(Activity activity){
        activity.setDestination(this);
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public int getSignedUpPassengersCount(Activity activity) {
        int passengerCount = 0;
        for (Activity a : activities) {
            if (a.equals(activity)) {
                passengerCount++;
            }
        }
        return passengerCount;
    }

    public List<Activity> getAvailableActivities() {
        List<Activity> availableActivities = new ArrayList<>();
        for (Activity activity : activities) {
            if (activity.getAvailableSpace() > 0) {
                availableActivities.add(activity);
            }
        }
        return availableActivities;
    }
}
