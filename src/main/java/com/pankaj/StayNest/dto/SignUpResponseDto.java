package com.pankaj.StayNest.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignUpResponseDto {
    private Long id;
    private String username;
}
