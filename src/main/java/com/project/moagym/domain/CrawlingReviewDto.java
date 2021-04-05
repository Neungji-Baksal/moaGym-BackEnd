package com.project.moagym.domain;

import com.project.moagym.domain.enums.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class CrawlingReviewDto {

    @NotBlank
    private String reviewProductName; // 아이템 이름

    private String reviewProductDesc; //리뷰 내용

    private String reviewProductOption; // 리뷰 옵션

    private String authorName; // 리뷰작성자

    private String createdAt; // 작성일

    private Double reviewPoint; // 리뷰점수

    @NotNull
    private Item item;

    private List<String> reviewImageUrl; //이미지
}
