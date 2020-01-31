package com.javastudy.coworkings.dao.jdbc;

import com.javastudy.coworkings.ServiceLocator;
import com.javastudy.coworkings.dao.CoworkingDao;
import com.javastudy.coworkings.dao.jdbc.mapper.CoworkingRowMapper;
import com.javastudy.coworkings.entity.Coworking;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcCoworkingDao implements CoworkingDao {
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
                    throw new RuntimeException("Error happened while getting eight top-rated Coworkings");
                }

                List<Coworking> topEight = new ArrayList<>();
                int i = 0;

                while (resultSet.next()) {
                    Coworking coworking = coworkingRowMapper.rowMap(resultSet);
                    topEight.add(coworking);
                    i++;
                    if (i >= 8) {
                        throw new RuntimeException("Found more than eight results of top-rated Coworkings, error happened;");
                    }
                }
                return topEight;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error happened while tried to get eight top-rated Coworkings;");
        }
    }
}
