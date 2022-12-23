# ⛺ **MTZ(마운틴즈)**
### 🎉 마운틴즈 소개 | About Us
![image](https://user-images.githubusercontent.com/97796338/209322455-f4ea6eb7-b11b-4707-8ef7-a34218413c93.png)

등산에 관심이 생긴, 취미가 등산인, 산을 정복하고 싶은 여러분 주목!<br/>
남녀노소 누구나 '등산왕'이 될 수 있는 등산 필수앱 "MTZ"를 소개합니다!

![title](https://user-images.githubusercontent.com/102575747/207062833-e189d6b6-cb27-4498-8579-639ad6a7651c.png)

### :evergreen_tree: 웹사이트 | Website [MTZ 바로가기](https://www.mountainz.net/)

<br/>

<details open>
 <summary><span style="font-size:15px">Link List</span>
 </summary>
 <div markdown="1">

#### 🌎 프로젝트 문서 | [Project Docs](https://docs.google.com/spreadsheets/d/1HtE2SUzSS5GC-LgEqoSsjKZNVe2-Jc1JQHZ8GDhCGHs/edit#gid=0)

#### 🌎 Notion Link | [Notion Link](https://www.notion.so/MTZ-251008683c60489c909a51ee189279e4)

#### 🌎 PostMan API Link | [PostMan API로 Click!](https://documenter.getpostman.com/view/23686396/2s8YzUvg4o)

#### 🌎 Swagger API Link | [Swagger API로 Click!](https://www.dongjae-bk.shop/swagger-ui.html)

#### 🔑 FE Github URL | [FE GITHUB로 Click!](https://github.com/M0untainZ/MTZ-Front.git)

#### 🔒 BE Github URL | [BE GITHUB로 Click!](https://github.com/M0untainZ/MTZ-BE.git)

</div>
</details>

</br>

# 🔖**목차**</br>

### [1. 아키텍쳐](#아키텍쳐)

### [2. 페이지별 기능](#페이지별-기능)

### [3. 기술 스택](#기술-스택)

### [4. 프로젝트 기술 정보](#프로젝트-기술-정보)

### [5. ERD](#ERD)

### [6. 라이브러리](#라이브러리)

### [7. 트러블슈팅](#트러블슈팅)

### [8. MTZ 대장님들](#MTZ-대장님들)

</br>

# 🛠 아키텍쳐 | Architecture

![최종 아키텍처 사진](https://user-images.githubusercontent.com/97796338/207010293-a85d9bb2-d131-4a43-96fd-6d4ed0ab6817.png)

</br>

# 📖 페이지별 기능 | Feature per Page

| PAGE             | FUNCTION                                                             |
| ---------------- | -------------------------------------------------------------------- |
| 📑 MAIN          | 메인화면, 위치정보 기반 날씨, 추천 등산 코스, 랭킹, 인증 사진 리스트 |
| 📑 DETAIL 01     | 산 리스트, 산 리스트 필터, 무한 스크롤                               |
| 📑 DETAIL 02     | 각 산에 대한 정보, 카카오맵 기반 산의 위치, 사진 인증하기            |
| 📑 CERTIFICATION | 인증 사진 리스트, 인증사진 지역 필터 , 무한 스크롤                   |
| 📑 MYPAGE        | 프로필 수정, 뱃지 리스트                                             |
| 📑 LOGIN         | 로그인, 회원가입, 소셜(카카오) 로그인                                |

</br></br>

# 💻 기술 스택 | Tech Stack

- JWT를 이용한 로그인 기능
- OAuth2 소셜 로그인 카카오톡 지원
- QueryDSL를 이용한 다양한 경우의 검색 요청 일괄 처리
- Redis를 이용한 자주 조회하는 데이터 캐싱 처리
- Slack Webhook, Logback를 이용한 배포된 서버의 에러 로그를 쉽게 확인 가능
- Route53, ELB를 활용한 HTTPS 처리
- Githib Action, S3, CodeDeploy를 이용한 CI/CD 자동 배포
- Nginx를 이용한 무중단 배포
- S3, CloudFront 캐싱 정책
- react-query 캐싱

</br></br>

# ✨ 프로젝트 기술 정보 | Project Tech Information

## 🔑 **FRONTEND**

<img src="https://img.shields.io/badge/React-61DAFB?style=flat-square&logo=React&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=white"/> &nbsp;
<img src="https://img.shields.io/badge/Yarn-2C8EBB?style=flat-square&logo=Yarn&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Axios-5A29E4?style=flat-square&logo=Axios&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Redux-764ABC?style=flat-square&logo=Redux&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/React Query-FF4154?style=flat-square&logo=React Query&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/styled--components-DB7093?style=flat-square&logo=styled-components&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat-square&logo=Amazon S3&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/CloudFront-DA1F26?style=flat-square&logo=CloudFront&logoColor=white"/>

<br/>

## 🔒**BACKEND**

#### 백엔드 개발환경 <br/>

<img src="https://img.shields.io/badge/JAVA-006272?style=flat-square&logo=JAVA&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Spring-13C100?style=flat-square&logo=Spring&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/SPRING DATA JPA-569A31?style=flat-square&logo=JSP&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/QueryDSL-527FFF?style=flat-square&logo=QueryDSL&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=Redis&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>&nbsp;

#### 보안 <br>

<img src="https://img.shields.io/badge/JSON Web Tokens-A100FF?style=flat-square&logo=JSON Web Tokens&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Spring Security-6DB33F?style=flat-square&logo=Spring Security&logoColor=white"/>&nbsp;

#### AWS <br>

<img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat-square&logo=Amazon EC2&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=flat-square&logo=Amazon RDS&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat-square&logo=Amazon S3&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Route53-FCC624?style=flat-square&logo=Route53&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/CodeDeploy-071D49?style=flat-square&logo=CodeDeploy&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/ELB-FF9900?style=flat-square&logo=ELB&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Certificate Manager-C71D23?style=flat-square&logo=Certificate Manager&logoColor=white"/>&nbsp;

#### CI/CD <br>

<img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=flat-square&logo=GitHub Actions&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/NGINX-009639?style=flat-square&logo=NGINX&logoColor=white"/>&nbsp;

<br/><br/>

# ✨ ERD

![ERD](https://user-images.githubusercontent.com/102575747/207043103-844aa2c9-8293-40d6-8f99-38fb49841543.png)

<br/><br/>

# ✨ 라이브러리 | Library

- @reduxjs/toolkit
- axios
- browser-image-compression
- react-icons
- react-images-uploading
- react-intersection-observer
- react-query
- react-redux
- react-router-dom
- react-scripts
- react-toastify
- redux
- redux-thunk
- styled-components
- sweetalert2
- sweetalert2-react-content
- swiper

<br/>

# ✨트러블슈팅 | Trouble Shooting

## 🔑 **FE**

 <details>
 <summary><span style="font-size:16px">웹 성능 개선 및 캐싱</span>
 </summary>
 <div markdown="1">

 <br/>

### 문제상황 <br>

![mater01](https://user-images.githubusercontent.com/102575747/207054952-d5ce91f6-ce16-49b2-ae93-8a504f6ecfd3.png)

![mater02](https://user-images.githubusercontent.com/102575747/207055031-22984a9c-888b-4434-87e6-6fbd63b7edbe.png)

- 이미지가 많이 사용되는 프로젝트 특성상 PNG 나 JPG 같은 고화질 이미지를 많이 사용함
- 이러한 이미지들은 웹 성능을 저하시킴<br/><br/>

### 의견조율 <br>

- 화질은 최대한 유지하면서 압축률은 높일 수 있는 webp 형식의 이미지 파일로 교체
- 서버에 있는 이미지를 불러올 땐 react-query의 캐싱기능을 활용해 이미지 랜더링 속도를 향상
- 프로젝트 내부에서 사용되는 이미지들은 S3 와 CloudFront 의 캐싱 정책을 변경해 처리<br/><br/>

### 결과 <br>

**성능테스트 결과**

![solution01](https://user-images.githubusercontent.com/102575747/207055071-9d3717f0-3179-4b80-8591-55923de26ec6.png)

  </br>

![solution02](https://user-images.githubusercontent.com/102575747/207055129-9bf1ef34-c4a6-473d-b87c-6157650986e4.png)

 </div>
 </details>

 <details>
 <summary><span style="font-size:16px">UX측면에서 GeoLocation API 사용하기</span>
 </summary>
 <div markdown="1">

 <br/>

### 문제상황 <br>

- 프로젝트에서 사용자의 현재 위치 날씨를 보여주기 위해 사용자 위치정보를 묻는 geolocation API 를 사용
- geolocation API 는 별다른 처리를 하지 않으면 페이지 로드시 곧바로 사용자에게 위치 정보 권한 요청을 보냄

![mater03](https://user-images.githubusercontent.com/102575747/207055608-b6dadfbe-908e-4960-92c7-8ec1ec9110ec.png)

하지만 이런 방식은 사용자에게 신뢰감을 줄 수 없다고 판단하였습니다. <br>
(사용자 입장에서 어떤 목적을 가진 사이트인지 판단하기 어려운데 권한 요청을 보내면 거부감이 들기 때문)<br/><br/>

### 의견조율 <br>

![solution06](https://user-images.githubusercontent.com/102575747/207065960-b81e6bd8-59e5-4a58-948b-5d8e771f8683.png)

![solution07](https://user-images.githubusercontent.com/102575747/207066329-9c2a1a76-086a-4dfe-a50a-abf5c07b75ab.png)

- 크롬 문서에서도 사용자에게 위치 정보를 책임감 있게 요청하라는 내용이 명시되어 있음
- 페이지 로드시 곧바로 권한 요청을 묻는게 아닌 사용자의 행동에 따라 수동적으로 권한요청을 할 수 있도록 ui 를 변경

![soluton03](https://user-images.githubusercontent.com/102575747/207055661-cf3e8d41-cd5f-49d6-9600-65fdef76d58e.png)

<br/>

### 결과 <br>

- 날씨보기 버튼을 클릭하면 다음과 같이 요청을 보내고

![solution04](https://user-images.githubusercontent.com/102575747/207055774-3f6df737-b053-424b-b389-6f3ad208b9c5.png)

- 허용했을 경우에 api를 호출하는 방식으로 개선

![solution05](https://user-images.githubusercontent.com/102575747/207055827-5396abf8-05c4-4eba-8dfd-747ba6b3b1f6.png)

- 개선 후 성능 테스트 결과 Best Practices 부분도 상당히 개선된 것을 확인할 수 있음

![solution08](https://user-images.githubusercontent.com/102575747/207066391-20b979f9-ec00-4480-9ef7-e38bac21a134.png)

 </div>
 </details>

<br/>

## 🔒 BE

 <details>
<summary><span style="font-size:16px">필터 검색시 데이터 동적처리 하기</span>
</summary>
<div markdown="1">

### 문제상황 <br>

- Spring Data JPA만을 가지고 프로젝트를 하던중 필터검색을 구현해야하는 상황을 맞이함
- 동적 처리를 위해 JPQL을 사용했으나 문자열을 통해서 쿼리를 만들게 되면 작성한 문자열 쿼리 중 띄어쓰기 혹은 알파벳의 오류가 있을 경우 이를 컴파일 타임에서 잡아주지 못한다는 단점이 존재했음<br/><br/>

### 의견조율 <br>

**QueryDsl을 사용하여 동적으로 데이터를 처리해보기**

- 문자열이 아닌 자바 코드로 쿼리를 생성하여 더 쉽고 간결하며<br>
  형태도 SQL과 비슷하게 개발할 수 있어 가독성이 좋은 QueryDsl을 사용하기로 함<br/><br/>

### 결과 <br>

![필터검색 사진](https://user-images.githubusercontent.com/97796338/206993318-228cf809-b333-48ef-9c13-d04065574542.png)

</div>
</details>

<details>
<summary><span style="font-size:16px">태그 조회시 데이터 처리 성능 개선</span>
</summary>
<div markdown="1">

### 문제상황 <br>

사용자들에게 공통적이고 반복적으로 보여지게될 정보에 대해서 처리 속도를 높여주고 싶은 생각이 듬<br/><br/>

### 의견조율 <br>

**Redis를 사용하여 캐싱처리 해보기**

- 같은 내용을 보여주는 데이터라면 캐싱처리를 해서 매번 DB에 접근하는것을 Redis를 통해 성능을 개선해보기로 함<br/><br/>

### 결과 <br>

**캐싱 처리 전**
![image](https://user-images.githubusercontent.com/97796338/207011178-551d4885-7b23-4103-95b7-7da8edb6df2d.png)

<br>

**캐싱 처리 후**
![image](https://user-images.githubusercontent.com/97796338/207011365-06735785-6791-4872-a861-1c4c538f7e21.png)

</div>
</details>

<details>
<summary><span style="font-size:16px">CI/CD 설정 오류 해결</span>
</summary>
<div markdown="1">

### 문제상황 <br>

1. EC2(Ubuntu)에서 default 메모리 설정으로 무중단 배포를 적용하니 신버전 배포 시 서버가 다운
2. EC2(Ubuntu)에 Nginx 설치에 필요한 Ruby 2.5.1 버전이 다운로드 되지 않음
3. 신버전을 배포할 때 codedepoly에서 실패가 뜨고 신버전이 배포가 되지 않음<br/><br/>

### 의견조율 <br>

1. **메모리를 늘리는 방법을 찾아보자고 함**

   - default 메모리가 1GB만 설정되어 있고 swap 설정이 안되어 있었고
     1GB가 넘는 boot 파일 두개를 배포하려고 하니 default 메모리 설정이 감당을 못하고
     서버가 다운되는 것같아 swap 메모리 설정을 하기로 함

2. **버전 문제인것 같다고 판단해서 버전을 바꿔보자고 함**

   - Ubuntu 22.04는 OpenSSL 3.0과 함께 제공되기 때문에 Ruby 3.0 이하에서는
     이를 지원하지 않는걸 확인하고 바꾸기로 함

3. **aws codedepoly error log와 EC2(Ubuntu) deploy error log를 확인해보기로 함**

   - 신버전 depoly 시에 EC2(Ubuntu)에서 신버전 boot 파일을 열지 못하는 것을 확인했다
   - shell script를 bash용으로 작성했는데 Ubuntu의 기본 쉘은 dash여서 호환성 문제로 script를 읽지 못함<br/><br/>

### 결과 <br>

1. EC2(Ubuntu) default 메모리가 용량 초과 시 swap 메모리로 넘어갈 수 있게 충분한 용량을 주어 설정해줌
2. Ruby 2.5.1을 2.7로 변경하니 정상 작동
3. Ubuntu의 기본 쉘을 dash에서 bash로 변경해주고 switch 스크립트를 실행시킴<br/><br/>

</div>
</details>

<br/><br/>

# 🏅**MTZ 대장님들**<br/>

|        |                                                              조평연                                                              |                                                              이동재                                                              |                                                              오경민                                                              |                                                              김민석                                                              |                                                              이현진                                                              |
| :----: | :------------------------------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------------------------------: |
|  역할  |                                                            BE (리더)                                                             |                                                                BE                                                                |                                                           FE (부리더)                                                            |                                                                FE                                                                |                                                                FE                                                                |
|  🙋🏻‍♂️🙋🏻‍♀️  | <img src="https://user-images.githubusercontent.com/102575747/207052487-11c12922-ecb5-460c-b695-a2529e3bd7b0.png" width="350" /> | <img src="https://user-images.githubusercontent.com/102575747/207052297-7ae4301c-d196-490d-b617-017ed8b07db2.png" width="350" /> | <img src="https://user-images.githubusercontent.com/102575747/207052021-7a27ad9e-b959-456d-ac78-f1b0eab20c15.png" width="350" /> | <img src="https://user-images.githubusercontent.com/102575747/207027594-40834306-7d11-4ed8-8d36-9ec00a4a1588.png" width="350" /> | <img src="https://user-images.githubusercontent.com/102575747/207053599-5233e49c-8fc0-4023-994b-22911b9efbd0.png" width="350" /> |
| GITHUB |                                                [🔗](https://github.com/vuddus526)                                                |                                               [🔗](https://github.com/Pdongjaelee)                                               |                                                 [🔗](https://github.com/ogaeng1)                                                 |                                                 [🔗](https://github.com/kms8381)                                                 |                                                 [🔗](https://github.com/zinnli)                                                  |
