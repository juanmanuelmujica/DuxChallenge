package com.duxchallenge.apifootballteams.data.dto;


import jakarta.validation.constraints.NotBlank;

public class TeamDto {
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    private String league;
    @NotBlank
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
