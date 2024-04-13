package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Venue;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


@Test
public class EventTest {

    @Test(groups = {"creation"})
    public void testEventCreation() {


        Venue actualVenue = new Venue(1, "Grand Ballroom", "123 Main St", 500);
        Event event = new Event(1, "Wedding", "A beautiful wedding ceremony.", actualVenue);

        Assert.assertNotNull(event, "Event should not be null");
        Assert.assertEquals(event.getVenue(), actualVenue);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEventCreationWithMissingName() {
        Venue venue = new Venue(1, "Conference Hall", "101 Broad St", 300);
        new Event(1, null, "Some description", venue);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEventCreationWithMissingDescription() {
        Venue venue = new Venue(1, "Conference Hall", "101 Broad St", 300);
        new Event(1, "Tech Summit", null, venue);
    }

    @Test
    public void AddEventToDB(Event event) {
        List<Event> mockEventDB = new ArrayList<>();
        Venue venue = new Venue(1, "Ocean View Hall", "101 Beach Ave", 200);
        event = new Event(1, "Business Summit", "Annual business gathering", venue);
        mockEventDB.add(event);

        // Verify that the event is added to the list
        Assert.assertTrue(mockEventDB.contains(event));
        Event retrievedEvent = mockEventDB.get(0);
        Assert.assertEquals(retrievedEvent.getId(), event.getId());
        Assert.assertEquals(retrievedEvent.getName(), event.getName());
        Assert.assertEquals(retrievedEvent.getDescription(), event.getDescription());
        Assert.assertEquals(retrievedEvent.getVenue(), event.getVenue());

    }

    @Test
    public void RetrieveEventFromDB() {
        List<Event> mockEventDB = new ArrayList<>();
        Venue venue = new Venue(1, "Ocean View Hall", "101 Beach Ave", 200);
        Event event = new Event(1, "Business Summit", "Annual business gathering", venue);
        mockEventDB.add(event);


        Event retrievedEvent = mockEventDB.get(0);
        Assert.assertEquals(retrievedEvent, event, "Retrieved event should be the same as the stored one.");
    }

    private Event RetrieveEventById(int id) {

        List<Event> mockEventDB = new ArrayList<>();
        Venue venue = new Venue(1, "Ocean View Hall", "101 Beach Ave", 200);
        Event event = new Event(1, "Business Summit", "Annual business gathering", venue);
        mockEventDB.add(event);

        Event retrievedEvent = null;
        Assert.assertEquals(retrievedEvent.getVenue(), event.getVenue());

        return event;
    }

    @Test(groups = {"creation"})
    public void testEventRetention() {
        Venue venue = new Venue(1, "Banquet Hall", "500 Festive Lane", 250);
        Event event = new Event(2, "Gala Dinner", "Yearly company gala.", venue);

        AddEventToDB(event);
        Event retrievedEvent = RetrieveEventById(event.getId());
        Assert.assertNotNull(retrievedEvent, "Event retrieval failed.");
        Assert.assertEquals(retrievedEvent.getName(), event.getName());
        Assert.assertEquals(retrievedEvent.getDescription(), event.getDescription());
        Assert.assertEquals(retrievedEvent.getVenue(), event.getVenue());
    }




    @BeforeClass
    public void setUpClass() {
        System.out.println("Setting up resources for EventTest class...");
    }

    @AfterClass
    public void tearDownClass() {
        System.out.println("Releasing resources for EventTest class...");
    }



}
