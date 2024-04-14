package com.ultralesson.eventplanner;
import com.ultralesson.eventplanner.model.Attendee;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AttendeeTest {
    private Attendee attendee;
    //attendee = new Attendee(1, "Punnya", "invalid email");
    @BeforeMethod
    public void setup(){
        attendee = new Attendee(1, "Punnya", "punnyajoshi@gmail.com");

    }
    @Test(groups = {"creation", "attendeeCreation"}, priority = 1)
    public void testAttendeeCreation(){
        Attendee attendee = new Attendee(1, "Punnya", "punnyajoshi@gmail.com");
        Assert.assertEquals(attendee.getName(), "Punnya");
        Assert.assertEquals(attendee.getEmail(), "punnyajoshi@gmail.com");
        Assert.assertEquals(attendee.getId(), 1);
    }
    @Test(groups = {"validation"})
    public void testAttendeeProperties() {
        Attendee attendee = new Attendee(1, "John Doe", "john.doe@example.com");
        Assert.assertEquals(attendee.getId(), 1, "The id should match the one provided");
        Assert.assertEquals(attendee.getName(), "John Doe", "The name should match the one provided");
        Assert.assertEquals(attendee.getEmail(), "john.doe@example.com", "The email should match the one provided");
    }
    /* @Test
     public void testAttendeeEmailValidation(){

         attendee=new Attendee(1, "Punnya", "valid_email");
     }*/
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForInvalidEmail(){

        attendee=new Attendee(1, "new attendee", "google.com");
    }
    @AfterMethod
    public void tearDown(){
        // it will reset the attendee instance to null before executing the next test.
        attendee = null;
    }



}