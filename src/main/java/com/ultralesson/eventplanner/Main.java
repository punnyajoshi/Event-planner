package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.model.Attendee;
import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Schedule;
import com.ultralesson.eventplanner.model.Venue;
import com.ultralesson.eventplanner.service.EventPlanner;
import com.ultralesson.eventplanner.service.InvitationSender;
import com.ultralesson.eventplanner.service.ScheduleFinder;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create sample events, venues, and attendees
        Venue actualVenue = new Venue(2, "Hotel Ballroom", "Washington DC", 200);


        Event event1 = new Event(1, "Tech Conference", "A conference about technology", actualVenue);

        Attendee attendee1 = new Attendee(1, "John Doe", "john.doe@example.com");
        Attendee attendee2 = new Attendee(2, "Jane Smith", "jane.smith@example.com");

        // Add attendees to the events
        event1.addAttendee(attendee1);
        event1.addAttendee(attendee2);

        // Create an event planner and add events
        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.addEvent(event1);

        // Create schedules for the events

        // Add schedules to the event planner
        eventPlanner.scheduleEvent(event1, actualVenue, LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3));

        // Use ScheduleFinder to find schedules for today
        ScheduleFinder scheduleFinder = new ScheduleFinder(eventPlanner);
        List<Schedule> todaysSchedules = scheduleFinder.findSchedulesToday();
        System.out.println("Today's schedules:");
        for (Schedule schedule : todaysSchedules) {
            System.out.println(schedule);
        }

        // Send invitations for event1
        InvitationSender invitationSender = new InvitationSender();
        invitationSender.sendInvitations(event1);
    }
}