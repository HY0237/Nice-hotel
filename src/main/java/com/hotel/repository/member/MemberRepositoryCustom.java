package com.hotel.repository.member;

import com.hotel.dto.client.ClientDto;
import com.hotel.dto.client.ClientSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {

    Page<ClientDto> getClientPage(ClientSearchDto clientSearchDto, Pageable pageable);
}
