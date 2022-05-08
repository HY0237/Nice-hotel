package com.hotel.repository.reservation;

import com.hotel.dto.reservation.ReservationSearchDto;
import com.hotel.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationRepositoryCustom {

    Page<Reservation> getAdminReserPage(ReservationSearchDto reservationSearchDto, Pageable pageable);

}
