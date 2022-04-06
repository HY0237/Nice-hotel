package com.hotel.dto.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientSearchDto {

    private String searchDateType;

    private String searchBy;

    private String searchQuery= "";
}
