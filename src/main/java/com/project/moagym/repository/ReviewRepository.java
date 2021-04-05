package com.project.moagym.repository;

import com.project.moagym.domain.Img;
import com.project.moagym.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private final EntityManager em;

    public void save(Review review){
        if (review.getReviewId() == null){
            em.persist(review);
        } else {
            em.merge(review); // update
        }
    }

    public List<Review> findByReviewId(Long reviewId) {
        return em.createQuery("select r from Review r where r.reviewId = :reviewId",
                Review.class)
                .setParameter("reviewId", reviewId)
                .getResultList();
    }


}
