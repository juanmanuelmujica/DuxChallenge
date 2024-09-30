package com.duxchallenge.apifootballteams.service.iservice;

import com.duxchallenge.apifootballteams.data.dto.AuthenticationRequestDto;
import com.duxchallenge.apifootballteams.data.model.User;

public interface IUserService {
    User register(AuthenticationRequestDto request);
    User login(AuthenticationRequestDto request);
}
