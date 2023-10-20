package org.travel.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return (int) activities.stream()
                .filter(activity::equals)
                .count();
    }

    public List<Activity> getAvailableActivities() {
        return activities.stream()
                .filter(activity -> activity.getAvailableSpace() > 0)
                .collect(Collectors.toList());
    }
}
