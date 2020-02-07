package com.javastudy.coworkings.service.impl;

import com.javastudy.coworkings.dao.ReviewDao;
import com.javastudy.coworkings.entity.Review;
import com.javastudy.coworkings.service.ReviewService;

import java.util.List;

public class DefaultReviewService implements ReviewService {
    private ReviewDao reviewDao;

    public DefaultReviewService(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public List<Review> getAllReviewsByCoworkingID(long id) {
        return reviewDao.getAllReviewsByCoworkingId(id);
    }
}
