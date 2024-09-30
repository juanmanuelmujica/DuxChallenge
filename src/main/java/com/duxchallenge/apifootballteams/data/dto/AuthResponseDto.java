package com.duxchallenge.apifootballteams.data.dto;

public class AuthResponseDto {
    public String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
