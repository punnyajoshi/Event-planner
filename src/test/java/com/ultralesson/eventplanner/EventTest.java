package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Venue;
import com.ultralesson.eventplanner.service.EventPlanner;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EventTest {
    Event event;
    Venue venue;
    @BeforeClass
    public void setUpClass(){
        System.out.println("Setting up the resources for event class");
    }

    @Test(groups = {"creation", "EventCreation"}, priority=3)
    public void testEventCreation(){
        venue = new Venue(1, "Heavenly Palace", "Near Central Park", 1000);
        event = new Event(1,"convocation ceremony", "Degree Completion", venue);
        Assert.assertEquals(event.getId(), 1);
        Assert.assertEquals(event.getName(), "convocation ceremony");
        Assert.assertEquals(event.getDescription(), "Degree Completion");
        Assert.assertEquals(event.getVenue(), venue);
        Assert.assertNotNull(event, "event creation got failed; instance of the event is null");
    }
    @Test
    public void testEventProperties(){
        Assert.assertEquals(event.getId(), 1, "Event Id does not match");
        Assert.assertEquals(event.getName(), "convocation ceremony", "Event Name does not match");
        Assert.assertEquals(event.getDescription(), "Degree Completion", "Event Address does not match");
        Assert.assertEquals(event.getVenue(), venue, "Event Venue does not match");
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForNullEventName() {
        Venue dummyVenue = new Venue(1, "Venue Test", "Test Address", 100);

        Event testEvent = new Event(1, null, "Test Event", dummyVenue);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForMissingEventDescription(){
        Venue venue = new Venue(1, "Heavenly Palace", "Near Central Park", 1000);
        new Event(1, "Tech Conference", null, venue); // Expect IllegalArgumentException due to null description
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForMissingEventVenue(){
        new Event(1, "Tech Conference", "A conference about technology", null); // Expect IllegalArgumentException due to null venue
    }

    /*@Test(timeOut = 500)
    public void testEventCreationWithTimeout() throws InterruptedException {
        Thread.sleep(600);
        Venue venue1= new Venue(1, "Test Venue", "123 Test Street", 100);
        Event event2 = new Event(1, "Test Event", "This is a test event", venue1);
    }*/
    @Test
    public void testEventIsStoredAndRetrieved() {
        EventPlanner eventPlanner = new EventPlanner();
        Venue venue = new Venue(1, "Heavenly Palace", "Near Central Park", 1000);
        Event event = new Event(1,"Convocation Ceremony", "Degree Completion", venue);
        eventPlanner.addEvent(event);

        Event retrievedEvent = eventPlanner.getEvents().stream()
                .filter(e -> e.getId() == event.getId())
                .findFirst()
                .orElse(null);

        Assert.assertNotNull(retrievedEvent, "Event should be retrieved successfully");
        Assert.assertEquals(retrievedEvent.getId(), event.getId(), "Event ID should match the created event");
        Assert.assertEquals(retrievedEvent.getName(), event.getName(), "Event name should match the created event");
        Assert.assertEquals(retrievedEvent.getDescription(), event.getDescription(), "Event description should match the created event");
        Assert.assertEquals(retrievedEvent.getVenue(), event.getVenue(), "Event venue should match the created event");
    }
    @AfterClass
    public void tearDownClass(){
        System.out.println("Cleaning up resources for event class...");

    }

    @DataProvider(name = "eventDataProvider")
    public Object[][] provideEventData() {
        Venue validVenue = new Venue(1, "Conference Hall", "123 Main St", 300);
        return new Object[][] {
                // Valid data
                {1, "Tech Conference", "Tech Conference Description", validVenue, true},
                // Edge case: Very long event name
                {2, "This is a very long event name that might cause issues in some systems...", "Long name description", validVenue, true},
                // Invalid data: Event with null name
                {3, null, "Event with null name", validVenue, false},
                // Invalid data: Event with empty description
                {4, "Empty Description Event", "", validVenue, false},
                // Invalid data: Event with invalid venue (null)
                {5, "Invalid Venue Event", "Description", null, false},
        };
    }

    @Test(dataProvider = "eventDataProvider", groups = {"creation"})
    public void testEventCreationWithDataProvider(Integer id, String name, String description, Venue venue, Boolean expectedSuccess) {
        try {
            Event event = new Event(id, name, description, venue);
            Assert.assertTrue(expectedSuccess, "Event creation should succeed for valid data");
        } catch (IllegalArgumentException e) {
            Assert.assertFalse(expectedSuccess, "Event creation should fail for invalid data");
        }
    }
}