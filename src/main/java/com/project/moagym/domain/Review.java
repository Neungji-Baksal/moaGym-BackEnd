package com.project.moagym.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue
    private Long reviewId;

    private String reviewProductName;

    private String reviewOption;

    @Column(columnDefinition = "TEXT")
    private String reviewDesc;

    private String authorName;

    private String createdTime;

    private Double reviewPoint;

    private String brandName;

    @OneToMany(mappedBy = "reviews")
    private List<Img> reviewImageUrl = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item items;

    //==연관관계 메서드 ==//
    public void setItem(Item item){
        this.items = item;
    }

    //생성 메소드//
    public static Review createReview(CrawlingReviewDto CRDto) {
        Review review = new Review();
        review.setReviewProductName(CRDto.getReviewProductName());
        review.setReviewDesc(CRDto.getReviewProductDesc());
        review.setReviewOption(CRDto.getReviewProductOption());
        review.setCreatedTime(CRDto.getCreatedAt());
        review.setAuthorName(CRDto.getAuthorName());
        review.setReviewPoint(CRDto.getReviewPoint());
        review.setItem(CRDto.getItem());
        if(CRDto.getItem() != null) review.setBrandName(CRDto.getItem().getBrandName());
        return review;
    }

}
