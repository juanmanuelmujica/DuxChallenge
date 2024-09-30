package com.duxchallenge.apifootballteams.service.iservice;

import com.duxchallenge.apifootballteams.data.dto.AuthenticationRequestDto;
import com.duxchallenge.apifootballteams.data.model.User;

public interface IUserService {
    User login(AuthenticationRequestDto request);
}
