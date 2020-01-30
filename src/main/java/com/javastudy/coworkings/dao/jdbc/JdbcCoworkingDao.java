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

public class JdbcCoworkingDao implements CoworkingDao {
    private static final String GET_COWORKING_BY_ID = "SELECT id, name, mainimage, overview," +
            "location, reviewscount, city, dayprice, weekprice, monthprice, rating, openinghours" +
            "containsdesk, containsoffice, containsmeetingroom FROM Coworcings WHERE id=?;";
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
}
