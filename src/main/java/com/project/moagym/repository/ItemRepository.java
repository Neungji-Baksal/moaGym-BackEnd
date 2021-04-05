package com.project.moagym.repository;

import com.project.moagym.api.ItemApiController;
import com.project.moagym.domain.Brand;
import com.project.moagym.domain.Img;
import com.project.moagym.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if (item.getItemId() == null){
            em.persist(item);
        } else {
            em.merge(item); // update
        }
    }


    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public List<Item> findBrandByName(String name) {
        return em.createQuery("select i from Item i where i.brandName = :brandName", Item.class)
                .setParameter("brandName", name)
                .getResultList();
    }

    public Item findOneByItemName(String productName) {
        try {
            return em.createQuery("select i from Item i where i.name = :name", Item.class)
                    .setParameter("name", productName)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

    }

    public Item findItemByItemIdWithReview(Long itemId) {
        return em.createQuery(
                "select i from Item i where i.itemId = :itemId", Item.class)
                .setParameter("itemId", itemId)
                .getSingleResult();
    }
}
