package com.project.moagym.api;

import com.project.moagym.domain.Img;
import com.project.moagym.domain.Item;
import com.project.moagym.domain.Review;
import com.project.moagym.domain.enums.Category;
import com.project.moagym.sevice.ItemService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;

    @GetMapping("/api/v1/allItems")
    public List<ItemRequestDto> AllItems(){

        List<Item> items = itemService.findAll();

        List<ItemRequestDto> result = items.stream()
                .map(i -> new ItemRequestDto(i))
                .collect(Collectors.toList());

        return result;

    }

    @GetMapping("/api/v1/itemsByBrand/{brandName}")
    public List<ItemRequestDto> itemsByBrand(
            @PathVariable("brandName") String name
    ){

        List<Item> items = itemService.findAllByBrandName(name);

        List<ItemRequestDto> result = items.stream()
                .map(i -> new ItemRequestDto(i))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/api/v1/itemsByBrandWithReview/{brandName}")
    public List<ItemRequestDto2> itemsByBrandWithReview(
            @PathVariable("brandName") String name
    ){

        List<Item> items = itemService.findAllByBrandName(name);

        List<ItemRequestDto2> result = items.stream()
                .map(i -> new ItemRequestDto2(i))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/api/v1/itemsByItemIdWithReview/{itemId}")
    public ItemRequestDto2 findItemByItemIdWithReview(
            @PathVariable("itemId") Long itemId
    ){

        Item item = itemService.findItemByItemIdWithReview(itemId);

        ItemRequestDto2 result = new ItemRequestDto2(item);
        return result;
    }

    @Data
    public class ItemRequestDto{

        private Long productId;
        private String productName;
        private String brandName;
        private Category category;
        private Double productReviewPoint;
        private Integer productReviewCnt;
        private Integer productPrice;
        private Integer productSortByNew;
        private List<Img> productImageUrl = new ArrayList<>();
        //private List<Review> productReview = new ArrayList<>();

        public ItemRequestDto(Item item){
            productId = item.getItemId();
            productName = item.getName();
            brandName = item.getBrandName();
            category = item.getCategory();
            productReviewCnt = item.getReviewNum();
            productReviewPoint = item.getPoint();
            productPrice = item.getPrice();
            productSortByNew = item.getSortByNew();
            productImageUrl = item.getImgs();
            //productReview = item.getReviews();
        }

    }

    @Data
    public class ItemRequestDto2{

        private Long productId;
        private String productName;
        private String brandName;
        private Category category;
        private Double productReviewPoint;
        private Integer productReviewCnt;
        private Integer productPrice;
        private Integer productSortByNew;
        private List<Img> productImageUrl = new ArrayList<>();
        private List<Review> productReview = new ArrayList<>();

        public ItemRequestDto2(Item item){
            productId = item.getItemId();
            productName = item.getName();
            brandName = item.getBrandName();
            category = item.getCategory();
            productReviewCnt = item.getReviewNum();
            productReviewPoint = item.getPoint();
            productPrice = item.getPrice();
            productSortByNew = item.getSortByNew();
            productImageUrl = item.getImgs();
            productReview = item.getReviews();
        }

    }
}
