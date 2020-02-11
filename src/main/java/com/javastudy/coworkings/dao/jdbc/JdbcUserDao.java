package com.javastudy.coworkings.dao.jdbc;

import com.javastudy.coworkings.dao.UserDao;
import com.javastudy.coworkings.dao.jdbc.mapper.UserMapper;
import com.javastudy.coworkings.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDao implements UserDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String GET_BY_ID = "SELECT id, username, password, fullname, role, tel, email, city, sole FROM users WHERE id = ?";
    private static final String GET_BY_USERNAME = "SELECT id, username, password, fullname, role, tel, email, city, sole FROM users WHERE username = ?";

    private static final UserMapper USER_MAPPER = new UserMapper();
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
                    logger.warn("ResultSet was empty for user with id: {}", id);
                }

                User user = USER_MAPPER.mapRow(resultSet);

                if (resultSet.next()) {
                    logger.warn("Found more than one User with same id: {}", id);
                }

                return user;
            }
        } catch (SQLException e) {
            logger.error("SQL Failed: {}", GET_BY_ID);
            throw new RuntimeException("Error happened while trying to find a user with id: " + id + " in DB ", e);
        }
    }

    @Override
    public User getByUsername(String username) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_USERNAME)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    logger.warn("ResultSet was empty for user with username: {}", username);
                }

                User user = USER_MAPPER.mapRow(resultSet);

                if (resultSet.next()) {
                    logger.warn("Found more than one User with same username: {}", username);
                }

                return user;
            }
        } catch (SQLException e) {
            logger.error("SQL Failed: {}", GET_BY_USERNAME);
            throw new RuntimeException("Error happened while trying to find a user with username: " + username + " in DB ", e);
        }
    }
}
