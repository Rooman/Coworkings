package com.javastudy.coworkings.dao.jdbc.mapper;

import com.javastudy.coworkings.entity.User;
import com.javastudy.coworkings.entity.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public User rowMap(ResultSet resultSet) {
        try {
            //Not sure if we need to separate getting values from ResultSet and assigning
            //them via builder. If needed, I can refactor like this: setId(resultSet.getLong("id")

            Long id = resultSet.getLong("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String fullName = resultSet.getString("fullName");
            UserRole userRole = UserRole.getByName(resultSet.getString("role"));
            int tel = resultSet.getInt("tel");
            String email = resultSet.getString("email");
            String city = resultSet.getString("city");
            String sole = resultSet.getString("sole");

            User user = User.newBuilder()
                    .setId(id)
                    .setUsername(username)
                    .setPassword(password)
                    .setFullName(fullName)
                    .setRole(userRole)
                    .setTel(tel)
                    .setEmail(email)
                    .setCity(city)
                    .setSole(sole)
                    .build();

            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Error happened while getting User info from ResultSet", e);
        }
    }
}
