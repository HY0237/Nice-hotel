package com.hotel.dto.client;

import com.hotel.constant.Role;
import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
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

    @QueryProjection
    public ClientDto(Long id, String name, String email, String phoneNum, Role role) {
        this.id = id;
        this.name =name;
        this.email = email;
        this.phoneNum= phoneNum;
        this.role = role;
    }
}
