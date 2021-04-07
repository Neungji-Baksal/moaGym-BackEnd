# moaGym-BackEnd란?
MoaGym 은 무신사 같은 국내 의류 쇼핑몰처럼 헬스, 요가, 필라테스 등의 의류 만을 모아서 보여주자는 의미로 시작한 프로젝트입니다. Jsoup을 이용해 네이버 웹스토어를 크롤링하여 DB에 저장 후 사용자들에게 노출 시켜줍니다.

see [postman link](https://documenter.postman.com/preview/12746129-ec58c104-16fe-4fe1-a107-4e5ce4783f53?environment=&versionTag=latest&apiName=CURRENT&version=latest&documentationLayout=classic-double-column&right-sidebar=303030&top-bar=FFFFFF&highlight=EF5B25)

----
## 목차
* [브랜드 정보 api](#브랜드-정보-api)
* [모든 브랜드 api](#모든-브랜드-api)
* [모든 브랜드 이름 api](#모든-브랜드-이름-api)
* [아이템 정보 api](#아이템-정보-api)
* [모든 아이템 api](#모든-아이템-api)
* [브랜드에 해당하는 아이템 api](#브랜드에-해당하는-아이템-api)
* [브랜드에 해당하는 아이템+리뷰 api](#브랜드에-해당하는-아이템+리뷰-api)
* [아이템 아이디에 해당하는 아이템+리뷰 api](#아이템-아이디에-해당하는-아이템+리뷰-api)
----
## 브랜드 정보 api

----
## 모든 브랜드 api
* method : GET
* 주소 : /api/v1/allBrands

*모든 브랜드의 정보를 반환하는 API

>response

    {
      "brandId": 1,
      "brandImg": "https://shop-phinf.pstatic.net/20180523_289/qjawlr_1527081345580pOtkf_JPEG/50388505201642497_850481204.jpg?type=m120",
      "name": "멧블랙"
    }
    
    
----
## 모든 브랜드 이름 api
* method : GET
* 주소 : /api/v1/allBrandsName

*모든 브랜드의 이름을 반환하는 API*

>reponse

     [
      "멧블랙",
      "온엔핏",
      "리프티즘",
      "쿼터핏",
      "페트릭",
      "주식회사매버릭",
      "랩스온랩스(REPSONREPS)"
    ]

----
## 아이템 정보 api

----
## 모든 아이템 api
* method : GET
* 주소 : /api/v1/allItems

*모든 아이템의 정보를 반환하는 API*

>response

    [
      {
        "productId": 8,
        "productName": "[멧블랙] 와이드쇼츠 (4color)",
        "brandName": "멧블랙",
        "category": "하의",
        "productReviewPoint": 0,
        "productReviewCnt": 0,
        "productPrice": 33000,
        "productSortByNew": 1,
        "productImageUrl": [
          {
            "url": "https://shop-phinf.pstatic.net/20210327_17/1616848843281Hgj0B_JPEG/17984623001109071_1829292753.jpgtype=m510"
          },
          {
            "url": "https://shop-phinf.pstatic.net/20210327_17/1616848843281Hgj0B_JPEG/17984623001109071_1829292753.jpgtype=f40"
          },
          {
            "url": "https://shop-phinf.pstatic.net/20210327_44/1616848872273MOvHR_JPEG/17984651995550282_72293015.jpgtype=f40"
          },
          {
            "url": "https://shop-phinf.pstatic.net/20210327_64/16168488724238jdX3_JPEG/17984652142159143_1287318079.jpgtype=f40"
          },
          {
            "url": "https://shop-phinf.pstatic.net/20210327_136/1616848872525HqyjO_JPEG/17984652247765187_3827045.jpgtype=f40"
          },
          {
            "url": "https://shop-phinf.pstatic.net/20210327_279/1616848872594ipRfv_JPEG/17984652317185658_1428267272.jpgtype=f40"
          }
        ]
      },
      {
        "productId": 888,
        "productName": "랩스온랩스 짐웨어 피마코튼 머슬핏 반팔티 (화이트)",
        "brandName": "랩스온랩스(REPSONREPS)",
        "category": "짐웨어",
        "productReviewPoint": 4.7,
        "productReviewCnt": 66,
        "productPrice": 32000,
        "productSortByNew": 79,
        "productImageUrl": [
          {
            "url": "https://shop-phinf.pstatic.net/20200808_169/1596885887923rbXce_JPEG/34249276443276684_212189825.jpgtype=m510"
          }
        ]
      },
    ]
    
----
## 브랜드에 해당하는 아이템 api
* method : GET
* 주소 : /api/v1/itemsByBrand/{brandName}
* param
    1. brandName : 멧블랙 

*해당 브랜드의 아이템 정보를 반환하는 API*

>response 

      {
        "productId": 8,
        "productName": "[멧블랙] 와이드쇼츠 (4color)",
        "brandName": "멧블랙",
        "category": "하의",
        "productReviewPoint": 0,
        "productReviewCnt": 0,
        "productPrice": 33000,
        "productSortByNew": 1,
        "productImageUrl": [
          {
            "url": "https://shop-phinf.pstatic.net/20210327_17/1616848843281Hgj0B_JPEG/17984623001109071_1829292753.jpgtype=m510"
          },
          {
            "url": "https://shop-phinf.pstatic.net/20210327_17/1616848843281Hgj0B_JPEG/17984623001109071_1829292753.jpgtype=f40"
          },
          {
            "url": "https://shop-phinf.pstatic.net/20210327_44/1616848872273MOvHR_JPEG/17984651995550282_72293015.jpgtype=f40"
          },
          {
            "url": "https://shop-phinf.pstatic.net/20210327_64/16168488724238jdX3_JPEG/17984652142159143_1287318079.jpgtype=f40"
          },
          {
            "url": "https://shop-phinf.pstatic.net/20210327_136/1616848872525HqyjO_JPEG/17984652247765187_3827045.jpgtype=f40"
          },
          {
            "url": "https://shop-phinf.pstatic.net/20210327_279/1616848872594ipRfv_JPEG/17984652317185658_1428267272.jpgtype=f40"
          }
        ]
      }


----
## 브랜드에 해당하는 아이템+리뷰 api
* method : GET
* 주소 : /api/v1/itemsByBrandWithReview/{brandName}
* param
    1. brandName : 멧블랙 

*해당 브랜드의 아이템 + 리뷰 정보를 반환하는 API*

>response

     {
        "productId": 15,
        "productName": "[멧블랙] 베이직 나시(로고) (3color)",
        "brandName": "멧블랙",
        "category": "짐웨어",
        "productReviewPoint": 4.8,
        "productReviewCnt": 10,
        "productPrice": 21800,
        "productSortByNew": 2,
        "productImageUrl": [
          {
            "url": "https://shop-phinf.pstatic.net/20210313_131/1615578883104bwxk9_JPEG/16714710930607072_1958940786.jpgtype=m510"
          },
          {
            "url": "https://shop-phinf.pstatic.net/20210313_131/1615578883104bwxk9_JPEG/16714710930607072_1958940786.jpgtype=f40"
          },
          {
            "url": "https://shop-phinf.pstatic.net/20210313_270/1615578894700p3XhT_JPEG/16714722521706888_1162792791.jpgtype=f40"
          },
          {
            "url": "https://shop-phinf.pstatic.net/20210313_118/1615578894834WpAcq_JPEG/16714722660067940_1079451047.jpgtype=f40"
          },
          {
            "url": "https://shop-phinf.pstatic.net/20210313_234/1615578895010zqj4s_JPEG/16714722832472553_482197386.jpgtype=f40"
          },
          {
            "url": "https://shop-phinf.pstatic.net/20210313_192/1615578895214Laqk8_JPEG/16714723043120985_1555571973.jpgtype=f40"
          }
        ],
        "productReview": [
          {
            "reviewId": 926,
            "reviewProductName": "[멧블랙] 베이직 나시(로고) (3color)",
            "reviewOption": "베이직나시(로고): 블랙(L)",
            "reviewDesc": "재질 두꺼워영 좋습니다",
            "authorName": "hyun****",
            "createdTime": "2021-03-26T06:08:26.623+0000",
            "reviewPoint": 5,
            "brandName": "멧블랙",
            "reviewImageUrl": []
          }
      }
      
----
## 아이템 아이디에 해당하는 아이템+리뷰 api
* method : GET
* 주소 : /api/v1/itemsByItemIdWithReview/{itemId}
* param
    1. itemId : 8 

*해당 아이템 아이디의 아이템 + 리뷰 정보를 반환하는 API*

>response

    {
      "productId": 8,
      "productName": "[멧블랙] 와이드쇼츠 (4color)",
      "brandName": "멧블랙",
      "category": "하의",
      "productReviewPoint": 0,
      "productReviewCnt": 0,
      "productPrice": 33000,
      "productSortByNew": 1,
      "productImageUrl": [
        {
          "url": "https://shop-phinf.pstatic.net/20210327_17/1616848843281Hgj0B_JPEG/17984623001109071_1829292753.jpgtype=m510"
        },
        {
          "url": "https://shop-phinf.pstatic.net/20210327_17/1616848843281Hgj0B_JPEG/17984623001109071_1829292753.jpgtype=f40"
        },
        {
          "url": "https://shop-phinf.pstatic.net/20210327_44/1616848872273MOvHR_JPEG/17984651995550282_72293015.jpgtype=f40"
        },
        {
          "url": "https://shop-phinf.pstatic.net/20210327_64/16168488724238jdX3_JPEG/17984652142159143_1287318079.jpgtype=f40"
        },
        {
          "url": "https://shop-phinf.pstatic.net/20210327_136/1616848872525HqyjO_JPEG/17984652247765187_3827045.jpgtype=f40"
        },
        {
          "url": "https://shop-phinf.pstatic.net/20210327_279/1616848872594ipRfv_JPEG/17984652317185658_1428267272.jpgtype=f40"
        }
      ],
      "productReview": []
    }
