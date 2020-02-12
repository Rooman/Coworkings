package com.javastudy.coworkings.dao.jdbc;

import com.javastudy.coworkings.dao.CoworkingDao;
import com.javastudy.coworkings.dao.jdbc.mapper.CoworkingRowMapper;
import com.javastudy.coworkings.entity.Coworking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcCoworkingDao implements CoworkingDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String GET_COWORKING_BY_ID = "SELECT id, name, mainimage, overview," +
            "location, reviewscount, city, dayprice, weekprice, monthprice, rating, openinghours, " +
            "containsdesk, containsoffice, containsmeetingroom FROM Coworkings WHERE id=?;";

    private static final String GET_TOP_EIGHT = "SELECT id, name, mainimage, overview, location, reviewscount, " +
            "city, dayprice, weekprice, monthprice, rating, openinghours, containsdesk, containsoffice, " +
            "containsmeetingroom FROM coworkings ORDER BY rating LIMIT ?;";

    private static final String GET_BY_CITY = "SELECT id, name, mainimage, overview, location, reviewscount, " +
            "city, dayprice, weekprice, monthprice, rating, openinghours, containsdesk, containsoffice, " +
            "containsmeetingroom FROM coworkings WHERE city=?;";

    private static final String SEARCH_COWORKINGS_BY_NAME = "SELECT id, name, mainimage, overview," +
            "location, reviewscount, city, dayprice, weekprice, monthprice, rating, openinghours, " +
            "containsdesk, containsoffice, containsmeetingroom FROM Coworkings WHERE lower(name) like lower(?);";

    private static final CoworkingRowMapper COWORKING_ROW_MAPPER = new CoworkingRowMapper();
    private DataSource dataSource;

    public JdbcCoworkingDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Coworking getById(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COWORKING_BY_ID)) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (!resultSet.next()) {
                    throw new RuntimeException("Coworking with id: " + id + " isn't found");
                }
                Coworking coworking = COWORKING_ROW_MAPPER.rowMap(resultSet);

                if (resultSet.next()) {
                    throw new RuntimeException("Co-working's with id: " + id + " are several");
                }

                return coworking;
            }
        } catch (SQLException e) {
            logger.error("SQL Failed: {}", GET_COWORKING_BY_ID);
            throw new RuntimeException("Co-working with id: " + id + "wasn't found", e);
        }
    }

    public List<Coworking> getTop(int count) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_TOP_EIGHT)) {
            statement.setInt(1, count);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.isBeforeFirst()) {
                    logger.info("Error happened while getting top-rated Coworkings. Do not have expected data in the ResultSet.");
                }

                List<Coworking> top = new ArrayList<>();

                while (resultSet.next()) {
                    Coworking coworking = COWORKING_ROW_MAPPER.rowMap(resultSet);
                    top.add(coworking);
                }

                return top;
            }
        } catch (SQLException e) {
            logger.error("Exception occurred while getting top {} co-workings", count, e);
            throw new RuntimeException("Failed to execute query", e);
        }
    }

    public List<Coworking> searchByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_COWORKINGS_BY_NAME)) {
            List<Coworking> coworkings = new ArrayList<>();
            preparedStatement.setString(1, "%" + name + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Coworking coworking = COWORKING_ROW_MAPPER.rowMap(resultSet);
                    coworkings.add(coworking);
                }

                if (coworkings.size() == 0) {
                    logger.warn("No co-workings are found by following name of part of name {}", name);
                } else {
                    logger.info("{} co-workings were found by name of part of name {}", coworkings.size(), name);
                }

                return coworkings;
            }
        } catch (SQLException e) {
            logger.error("Exception occurred while getting co-working by name: {}", name, e);
            throw new RuntimeException("Failed to execute query", e);
        }
    }

    @Override
    public List<Coworking> getByCity(String city) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_CITY)) {
            statement.setString(1, city);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.isBeforeFirst()) {
                    logger.info("Error happened while getting Coworkings by city {}. Do not have expected data in the ResultSet.", city);
                }

                List<Coworking> coworkings = new ArrayList<>();

                while (resultSet.next()) {
                    Coworking coworking = COWORKING_ROW_MAPPER.rowMap(resultSet);
                    coworkings.add(coworking);
                }

                return coworkings;
            }
        } catch (SQLException e) {
            logger.error("SQL Failed: {}", GET_BY_CITY);
            throw new RuntimeException("Error happened while getting Coworkings by city: " + city, e);
        }
    }
}

