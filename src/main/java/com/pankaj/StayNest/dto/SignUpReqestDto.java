package com.pankaj.StayNest.dto;

import com.pankaj.StayNest.entity.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpReqestDto {

    private String username;
    private String password;
    private String name;
    private String email;
    private Long payoutAccountNumber;

    private Set<RoleType> roles = new HashSet<>();
}
