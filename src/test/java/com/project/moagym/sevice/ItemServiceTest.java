package com.project.moagym.sevice;

import com.project.moagym.api.ItemApiController;
import com.project.moagym.domain.Item;
import com.project.moagym.domain.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    void findAllWithBrandName() {

        List<Item> items = itemService.findAllByBrandName("멧블랙");

        for(Item i : items){
            System.out.println("상품 이름 : " + i.getName());
        }


    }

    @Test
    void findItemByItemIdWithReview() {

        Long itemId = Long.valueOf(162);
        Item item = itemService.findItemByItemIdWithReview(itemId);

        System.out.println("findItemByItemIdWithReview");

        for(Review i : item.getReviews()){
            System.out.println("상품 이름 : " + i.getReviewProductName());
        }

    }
}