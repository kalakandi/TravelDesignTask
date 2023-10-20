package org.travel.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.travel.exceptions.ActivityCapacityIsFullException;
import org.travel.exceptions.InsufficientBalanceException;
import org.travel.exceptions.TravelPackageFullException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TravelPackageTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void redirectOutput() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreOutput() {
        System.setOut(originalOut);
    }

    @Test
    void testPrintItinerary() {
        TravelPackage travelPackage = new TravelPackage("Mountain Adventure", 20);
        Destination destination1 = new Destination("Mountain Retreat");
        Destination destination2 = new Destination("Beach Getaway");

        Activity activity1 = new Activity("Hiking", "Enjoy hiking in the mountains", 50.0, 10);
        Activity activity2 = new Activity("Snorkeling", "Explore underwater beauty", 60.0, 15);

        destination1.addActivity(activity1);
        destination2.addActivity(activity2);

        travelPackage.addDestination(destination1);
        travelPackage.addDestination(destination2);

        travelPackage.printItinerary();

        String expectedOutput = "Travel Package: Mountain Adventure\n" +
                "Destination: Mountain Retreat\n" +
                "Activity: Hiking\n" +
                "Description: Enjoy hiking in the mountains\n" +
                "Cost: 50.0\n" +
                "Capacity: 10\n" +
                "Destination: Beach Getaway\n" +
                "Activity: Snorkeling\n" +
                "Description: Explore underwater beauty\n" +
                "Cost: 60.0\n" +
                "Capacity: 15";

        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    void testPrintPassengerList() throws TravelPackageFullException {
        TravelPackage travelPackage = new TravelPackage("Mountain Adventure", 20);

        Passenger passenger1 = new Passenger("Grace", 1, PassengerType.GOLD, 70.0);
        Passenger passenger2 = new Passenger("Harry", 2, PassengerType.STANDARD, 60.0);

        travelPackage.addPassenger(passenger1);
        travelPackage.addPassenger(passenger2);

        travelPackage.printPassengerList();

        String expectedOutput = "Travel Package: Mountain Adventure\n" +
                "Capacity: 20\n" +
                "Number of Passengers: 2\n" +
                "Name: Grace\n" +
                "Number: 1\n" +
                "Name: Harry\n" +
                "Number: 2";

        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    void testPrintAvailableActivities() {
        TravelPackage travelPackage = new TravelPackage("Mountain Adventure", 20);
        Destination destination = new Destination("Mountain Retreat");

        Activity activity1 = new Activity("Hiking", "Enjoy hiking in the mountains", 50.0, 10);
        Activity activity2 = new Activity("Camping", "Experience the great outdoors", 40.0, 5);

        destination.addActivity(activity1);
        destination.addActivity(activity2);

        travelPackage.addDestination(destination);

        travelPackage.printAvailableActivities();

        String expectedOutput = "Available Activities:\n" +
                "Activity: Hiking\n" +
                "Available Space: 9\n" +
                "Activity: Camping\n" +
                "Available Space: 4";

        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    void testAddPassengerToFullPackage() throws TravelPackageFullException {
        TravelPackage travelPackage = new TravelPackage("Full Package", 2);
        Passenger passenger1 = new Passenger("Alice", 1, PassengerType.STANDARD, 100.0);
        Passenger passenger2 = new Passenger("Bob", 2, PassengerType.STANDARD, 100.0);
        Passenger passenger3 = new Passenger("Charlie", 3, PassengerType.STANDARD, 100.0);

        travelPackage.addPassenger(passenger1);
        travelPackage.addPassenger(passenger2);

        assertThrows(TravelPackageFullException.class, () -> travelPackage.addPassenger(passenger3));
    }

    @Test
    void testSignUpForActivityWithInsufficientBalance() {
        Destination destination = new Destination("Mountain Retreat");
        Activity activity = new Activity("Hiking", "Enjoy hiking in the mountains", 50.0, 10);
        destination.addActivity(activity);

        Passenger passenger = new Passenger("David", 4, PassengerType.STANDARD, 30.0);

        assertThrows(InsufficientBalanceException.class, () -> passenger.signUpForActivity(activity));
    }

    @Test
    void testSignUpForActivityWithSufficientBalance() throws InsufficientBalanceException, ActivityCapacityIsFullException {
        Destination destination = new Destination("Mountain Retreat");
        Activity activity = new Activity("Hiking", "Enjoy hiking in the mountains", 50.0, 10);
        destination.addActivity(activity);

        Passenger passenger = new Passenger("David", 4, PassengerType.STANDARD, 60.0);
        passenger.signUpForActivity(activity);

        assertEquals(10.0, passenger.getBalance());
    }

    @Test
    public void testSignUpForActivityWithStandardPassenger() throws ActivityCapacityIsFullException, InsufficientBalanceException {
        Destination destination = new Destination("Mountain Retreat");
        Activity activity = new Activity("Hiking", "Enjoy hiking in the mountains", 50.0, 10);
        destination.addActivity(activity);

        Passenger passenger = new Passenger("Eve", 5, PassengerType.STANDARD, 70.0);
        passenger.signUpForActivity(activity);

        assertEquals(20.0, passenger.getBalance());
    }

    @Test
    public void testSignUpForActivityWithGoldPassenger() throws ActivityCapacityIsFullException, InsufficientBalanceException {
        Destination destination = new Destination("Mountain Retreat");
        Activity activity = new Activity("Hiking", "Enjoy hiking in the mountains", 50.0, 10);
        destination.addActivity(activity);

        Passenger passenger = new Passenger("Eve", 5, PassengerType.GOLD, 70.0);
        passenger.signUpForActivity(activity);

        assertEquals(25.0, passenger.getBalance());
    }

    @Test
    public void testSignUpForActivityWithPremiumPassenger() throws InsufficientBalanceException, ActivityCapacityIsFullException {
        Destination destination = new Destination("Mountain Retreat");
        Activity activity = new Activity("Hiking", "Enjoy hiking in the mountains", 50.0, 10);
        destination.addActivity(activity);

        Passenger passenger = new Passenger("Frank", 6, PassengerType.PREMIUM, 100.0);
        passenger.signUpForActivity(activity);

        assertEquals(100.0, passenger.getBalance());
    }

    @Test
    public void testAvailableActivities() {
        Destination destination = new Destination("Mountain Retreat");
        Activity activity1 = new Activity("Hiking", "Enjoy hiking in the mountains", 50.0, 10);
        Activity activity2 = new Activity("Camping", "Camp under the stars", 30.0, 5);
        destination.addActivity(activity1);
        destination.addActivity(activity2);

        TravelPackage travelPackage = new TravelPackage("Adventure Getaway", 20);
        travelPackage.addDestination(destination);

        activity1.setDestination(destination);
        activity2.setDestination(destination);

        activity1.setDestination(destination);
        activity2.setDestination(destination);

        assertEquals(2, destination.getAvailableActivities().size());
    }
}
