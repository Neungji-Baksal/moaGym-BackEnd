package com.project.moagym.sevice;

import com.project.moagym.domain.Brand;
import com.project.moagym.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    @Transactional
    public void saveBrand(Brand brand) {
        brandRepository.save(brand);
    }

    @Transactional
    public Brand findBrand(String name){
        return brandRepository.findBrandByName(name);
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
