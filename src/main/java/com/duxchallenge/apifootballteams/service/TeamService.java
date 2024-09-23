package com.duxchallenge.apifootballteams.service;

import com.duxchallenge.apifootballteams.data.dto.TeamDto;
import com.duxchallenge.apifootballteams.data.mapper.TeamMapper;
import com.duxchallenge.apifootballteams.data.model.Team;
import com.duxchallenge.apifootballteams.repository.ITeamRepository;
import com.duxchallenge.apifootballteams.service.iservice.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TeamService implements ITeamService {
    @Autowired
    private TeamMapper mapper;
    @Autowired
    private ITeamRepository repository;

    @Override
    public List<TeamDto> getAllTeams() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamDto getTeamById(int id) throws NoSuchElementException {
        return mapper.toDto(findTeamById(id));
    }

    @Override
    public TeamDto getTeamByName(String name) throws NoSuchElementException {
        return mapper.toDto(repository
                .findTeamByName(name)
                .orElseThrow());
    }

    @Override
    public TeamDto saveTeam(TeamDto teamDto) {
        return mapper.toDto(repository.save(mapper.fromDto(teamDto)));
    }

    @Override
    public TeamDto updateTeam(int id, TeamDto teamDto) {
        Team team = findTeamById(id);
        setNewValuesToTeam(team,teamDto);
        return mapper.toDto(repository.save(team));
    }

    @Override
    public void deleteTeam(int id) {
        repository.deleteById(id);
    }

    private void setNewValuesToTeam(Team team, TeamDto teamDto) {
        team.setName(teamDto.getName());
        team.setLeague(teamDto.getLeague());
        team.setCountry(teamDto.getCountry());
    }

    private Team findTeamById(int id) throws NoSuchElementException{
        return repository.findById(id).orElseThrow();
    }


}
