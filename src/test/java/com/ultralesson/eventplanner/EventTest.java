package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Venue;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;


@Test
public class EventTest {
    
    Venue venue;
    Event event;
    Venue actualVenue = event.getVenue();

    @Test(groups = {"creation"})
    public void testEventCreation() {


        new Event(1, "Wedding", "A beautiful wedding ceremony.", actualVenue);
        event = new Event(1, "Sample Event", "Sample Description", actualVenue);
        venue = new Venue(1, "Test Venue", "123 Test Street", 200);
        event = new Event(1, "Test Event", "This is a test event.", actualVenue);

        assertEquals(event.getName(), "Wedding");
        assertEquals(event.getId(), 1);
        assertEquals(event.getDescription(), "A beautiful wedding ceremony.");
        assertEquals(event.getVenue(), venue);
        Assert.assertNotNull(event, "Event should not be null");
        Assert.assertNotNull(event, "Event creation failed; event instance is null.");
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

    @Test(groups = {"creation"})
    public void testEventCreationWithVenue() {
        Venue venue = new Venue(1, "Grand Ballroom", "123 Main St", 500);
        Event event = new Event(1, "Wedding", "A beautiful wedding ceremony.", actualVenue);
        Assert.assertNotNull(event, "Event should not be null");
        Assert.assertNotNull(event.getVenue(), "Event venue should not be null");
        assertEquals(event.getName(), "Wedding", "Event name did not match the expected value");
        assertEquals(event.getDescription(), "A beautiful wedding ceremony.", "Event description did not match the expected value");
    }

    @Test
    public void testAddEventToDB() {
        List<Event> mockEventDB = new ArrayList<>();
        Venue venue = new Venue(1, "Ocean View Hall", "101 Beach Ave", 200);
        Event event = new Event(1, "Business Summit", "Annual business gathering", venue);
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
    public void testRetrieveEventFromDB() {
        List<Event> mockEventDB = new ArrayList<>();
        Venue venue = new Venue(1, "Ocean View Hall", "101 Beach Ave", 200);
        Event event = new Event(1, "Business Summit", "Annual business gathering", venue);
        mockEventDB.add(event);

        // Retrieve the event and verify it
        Event retrievedEvent = mockEventDB.get(0);
        Assert.assertEquals(retrievedEvent, event, "Retrieved event should be the same as the stored one.");
    }
    @BeforeClass
    public void setUpClass() {
        System.out.println("Setting up resources for EventTest class...");
    }

    @AfterClass
    public void tearDownClass() {
        System.out.println("Releasing resources for EventTest class...");
    }

    @Test
    public void testPropertiesOfEvent() {
        Venue venue = new Venue(1, "Test Venue", "123 Test Street", 200);
        Event event = new Event(1, "Test Event", "This is a test event.", actualVenue);
        assertEquals(event.getId(), 1, "Event ID mismatch");
        assertEquals(event.getName(), "Test Event", "Event name mismatch");
        assertEquals(event.getDescription(), "This is a test event.", "Event description mismatch");
        assertEquals(event.getVenue(), venue, "Event venue mismatch");
    }

    @Test (expectedExceptions = IllegalArgumentException.class,groups = {"validation"})
    public void expectExceptionWhenCreatingEventWithNullName(){
        Venue dummyVenue = new Venue(1, "Test Venue", "123 Test Street", 100);
        new Event(1, null, "Test Event", actualVenue);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, groups = {"validation"})
    public void testEventCreationWithNullNameThrowsException() {
        Venue dummyVenue = new Venue(1, "Test Venue", "123 Test Street", 100);
        new Event(1, null, "Test Event Description", actualVenue);
    }

}
