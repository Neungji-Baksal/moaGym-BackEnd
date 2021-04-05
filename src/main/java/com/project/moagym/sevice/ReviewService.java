package com.project.moagym.sevice;

import com.project.moagym.domain.Review;
import com.project.moagym.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void saveReview(Review review){
        reviewRepository.save(review);
    }

    public List<Review> findByReviewId(Long reviewId) {
        return reviewRepository.findByReviewId(reviewId);
    }

}
