# 🏤 **Nice Hotel** 

## ❔ 프로젝트 계기
이 프로젝트를 시작한 계기는 웹 프로그래밍의 기본 기술인 서버 프레임워크, 웹 언어 그리고 ORM등의 개념을 공부하고 
각 기술들이 어떻게 유기적으로 연결이 되는지 이해하고 싶어 만들어 보게 되었습니다.

## 🙋‍♀️ 프로젝트 소개
간단한 호텔 객실 예약 및 관리 시스템입니다. 일반인 회원과 관리자에 따라 객실 예약, 조회, 관리등을 할 수 있습니다.

## 📝 프로젝트 기능
프로젝트의 주요 기능은 다음과 같습니다.
![nice_hotel기능](https://user-images.githubusercontent.com/68864993/165903591-3013ddcc-c1ed-44af-9c89-05ed255ea5a4.JPG)

## 💻 사용기술
- ### Build Tool
  maven
- ### Language
  Java 11
- ### Framework
  SpringBoot 2.6.4
- ### Database
  MYSQL
- ### Dependencies
  - Spring Web
  - Spring data JPA
  - Spring Security
  - Lombok
  - QueryDSL
  - Thymelaf
  - Devtools
  - Bootstrap
  - Modelmapper
  - JUnit 5
 ## 🛠 구조 및 설계
 ### DB 설계
 ![nice_hotel_ERD](https://user-images.githubusercontent.com/68864993/165909386-eb711ca0-0117-46fa-ba9c-50a610f8a9e4.JPG)
 ### API 설계
 <img src="https://user-images.githubusercontent.com/68864993/165914999-318498d4-b9d8-4dd7-b2e0-5ea20b79356d.JPG"  width="800" height="300"/>
 <img src="https://user-images.githubusercontent.com/68864993/165915003-aa8b85bb-a72d-48bb-93ab-dcc24914a6a7.JPG"  width="800" height="250"/>
 <img src="https://user-images.githubusercontent.com/68864993/165915009-10a16953-d590-4e75-97cc-7dd6a098b13b.JPG"  width="800" height="400"/>
 <img src="https://user-images.githubusercontent.com/68864993/165915015-7f404a40-235f-4067-bec4-5acac6b72549.JPG"  width="800" height="200"/>
 
  ## 🖥 실행 화면
  ### 1. 회원가입
  > 이름 이메일 비밀번호를 입력해 회원가입을 합니다 (Role 옵션은 나중에 지울 예정입니다)
  
![회원가입](https://user-images.githubusercontent.com/68864993/165917462-a4ca9cb1-b43a-41b8-98db-f75c91ed59f2.JPG)

  ### 2. 로그인
  > 생성한 이메일 비밀번호로 로그인 합니다
  
![로그인 화면](https://user-images.githubusercontent.com/68864993/165917470-04cde126-db39-4213-97ae-9d58d368fb85.JPG)


  ### 3. 사용자용 홈
  > 체크인, 체크아웃, 게스트 수의 옵션과 객실 이름을 검색해 예약 가능한 객실을 조회할 수 있습니다. 조회한 객실을 선택해 예약 할 수 있습니다
![사용자용MAIN](https://user-images.githubusercontent.com/68864993/165920088-93255e41-a9bc-4c79-9fc0-6f4914022d9c.JPG)

  ### 4. 예약 페이지
  > 이름, 이메일, 게스트 수를 입력해 객실을 예약할 수 있습니다. 체크인 체크아웃 날짜는 기존의 메인 페이지에서 넣었던 조건을 넣어 반영하였습니다. 게스트 수는 최대 객실 인원 수 이하로 넣을 수 있습니다.  
  
![예약페이지](https://user-images.githubusercontent.com/68864993/165920778-f8e4d1b2-fab4-4854-b5c2-9d55f8886d9f.JPG)

  ### 5. 예약 내역
  > 예약한 객실을 확인하고 예약을 취소를 할 수 있습니다.
  
![회원예약객실조회](https://user-images.githubusercontent.com/68864993/165920417-71bb7f6a-c864-4e34-92cd-3674dc4b58d9.JPG)

  ### 6. 관리자용 홈
  > 사용자와 같이 예약 가능한 객실을 조회 할 수 있습니다. 왼쪽 메뉴에는 회원, 객실, 예약을 조회 할 수 있는 버튼들이 있습니다.
  
![niceHotel_mainPage](https://user-images.githubusercontent.com/68864993/165918471-efcac35a-9714-4f75-8a57-93e640287008.JPG)

  ### 7. 관리자용 회원 관리
  > 등록 기간, 회원 이름, 이메일로 전체 회원을 조회 할 수 있습니다. 각 회원님의 정보를 수정하고 삭제할 수 있습니다.
  
![회원 조회](https://user-images.githubusercontent.com/68864993/165922182-1edb50a2-abb1-4a11-953c-a65a53fa023d.JPG)

  ### 8. 관리자용 객실 관리 
  > 등록 기간, 객실 타입, 객실 이름으로 전체 객실을 조회 할 수 있습니다. 객실을 추가, 수정, 삭제할 수 있습니다.
  
![객실 조회](https://user-images.githubusercontent.com/68864993/165922555-72d1d409-130e-47b8-8de3-7a2b894061ba.JPG)

  ### 6. 관리자용 예약 관리 
  > 등록 기간, 객실 이름, 회원 이름, 회원 메일로 전체 예약을 조회 할 수 있습니다. 예약을 추가 또는 삭제할 수 있습니다.
  
![예약 조회](https://user-images.githubusercontent.com/68864993/165924536-747ea897-7cf7-4ff3-bdca-825018dc0b94.JPG)

  ## SpringBoot (API Server)
  구조는 다음과 같습니다.
  - config: project configuration을 관리한다.
  - constant: 상수 enum 타입을 관리한다.
  - controller: API를 관리한다
  - dto: 데이터 교환을 위한 빈으로 사용한다
  - entity: Domain을 관리한다
  - repository: JPA + QueryDSL를 관리한다
  - service: Domain에 정의한 비지니스 로직 호출 순서를 관리한다

  ## Sprig Security
  > security 설정을 추가해 사용자만 특정 API에 접근할 수 있도록 제한 합니다. 
  구조는 다음과 같습니다
  - CSRF: Default
  - Authentication Entry Point: CustomAuthenticationEntryPoint.class

permitAll()을 통해 사용자가 인증없이 해당 경로에 접근할 수 있도록 설정합니다. 반대로 "/admin"으로 시작하는 경로는 해당 계정이 ADMIN Role일 경우일때만 접근 가능 하도록 합니다.

  ## JPA & QueryDSL (ORM)
  - JPA: 반복적인 CRUD 작업을 대체해 간단한 DB에서 데이터를 조회한다
  - QueryDSL : JPA로 해결 할 수 없는 복잡한 쿼리는 QueryDSL로 작성한다.

  예시) 예약 가능한 객실을 조회 할때
  - 구조
    - Reservation (Domain Class)
    - ReservationRespository (JPA interface)
    - ReservationRepositoryCustom (QueryDSL interface)
    - ReservationRepositoryCustomImpl (QueryDSL implements Class)

 - JPA와 QueryDSL로 구현하기 전 Mysql workbench로 실행이 되는지 확인해 보았습니다.
 
``` sql
select * from room where room_id not in (select room_id from reservation 
where ('2022-04-10' <= Date(check_in) and  Date(check_in) < '2022-04-11')
or ('2022-04-10' <  Date(check_out) and  Date(check_out) <= '2022-04-11')
or( Date(check_in) < '2022-04-10' and '2022-04-11' < Date(check_out)));
```

   ## JPA의 Auditing
   > 객실 예약 멤버 엔티티가 저장 또는 수정될 때 자동으로 등록일, 수정일, 등록자, 수정자를 입력해 줍니다.
   - BaseTimeEntity class: 등록일 @CreatedDate 수정일 @LastModifiedDate를 엔티티가 생성 변경 될때 자동으로 저장
   - BaseEntity: BaseTimeEntity를 상속 받으며 등록자 @CreatBy 수정자 @LastModifedBy를 엔티티가 생성 변경 될때 자동으로 저장
   - Room, Reservation, Member 엔티티는 BaseEntity 클래스를 상속 받았습니다.
   
   ## Devtools
   > 개발시 유용한 기능
   - Automatic Restart: classPath에 잇는 파일이 변경될 때마자 애플리케이션이 자동으로 재시작해 줍니다.
   - Live Reload: 정적 자원(html, css, js) 수정 시 애플리케이션 재구동 없이 변경된 리소스가 변영됩니다.
   - Property Defaults: 개발 환경에서 Thymeleaf의 캐싱 기능을 false로 설정 하였습니다.

   ## Modelmapper
   > Object field값을 getter, setter를 통해 값을 복사해서 다른 객체에 반환해줍니다
   - Room DTO 객체와 Room 엔티티 객체간의 데이터를 복사하여 복사한 객체를 서로 반환해주었습니다. 
   
   ## JUnit (Test)
   > Layer 별로 테스트 하고자 하는 로직에 집중해 테스트를 수행하였습니다. 가볍고 빠른 H2 데이터 베이스를 사용하여 테스트해 보았습니다.
   - Domain 테스트: domain 객체들이 가장 핵심이며 이 객체를 사용하는 계층들이 프로젝트에 다양하게 분포되기 때문에 반드시 테스트 코드를 작성하였습니다.
   - Repository 테스트: 객실, 예약 저장 테스트 실행
   - Service 테스트: @withMockUser을 사용하여 권한 테스트
   - Controller 테스트: @Transactional 어노테이션을 추가해 테스트후 DB를 자동으로 Rollback 하였습니다.
   
   ## 💡 깨달은 점
   - html(view) 프론트에서 날짜 데이터를 controller에게 넘겨서 DTO 클래스에 있는 날짜 데이터로 주입할 때 직렬화를 못해 string으로 받은 후 서비스 레이어에서 localdatetime변환을 했었는데 @DateTimeFormate이라는 스프링에서 지원하는 어노테이션을 안 뒤로 이 어노테이션을 사용해 날짜 관련 타입 데이터를 쉽게 직렬화하고 커스터마이징을해서 데이터를 입력 받아 올 수 있었다.
   - spring security를 이용할 때 ajax의 Post호출시403 에러가 난적이 있다. 보았더니 csrf쪽 관련 문제였다.  구글 검색 결과의 몇몇 문서에서는 http.csrf().disable()와 같이 csrf를 disable 하라고 했지만 심플하게 Ajax 요청 Header에 csrf token 정보를 포함해서 전송하여 해결 하였다. 
   - 예약 가능한 객실 조회하는 기능을 만들 때 쿼리가 굉장히 복잡했다. Room 과 Reservation table을 조인하고 체크인 체크아웃날짜를 비교하고 최대 객실 인원수도 체크해야 했기 때문에 jpa에 제공하는 쿼리 메소드로는 조금 무리가 될 거 같아  jap queryDSl을 사용하였습니다. queryDSL은 SQL 문자열이 아닌 자바 코드로 작성했기 때문에 컴파일시 에러를 쉽게 발견할 수 있는 장점이 있었다. 또한 조건에 맞게 동적으로 쿼리를 생성 할 수 있고 자바 소스코드로 작성하기 때문에 가독성을 향상 시킬 수 있었다.
 

 
