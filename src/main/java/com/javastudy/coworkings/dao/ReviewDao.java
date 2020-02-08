package com.javastudy.coworkings.dao;

import com.javastudy.coworkings.entity.Review;

import java.util.List;

public interface ReviewDao {
    List<Review> getAllReviewsByCoworkingId (long id);
}
