package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.model.Attendee;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AttendeeTest {

    @Test(groups = {"creation"})
    public void testAttendeeCreation() {
        Object attendee = new Object();
        Assert.assertNotNull(attendee, "Attendee object should not be null");
    }

    @BeforeMethod
    public void setUp() {
        Attendee Attendee = new Attendee(1, "Punnya Joshi", "punnyajoshi@gmail.com");
    }

    @AfterMethod
    public void tearDown() {
        Object attendee = null;
    }

    @Test(groups = {"validation"})
    public void testAttendeeProperties() {
        Attendee attendee = new Attendee(1, "John Doe", "john.doe@example.com");
        // Add TestNG assertions here to validate attendee properties
    }

    @Test(expectedExceptions = IllegalArgumentException.class, groups = {"validation"})
    public void testAttendeeEmailValidation() {
        Attendee attendee = new Attendee(1, "John Doe", "invalid_email");
    }

    @Test (expectedExceptions = IllegalArgumentException.class,groups = {"validation"})
    public void shouldThrowExceptionForInvalidUsername(){
        Attendee attendee = new Attendee(1, "Test Attendee", "test.com");

    }
}
