package com.hotel.repository.room;

import com.hotel.entity.RoomImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomImgRepository extends JpaRepository<RoomImg, Long> {

    List<RoomImg> findByRoomIdOrderByIdAsc(Long roomId);
}
