package com.duxchallenge.apifootballteams.data.mapper;

import com.duxchallenge.apifootballteams.data.dto.TeamDto;
import com.duxchallenge.apifootballteams.data.model.Team;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    private final ObjectMapper objectMapper;

    public TeamMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Team fromDto(TeamDto team){
        return objectMapper.convertValue(team, Team.class);
    }

    public TeamDto toDto(Team team){
        return objectMapper.convertValue(team, TeamDto.class);
    }
}
