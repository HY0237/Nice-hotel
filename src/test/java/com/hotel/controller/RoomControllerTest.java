package com.hotel.controller;

import com.hotel.constant.Role;
import com.hotel.constant.RoomType;
import com.hotel.dto.room.RoomFormDto;
import com.hotel.entity.Member;
import com.hotel.entity.Room;
import com.hotel.entity.RoomImg;
import com.hotel.repository.room.RoomImgRepository;
import com.hotel.repository.room.RoomRepository;
import com.hotel.service.RoomImgService;
import com.hotel.service.RoomService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
class RoomControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomService roomService;

    List<MultipartFile> createMultipartFiles() throws Exception {
        List<MultipartFile> multipartFileList = new ArrayList();

        for(int i = 0; i < 5; i++) {
            String path = "F:/niceHotel/room";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName, "image/jpg", new byte[]{1, 2, 3, 4});
            multipartFileList.add(multipartFile);
        }

        return multipartFileList;
    }

    public void createRoomList() throws Exception {

        for(int i = 1; i <= 10; i++) {
            RoomFormDto roomFormDto = new RoomFormDto();
            roomFormDto.setRoomNm("테스트 상품"+i);
            roomFormDto.setRoomType(RoomType.SINGLE);
            roomFormDto.setRoomDetail("테스트 상품 입니다");
            roomFormDto.setPricePerNight(1000*i);
            roomFormDto.setMaxPeople(i);
            List<MultipartFile> multipartFileList = this.createMultipartFiles();
            roomService.saveRoom(roomFormDto, multipartFileList);
        }

    }

    @Test
    @DisplayName("객실 추가 페이지 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void roomFormTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/room/new"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("객실 추가 테스트")
    @WithMockUser(username = "admin", roles = "USER")
    public void roomFormNotAdminTest() throws Exception{


        MockMultipartFile file = new MockMultipartFile("file", "orig", null, "bar".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/admin/room/new")
                        .file("roomImgFile", file.getBytes())
                        .param("roomNm", "testRoom")
                        .param("pricePerNight", "2000")
                        .param("maxPeople", "4")
                        .param("roomDetail", "testRoom")
                        .param("roomType", "SINGLE")
                        .with(csrf()))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/rooms"));
    }

    @Test
    @DisplayName("객실 전체 조회 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void getRoomAll_test() throws Exception {

        this.createRoomList();
        /**
         * Page 0 : test10~test7
         * Page 1: test6~test3
         * Page 2: test3~test1
         */
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/rooms/{page}", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("객실 이름으로 조회 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void getRoomAllByName_test() throws Exception {

        this.createRoomList();
        /**
         * 객실 이름 1을 입력 값으로 넣었을때 test1, test10 출력
         */
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/rooms/{page}", "0")
                        .param("searchQuery", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원 이메일로 조회 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void getClientAllByEmail_test() throws Exception {

        this.createRoomList();

        /**
         * 객실 타입 Single을 입력 값으로 넣었을때 모두 출력
         */
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/rooms/{page}", "0")
                        .param("searchRoomType", "SINGLE"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("객실 정보 상세보기 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void getClientDtl_test() throws Exception {

        this.createRoomList();
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/room/{roomId}", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("객실 수정 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void postClientUpdate_test() throws Exception {

        MockMultipartFile file = new MockMultipartFile("F:/niceHotel/room", "image.jpg", "image/jpg", new byte[]{1, 2, 3, 4});


        this.createRoomList();

        mockMvc.perform(MockMvcRequestBuilders.multipart("/admin/room/{roomId}", "1")
                        .file("roomImgFile", file.getBytes())
                        .param("id", "1")
                        .param("roomNm", "testRoom")
                        .param("pricePerNight", "2000")
                        .param("maxPeople", "4")
                        .param("roomDetail", "testRoom")
                        .param("roomType", "SINGLE")
                        .param("roomImgIds", new String[]{"2", "3","4","5","6"})
                        .with(csrf()))
                .andDo(print())
                .andExpect(redirectedUrl("/admin/rooms"));
    }

    @Test
    @DisplayName("객실 삭제 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void deleteClient_test() throws Exception {

        this.createRoomList();

        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/room/delete/{roomId}","1")
                        .with(csrf()))
                .andDo(print());

        Assertions.assertThat(roomRepository.findById(1L).isEmpty());
    }




}