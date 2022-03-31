package com.hotel.repository;

import com.hotel.dto.ReservationMainDto;
import com.hotel.dto.RoomSearchDto;
import com.hotel.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomRepositoryCustom {

    Page<Room> getAdminRoomPage(RoomSearchDto roomSearchDto, Pageable pageable);

    Page<ReservationMainDto> getReserveRoomPage(RoomSearchDto roomSearchDto, Pageable pageable);
}
