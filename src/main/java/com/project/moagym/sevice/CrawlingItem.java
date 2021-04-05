package com.project.moagym.sevice;

import com.project.moagym.domain.*;
import com.project.moagym.domain.enums.Category;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
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
public class CrawlingItem {

    private final ImgService imgService;
    private final ItemService itemService;
    private final BrandService brandService;
    private final ReviewService reviewService;


    private static String mat_black =
            "https://smartstore.naver.com/matblack/category/9cb79659f04f46af989f76c9526c49f8?st=RECENT&free=false&dt=BIG_IMAGE&page=1&size=80";
    private static String on_n_fit =
            "https://smartstore.naver.com/onnfit/category/6a22f2f1fb4946f7a5fb2b6041749445?st=st=RECENT&free=false&dt=BIG_IMAGE&page=1&size=80";
    private static String quarter_fit =
            "https://smartstore.naver.com/quarter-fit/category/9617ce4fe1284bb89ac618207fb33ebe?st=st=RECENT&free=false&dt=BIG_IMAGE&page=1&size=80";
    private static String patrick =
            "https://smartstore.naver.com/patrick/category/93be8a6a6c864320bdcf86a1a7c2c1f9?st=st=RECENT&free=false&dt=BIG_IMAGE&page=1&size=80";
    private static String mavrk =
            "https://smartstore.naver.com/maverick19/category/4e8cdd1699764fe89a5e2c609003de07?st=st=RECENT&free=false&dt=BIG_IMAGE&page=1&size=80";
    private static String rsrs =
            "https://smartstore.naver.com/rsrs/category/b3717cedbc8e4cab811ec74c53969454?st=st=RECENT&free=false&dt=IMAGE&page=1&size=80";
    private static String liftizm =
            "https://smartstore.naver.com/liftizm/category/0fc2450e3f104bbba8a99db282e5c08d?st=st=RECENT&free=false&dt=BIG_IMAGE&page=1&size=80";


    @PostConstruct
    public void getItem() throws IOException {

        List<String> links = new ArrayList<>();
        links.add(mat_black);
        links.add(on_n_fit);
        links.add(liftizm);

        List<String> links2 = new ArrayList<>();
        links2.add(quarter_fit);
        links2.add(patrick);
        links2.add(mavrk);
        links2.add(rsrs);

        for(String link : links){
            getSmartStoreItemInfo(link);
        }

        for(String link : links2){
            getSmartStoreItemInfo2(link);
        }


    }


    @Transactional
    public void move(CrawlingItemDto CIDto){
        Item item = Item.createItem(CIDto);
        itemService.saveItem(item);

        for (String url : CIDto.getImg()) {
            Img img = Img.createItemImg(item, url);
            imgService.saveImg(img);
        }
    }

    public Category category(String name){

        String str = name;

        if( str.indexOf("양말") != -1 || str.indexOf("삭스") != -1 || str.indexOf("SOCKS") != -1 ||
                str.indexOf("socks") != -1) return Category.양말;
        else if ( str.indexOf("요가") != -1 || str.indexOf("브라") != -1 ) return Category.요가복;
        else if(str.indexOf("레깅스") != -1) return Category.레깅스;
        else if( str.indexOf("나시") != -1 || str.indexOf("짐웨어") != -1 || str.indexOf("헬스복") != -1 ||
                str.indexOf("머슬핏") != -1 || str.indexOf("웨이트") != -1) return Category.짐웨어;
        else if(str.indexOf("팬츠") != -1 || str.indexOf("하의") != -1 || str.indexOf("쇼츠") != -1) return Category.하의;
        else if( str.indexOf("티") != -1 || str.indexOf("필리퍼") != -1 || str.indexOf("셔츠") != -1 ||
                str.indexOf("바람막이") != -1 || str.indexOf("상의") != -1 || str.indexOf("크롭탑") != -1 ||
                str.indexOf("슬리브") != -1 || str.indexOf("후드") != -1 || str.indexOf("맨투맨") != -1) return Category.상의;
        else return Category.기타;
    }

    @Transactional
    public void getSmartStoreItemInfo(String parseLink) throws IOException {

        List<String> links = new ArrayList<>();

        Document doc = Jsoup.connect(parseLink).get();
        Elements contents = doc.select("._2GnLj_nx20 a");

        int cnt = 0;
        for (Element element : contents) {
            String name = element.absUrl("href");
            if((cnt % 3) == 0) links.add(name);
            cnt++;
        }

        int sbt = 1;
        for (String link : links) {
            getSmartStoreItemInfoData(link, sbt);
            sbt++;
        }
    }


