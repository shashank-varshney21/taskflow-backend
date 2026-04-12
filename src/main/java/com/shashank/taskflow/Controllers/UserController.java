package com.shashank.taskflow.Controllers;

import com.shashank.taskflow.Dtos.LoginResponseDto;
import com.shashank.taskflow.Dtos.SignUpRequestDto;
import com.shashank.taskflow.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
        public ResponseEntity<LoginResponseDto> login(@RequestBody SignUpRequestDto loginRequestDto) {
            return userService.login(loginRequestDto);
    }

    @PostMapping("/register")
        public ResponseEntity<LoginResponseDto> register(@RequestBody SignUpRequestDto signUpRequestDto){
            return userService.register(signUpRequestDto);
    }
}
