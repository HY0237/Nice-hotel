# 🏤 **Nice Hotel** 

## ❔ 프로젝트 계기
이 프로젝트를 시작한 계기는 웹 프로그래밍의 기본 기술인 서버 프레임워크, 웹 언어 그리고 데이터베이스등의 개념을 공부하고 
각 기술들이 어떻게 유기적으로 연결이 되는지 이해하고 싶어 만들어 보게 되었습니다. 그리고 여러 숙박 예약 사이트에서 호텔과 객실을 검색하는 기능을 보고 이에 관한 데이터 쿼리도 한번 만들어보고 싶은 마음도 있었습니다. 

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
  MYSQL 8.0.27
- ### Dependencies
  - Spring Web 
  - Spring data JPA 
  - Spring Security 
  - Lombok 1.18.22
  - QueryDSL 5.0.0
  - Thymelaf 
  - Devtools 
  - Modelmapper 2.3.9
  - JUnit 4.13.2
 - ### Front-end
  - html
  - css
  - jquery

 
 ## 🛠 DB 설계
 
 ![nice_hotel_ERD](https://user-images.githubusercontent.com/68864993/165909386-eb711ca0-0117-46fa-ba9c-50a610f8a9e4.JPG)
 
 ## 🛠 API 설계
 
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
  > 등록 기간, 회원 이름, 이메일 옵션을 넣어 회원을 조회 할 수 있습니다. 각 회원님의 정보를 수정하고 삭제할 수 있습니다.
  
![회원 조회](https://user-images.githubusercontent.com/68864993/165922182-1edb50a2-abb1-4a11-953c-a65a53fa023d.JPG)

  ### 8. 관리자용 객실 관리 
  > 등록 기간, 객실 타입, 객실 이름 옵션을 넣어 객실을 조회 할 수 있습니다. 객실을 추가, 수정, 삭제할 수 있습니다.
  
![객실 조회](https://user-images.githubusercontent.com/68864993/165922555-72d1d409-130e-47b8-8de3-7a2b894061ba.JPG)

  ### 6. 관리자용 예약 관리 
  > 등록 기간, 객실 타입, 회원 이름, 회원 메일 옵션을 넣어 예약을 조회 할 수 있습니다. 예약을 추가 또는 삭제할 수 있습니다.
  
![예약 조회](https://user-images.githubusercontent.com/68864993/165924536-747ea897-7cf7-4ff3-bdca-825018dc0b94.JPG)


  ## SpringBoot (API Server)
  >  api를 만들기 위해 계층별로 7가지 패키지의 구조로 나타냈습니다.
  
  구조는 다음과 같습니다.
  - config: project configuration을 관리합니다.
  - controller: 외부 요청과 응답 API를 관리합니다.
  - service: Domain에 정의한 비지니스 로직 호출 순서를 관리합니다.
  - entity: Domain model을 관리합니다.
  - constant: 상수 enum 타입을 관리합니다.
  - dto: 데이터 교환을 위한 객체들을 관리합니다.
  - repository: JPA + QueryDSL를 관리합니다.
  

  ### 배운 점 📚 
  - Service에서 loombok의 @RequiredArgsContructor를 사용해 final이 선언된 모든 필드를 인자값으로 생성합니다. 이를 통해 생성자 코드를 계속해서 수정하는 번거로움을 해결할 수 있습니다.
  - Service 클래스에 @Transactional 어노테이션을 선언해 로직을 처리하다가 에러가 발생하면 로직을 수행하기 이전 상태로 콜백시켜줍니다.
  - Entity 클래스를 Request/Response 클래스로 사용하지 않도록! 그 이유는 Entity 클래스는 데이터 베이스와 맞닿은 클래스이기 때문입니다.
  - update 기능에서 데이터베이스에 Update 쿼리를 날릴 필요가 없습니다. 엔티티를 영구 저장하는 JPA의 영속성 컨텍스트 환경으로 인해 데이터의 값이 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영합니다.
    ```
        public Long updateClient(ClientDto clientDto){
          Member member = memberRepository.findById(clientDto.getId()).orElseThrow(EntityExistsException::new);
          member.updateClient(clientDto);
          return member.getId();
         }
    ```
- @DateTimeFormate이라는 스프링에서 지원하는 어노테이션을 사용해 날짜 관련 타입 데이터를 쉽게 직렬화하고 커스터마이징을해서 데이터를 입력 받아 올 수 있었습니다.
  ```
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkIn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOut;
    
   ```


## Spring Security
  > security 인증, 인가 설정을 추가해 회원가입 로그인을 구현해 보고 관리자 페이지에 접근 권한을 부여하는 서비스를 구현해보았습니다. 
  - 인증이 필요없는 경우 : 예약 가능한 객실 조회
  - 인증이 필요한 경우 : 객실 예약, 예약 상세보기
  - 관리자 권한이 필요한 경우 : 객실 등록, 수정, 조회하기, 회원 수정, 조회하기, 예약 조회하기 

 ### 배운 점 📚
- permitAll()을 통해 사용자가 인증없이 해당 경로에 접근할 수 있도록 설정합니다. 반대로 "/admin"으로 시작하는 경로는   해당 계정이 ADMIN Role일 경우일때만 접근 가능 하도록 설정했습니다.
    ```
        http.authorizeRequests()
                .mvcMatchers("/**", "/reservation/**", "/members/**", "/room/**","/images/**").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();
    ```
- static 디렉터리의 하위 파일은 인증을 무시하도록 설정했습니다.
    ```
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/assets/**","/forms/**","/img/**");
        }
    ```

- spring security를 사용할 경우 기본적으로 CSRF를 방어하기 위해 모든 POST 방식의 데이터 전송에 CSRF 토큰 값을 보내 전송했습니다.
    ```
        <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}">
    ```
    ```
        var token = $("meta[name='_csrf']").attr("content");
         var header = $("meta[name='_csrf_header']").attr("content");
                  ...
        $.ajax({
            url      : url,
            type     : "POST",
            contentType : "application/json",
            data     : param,
            beforeSend : function(xhr){
             /* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
               xhr.setRequestHeader(header, token);
            },
            dataType : "json",
            cache   : false,
            success  : function(result, status){
            alert("예약이 완료 되었습니다.");
            location.href='/';
            }
        });
    ```

 ## JPA 
  > ORM을 위해 자바에서 제공하는 API, 반복적인 CRUD 작업을 대체해 DB에서 간단한 데이터를 조회 합니다.
  
-  SpringDataJpa에서 제공하지 않는 메소드는 @Query를 작성하여 사용해 보았습니다.
      ```
            @Query("select r from Reservation r "+
                    "where r.member.email = :email "+
                    "order by r.regTime desc")
            List<Reservation> findReservations(@Param("email") String email, Pageable pageable);
        
            @Query("select count(r) from Reservation r "+
                    "where r.member.email = :email ")
            Long countReservation(@Param("email") String email);
      ```
    
  ### JPA의 Auditing
   > 객실, 예약, 멤버 엔티티가 저장 또는 수정될 때 자동으로 등록일, 수정일, 등록자, 수정자를 입력해 줍니다.

   - BaseTimeEntity class: 등록일 @CreatedDate 수정일 @LastModifiedDate를 엔티티가 생성 변경 될때 자동으로 저장합니다.
   - BaseEntity: BaseTimeEntity를 상속 받으며 등록자 @CreatBy 수정자 @LastModifedBy를 엔티티가 생성 변경 될때 자동으로 저장합니다.
   - Room, Reservation, Member 엔티티는 BaseEntity 클래스를 상속 받았습니다.


  
 ## QueryDSL
 > JPA로 해결 할 수 없는 복잡한 쿼리는 QueryDSL로 작성했습니다.

 - 예약 가능한 객실을 조회 할때
    - 구조
        - Reservation (Domain Class)
        - ReservationRespository (JPA interface)
        - ReservationRepositoryCustom (QueryDSL interface)
        - ReservationRepositoryCustomImpl (QueryDSL implements Class)
        
    - QueryDSL로 구현하기 전 Mysql workbench로 여러 체크인 체크아웃 날짜를 넣어 테스트하고 수정하며 쿼리를 구현해 보았습니다.
    ``` sql
                select * from room where room_id not in (select room_id from reservation 
                where ('2022-04-10' <= Date(check_in) and  Date(check_in) < '2022-04-11')
                or ('2022-04-10' <  Date(check_out) and  Date(check_out) <= '2022-04-11')
                or( Date(check_in) < '2022-04-10' and '2022-04-11' < Date(check_out)));
    ```
    - 사용자가 넣은 checkIn과 checkOut에 따라서 다른 예약 날짜를 비교해 예약 가능한 객실 조회하도록 조건값을 반환합니다.
    ```
            private BooleanExpression searchDate(LocalDate checkIn, LocalDate checkOut){
        
                LocalDate checkInDefault = LocalDate.now();
                LocalDate checkOutDefault = LocalDate.now().plusDays(1);
                
                // 체크인 체크아웃 날짜를 넣지 않았을 때 오늘과 내일 날짜로 default 해서 넣기
                if(checkIn == null && checkOut == null){
                    checkIn = checkInDefault;
                    checkOut = checkOutDefault;
                }
                /**
                 *  case 1 : checkIn <= QcheckIn and QcheckIn < checkOut 
                 *  예약한 체크인 날짜가 사용가 넣은 검색 일짜 사이에 있을 때 
                 */
                BooleanExpression case1 =
                        QReservation.reservation.checkIn.goe(checkIn)
                                .and(QReservation.reservation.checkIn.lt(checkOut));
                /**
                 *  case 2 : checkIn <= QcheckOut and QcheckOut < checkOut 
                 *  예약한 체크아웃 날짜가 사용가 넣은 검색 일짜 사이에 있을 때 
                 */
                BooleanExpression case2 =
                        QReservation.reservation.checkOut.gt(checkIn)
                                .and(QReservation.reservation.checkOut.loe(checkOut));
                /**
                 *  case 3 : QcheckIn < checkIn and checkOut  < QcheckOut 
                 *  예약한 체크인 체크아웃 기간 안에 사용가 넣은 검색 일짜가 있을 때 
                 */
                BooleanExpression case3 =
                        QReservation.reservation.checkIn.lt(checkIn)
                                .and(QReservation.reservation.checkOut.gt(checkOut));
        
                return case1.or(case2).or(case3);
    }
    ```
    - 사용자가 넣은 guest 수에 따라 객실의 최대 인원수를 비교해 예약 가능한 객실을 조회하도록 조건값을 반환합니다
    ```
            private BooleanExpression searchMaxGuest(Integer guest){
                return guest == null? QRoom.room.maxPeople.goe(1): QRoom.room.maxPeople.goe(guest);
            }
    ```
    - 객실 이름에 사용자가 넣은 검색어가 포함하는 객실을 조회하도록 조건값을 반환합니다
    ```
            private BooleanExpression searchRoomByName(String searchQuery){
                return StringUtils.isEmpty(searchQuery) ? null : QRoom.room.roomNm.like("%" + searchQuery + "%");
             }
    ```
    - 사용자가 넣은 날짜, 게스트 수, 객실 이름 조건으로 예약 가능한 객실 조회하도록 출력합니다.
    ```
        @Override
        public Page<ReservationMainDto> getReserveRoomPage(RoomSearchDto roomSearchDto, Pageable pageable) {
            QRoom room = QRoom.room;
            QRoomImg roomImg = QRoomImg.roomImg;
            QReservation reservation = QReservation.reservation;
    
            List<ReservationMainDto> contents = queryFactory
                    .select(
                            new QReservationMainDto(
                                    room.id,
                                    room.roomNm,
                                    room.roomType,
                                    room.pricePerNight,
                                    room.maxPeople,
                                    roomImg.imgUrl))
                    .from(roomImg)
                    .join(roomImg.room(), room) // room과 roomImg 테이블 조인
                    .where(room.id.notIn( // reservation 테이블 에서 예약 가능한 room 아이디 조회
                            JPAExpressions
                                    .select(reservation.room().id)
                                    .from(reservation)
                                    .where(searchDate(roomSearchDto.getSearchCheckIn(), // 예약 날짜 조건
                                            roomSearchDto.getSearchCheckOut()))),
                            roomImg.repimgYn.eq("Y"), // 대표 이미지 조건
                            searchRoomByName(roomSearchDto.getSearchQuery()), // 객실 이름 조건
                            searchMaxGuest(roomSearchDto.getSearchAdults())) // 게스트 수 조건
                    .orderBy(room.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
            
            long total = contents.size();
            return new PageImpl<>(contents, pageable, total);
    }
    ```
    

 ## Modelmapper
   > Object field값을 getter, setter를 통해 값을 복사해서 다른 객체에 반환해줍니다
   - Room DTO 객체와 Room 엔티티 객체간의 데이터를 복사하여 복사한 객체를 서로 반환해주었습니다. 
       ```
           private static ModelMapper modelMapper = new ModelMapper();
           
           public Room createRoom() {
                        return modelMapper.map(this, Room.class);
            }
            public static RoomFormDto of(Room room){
                return modelMapper.map(room, RoomFormDto.class);
            }
       ```
       
 ## JQuery
   > HTML 문서의 이벤트 처리를 위해 사용하고 Ajax 메소드를 통해 데이터를 서버로 보낼 수 있었습니다.
   
   - Jquery로 페이지 처리 함수를 구현해 보았습니다.
       ```
       function page(page){
                  var searchQuery = $("#searchQuery").val();
                  var checkIn = $("#checkIn").val();
                  var checkOut = $("#checkOut").val();
                  var guest = $("#searchAdults").val();
    
                  location.href="/" + page
                  + "?searchQuery=" + searchQuery
                  + "&searchCheckIn=" + checkIn
                  + "&searchCheckOut=" + checkOut
                  + "&searchAdults=" + guest;
              }
       ```
   - Post Ajax로 예약 함수를 구현해 보았습니다.
       ```
       function reservation(){
              var token = $("meta[name='_csrf']").attr("content");
              var header = $("meta[name='_csrf_header']").attr("content");

              var url = "/reservation/new";
              var paramData = {
                  checkIn : $("#checkin").val(),
                  checkOut : $("#checkout").val(),
                  price : $("#totalPrice").val(),
                  guest : $("#guests").val(),
                  roomId : $("#roomId").val(),
                  name : $("#name").val(),
                  email : $("#email").val()
              };

              var param = JSON.stringify(paramData);

              $.ajax({
                  url      : url,
                  type     : "POST",
                  contentType : "application/json",
                  data     : param,
                  beforeSend : function(xhr){
                      /* 데이터를 전송하기 전에 헤더에 csrf값을 설정 */
                      xhr.setRequestHeader(header, token);
                  },
                  dataType : "json",
                  cache   : false,
                  success  : function(result, status){
                      alert("예약이 완료 되었습니다.");
                      location.href='/';
                  }
              });
            }
       ```
- 숙박 기간에 따른 총 결제 금액 함수를 구현해 보았습니다.
    ```
            function calculateTotalPrice(){

              var date1 = new Date($("#checkin").val());
              var date2 = new Date($("#checkout").val());

              var Difference_In_Time = date2.getTime() - date1.getTime();
              var nights = Difference_In_Time / (1000 * 3600 * 24);

              var price = $("#pricePerNight").val();
              var totalPrice = price*nights;
              $("#totalPrice").val(totalPrice);
              $("#totalPriceH").html(totalPrice + '원');
              $("#nights").html(nights);

            } 
    ```
   
## JUnit 4 (Test)
   > H2 데이터 베이스를 사용하여 Layer 별 로직에 집중해 테스트를 수행하였습니다.
   - Domain 테스트: Entity 클래스들의 비지니스 로직 updateClient, updateRoom등의 메소드을 테스트 했습니다.
        ```
            @Test
            @DisplayName("객실 수정 테스트")
            void updateRoom_test(){
    
                RoomFormDto roomFormDto = new RoomFormDto();
                roomFormDto.setRoomNm("test10");
                roomFormDto.setRoomType(RoomType.DOUBLE);
                roomFormDto.setRoomDetail("testtestest");
                roomFormDto.setMaxPeople(4);
                roomFormDto.setPricePerNight(3000);
        
                room.updateRoom(roomFormDto);
        
                Assertions.assertEquals(roomFormDto.getRoomNm(), room.getRoomNm());
                Assertions.assertEquals(roomFormDto.getRoomType(), room.getRoomType());
                Assertions.assertEquals(roomFormDto.getRoomDetail(), room.getRoomDetail());
                Assertions.assertEquals(roomFormDto.getMaxPeople(), room.getMaxPeople());
                Assertions.assertEquals(roomFormDto.getPricePerNight(), room.getPricePerNight());
            
            }
        ```
   - Repository 테스트: Repository안에 있는 Jpa, QueryDSL 쿼리들을 테스트 했습니다.
        ```
            @Test
            @DisplayName("객실 저장 테스트")
            void createRoomTest(){
                Room room = new Room();
                room.setRoomNm("테스트 객실");
                room.setPricePerNight(40000);
                room.setRoomDetail("객실 상세 설명");
                room.setRoomType(RoomType.SINGLE);
                room.setRegTime(LocalDateTime.now());
                room.setUpdateTime(LocalDateTime.now());
                Room savedRoom = roomRepository.save(room);
                System.out.println(savedRoom.toString());
            }
        ```
   - Service 테스트: @Transactional 어노테이션을 추가해 테스트후 DB를 자동으로 Rollback 하였습니다.
        ```
            @SpringBootTest
            @Transactional
            @TestPropertySource(locations = {"classpath:application-test.properties"})
            class RoomServiceTest {
                  ...
                  
                  @Test
                  @DisplayName("객실 삭제 테스트")
                  @WithMockUser(username = "admin", roles = {"ADMIN"})
                  void deleteRoom() throws Exception {
                      this.createRoomList();
                      roomService.deleteRoom(1L);
                      Assertions.assertThat(roomRepository.findById(1L)).isEmpty();
                  }
            }
       ```
   - Controller 테스트: @withMockUser을 사용하여 권한을 테스트 해보고 MockMvc를 이용해 GET, POST, DELETE api를 테스트 하였습니다.
       ```
                @Test
                @DisplayName("객실 예약 테스트")
                @WithMockUser(username = "test@test.com", roles = "USER")
                void reservationNew_test() throws Exception{
                    Member member = this.saveMember();
                    Long roomId = this.createRoom();

                    ReservationDto reservationDto = new ReservationDto();
                    reservationDto.setEmail(member.getEmail());
                    reservationDto.setName(member.getName());
                    reservationDto.setCheckIn(LocalDate.now());
                    reservationDto.setCheckOut(LocalDate.now());
                    reservationDto.setGuest(3);
                    reservationDto.setPrice(10000);
                    reservationDto.setRoomId(roomId);


                    mockMvc.perform(MockMvcRequestBuilders.post("/reservation/new/")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(reservationDto))
                                    .with(csrf()))

                            .andDo(print())
                            .andExpect(status().isOk());
                }
        
       ```
       
   
  - 🔑 총 54개의 Test Case를 작성했습니다. (Domain : 6 / Repository : 10 / Service : 14 / Controller : 24)
    
  ## References
   - 백견불여일타 스프링부트 쇼핑몰 프로젝트 with JPA
   - 스프링 부트와 AWS로 혼자 구현하는 웹 서비스
   - Nice Admin -Free bootstrap admin HTML template
  
 ## 프로젝트에 추가할 기능
   - OAuth + JWT 구글 페이스북 로그인 기능 넣기
   - spring batch로 하루 전 예약 알림 기능 넣기
   - Jenkins와 Codedeploy로  CI/CD 구현하기
   - AWS에 무중단 배포 하기
 

 
