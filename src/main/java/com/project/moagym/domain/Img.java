package com.project.moagym.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Img {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long ImgId;

    private String url;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item items;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review reviews;

    //==연관관계 메서드 ==//
    public void setItem(Item item){
        this.items = item;
        item.getImgs().add(this);
    }

    public void setReview(Review review){
        this.reviews = review;
        review.getReviewImageUrl().add(this);
    }

    //생성 메소드//
    public static Img createItemImg(Item item, String url) {
        Img img = new Img();
        img.setItem(item);
        img.setUrl(url);
        return img;
    }

    //생성 메소드//
    public static Img createReviewImg(Review review, String url) {
        Img img = new Img();
        img.setReview(review);
        img.setUrl(url);
        return img;
    }
}
