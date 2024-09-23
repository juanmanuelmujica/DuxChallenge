package com.duxchallenge.apifootballteams.service.iservice;

import com.duxchallenge.apifootballteams.data.dto.TeamDto;
import com.duxchallenge.apifootballteams.exception.TeamNotFoundException;

import java.util.List;

public interface ITeamService {
    List<TeamDto> getAllTeams();

    TeamDto getTeamById(int id);

    TeamDto getTeamByName(String name);

    TeamDto saveTeam(TeamDto team);

    TeamDto updateTeam(int id, TeamDto team);

    void deleteTeam(int id) throws TeamNotFoundException;
}
