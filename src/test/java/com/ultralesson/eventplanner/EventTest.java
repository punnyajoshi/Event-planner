package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Venue;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;


@Test
public class EventTest {

    Venue dummyVenue;
    @Test(groups = {"creation"})
    public void testEventCreation() {
        Venue venue = new Venue(1, "Grand Ballroom", "123 Main St", 500);
        Event event = new Event(1, "Wedding", "A beautiful wedding ceremony.", dummyVenue);
        event = new Event(1, "Sample Event", "Sample Description", dummyVenue);
        venue = new Venue(1, "Test Venue", "123 Test Street", 200);
        event = new Event(1, "Test Event", "This is a test event.", dummyVenue);

        assertEquals(event.getName(), "Wedding");
        assertEquals(event.getId(), 1);
        assertEquals(event.getDescription(), "A beautiful wedding ceremony.");
        assertEquals(event.getVenue(), venue);
        Assert.assertNotNull(event, "Event should not be null");
        Assert.assertNotNull(event, "Event creation failed; event instance is null.");
    }

    @Test(groups = {"creation"})
    public void testEventCreationWithVenue() {
        Venue venue = new Venue(1, "Grand Ballroom", "123 Main St", 500);
        Event event = new Event(1, "Wedding", "A beautiful wedding ceremony.", dummyVenue);
        Assert.assertNotNull(event, "Event should not be null");
        Assert.assertNotNull(event.getVenue(), "Event venue should not be null");
        assertEquals(event.getName(), "Wedding", "Event name did not match the expected value");
        assertEquals(event.getDescription(), "A beautiful wedding ceremony.", "Event description did not match the expected value");
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
        Event event = new Event(1, "Test Event", "This is a test event.", dummyVenue);
        assertEquals(event.getId(), 1, "Event ID mismatch");
        assertEquals(event.getName(), "Test Event", "Event name mismatch");
        assertEquals(event.getDescription(), "This is a test event.", "Event description mismatch");
        assertEquals(event.getVenue(), venue, "Event venue mismatch");
    }

    @Test (expectedExceptions = IllegalArgumentException.class,groups = {"validation"})
    public void expectExceptionWhenCreatingEventWithNullName(){
        Venue dummyVenue = new Venue(1, "Test Venue", "123 Test Street", 100);
        new Event(1, null, "Test Event", dummyVenue);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, groups = {"validation"})
    public void testEventCreationWithNullNameThrowsException() {
        Venue dummyVenue = new Venue(1, "Test Venue", "123 Test Street", 100);
        new Event(1, null, "Test Event Description", dummyVenue);
    }

}
