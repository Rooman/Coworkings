package com.javastudy.coworkings.dao.jdbc;

import com.javastudy.coworkings.ServiceLocator;
import com.javastudy.coworkings.dao.CoworkingDao;
import com.javastudy.coworkings.entity.Coworking;
import com.javastudy.coworkings.entity.CoworkingFilter;

import com.javastudy.coworkings.entity.RatingOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.javastudy.coworkings.util.DataSourceLoader.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JdbcCoworkingDaoTest {
    private CoworkingDao coworkingDao = ServiceLocator.getService(CoworkingDao.class);
    private static Coworking expectedCoworking;
    private static Coworking expectedCoworkingForFiltersTest;

    @BeforeAll
    static void before() throws IOException, URISyntaxException {
        init("db/dml_db_data.sql");
        ServiceLocator.register(CoworkingDao.class, new JdbcCoworkingDao(getDataSource()));
        expectedCoworking = Coworking.builder()
                .id((long) 1)
                .name("TestName")
                .mainImage("http://testlink")
                .overview("TestOverview")
                .location("TestLocation")
                .reviewsCount((long) 1)
                .city("Kyiv")
                .dayPrice(1)
                .weekPrice(2)
                .monthPrice(3)
                .rating(5)
                .openingHours("TestOpeningHours")
                .containsDesk(true)
                .containsOffice(true)
                .containsMeetingRoom(true)
                .build();


        expectedCoworkingForFiltersTest = Coworking.builder()
                .id((long) 4)
                .name("AnotherName3")
                .mainImage("http://testlink4")
                .overview("TestOverview4")
                .location("TestLocation4")
                .reviewsCount((long) 1)
                .city("Lviv")
                .dayPrice(7)
                .weekPrice(8)
                .monthPrice(9)
                .rating(11)
                .openingHours("TestOpeningHours4")
                .containsDesk(true)
                .containsOffice(false)
                .containsMeetingRoom(true)
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
    public void testGetFilteredFiltersOnly() {
        CoworkingFilter coworkingFilter = new CoworkingFilter();
        List<String> filter = new ArrayList<>();
        filter.add("containsdesk");
        filter.add("containsmeetingRoom");
        coworkingFilter.setFilters(filter);
        coworkingFilter.setCity("Lviv");
        List<Coworking> factCoworking = coworkingDao.getFiltered(coworkingFilter);

        assertEquals(1, factCoworking.size());
        assertEquals(expectedCoworkingForFiltersTest.getId(), factCoworking.get(0).getId());
        assertEquals(expectedCoworkingForFiltersTest.getName(), factCoworking.get(0).getName());
        assertEquals(expectedCoworkingForFiltersTest.isContainsDesk(), factCoworking.get(0).isContainsDesk());
        assertEquals(expectedCoworkingForFiltersTest.isContainsOffice(), factCoworking.get(0).isContainsOffice());
        assertEquals(expectedCoworkingForFiltersTest.isContainsMeetingRoom(), factCoworking.get(0).isContainsMeetingRoom());
    }

    @Test
    public void testGetFilteredRatingOnlyDesc() {
        CoworkingFilter coworkingFilter = new CoworkingFilter();
        Map<String, RatingOrder> ratingOrder = new HashMap<>();
        ratingOrder.put("ratingOrder", RatingOrder.HIGH_TO_LOW);
        coworkingFilter.setCity("Lviv");
        coworkingFilter.setRating(ratingOrder);

        List<Coworking> factCoworking = coworkingDao.getFiltered(coworkingFilter);
        assertEquals(3, factCoworking.size());
        assertEquals(3, factCoworking.get(0).getId());
        assertEquals(4, factCoworking.get(1).getId());
        assertEquals(2, factCoworking.get(2).getId());
    }

    @Test
    public void testGetFilteredRatingOnlyAsc() {
        CoworkingFilter coworkingFilter = new CoworkingFilter();
        Map<String, RatingOrder> ratingOrder = new HashMap<>();
        ratingOrder.put("ratingOrder", RatingOrder.LOW_TO_HIGH);
        coworkingFilter.setCity("Lviv");
        coworkingFilter.setRating(ratingOrder);

        List<Coworking> factCoworking = coworkingDao.getFiltered(coworkingFilter);
        assertEquals(3, factCoworking.size());
        assertEquals(2, factCoworking.get(0).getId());
        assertEquals(4, factCoworking.get(1).getId());
        assertEquals(3, factCoworking.get(2).getId());
    }

    @Test
    public void testGetFilteredPriceOnly100200() {
        CoworkingFilter coworkingFilter = new CoworkingFilter();
        List<String> price = new ArrayList<>();
        price.add("100-200");
        coworkingFilter.setPrice(price);
        coworkingFilter.setCity("Lviv");
        List<Coworking> factCoworking = coworkingDao.getFiltered(coworkingFilter);

        assertEquals(2, factCoworking.size());
        /*assertEquals(2, factCoworking.get(0).getId());
        assertEquals(4, factCoworking.get(1).getId());*/ //todo
    }

    @Test
    public void testGetFilteredPriceOnlyAbove300() {
        CoworkingFilter coworkingFilter = new CoworkingFilter();
        List<String> price = new ArrayList<>();
        price.add("above300");
        coworkingFilter.setPrice(price);
        coworkingFilter.setCity("Kyiv");
        List<Coworking> factCoworking = coworkingDao.getFiltered(coworkingFilter);

        assertEquals(1, factCoworking.size());
        assertEquals(1, factCoworking.get(0).getId());
    }

    @Test
    public void testGetFilteredPriceOnly100300() {
        CoworkingFilter coworkingFilter = new CoworkingFilter();
        List<String> price = new ArrayList<>();
        price.add("100-200");
        price.add("200-300");
        coworkingFilter.setPrice(price);
        coworkingFilter.setCity("Lviv");
        List<Coworking> factCoworking = coworkingDao.getFiltered(coworkingFilter);

        assertEquals(3, factCoworking.size());
        /*assertEquals(2, factCoworking.get(0).getId()); //todo
        assertEquals(3, factCoworking.get(1).getId());
        assertEquals(4, factCoworking.get(2).getId());*/
    }

    @Test
    public void testGetFilteredPriceOnly100200Above300() {
        CoworkingFilter coworkingFilter = new CoworkingFilter();
        List<String> price = new ArrayList<>();
        price.add("100-200");
        price.add("above300");
        coworkingFilter.setPrice(price);
        coworkingFilter.setCity("Lviv");
        List<Coworking> factCoworking = coworkingDao.getFiltered(coworkingFilter);

        assertEquals(3, factCoworking.size());
        /*assertEquals(2, factCoworking.get(0).getId()); //todo
        assertEquals(4, factCoworking.get(1).getId());
        assertEquals(5, factCoworking.get(2).getId());*/
    }

        @Test
        public void testSearchByName(){

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
        public void testGetByCity () {

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
