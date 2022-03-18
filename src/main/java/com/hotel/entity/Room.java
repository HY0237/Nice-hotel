package com.hotel.entity;

import com.hotel.constant.RoomStatus;
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
public class Room {

    @Id
    @Column(name="room_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String roomNm;

    @Column(name = "price", nullable = false)
    private int pricePerNight;

    @Lob
    @Column(nullable = false)
    private String roomDetail;

    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}
