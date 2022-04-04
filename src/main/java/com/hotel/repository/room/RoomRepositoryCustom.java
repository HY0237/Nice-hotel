package com.hotel.repository.room;

import com.hotel.dto.reservation.ReservationMainDto;
import com.hotel.dto.room.RoomSearchDto;
import com.hotel.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomRepositoryCustom {

    Page<Room> getAdminRoomPage(RoomSearchDto roomSearchDto, Pageable pageable);

    Page<ReservationMainDto> getReserveRoomPage(RoomSearchDto roomSearchDto, Pageable pageable);
}
