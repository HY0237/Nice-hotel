package com.hotel.dto.client;

import com.hotel.constant.Role;
import com.hotel.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClientDto {

    private Long id;

    private String name;

    private String email;

    private String phoneNum;

    private Role role;

    public ClientDto(){

    }

    @QueryProjection
    public ClientDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.phoneNum= member.getPhoneNum();
        this.role = member.getRole();
    }
}
