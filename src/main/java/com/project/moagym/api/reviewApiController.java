package com.project.moagym.api;

import com.project.moagym.domain.Img;
import com.project.moagym.domain.Item;
import com.project.moagym.domain.Review;
import com.project.moagym.domain.enums.Category;
import com.project.moagym.sevice.ReviewService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class reviewApiController {

    private final ReviewService reviewService;

    @GetMapping("/api/v1/reviewsById/{reviewId}")
    public List<ReviewRequestDto> reviewsByItem(
            @PathVariable("reviewId") Long reviewId
    ){

        List<Review> reviews = reviewService.findByReviewId(reviewId);

        List<ReviewRequestDto> result = reviews.stream()
                .map(i -> new reviewApiController.ReviewRequestDto(i))
                .collect(Collectors.toList());

        return result;

    }

    @GetMapping("/api/v1/reviewsByItemId/{itemId}")
    public List<ReviewRequestDto> reviewsByItemId(
            @PathVariable("itemId") Long itemId
    ){

        List<Review> reviews = reviewService.findByReviewId(itemId);

        List<ReviewRequestDto> result = reviews.stream()
                .map(i -> new reviewApiController.ReviewRequestDto(i))
                .collect(Collectors.toList());

        return result;

    }

    @Data
    public class ReviewRequestDto{

        private Long reviewId;
        private String reviewProductName;
        private String reviewProductDesc;
        private String reviewProductOption;
        private String CreatedAt;
        private String authorName;
        private Double reviewPoint;
        private List<Img> reviewImageUrl = new ArrayList<>();

        public ReviewRequestDto(Review review){
            reviewId = review.getReviewId();
            reviewProductName = review.getReviewProductName();
            reviewProductDesc = review.getReviewDesc();
            reviewProductOption = review.getReviewOption();
            CreatedAt = review.getCreatedTime();
            authorName = review.getAuthorName();
            reviewPoint = review.getReviewPoint();
            reviewImageUrl = review.getReviewImageUrl();
        }

    }
}