package com.project.moagym.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.moagym.domain.enums.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue
    private Long itemId;

    @NotBlank(message = "공백을 사용할 수 없으며 반드시 해당 값을 입력하여야 합니다.")
    private String name;

    @NotBlank(message = "공백을 사용할 수 없으며 반드시 해당 값을 입력하여야 합니다.")
    private String brandName;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Double point;

    private Integer reviewNum;

    @NotNull(message = "해당 값을 입력하여야 합니다.")
    private Integer price;

    private Integer sortByNew;

    @JsonIgnore
    @OneToMany(mappedBy = "items")
    private List<Img> imgs = new ArrayList<>();

    //@JsonIgnore
    @OneToMany(mappedBy = "items")
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    //    @ManyToMany(mappedBy = "items")
//    private List<Tag> tags = new ArrayList<>();
//
//    @OneToMany(mappedBy = "items")
//    private List<Review> reviews = new ArrayList<>();
//
//
//    //조회 로직//
//    //아이템 리뷰 갯수 반환//
//    public int getReviewCnt(Item item){
//        List<Review> reviews = item.getReviews();
//        int cnt = reviews.size();
//        return cnt;
//    }

    //==연관관계 메서드 ==//
    public void setBrand(Brand brand){
        this.brand = brand;
    }

    //생성 로직//
    //Item 생성//
    public static Item createItem(CrawlingItemDto CIDto) {
        Item item = new Item();
        item.setName(CIDto.getName());
        item.setBrandName(CIDto.getBrandName());
        item.setCategory(CIDto.getCategory());
        item.setBrand(CIDto.getBrand());
        item.setPrice(CIDto.getPrice());
        item.setPoint(CIDto.getPoint());
        item.setSortByNew(CIDto.getSortByNew());
        item.setReviewNum(CIDto.getReviewCnt());
        return item;
    }

}