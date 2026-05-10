package com.gsm._8th.class4.backed.task._1._1.domain.controller;

import com.gsm._8th.class4.backed.task._1._1.domain.dto.SignInRequestDto;
import com.gsm._8th.class4.backed.task._1._1.domain.dto.SignUpRequestDto;
import com.gsm._8th.class4.backed.task._1._1.domain.dto.SignInResponseDto;
import com.gsm._8th.class4.backed.task._1._1.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto request) {
        userService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto request) {
        SignInResponseDto response = userService.signIn(request);
        return ResponseEntity.ok(response);
    }
}