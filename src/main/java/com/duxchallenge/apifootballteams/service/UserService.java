package com.duxchallenge.apifootballteams.service;

import com.duxchallenge.apifootballteams.data.dto.AuthenticationRequestDto;
import com.duxchallenge.apifootballteams.data.enums.Role;
import com.duxchallenge.apifootballteams.data.model.User;
import com.duxchallenge.apifootballteams.repository.IUserRepository;
import com.duxchallenge.apifootballteams.service.iservice.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(AuthenticationRequestDto request) {
        var user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(Role.USER);

        userRepository.save(user);
        return user;
    }

    @Override
    public User login(AuthenticationRequestDto request) {
        return userRepository.findByUsername(request.getUsername()).orElseThrow();
    }
}
