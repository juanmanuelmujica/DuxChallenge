package com.duxchallenge.apifootballteams.controller;

import com.duxchallenge.apifootballteams.data.dto.AuthResponseDto;
import com.duxchallenge.apifootballteams.data.dto.AuthenticationRequestDto;
import com.duxchallenge.apifootballteams.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody AuthenticationRequestDto request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthenticationRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
