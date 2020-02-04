package com.javastudy.coworkings.entity;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoworkingBuilderTest {
    @Test
    public void newCoworkingBuilderTest() {
        Coworking coworking = Coworking.newCoworkingBuilder().setCity("Kiev")
                .setContainsDesk(true)
                .setContainsMeetingRoom(false)
                .setContainsOffice(true)
                .setName("CoworkingName")
                .setDayPrice(250)
                .setId(1)
                .setLocation("Khreshatyk St., 15")
                .setMainImage("imagePath")
                .setMonthPrice(5000)
                .setOpeningHours("10.00-21.00")
                .setOverview("Overview")
                .setRating(9.8)
                .setReviewsCount(52)
                .setWeekPrice(1400)
                .buildCoworking();

        assertEquals("Kiev", coworking.getCity());
        assertTrue(coworking.isContainsDesk());
        assertFalse(coworking.isContainsMeetingRoom());
        assertTrue(coworking.isContainsOffice());
        assertEquals("CoworkingName", coworking.getName());
        assertEquals(250, coworking.getDayPrice(), 0);
        assertEquals(1, coworking.getId());
        assertEquals("Khreshatyk St., 15", coworking.getLocation());
        assertEquals("imagePath", coworking.getMainImage());
        assertEquals(5000, coworking.getMonthPrice(), 0);
        assertEquals("10.00-21.00", coworking.getOpeningHours());
        assertEquals("Overview", coworking.getOverview());
        assertEquals(9.8, coworking.getRating(), 0);
        assertEquals(52, coworking.getReviewsCount(), 0);
        assertEquals(1400, coworking.getWeekPrice(), 0);
    }
}
