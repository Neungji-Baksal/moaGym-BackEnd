package com.project.moagym.domain;

import com.project.moagym.domain.enums.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class CrawlingItemDto {

    @NotBlank
    private String name;

    @NotBlank
    private String brandName;

    @NotNull
    private Category category;

    private Double point;

    private Integer reviewCnt;

    @NotNull
    private Integer price;

    @NotNull
    private Brand brand;

    private Integer sortByNew;

    private List<String> img;

}