package com.project.moagym.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Brand {

    @Id
    @GeneratedValue
    private Long brandId;

    @NotBlank(message = "공백을 사용할 수 없으며 반드시 해당 값을 입력하여야 합니다.")
    private String name;

    @NotBlank(message = "공백을 사용할 수 없으며 반드시 해당 값을 입력하여야 합니다.")
    private String brandImg;

    @JsonIgnore
    @OneToMany(mappedBy = "brand")
    private List<Item> items = new ArrayList<>();


    //생성 로직//
    //Brand 생성//
    public static Brand createBrand(CrawlingBrandDto CBDto) {
        Brand brand = new Brand();
        brand.setName(CBDto.getName());
        brand.setBrandImg(CBDto.getBrandImg());
        return brand;
    }
}
