package com.javastudy.coworkings.dao.jdbc.mapper;

import com.javastudy.coworkings.entity.User;
import com.javastudy.coworkings.entity.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public User mapRow(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String fullName = resultSet.getString("fullName");
            UserRole userRole = UserRole.getByName(resultSet.getString("role"));
            String tel = resultSet.getString("tel");
            String email = resultSet.getString("email");
            String city = resultSet.getString("city");
            String sole = resultSet.getString("sole");

            User user = User.builder()
                    .id(id)
                    .username(username)
                    .password(password)
                    .fullName(fullName)
                    .userRole(userRole)
                    .tel(tel)
                    .email(email)
                    .city(city)
                    .sole(sole)
                    .build();

            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error happened while getting User info from ResultSet", e);
        }
    }
}
