package com.javastudy.coworkings.dao.jdbc.mapper;

import com.javastudy.coworkings.entity.Coworking;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoworkingRowMapper {
    public Coworking rowMap(ResultSet resultSet) throws SQLException {

        Coworking coworking = Coworking.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .mainImage(resultSet.getString("mainimage"))
                .overview(resultSet.getString("overview"))
                .location(resultSet.getString("location"))
                .reviewsCount(resultSet.getLong("reviewscount"))
                .city(resultSet.getString("city"))
                .dayPrice(resultSet.getDouble("dayprice"))
                .weekPrice(resultSet.getDouble("weekprice"))
                .monthPrice(resultSet.getDouble("monthprice"))
                .rating(resultSet.getDouble("rating"))
                .openingHours(resultSet.getString("openinghours"))
                .containsDesk(resultSet.getBoolean("containsdesk"))
                .containsOffice(resultSet.getBoolean("containsoffice"))
                .containsMeetingRoom(resultSet.getBoolean("containsmeetingroom"))
                .hasSingleMonitors(resultSet.getBoolean("hassinglemonitors"))
                .hasDualMonitors(resultSet.getBoolean("hasdualmonitors"))
                .hasVideoRec(resultSet.getBoolean("hasvideorec"))
                .hasPrinter(resultSet.getBoolean("hasprinter"))
                .hasScanner(resultSet.getBoolean("hasscanner"))
                .hasProjector(resultSet.getBoolean("hasprojector"))
                .hasMicrophone(resultSet.getBoolean("hasmicrophone"))
                .build();


        return coworking;
    }
}

