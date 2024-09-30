package com.duxchallenge.apifootballteams.service;

import com.duxchallenge.apifootballteams.data.dto.AuthResponseDto;
import com.duxchallenge.apifootballteams.data.dto.AuthenticationRequestDto;
import com.duxchallenge.apifootballteams.data.model.User;
import com.duxchallenge.apifootballteams.service.iservice.IAuthService;
import com.duxchallenge.apifootballteams.service.iservice.IUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    private final IUserService service;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(IUserService service, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponseDto login(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = service.login(request);
        return setAuthResponseDto(user);
    }

    private AuthResponseDto setAuthResponseDto(User user) {
        AuthResponseDto authResponseDto = new AuthResponseDto();

        authResponseDto.setJwt(jwtService.generateToken(user));
        return authResponseDto;
    }
}
