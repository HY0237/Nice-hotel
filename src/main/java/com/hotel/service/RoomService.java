package com.hotel.service;

import com.hotel.dto.ReservationMainDto;
import com.hotel.dto.RoomFormDto;
import com.hotel.dto.RoomImgDto;
import com.hotel.dto.RoomSearchDto;
import com.hotel.entity.Room;
import com.hotel.entity.RoomImg;
import com.hotel.repository.RoomImgRepository;
import com.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomImgService roomImgService;
    private final RoomImgRepository roomImgRepository;

    public Long saveRoom(RoomFormDto roomFormDto, List<MultipartFile> roomImgFileList) throws Exception{

        Room room = roomFormDto.createRoom();
        roomRepository.save(room);

        for(int i =0; i< roomImgFileList.size();i++){
            RoomImg roomImg = new RoomImg();
            roomImg.setRoom(room);
            if(i == 0){
                roomImg.setRepimgYn("Y");
            }else{
                roomImg.setRepimgYn("N");
            }
            roomImgService.saveItemImg(roomImg, roomImgFileList.get(i));
        }

        return room.getId();
    }

    @Transactional(readOnly = true) //데이터를 읽어 오는 트랜잭션 읽기 전용
    public RoomFormDto getRoomDtl(Long roomId){

        List<RoomImg> roomImgList = roomImgRepository.findByRoomIdOrderByIdAsc(roomId);
        List<RoomImgDto> roomImgDtoList = new ArrayList<>();
        for(RoomImg roomImg: roomImgList){
            RoomImgDto roomImgDto = RoomImgDto.of(roomImg);
            roomImgDtoList.add(roomImgDto);
        }

        Room room = roomRepository.findById(roomId).orElseThrow(EntityNotFoundException::new);
        RoomFormDto roomFormDto = RoomFormDto.of(room);
        roomFormDto.setRoomImgDtoList(roomImgDtoList);
        return roomFormDto;

    }

    public Long updateRoom(RoomFormDto roomFormDto, List<MultipartFile> roomImgFileList) throws Exception{
        //상품 수정
        Room room = roomRepository.findById(roomFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        room.updateRoom(roomFormDto);
        List<Long> roomImgIds = roomFormDto.getRoomImgIds();

        //이미지 등록
        for(int i=0; i < roomImgFileList.size(); i++){
            roomImgService.updateRoomImg(roomImgIds.get(i), roomImgFileList.get(i));
        }

        return room.getId();
    }

    @Transactional(readOnly=true)
    public Page<Room> getAdminRoomPage(RoomSearchDto roomSearchDto, Pageable pageable){
        return roomRepository.getAdminRoomPage(roomSearchDto, pageable);
    }

    @Transactional(readOnly=true)
    public Page<ReservationMainDto> getReserveRoomPage(RoomSearchDto roomSearchDto, Pageable pageable){
        return roomRepository.getReserveRoomPage(roomSearchDto, pageable);
    }

}
