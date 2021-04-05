package com.project.moagym.repository;

import com.project.moagym.domain.Brand;
import com.project.moagym.domain.Img;
import com.project.moagym.domain.Item;
import com.project.moagym.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BrandRepository {

    private final EntityManager em;

    public void save(Brand brand){
        if (brand.getBrandId() == null){
            em.persist(brand);
        } else {
            em.merge(brand); // update
        }
    }

    public Brand findBrandByName(String name) {
        return em.createQuery("select b from Brand b where b.name = :name", Brand.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Brand> findAll() {
        return em.createQuery("select b from Brand b", Brand.class)
                .getResultList();
    }
}
