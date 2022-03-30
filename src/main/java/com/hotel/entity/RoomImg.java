package com.hotel.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name= "room_img")
@Getter
@Setter
public class RoomImg {

    @Id
    @Column(name="room_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repimgYn;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public void updateRoomImg(String oriImgName, String imgName, String imgUrl){
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl=imgUrl;

    }
}
