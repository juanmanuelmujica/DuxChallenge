package com.duxchallenge.apifootballteams.service;

import com.duxchallenge.apifootballteams.data.dto.TeamDto;
import com.duxchallenge.apifootballteams.data.mapper.TeamMapper;
import com.duxchallenge.apifootballteams.data.model.Team;
import com.duxchallenge.apifootballteams.exception.InvalidTeamDtoException;
import com.duxchallenge.apifootballteams.exception.TeamNotFoundException;
import com.duxchallenge.apifootballteams.repository.ITeamRepository;
import com.duxchallenge.apifootballteams.service.iservice.ITeamService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
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
    public TeamDto getTeamById(int id) throws TeamNotFoundException {
        return mapper.toDto(findTeamById(id));
    }

    @Override
    public TeamDto getTeamByName(String name) throws TeamNotFoundException {
        return mapper.toDto(repository
                .findTeamByName(name)
                .orElseThrow(TeamNotFoundException::new));
    }

    @Override
    public TeamDto saveTeam(TeamDto teamDto){
        if(isValidTeamDto(teamDto)){
            return mapper.toDto(repository.save(mapper.fromDto(teamDto)));
        } else {
            throw new InvalidTeamDtoException();
        }
    }

    @Override
    public TeamDto updateTeam(int id, TeamDto teamDto) {
        Team team = findTeamById(id);
        setNewValuesToTeam(team,teamDto);
        return mapper.toDto(repository.save(team));
    }

    @Override
    public void deleteTeam(int id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        } else {
            throw new TeamNotFoundException();
        }
    }

    private void setNewValuesToTeam(Team team, TeamDto teamDto) {
        team.setName(teamDto.getName());
        team.setLeague(teamDto.getLeague());
        team.setCountry(teamDto.getCountry());
    }

    private Team findTeamById(int id) throws TeamNotFoundException{
        return repository.findById(id).orElseThrow(TeamNotFoundException::new);
    }

    private boolean isValidTeamDto(TeamDto teamDto) {
        Validator validator = Validation.buildDefaultValidatorFactory()
                                        .getValidator();
        Set<ConstraintViolation<TeamDto>> violations = validator.validate(teamDto);
        return violations.isEmpty();
    }
}
