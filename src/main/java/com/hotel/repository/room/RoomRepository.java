package com.hotel.repository.room;

import com.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> , QuerydslPredicateExecutor<Room>, RoomRepositoryCustom {


    List<Room> findByRoomNm(String roomNm);
}
