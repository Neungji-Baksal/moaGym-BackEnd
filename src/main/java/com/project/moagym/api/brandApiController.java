package com.project.moagym.api;

import com.project.moagym.domain.Brand;
import com.project.moagym.sevice.BrandService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class brandApiController {

    private final BrandService brandService;

    @GetMapping("/api/v1/allBrands")
    public List<BrandRequestDto> Allbrands(){
        List<Brand> brands = brandService.findAll();

        List<BrandRequestDto> result = brands.stream()
                .map(i -> new brandApiController.BrandRequestDto(i))
                .collect(Collectors.toList());
        return result;
    }

    @GetMapping("/api/v1/allBrandsName")
    public List<String> AllbrandsName(){
        List<Brand> brands = brandService.findAll();
        List<String> names = new ArrayList<>();
        for (Brand b : brands) {
            names.add(b.getName());
        }
        return names;
    }

    @Data
    public class BrandRequestDto{

        private Long brandId;
        private String brandImg;
        private String name;

        public BrandRequestDto(Brand brand){
            brandId = brand.getBrandId();
            brandImg = brand.getBrandImg();
            name = brand.getName();
        }
    }
}



