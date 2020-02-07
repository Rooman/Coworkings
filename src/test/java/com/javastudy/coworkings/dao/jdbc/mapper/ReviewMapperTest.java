package com.javastudy.coworkings.dao.jdbc.mapper;

import com.javastudy.coworkings.entity.Review;
import com.javastudy.coworkings.entity.ReviewStatus;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReviewMapperTest {

    @Test
    public void testRowMap () throws SQLException {
        ReviewMapper reviewMapper = new ReviewMapper();
        ResultSet resultSetMock = mock(ResultSet.class);

        when(resultSetMock.getLong("id")).thenReturn(3L);
        when(resultSetMock.getString("description")).thenReturn("Very good coworking place, quiet and cozy");
        when(resultSetMock.getString("status")).thenReturn("NEW");
        when(resultSetMock.getLong("coworkingId")).thenReturn(1L);
        when(resultSetMock.getLong("userId")).thenReturn(2L);

        Review actual = reviewMapper.mapRow(resultSetMock);

        assertEquals(3, actual.getId());
        assertEquals("Very good coworking place, quiet and cozy", actual.getDescription());
        assertEquals(ReviewStatus.NEW, actual.getReviewStatus());
        assertEquals(1, actual.getCoworkingId());
        assertEquals(2, actual.getUserId());
    }
}
