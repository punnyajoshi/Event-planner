package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Venue;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class EventTest {

    @Test(groups = {"creation"})
    public void testEventCreation() {
        Venue venue = new Venue(1, "Grand Ballroom", "123 Main St", 500);
        Event event = new Event(1, "Wedding", "A beautiful wedding ceremony.");
        assertEquals(event.getName(), "Wedding");
        assertEquals(event.getId(), 1);
        assertEquals(event.getDescription(), "A beautiful wedding ceremony.");
        assertEquals(event.getVenue(), venue);
        event = new Event(1, "Sample Event", "Sample Description");
        Assert.assertNotNull(event, "Event should not be null");

        venue = new Venue(1, "Test Venue", "123 Test Street", 200);
        event = new Event(1, "Test Event", "This is a test event.", venue);
        Assert.assertNotNull(event, "Event creation failed; event instance is null.");
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
        Event event = new Event(1, "Test Event", "This is a test event.", venue);
        assertEquals(event.getId(), 1, "Event ID mismatch");
        assertEquals(event.getName(), "Test Event", "Event name mismatch");
        assertEquals(event.getDescription(), "This is a test event.", "Event description mismatch");
        assertEquals(event.getVenue(), venue, "Event venue mismatch");
    }

    @Test (expectedExceptions = IllegalArgumentException.class,groups = {"validation"})
    public void expectExceptionWhenCreatingEventWithNullName(){
        Venue dummyVenue = new Venue(1, "Test Venue", "123 Test Street", 100);
        Event testEvent = new Event(1, null, "Test Event", dummyVenue);
    }

    @Test(timeOut = 500, groups = {"creation"})
    public void testEventCreationWithTimeout() throws InterruptedException {
        Thread.sleep(600);
        Venue venue = new Venue(1, "Test Venue", "123 Test St", 100);
        Event event = new Event(1, "Test Event", "This is a test event", venue);
    }

}
