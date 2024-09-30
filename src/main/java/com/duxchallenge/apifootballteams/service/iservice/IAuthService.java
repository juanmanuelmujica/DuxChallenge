package com.duxchallenge.apifootballteams.service.iservice;

import com.duxchallenge.apifootballteams.data.dto.AuthResponseDto;
import com.duxchallenge.apifootballteams.data.dto.AuthenticationRequestDto;

public interface IAuthService {
    AuthResponseDto login(AuthenticationRequestDto request);
}
