package com.pankaj.StayNest.controller;


import com.pankaj.StayNest.dto.LoginRequestDto;
import com.pankaj.StayNest.dto.LoginResponseDto;
import com.pankaj.StayNest.dto.SignUpReqestDto;
import com.pankaj.StayNest.dto.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/login")
    private ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        //

    }

    @PostMapping("/singup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpReqestDto signUpReqestDto){

    }
}
