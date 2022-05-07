# baekdoong-banjeom
백엔드 데브코스 1차 프로젝트2 - (개인) Rest API 제작

중화요리 전문점 백둥반점 고객 사이트 & 관리 페이지

## 개발 환경
- JDK 11.0.12
- Node JS 16.13.1
- React 18.1.0

## 데이터베이스 스키마
- 메뉴 테이블

![image](https://user-images.githubusercontent.com/62789083/167266224-017ab3ae-a7e9-4a99-bf31-9c8f04021bc5.png)
- 주문 테이블

![image](https://user-images.githubusercontent.com/62789083/167266241-cf77033c-95e9-4ccd-b17b-ed4e90871b1f.png)
- 주문 상품 테이블

![image](https://user-images.githubusercontent.com/62789083/167266249-fb0d7f9b-14c3-45ff-b8d9-13c5e329891a.png)

## Rest API
- 메뉴 전체 가져오기

![image](https://user-images.githubusercontent.com/62789083/167266305-3f5b5c87-1601-4647-bbaa-bbdf2c069702.png)
- 메뉴 하나 가져오기

![image](https://user-images.githubusercontent.com/62789083/167266337-c04c4392-803e-4e7a-bf95-c66819a3c539.png)
- 메뉴 이름으로 검색하기

![image](https://user-images.githubusercontent.com/62789083/167266359-5228032b-f938-422d-a5ef-f6b3c115057c.png)
- 주문하기
   - 요청 json
 
![image](https://user-images.githubusercontent.com/62789083/167266520-7795145e-6f7b-47c2-b456-acd94d4da320.png)
   - 응답 json
 
 ![image](https://user-images.githubusercontent.com/62789083/167266533-24edeeeb-2571-45ea-85df-5394e29a69d5.png)

- 카테고리 가져오기

![image](https://user-images.githubusercontent.com/62789083/167266768-455e77e3-4268-41f2-94a7-f3096227d8d5.png)

## 클론코딩 대비 수정 & 추가 구현 사항
![image](https://user-images.githubusercontent.com/62789083/167266652-02d655da-55f2-41e8-8d31-89e1d15e482f.png)
- 도메인 중국집으로 변경, 카테고리별로 메뉴 정렬, 메뉴 검색 기능 추가, 주문 상품 삭제 버튼 추가

![image](https://user-images.githubusercontent.com/62789083/167266702-768ce876-d17b-4f65-921e-6d1aa023f828.png)
![image](https://user-images.githubusercontent.com/62789083/167266709-728d8b12-b5d0-4094-b1e5-370304ffc396.png)
![image](https://user-images.githubusercontent.com/62789083/167266718-dd5ddb52-78e8-485d-9f0c-189b7d280318.png)
![image](https://user-images.githubusercontent.com/62789083/167266728-a34b9627-d3f6-4db3-8bf1-9ee49c12d84f.png)
- 주문 관리 페이지 추가
