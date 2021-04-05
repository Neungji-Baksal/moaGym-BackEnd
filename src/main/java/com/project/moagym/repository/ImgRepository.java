package com.project.moagym.repository;

import com.project.moagym.domain.Img;
import com.project.moagym.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ImgRepository {

    private final EntityManager em;

    public void save(Img img){
        if (img.getImgId() == null){
            em.persist(img);
        } else {
            em.merge(img); // update
        }
    }
}
