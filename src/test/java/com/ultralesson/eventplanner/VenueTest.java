package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.model.Venue;
import org.testng.Assert;
import org.testng.annotations.Test;
@Test
public class VenueTest {

    @Test(groups = {"creation"})
    public void testVenueCreation() {
        Venue venue = new Venue(1, "Grand Ballroom", "123 Main St", 500);
        Assert.assertEquals(venue.getId(), 1);
        Assert.assertEquals(venue.getAddress(), "123 Main St");
        Assert.assertEquals(venue.getCapacity(), 500);

        venue = new Venue(1, "Test Venue", "This is a test venue.", 100);
        Assert.assertEquals(venue.getId(), 1);
        Assert.assertEquals(venue.getName(), "Test Venue");
        Assert.assertEquals(venue.getAddress(), "This is a test venue.");
        Assert.assertEquals(venue.getCapacity(), 100);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,groups = {"validation"})
    public void shouldThrowExceptionForNegativeCapacity() {
        Venue venue = new Venue(1, "Test Venue", "123 Test St", -10);
    }


}

