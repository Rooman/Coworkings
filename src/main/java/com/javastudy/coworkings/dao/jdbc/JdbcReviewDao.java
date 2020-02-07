package com.javastudy.coworkings.dao.jdbc;

import com.javastudy.coworkings.dao.ReviewDao;
import com.javastudy.coworkings.dao.jdbc.mapper.ReviewMapper;
import com.javastudy.coworkings.entity.Review;
import com.javastudy.coworkings.entity.ReviewStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcReviewDao implements ReviewDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String GET_ALL_REVIEWS_BY_COWORKING_ID = "SELECT id, description, status, coworkingid, userid " +
            "FROM Reviews WHERE coworkingid = ?;";

    private static final ReviewMapper REVIEW_MAPPER = new ReviewMapper();
    private DataSource dataSource;

    public JdbcReviewDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Review> getAllReviewsByCoworkingId(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_REVIEWS_BY_COWORKING_ID)) {

            List<Review> reviews = new ArrayList<>();
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Review review = REVIEW_MAPPER.mapRow(resultSet);
                    reviews.add(review);
                }
                if (reviews.size() == 0) {
                    logger.warn("No reviews are found by following id of coworking: {}", id);
                } else {
                    logger.info("{} reviews were found by following id of coworking: {}", reviews.size(), id);
                }
                return reviews;
            }
        }  catch (SQLException e) {
            logger.error("SQL Failed: {}", GET_ALL_REVIEWS_BY_COWORKING_ID);
            throw new RuntimeException("Connection to database is not available . It is not possible to search review by id: " + id, e);
        }


    }
}
