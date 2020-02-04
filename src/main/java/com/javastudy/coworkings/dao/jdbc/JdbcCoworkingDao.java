package com.javastudy.coworkings.dao.jdbc;

import com.javastudy.coworkings.ServiceLocator;
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
            "location, reviewscount, city, dayprice, weekprice, monthprice, rating, openinghours" +
            "containsdesk, containsoffice, containsmeetingroom FROM Coworkings WHERE id=?;";

    private static final String GET_TOP_EIGHT = "SELECT id, name, mainimage, overview, location, reviewscount, " +
            "city, dayprice, weekprice, monthprice, rating, openinghours, containsdesk, containsoffice, " +
            "containsmeetingroom FROM coworkings ORDER BY rating DESC FETCH FIRST 8 ROWS ONLY;";
    private CoworkingRowMapper coworkingRowMapper = new CoworkingRowMapper();
    private DataSource dataSource = ServiceLocator.getService(DataSource.class);

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

    @Override
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
        }
    }
}
