package com.javastudy.coworkings.entity;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoworkingBuilderTest {
    @Test
    public void newCoworkingBuilderTest() {

        Coworking coworking = Coworking.builder()
                .city("Kiev")
                .containsDesk(true)
                .containsMeetingRoom(false)
                .containsOffice(true)
                .name("CoworkingName")
                .dayPrice(250)
                .id(1)
                .location("Khreshatyk St., 15")
                .mainImage("imagePath")
                .monthPrice(5000)
                .openingHours("10.00-21.00")
                .overview("Overview")
                .rating(9.8)
                .reviewsCount(52)
                .weekPrice(1400)
                .build();

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
