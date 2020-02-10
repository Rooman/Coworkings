package com.javastudy.coworkings.dao.jdbc;

import com.javastudy.coworkings.ServiceLocator;
import com.javastudy.coworkings.dao.ReviewDao;
import com.javastudy.coworkings.dao.UserDao;
import com.javastudy.coworkings.entity.Review;
import com.javastudy.coworkings.entity.ReviewStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import static com.javastudy.coworkings.util.DataSourceLoader.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JdbcReviewDaoTest {
    private ReviewDao reviewDao = ServiceLocator.getService(ReviewDao.class);

    @BeforeAll
    static void before() throws IOException, URISyntaxException {
        init("db/dml_db_data.sql");
        ServiceLocator.register(ReviewDao.class, new JdbcReviewDao(getDataSource()));
    }

    @BeforeEach
    void setUp() throws SQLException {
        reloadData();
    }

    @AfterEach
    void tearDown() throws SQLException {
        cleanSchema();
    }

    @Test
    public void testSearchAllReviewsByCoworkingId() {
        Review actualReview = Review.builder()
                .id((long) 4)
                .description("All is fine, but too loud")
                .reviewStatus(ReviewStatus.NEW)
                .coworkingId((long) 2)
                .userId((long) 1)
                .build();

        List<Review> factReview = reviewDao.getAllReviewsByCoworkingId(2);

        assertEquals(actualReview.getId(), factReview.get(0).getId());
        assertEquals(actualReview.getDescription(), factReview.get(0).getDescription());
        assertEquals(actualReview.getReviewStatus(), factReview.get(0).getReviewStatus());
        assertEquals(actualReview.getCoworkingId(), factReview.get(0).getCoworkingId());
        assertEquals(actualReview.getUserId(), factReview.get(0).getUserId());
    }
}
