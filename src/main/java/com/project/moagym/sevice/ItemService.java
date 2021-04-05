package com.project.moagym.sevice;


import com.project.moagym.api.ItemApiController;
import com.project.moagym.domain.Brand;
import com.project.moagym.domain.Img;
import com.project.moagym.domain.Item;
import com.project.moagym.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final EntityManager em;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<Item> findAllByBrandName(String name) {
        return itemRepository.findBrandByName(name);
    }

    public Item findByItemName(String productName) {
        return itemRepository.findOneByItemName(productName);
    }

    public Item findItemByItemIdWithReview(Long itemId) {
        return itemRepository.findItemByItemIdWithReview(itemId);
    }
}

//        return em.createQuery(
//                "select i from Item i where i.name =:name" +
//                " join fetch i.imgs is", Item.class
//        ).setParameter("name",name).getResultList();
