package com.hotel.repository;

import com.hotel.dto.RoomSearchDto;
import com.hotel.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomRepositoryCustom {

    Page<Room> getAdminItemPage(RoomSearchDto roomSearchDto, Pageable pageable);
}
