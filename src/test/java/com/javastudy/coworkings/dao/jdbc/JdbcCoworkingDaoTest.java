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
    private static Coworking actualCoworking;

    @BeforeAll
    static void before() throws IOException, URISyntaxException {
        init("db/dml_db_data.sql");
        ServiceLocator.register(CoworkingDao.class, new JdbcCoworkingDao(getDataSource()));
        actualCoworking = Coworking.builder()
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

        List<Coworking> factCoworking = coworkingDao.searchByName("test");

        assertEquals(1, factCoworking.size());
        assertEquals(actualCoworking.getId(), factCoworking.get(0).getId());
        assertEquals(actualCoworking.getName(), factCoworking.get(0).getName());
        assertEquals(actualCoworking.getMainImage(), factCoworking.get(0).getMainImage());
        assertEquals(actualCoworking.getOverview(), factCoworking.get(0).getOverview());
        assertEquals(actualCoworking.getLocation(), factCoworking.get(0).getLocation());
        assertEquals(actualCoworking.getReviewsCount(), factCoworking.get(0).getReviewsCount());
        assertEquals(actualCoworking.getCity(), factCoworking.get(0).getCity());
        assertEquals(actualCoworking.getDayPrice(), factCoworking.get(0).getDayPrice());
        assertEquals(actualCoworking.getWeekPrice(), factCoworking.get(0).getWeekPrice());
        assertEquals(actualCoworking.getMonthPrice(), factCoworking.get(0).getMonthPrice());
        assertEquals(actualCoworking.getRating(), factCoworking.get(0).getRating());
        assertEquals(actualCoworking.getOpeningHours(), factCoworking.get(0).getOpeningHours());
        assertEquals(actualCoworking.isContainsDesk(), factCoworking.get(0).isContainsDesk());
        assertEquals(actualCoworking.isContainsOffice(), factCoworking.get(0).isContainsOffice());
        assertEquals(actualCoworking.isContainsMeetingRoom(), factCoworking.get(0).isContainsMeetingRoom());
    }

    @Test
    public void testGetTop() {

        List<Coworking> factCoworking = coworkingDao.getTop(2);

        assertEquals(2, factCoworking.size()); //todo test fails
    }

    @Test
    public void testGetById() {

        Coworking factCoworking = coworkingDao.getById(1);

        assertEquals(actualCoworking.getId(), factCoworking.getId());
        assertEquals(actualCoworking.getName(), factCoworking.getName());
        assertEquals(actualCoworking.getMainImage(), factCoworking.getMainImage());
        assertEquals(actualCoworking.getOverview(), factCoworking.getOverview());
        assertEquals(actualCoworking.getLocation(), factCoworking.getLocation());
        assertEquals(actualCoworking.getReviewsCount(), factCoworking.getReviewsCount());
        assertEquals(actualCoworking.getCity(), factCoworking.getCity());
        assertEquals(actualCoworking.getDayPrice(), factCoworking.getDayPrice());
        assertEquals(actualCoworking.getWeekPrice(), factCoworking.getWeekPrice());
        assertEquals(actualCoworking.getMonthPrice(), factCoworking.getMonthPrice());
        assertEquals(actualCoworking.getRating(), factCoworking.getRating());
        assertEquals(actualCoworking.getOpeningHours(), factCoworking.getOpeningHours());
        assertEquals(actualCoworking.isContainsDesk(), factCoworking.isContainsDesk());
        assertEquals(actualCoworking.isContainsOffice(), factCoworking.isContainsOffice());
        assertEquals(actualCoworking.isContainsMeetingRoom(), factCoworking.isContainsMeetingRoom());
    }
}
