package com.javastudy.coworkings.dao;

import com.javastudy.coworkings.dao.jdbc.mapper.CoworkingRowMapper;
import com.javastudy.coworkings.entity.Coworking;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoworkingRowMapperTest {
    @Test
    public void rowMapTest() throws SQLException {
        CoworkingRowMapper coworkingRowMapper = new CoworkingRowMapper();

        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockResultSet.getLong("id")).thenReturn(1L);
        when(mockResultSet.getString("name")).thenReturn("Name");
        when(mockResultSet.getString("mainimage")).thenReturn("mainimage");
        when(mockResultSet.getString("overview")).thenReturn("overview");
        when(mockResultSet.getString("location")).thenReturn("location");
        when(mockResultSet.getLong("reviewscount")).thenReturn(1L);
        when(mockResultSet.getString("city")).thenReturn("city");
        when(mockResultSet.getDouble("dayprice")).thenReturn(1D);
        when(mockResultSet.getDouble("weekprice")).thenReturn(1D);
        when(mockResultSet.getDouble("monthprice")).thenReturn(1D);
        when(mockResultSet.getDouble("rating")).thenReturn(1D);
        when(mockResultSet.getString("openinghours")).thenReturn("openinghours");
        when(mockResultSet.getBoolean("containsdesk")).thenReturn(true);
        when(mockResultSet.getBoolean("containsoffice")).thenReturn(false);
        when(mockResultSet.getBoolean("containsmeetingroom")).thenReturn(true);

        Coworking actual = coworkingRowMapper.rowMap(mockResultSet);

        assertNotNull(actual);
        assertEquals(1L, actual.getId());
        assertEquals("Name", actual.getName());
        assertEquals("mainimage", actual.getMainImage());
        assertEquals("overview", actual.getOverview());
        assertEquals("location", actual.getLocation());
        assertEquals(1L, actual.getReviewsCount());
        assertEquals("city", actual.getCity());
        assertEquals(1D, actual.getDayPrice(), 0);
        assertEquals(1D, actual.getWeekPrice(), 0);
        assertEquals(1D, actual.getMonthPrice(), 0);
        assertEquals(1D, actual.getRating(), 0);
        assertEquals("openinghours", actual.getOpeningHours());
        assertTrue(actual.isContainsDesk());
        assertFalse(actual.isContainsOffice());
        assertTrue(actual.isContainsMeetingRoom());
    }
}
