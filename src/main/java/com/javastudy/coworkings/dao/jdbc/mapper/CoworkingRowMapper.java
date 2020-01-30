package com.javastudy.coworkings.dao.jdbc.mapper;

import com.javastudy.coworkings.entity.Coworking;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoworkingRowMapper {
    public Coworking rowMap(ResultSet resultSet) throws SQLException {
        Coworking coworking = Coworking.newCoworkingBuilder()
                .setId(resultSet.getLong("id"))
                .setName(resultSet.getString("name"))
                .setMainImage(resultSet.getString("mainimage"))
                .setOverview(resultSet.getString("overview"))
                .setLocation(resultSet.getString("location"))
                .setReviewsCount(resultSet.getLong("reviewscount"))
                .setCity(resultSet.getString("city"))
                .setDayPrice(resultSet.getDouble("dayprice"))
                .setWeekPrice(resultSet.getDouble("weekprice"))
                .setMonthPrice(resultSet.getDouble("monthprice"))
                .setRating(resultSet.getDouble("rating"))
                .setOpeningHours(resultSet.getString("openinghours"))
                .setContainsDesk(resultSet.getBoolean("containsdesk"))
                .setContainsOffice(resultSet.getBoolean("containsoffice"))
                .setContainsMeetingRoom(resultSet.getBoolean("containsmeetingroom"))
                .buildCoworking();
        return coworking;
    }
}

