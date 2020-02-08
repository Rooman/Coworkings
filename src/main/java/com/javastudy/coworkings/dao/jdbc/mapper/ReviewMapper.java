package com.javastudy.coworkings.dao.jdbc.mapper;

import com.javastudy.coworkings.entity.Review;
import com.javastudy.coworkings.entity.ReviewStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper {
    public Review mapRow (ResultSet resultSet){
        try {
            Long id = resultSet.getLong("id");
            String description = resultSet.getString("description");
            ReviewStatus reviewStatus = ReviewStatus.getByName(resultSet.getString("status"));
            Long coworkingId = resultSet.getLong("coworkingId");
            Long userId = resultSet.getLong("userId");

                Review review = Review.builder()
                        .id(id)
                        .description(description)
                        .reviewStatus(reviewStatus)
                        .coworkingId(coworkingId)
                        .userId(userId)
                        .build();

                return review;

        } catch (SQLException e) {
            throw new RuntimeException("Error happened while getting Review info from ResultSet", e);
        }
    }
}
