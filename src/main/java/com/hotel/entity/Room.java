package com.hotel.entity;

import com.hotel.constant.RoomType;
import com.hotel.dto.RoomFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="room")
@Getter
@Setter
@ToString
public class Room extends BaseEntity {

    @Id
    @Column(name="room_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String roomNm;

    @Column(name = "price", nullable = false)
    private int pricePerNight;

    @Column(name = "maxPeople", nullable = false)
    private int maxPeople;

    @Lob
    @Column(nullable = false)
    private String roomDetail;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;


    public void updateRoom(RoomFormDto roomFormDto){
        this.roomNm = roomFormDto.getRoomNm();
        this.pricePerNight = roomFormDto.getPricePerNight();
        this.maxPeople = roomFormDto.getMaxPeople();
        this.roomDetail = roomFormDto.getRoomDetail();
        this.roomType = roomFormDto.getRoomType();
    }
}
