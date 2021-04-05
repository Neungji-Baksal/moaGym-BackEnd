package com.project.moagym.sevice;

import com.project.moagym.domain.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CrawlingReview {

    private final ImgService imgService;
    private final ItemService itemService;
    private final ReviewService reviewService;

    private static String mat_black =
            "https://smartstore.naver.com/i/v1/reviews/attaches/ids-count?merchantNo=510172967";
    private static String on_n_fit =
            "https://smartstore.naver.com/i/v1/reviews/attaches/ids-count?merchantNo=510232956";
    private static String quarter_fit =
            "https://smartstore.naver.com/i/v1/reviews/attaches/ids-count?merchantNo=510579262";
    private static String patrick =
            "https://smartstore.naver.com/i/v1/reviews/attaches/ids-count?merchantNo=510639449";
    private static String mavrk =
            "https://smartstore.naver.com/i/v1/reviews/attaches/ids-count?merchantNo=510345558";
    private static String rsrs =
            "https://smartstore.naver.com/i/v1/reviews/attaches/ids-count?merchantNo=510013302";
    private static String lifizm =
            "https://smartstore.naver.com/i/v1/reviews/attaches/ids-count?merchantNo=510598230";


    @PostConstruct
    public void getReview() throws IOException{

        List<String> links = new ArrayList<>();
        links.add(mat_black);
        links.add(on_n_fit);
        links.add(quarter_fit);
        links.add(patrick);
        links.add(mavrk);
        links.add(rsrs);
        links.add(lifizm);

        for (String link : links) {
            getSmartStoreReview(link);
        }
    }

    @Transactional
    public void move(CrawlingReviewDto reviewDto, List<String> attachUrl){
        Review review = Review.createReview(reviewDto);
        System.out.println("review.getReviewProduct() = " + review.getReviewProductName());
        reviewService.saveReview(review);

        for (String url2 : attachUrl) {
            Img img = Img.createReviewImg(review, url2);
            imgService.saveImg(img);
        }
    }

    @Transactional
    public void getSmartStoreReview(String parseLink) throws IOException {

        String link = parseLink;

        List<String> reviews = new ArrayList<>();

        String doc = Jsoup.connect(link)
                .ignoreContentType(true)
                .data("merchantNo", "510172967")
                .execute().body();

        if (doc.startsWith("[")) {
            System.out.println("무야호 0");
            doc = doc.substring(1, doc.length() - 1);
        }
        if (doc.startsWith("{")) {
            System.out.println("무야호 1");
            JSONObject jsonObject = new JSONObject(doc);
            JSONArray ids = (JSONArray) jsonObject.get("ids");
            for (int i = 0; i < ids.length(); i++) {
                String id = (String) ids.get(i);
                reviews.add(id);
            }
        }

        String tempLink =
                "https://smartstore.naver.com/i/v1/reviews/details?reviewIds[]=";

        if (reviews.size() != 0) {
            for (String url : reviews) {
                String reviewLink = tempLink + url;

                String reviewStr = Jsoup.connect(reviewLink)
                        .ignoreContentType(true)
                        .data("reviewIds[]", url)
                        .execute().body();

                if (reviewStr.startsWith("[")) reviewStr = reviewStr.substring(1, reviewStr.length() - 1);
                JSONObject reviewObject = new JSONObject(reviewStr);

                System.out.println("무야호 3");
                String productName = (String) reviewObject.get("productName");
                String reviewContent = (String) reviewObject.get("reviewContent");
                String productOptionContent = null;
                if (reviewObject.has("productOptionContent")) {
                    productOptionContent = (String) reviewObject.get("productOptionContent");
                }
                String createDate = (String) reviewObject.get("createDate");
                String writerMemberId = (String) reviewObject.get("writerMemberId");
                Double reviewScore = Double.valueOf((Integer) reviewObject.get("reviewScore"));

                List<String> attachUrl = new ArrayList<>();
                JSONArray imgs = (JSONArray) reviewObject.get("reviewAttaches");
                for (int i = 0; i < imgs.length(); i++) {
                    JSONObject imgsObject = (JSONObject) imgs.get(i);
                    String img = (String) imgsObject.get("attachUrl");
                    attachUrl.add(img);
                    System.out.println("attachUrls = " + img);
                }

                System.out.println("무야호 4");

                Item item = itemService.findByItemName(productName);

                CrawlingReviewDto reviewTemp = CrawlingReviewDto.builder()
                        .reviewProductName(productName)
                        .reviewProductDesc(reviewContent)
                        .reviewProductOption(productOptionContent)
                        .createdAt(createDate)
                        .authorName(writerMemberId)
                        .reviewPoint(reviewScore)
                        .item(item)
                        .build();

                move(reviewTemp, attachUrl);
            }

        }
    }
}




//https://smartstore.naver.com/i/v1/reviews/attaches/ids-count?originProductNo=4834805950
//https://smartstore.naver.com/i/v1/reviews/attaches/ids-count?merchantNo=510172967
//https://smartstore.naver.com/i/v1/reviews/details?reviewIds[]=3137060685

//    Document ss = Jsoup.connect("https://smartstore.naver.com/matblack/products/5341959205#REVIEW")
//            .ignoreContentType(true)
//            .get();
//
//        System.out.println("doc = " + ss.html());
//
//                Elements ele = ss.select(".z7cS6-TO7X");
//                //ele = ele.select("#REVIEW");
//                System.out.println("doc = " + ele.html());