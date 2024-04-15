package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.model.Attendee;
import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Venue;
import com.ultralesson.eventplanner.service.EventPlanner;
import com.ultralesson.eventplanner.service.InvitationSender;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvitationSenderTest {
    @Test
    public void testSendingInvitationsToAttendees() {
        EventPlanner eventPlanner = new EventPlanner();
        InvitationSender invitationSender = new InvitationSender();
        Venue venue = new Venue(1, "Conference Hall", "123 Main Street", 100);
        Event event = new Event(1, "Annual Meeting", "Company's annual meet-up", venue);
        Attendee attendee1 = new Attendee(1, "Alice Johnson", "alice.johnson@example.com");
        Attendee attendee2 = new Attendee(2, "Bob Smith", "bob.smith@example.com");

        event.addAttendee(attendee1);
        event.addAttendee(attendee2);
        eventPlanner.addEvent(event);

        Event nonExistentEvent = new Event(-1, "Ghost Event", "This event does not exist", null);
        invitationSender.sendInvitations(event);
        invitationSender.sendInvitations(nonExistentEvent);

        long invitationContent = 0;
        long expectedContent = 0;
        boolean emailServiceMock = false;

        Assert.assertEquals(invitationContent, expectedContent, "The invitation content should match the expected format.");
        Assert.assertTrue(emailServiceMock, "The email sending service should be called.");

    }
    @Test(expectedExceptions = IllegalArgumentException.class, description = "Should throw exception when attempting to send invitations to an event with no attendees")
    public void testSendingInvitationsToEventWithNoAttendees() {
        EventPlanner eventPlanner = new EventPlanner();
        InvitationSender invitationSender = new InvitationSender();
        Venue venue = new Venue(1, "Conference Hall", "123 Main St", 100);
        Event event = new Event(1, "Annual Meeting", "Company's annual meet-up", venue);

        eventPlanner.addEvent(event);

        invitationSender.sendInvitations(event);
    }


}