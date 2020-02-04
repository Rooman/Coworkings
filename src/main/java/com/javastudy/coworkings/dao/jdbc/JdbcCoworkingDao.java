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
    private CoworkingRowMapper coworkingRowMapper = new CoworkingRowMapper();
    private DataSource dataSource;

    public JdbcCoworkingDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String GET_COWORKING_BY_ID = "SELECT id, name, mainimage, overview," +
            "location, reviewscount, city, dayprice, weekprice, monthprice, rating, openinghours, " +
            "containsdesk, containsoffice, containsmeetingroom FROM Coworkings WHERE id=?;";
            
    private static final String GET_TOP_EIGHT = "SELECT id, name, mainimage, overview, location, reviewscount, " +
            "city, dayprice, weekprice, monthprice, rating, openinghours, containsdesk, containsoffice, " +
            "containsmeetingroom FROM coworkings ORDER BY rating DESC FETCH FIRST 8 ROWS ONLY;";

    private static final String SEARCH_COWORKINGS_BY_NAME = "SELECT id, name, mainimage, overview," +
            "location, reviewscount, city, dayprice, weekprice, monthprice, rating, openinghours, " +
            "containsdesk, containsoffice, containsmeetingroom FROM Coworkings WHERE lower(name) like lower(?);";

    @Override
    public Coworking getById(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COWORKING_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new RuntimeException("Coworking with id: " + id + " isn't found");
                }
                Coworking coworking = coworkingRowMapper.rowMap(resultSet);
                if (resultSet.next()) {
                    throw new RuntimeException("Coworking's with id: " + id + " are several");
                }
                return coworking;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Coworking with id: " + id + "isn't found", e);
        }
    }

    public List<Coworking> getTopEight() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_TOP_EIGHT)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    logger.info("Error happened while getting top-rated Coworkings. Do not have expected data in the ResultSet.");
                }

                List<Coworking> topEight = new ArrayList<>();

                while (resultSet.next()) {
                    Coworking coworking = coworkingRowMapper.rowMap(resultSet);
                    topEight.add(coworking);
                }
                return topEight;
            }
        } catch (SQLException e) {
            logger.error("Error occured while connecting to DB with this query: {}" + GET_TOP_EIGHT);
            throw new RuntimeException("Error happened while getting eight top-rated Coworkings: " + e);
          
    public List<Coworking> searchByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_COWORKINGS_BY_NAME)) {
            List<Coworking> listOfCoworkings = new ArrayList<>();
            preparedStatement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Coworking coworking = coworkingRowMapper.rowMap(resultSet);
                    listOfCoworkings.add(coworking);
                }
                if (listOfCoworkings.size() == 0) {
                    logger.warn("No co-workings are found by following name of part of name \"{}\"", name);
                } else {
                    logger.info("{} coworkings were found by name of part of name \"{}\"", listOfCoworkings.size(), name);
                }
                return listOfCoworkings;
            }
        } catch (SQLException e) {
            logger.error("Error occurred during SQL: {}", SEARCH_COWORKINGS_BY_NAME);
            throw new RuntimeException("Connection to database is not available . It is not possible to search users by name" + name, e);
        }
    }
}
