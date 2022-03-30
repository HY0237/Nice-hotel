package com.hotel.dto;


import com.hotel.constant.RoomType;
import com.hotel.entity.Room;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RoomFormDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다")
    private String roomNm;

    @NotNull(message = "가격은 필수 입력 값입니다")
    private Integer pricePerNight;

    @NotNull(message = "최대 인원수는 필수 입력 값입니다")
    private Integer maxPeople;

    @NotBlank(message = "설명은 필수 입력 값입니다")
    private String roomDetail;

    private RoomType roomType;

    private List<RoomImgDto> roomImgDtoList = new ArrayList<>();

    private List<Long> roomImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Room createRoom(){
        return modelMapper.map(this, Room.class);
    }

    public static RoomFormDto of(Room room){
        return modelMapper.map(room, RoomFormDto.class);
    }



}
