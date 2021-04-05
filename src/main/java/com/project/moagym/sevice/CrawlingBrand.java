package com.project.moagym.sevice;

import com.project.moagym.domain.*;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CrawlingBrand {

    private final BrandService brandService;

    private static String mat_black =
            "https://smartstore.naver.com/matblack";
    private static String on_n_fit =
            "https://smartstore.naver.com/onnfit";
    private static String quarter_fit =
            "https://smartstore.naver.com/quarter-fit";
    private static String patrick =
            "https://smartstore.naver.com/patrick";
    private static String mavrk =
            "https://smartstore.naver.com/maverick19";
    private static String rsrs =
            "https://smartstore.naver.com/rsrs";
    private static String liftizm =
            "https://smartstore.naver.com/liftizm";

    @PostConstruct
    public void getBrand() throws IOException {

        List<String> links = new ArrayList<>();
        links.add(mat_black);
        links.add(on_n_fit);
        links.add(liftizm);

        List<String> links2 = new ArrayList<>();
        links2.add(quarter_fit);
        links2.add(patrick);
        links2.add(mavrk);
        links2.add(rsrs);

        for(String link : links) {
            getBrandInfo(link);
        }

        for(String link : links2) {
            getBrandInfo2(link);
            System.out.println(link);
        }
    }

    @Transactional
    public void move(CrawlingBrandDto CBDto){

        Brand brand = Brand.createBrand(CBDto);
        brandService.saveBrand(brand);

    }

    @Transactional
    public void getBrandInfo(String parseLink) throws IOException {

        Document doc = Jsoup.connect(parseLink).get();
        Elements contents = doc.select("._2plke59N8U");

        for(Element element : contents) {
            String img = element.absUrl("src");
            String name = element.attr("alt");

            CrawlingBrandDto CBDto = CrawlingBrandDto.builder()
                    .name(name)
                    .brandImg(img)
                    .build();

            move(CBDto);
        }
    }

    @Transactional
    public void getBrandInfo2(String parseLink) throws IOException {

        Document doc = Jsoup.connect(parseLink).get();

        Elements contents = doc.select("._1hBeKq0WZK");
        String nameT = contents.text();
        String strArr[] = null;
        strArr = nameT.split("[ ]");
        String name = "";
        for (String st : strArr) {
            if(st != " ")name += st;
        }

        System.out.println(contents.text());

        Elements contents2 = doc.select("._36jXoyswJd img");
        String img = "";
        for(Element ele : contents2) {
            img = ele.absUrl("src");
        }
        CrawlingBrandDto CBDto = CrawlingBrandDto.builder()
                .name(name)
                .brandImg(img)
                .build();

        move(CBDto);

    }


}
