package com.duxchallenge.apifootballteams.controller;

import com.duxchallenge.apifootballteams.data.dto.AuthResponseDto;
import com.duxchallenge.apifootballteams.data.dto.AuthenticationRequestDto;
import com.duxchallenge.apifootballteams.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "Inicia sesión y devuelve un jwt")
    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthenticationRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
