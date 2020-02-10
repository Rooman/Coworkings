package com.javastudy.coworkings.service;

import com.javastudy.coworkings.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviewsByCoworkingID(long id);
}
