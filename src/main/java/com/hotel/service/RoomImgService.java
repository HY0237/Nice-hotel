package com.hotel.service;

import com.hotel.entity.RoomImg;
import com.hotel.repository.room.RoomImgRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomImgService {

    @Value("${roomImgLocation}")
    private String roomImgLocation;

    private final RoomImgRepository roomImgRepository;

    private final FileService fileService;

    /**
     * 객실 이미지 저장
     * @param roomImg
     * @param roomImgFile
     * @throws Exception
     */
    public void saveRoomImg(RoomImg roomImg, MultipartFile roomImgFile) throws Exception{
        String oriImgName = roomImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(roomImgLocation, oriImgName, roomImgFile.getBytes());

            imgUrl = "/images/room/" + imgName;
        }

        roomImg.updateRoomImg(oriImgName, imgName, imgUrl);
        roomImgRepository.save(roomImg);
    }

    /**
     * 객실 이미지 수정
     * @param roomImgId
     * @param roomImgFile
     * @throws Exception
     */
    public void updateRoomImg(Long roomImgId, MultipartFile roomImgFile) throws Exception{
        if(!roomImgFile.isEmpty()){
            RoomImg savedRoomImg = roomImgRepository.findById(roomImgId).orElseThrow(EntityNotFoundException::new);

            if(!StringUtils.isEmpty(savedRoomImg.getImgName())) {
                fileService.deleteFile(roomImgLocation+"/"+
                        savedRoomImg.getImgName());
            }

            String oriImgName = roomImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(roomImgLocation, oriImgName, roomImgFile.getBytes());
            String imgUrl = "/images/room/" + imgName;
            savedRoomImg.updateRoomImg(oriImgName, imgName, imgUrl);


        }

    }


}
