package com.project.moagym.domain;

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
public class CrawlingBrandDto {

    @NotBlank
    private String name;

    @NotBlank
    private String brandImg;


}
