package com.javastudy.coworkings.dao.jdbc;

import com.javastudy.coworkings.ServiceLocator;
import com.javastudy.coworkings.dao.CoworkingDao;

import com.javastudy.coworkings.entity.Coworking;
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

public class JdbcCoworkingDaoTest {
    private CoworkingDao coworkingDao = ServiceLocator.getService(CoworkingDao.class);
    private static Coworking expectedCoworking;

    @BeforeAll
    static void before() throws IOException, URISyntaxException {
        init("db/dml_db_data.sql");
        ServiceLocator.register(CoworkingDao.class, new JdbcCoworkingDao(getDataSource()));
        expectedCoworking = Coworking.builder()
                .id((long) 1)
                .name ("TestName")
                .mainImage ("http://testlink")
                .overview ("TestOverview")
                .location ("TestLocation")
                .reviewsCount ((long)1)
                .city ("Kiev")
                .dayPrice (1)
                .weekPrice (2)
                .monthPrice (3)
                .rating (5)
                .openingHours ("TestOpeningHours")
                .containsDesk (true)
                .containsOffice (true)
                .containsMeetingRoom (true)
                .build();
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
    public void testSearchByName() {

        List<Coworking> actualCoworking = coworkingDao.searchByName("test");

        assertEquals(1, actualCoworking.size());
        assertEquals(expectedCoworking.getId(), actualCoworking.get(0).getId());
        assertEquals(expectedCoworking.getName(), actualCoworking.get(0).getName());
        assertEquals(expectedCoworking.getMainImage(), actualCoworking.get(0).getMainImage());
        assertEquals(expectedCoworking.getOverview(), actualCoworking.get(0).getOverview());
        assertEquals(expectedCoworking.getLocation(), actualCoworking.get(0).getLocation());
        assertEquals(expectedCoworking.getReviewsCount(), actualCoworking.get(0).getReviewsCount());
        assertEquals(expectedCoworking.getCity(), actualCoworking.get(0).getCity());
        assertEquals(expectedCoworking.getDayPrice(), actualCoworking.get(0).getDayPrice());
        assertEquals(expectedCoworking.getWeekPrice(), actualCoworking.get(0).getWeekPrice());
        assertEquals(expectedCoworking.getMonthPrice(), actualCoworking.get(0).getMonthPrice());
        assertEquals(expectedCoworking.getRating(), actualCoworking.get(0).getRating());
        assertEquals(expectedCoworking.getOpeningHours(), actualCoworking.get(0).getOpeningHours());
        assertEquals(expectedCoworking.isContainsDesk(), actualCoworking.get(0).isContainsDesk());
        assertEquals(expectedCoworking.isContainsOffice(), actualCoworking.get(0).isContainsOffice());
        assertEquals(expectedCoworking.isContainsMeetingRoom(), actualCoworking.get(0).isContainsMeetingRoom());
    }

    @Test
    public void testGetByCity() {

        List<Coworking> actualCoworking = coworkingDao.getByCity("Kiev");

        assertEquals(1, actualCoworking.size());
        assertEquals(expectedCoworking.getId(), actualCoworking.get(0).getId());
        assertEquals(expectedCoworking.getName(), actualCoworking.get(0).getName());
        assertEquals(expectedCoworking.getMainImage(), actualCoworking.get(0).getMainImage());
        assertEquals(expectedCoworking.getOverview(), actualCoworking.get(0).getOverview());
        assertEquals(expectedCoworking.getLocation(), actualCoworking.get(0).getLocation());
        assertEquals(expectedCoworking.getReviewsCount(), actualCoworking.get(0).getReviewsCount());
        assertEquals(expectedCoworking.getCity(), actualCoworking.get(0).getCity());
        assertEquals(expectedCoworking.getDayPrice(), actualCoworking.get(0).getDayPrice());
        assertEquals(expectedCoworking.getWeekPrice(), actualCoworking.get(0).getWeekPrice());
        assertEquals(expectedCoworking.getMonthPrice(), actualCoworking.get(0).getMonthPrice());
        assertEquals(expectedCoworking.getRating(), actualCoworking.get(0).getRating());
        assertEquals(expectedCoworking.getOpeningHours(), actualCoworking.get(0).getOpeningHours());
        assertEquals(expectedCoworking.isContainsDesk(), actualCoworking.get(0).isContainsDesk());
        assertEquals(expectedCoworking.isContainsOffice(), actualCoworking.get(0).isContainsOffice());
        assertEquals(expectedCoworking.isContainsMeetingRoom(), actualCoworking.get(0).isContainsMeetingRoom());

        actualCoworking = coworkingDao.getByCity("Lviv");

        assertEquals(2, actualCoworking.size());
    }
}
