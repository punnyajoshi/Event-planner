package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.model.Attendee;
import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Venue;
import com.ultralesson.eventplanner.service.EventPlanner;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AttendeeManagementTest {
    @Test
    public void testAddingAttendeeToEvent()
    {
        Event event = new Event(1, "Adding new Event", "Connect with Testers", new Venue(1, "Conference Hall A", "123 Business Rd.", 150));
        Attendee newAttendee = new Attendee(2, "John Dsouza", "John@gmail.com");
        int initialAttendeeCount = event.getAttendees().size();
        event.addAttendee(newAttendee);
        Assert.assertTrue(event.getAttendees().contains(newAttendee), "The new attendee should be added to the event.");
        Assert.assertEquals(event.getAttendees().size(), initialAttendeeCount + 1, "Attendee count should be increased by one.");
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddingInvalidAttendee() {
        Event event = new Event(1, "Adding new Event", "Connect with Testers", new Venue(1, "Conference Hall A", "123 Business Rd.", 150));
        Attendee invalidAttendee = new Attendee(3, "Invalid User", "invalidemail"); // Invalid email format
        event.addAttendee(invalidAttendee);
    }
    @Test
    public void testRemovingAttendeeFromEvent() {
        Event event = new Event(1, "Existing Event", "Event Description", new Venue(1, "Venue Name", "Venue Address", 100));
        EventPlanner eventPlanner = new EventPlanner();
        Attendee attendee = new Attendee(1, "John Mandise", "john@test.com");
        event.addAttendee(attendee);

        int initialCount = event.getAttendees().size();
        event.removeAttendee(attendee);
        Assert.assertFalse(event.getAttendees().contains(attendee), "Attendee should be removed from the event");
        Assert.assertEquals(event.getAttendees().size(), initialCount - 1, "Attendee count should get decrease by 1 on removal");
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddingAttendeeToNonExistentEvent() {
        EventPlanner eventPlanner = new EventPlanner();
        Attendee attendee = new Attendee(4, "Alexa", "alexa@example.com");
        Event nonExistentEvent = new Event(11, "Non Existent Event", "This event does not exist", null); // deliberately setting venue as null

        eventPlanner.addEvent(nonExistentEvent); //might be needed to trigger a specific exception here
        eventPlanner.addAttendee(attendee); // Expecting an exception due to non-existent event
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEmptyEmailAttendeeAddition() {
        Event event = new Event(1, "Test Event", "Test Description", new Venue(1, "Test Venue", "Test Address", 100));
        Attendee invalidAttendee = new Attendee(10, "Test Name", ""); // Empty email
        event.addAttendee(invalidAttendee);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddingAttendeeToNonExistentEvent() {
        EventPlanner eventPlanner = new EventPlanner();
        Attendee attendee = new Attendee(1, "Jane Doe", "jane.doe@example.com");
        Event nonExistentEvent = new Event(100, "Nonexistent Event", "An event that does not exist", null);

        // This should throw an exception because the event does not exist within the event planner.
        eventPlanner.addAttendeeToEvent(nonExistentEvent, attendee);
    }

    public void testSafeRemoveAttendeeFromEvent() {
        EventPlanner eventPlanner = new EventPlanner();
        Event event = new Event(1, "Event", "Description", new Venue(1, "Venue", "Address", 100));
        Attendee attendee = new Attendee(1, "John Doe", "john.doe@example.com");

        event.addAttendee(attendee);
        int initialAttendeeCount = event.getAttendees().size();

        // Try to remove an attendee that does not exist in the event.
        Attendee nonExistentAttendee = new Attendee(2, "Jane Doe", "jane.doe@example.com");
        event.removeAttendee(nonExistentAttendee);

        Assert.assertEquals(event.getAttendees().size(), initialAttendeeCount, "Attendee list size should remain unchanged");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForMissingEventDetails() {
        EventPlanner eventPlanner = new EventPlanner();
        Event event = new Event(1, "", "Description", new Venue(1, "Venue", "Address", 100));
        eventPlanner.addEvent(event);
    }

}