package com.javastudy.coworkings.dao.jdbc;

import com.javastudy.coworkings.dao.UserDao;
import com.javastudy.coworkings.dao.jdbc.mapper.UserMapper;
import com.javastudy.coworkings.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDao implements UserDao {
    private static final UserMapper USER_MAPPER = new UserMapper();
    private static final String GET_BY_ID = "SELECT id, username, password, fullName, role, tel, email, city, sole FROM users WHERE id = ?";

    private DataSource dataSource;

    public JdbcUserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User getById(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new RuntimeException("Error happened while tried to get User with id: " + id);
                }

                User user = USER_MAPPER.mapRow(resultSet);

                if (resultSet.next()) {
                    throw new RuntimeException("Found more than one User with same id: " + id);
                }

                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error happened while trying to find a user by this id: " + id + " in DB ", e);
        }
    }
}