    @Transactional
    public void getSmartStoreItemInfoData(String mat_black_1, int sbt) throws IOException {

        List<String> imgs = new ArrayList<>();

        System.out.println("mat_black_1 = " + mat_black_1);
        Document doc = Jsoup.connect(mat_black_1).get();
        Elements contents = doc.select("._1ziwSSdAv8 h3");
        String name = contents.text();
        System.out.println(name);

        Elements contents2 = doc.select("._1LY7DqCnwR");
        String temp = contents2.text();
        String aaa = "";
        String bbb = "";
        String strArr[] = null;
        strArr = temp.split("[,]");
        for (String st : strArr) {
            aaa += st;
        }
        strArr = aaa.split("[ ]");
        for (String st : strArr) {
            bbb = st;
        }

        Integer price = Integer.parseInt(bbb);
        System.out.println(price);

        Elements contents3 = doc.select("._25tOXGEYJa");
        Elements contents4 = contents3.select("img");
        for(Element img : contents4) {
            String img0 = "";
            String img1 = img.absUrl("src");
            strArr = img1.split("[?]");
            for(String st : strArr) {
                if(st == "type=f40") st.replace("type=f40", "type=m510");
                img0 += st;
            }
            imgs.add(img0);
            System.out.println(img0);
        }

        Elements contents5 = doc.select(".KasFrJs3SA");
        String brandNTemp = contents5.get(0).text();
        strArr = brandNTemp.split("[ ]");
        String brandN = "";
        for (String strTemp : strArr) {
            if(strTemp != " ") brandN += strTemp;
        }

//        String brandN ="";
//        for(int i = 0; i < contents5.size(); i++) {
//            brandN += contents5.get(i).text();
//        }
        System.out.println(brandN);

        Elements content6 = doc.select("._2pgHN-ntx6");
        String point1 = content6.get(1).text();
        String RC = content6.get(0).text();
        strArr = point1.split("[/]");
        point1 = strArr[0];
        strArr = RC.split("[,]");
        RC = "";
        for (String st : strArr) {
            if(st != ",") RC+=st;
        }
        Double tempPoint = Double.parseDouble(point1);
        Integer tempRC = Integer.parseInt(RC);
        System.out.println("point = " + content6.get(0).text());
        System.out.println("RC = " + content6.get(1).text());

        Category category = category(name);

        Brand brand = brandService.findBrand(brandN);

        CrawlingItemDto matItem = CrawlingItemDto.builder()
                .name(name)
                .price(price)
                .img(imgs)
                .brandName(brandN)
                .brand(brand)
                .category(category)
                .point(tempPoint)
                .reviewCnt(tempRC)
                .sortByNew(Integer.valueOf(sbt))
                .build();
        move(matItem);
    }

    @Transactional
    public void getSmartStoreItemInfo2(String parseLink) throws IOException {

        List<String> links = new ArrayList<>();

        Document doc = Jsoup.connect(parseLink).get();
        Elements contents = doc.select(".-qHwcFXhj0 a");

        for (Element element : contents) {
            String name = element.absUrl("href");
            links.add(name);
        }

        Integer sbt = 1;
        for (String link : links) {
            getSmartStoreItemInfoData2(link, sbt);
            sbt++;
        }
    }


    @Transactional
    public void getSmartStoreItemInfoData2(String mat_black_1, Integer sbt) throws IOException {

        List<String> imgs = new ArrayList<>();

        System.out.println("mat_black_1 = " + mat_black_1);
        Document doc = Jsoup.connect(mat_black_1).get();
        Elements contents = doc.select("._1ziwSSdAv8 h3");
        String name = contents.text();
        System.out.println(name);

        Elements contents2 = doc.select("._1LY7DqCnwR");
        String temp = contents2.text();
        String aaa = "";
        String bbb = "";
        String strArr[] = null;
        strArr = temp.split("[,]");
        for (String st : strArr) {
            aaa += st;
        }
        strArr = aaa.split("[ ]");
        for (String st : strArr) {
            bbb = st;
        }

        Integer price = Integer.parseInt(bbb);
        System.out.println(price);

        Elements contents3 = doc.select("._23RpOU6xpc");
        Elements contents4 = contents3.select("img");
        for(Element img : contents4) {
            String img0 = "";
            String img1 = img.absUrl("src");
            strArr = img1.split("[?]");
            for(String st : strArr) {
                if(st == "type=f40") st.replace("type=f40", "type=m510");
                img0 += st;
            }
            imgs.add(img0);
            System.out.println(img0);
        }

        Elements contents5 = doc.select("._1hBeKq0WZK");
        String brandNTemp = contents5.get(0).text();
        strArr = brandNTemp.split("[ ]");
        String brandN = "";
        for (String strTemp : strArr) {
            if(strTemp != " ") brandN += strTemp;
        }

//        String brandN ="";
//        for(int i = 0; i < contents5.size(); i++) {
//            brandN += contents5.get(i).text();
//        }
        System.out.println(brandN);

        Elements content6 = doc.select("._2pgHN-ntx6");
        String point1 = content6.get(1).text();
        String RC = content6.get(0).text();
        strArr = point1.split("[/]");
        point1 = strArr[0];
        strArr = RC.split("[,]");
        RC = "";
        for (String st : strArr) {
            if(st != ",") RC+=st;
        }
        Double tempPoint = Double.parseDouble(point1);
        Integer tempRC = Integer.parseInt(RC);
        System.out.println("ppppp = " + content6.get(1).text());

        Category category = category(name);

        Brand brand = brandService.findBrand(brandN);

        CrawlingItemDto matItem = CrawlingItemDto.builder()
                .name(name)
                .price(price)
                .img(imgs)
                .brandName(brandN)
                .brand(brand)
                .category(category)
                .point(tempPoint)
                .reviewCnt(tempRC)
                .sortByNew(sbt)
                .build();
        move(matItem);
    }

}
