package com.duxchallenge.apifootballteams.service;

import com.duxchallenge.apifootballteams.ApiequiposApplicationTests;
import com.duxchallenge.apifootballteams.data.dto.TeamDto;
import com.duxchallenge.apifootballteams.data.mapper.TeamMapper;
import com.duxchallenge.apifootballteams.data.model.Team;
import com.duxchallenge.apifootballteams.exception.InvalidTeamDtoException;
import com.duxchallenge.apifootballteams.exception.TeamNotFoundException;
import com.duxchallenge.apifootballteams.repository.ITeamRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ApiequiposApplicationTests.class)
@RunWith(SpringRunner.class)
class TeamServiceTest {

    @InjectMocks
    private TeamService teamService;
    @Mock
    private TeamMapper mapper;
    @Mock
    private ITeamRepository repository            ;
    private static Team team1, team2;
    private static TeamDto teamDto1, teamDto2;
    private static List<Team> teams;

    @BeforeAll
    public static void init(){
    buildMocks();
    }

    private static void buildMocks() {
    team1 = new Team();
    team1.setId(1);
    team1.setName("Real Madrid");
    team1.setLeague("LaLiga");
    team1.setCountry("Spain");

    team2 = new Team();
    team2.setId(2);
    team2.setName("Barcelona");
    team2.setLeague("LaLiga");
    team2.setCountry("Spain");

    teamDto1 = new TeamDto();
    teamDto1.setId(1);
    teamDto1.setName("Real Madrid");
    teamDto1.setLeague("LaLiga");
    teamDto1.setCountry("Spain");

    teamDto2 = new TeamDto();
    teamDto2.setId(2);
    teamDto2.setName("Barcelona");
    teamDto2.setLeague("LaLiga");
    teamDto2.setCountry("Spain");

    teams = List.of(team1, team2);
    }

    @Test
    @DisplayName("Get All Teams")
    public void whenGetAllTeamsReturnAllTheTeams() {
        when(repository.findAll()).thenReturn(teams);
        when(mapper.toDto(team1)).thenReturn(teamDto1);
        when(mapper.toDto(team2)).thenReturn(teamDto2);
        List<TeamDto> result = teamService.getAllTeams();

        assertAll(
                () -> assertEquals(result.get(0).getId(),team1.getId()),
                () -> assertEquals(result.get(0).getName(),team1.getName()),
                () -> assertEquals(result.get(0).getLeague(),team1.getLeague()),
                () -> assertEquals(result.get(0).getCountry(),team1.getCountry()),
                () -> assertEquals(result.get(1).getId(),team2.getId()),
                () -> assertEquals(result.get(1).getName(),team2.getName()),
                () -> assertEquals(result.get(1).getLeague(),team2.getLeague()),
                () -> assertEquals(result.get(1).getCountry(),team2.getCountry())
        );
    }

    @Test
    @DisplayName("Given an id of an existent team then return the team")
    void givenAnIdWhenGetTeamByIdThenReturnTheTeam() {
        when(repository.findById(1)).thenReturn(Optional.of(team1));
        when(mapper.toDto(team1)).thenReturn(teamDto1);

        TeamDto result = teamService.getTeamById(1);

        assertAll(
                () -> assertEquals(result.getId(),team1.getId()),
                () -> assertEquals(result.getName(),team1.getName()),
                () -> assertEquals(result.getLeague(),team1.getLeague()),
                () -> assertEquals(result.getCountry(),team1.getCountry())
        );
    }

    @Test
    @DisplayName("Given a id of an unexistent team then throw team not found exception")
    void givenAIdWhenGetTeamByIdThenThrowTeamNotFoundException() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(TeamNotFoundException.class, () -> teamService.getTeamById(1));
    }

    @Test
    @DisplayName("Given a name of an existent team then return the team")
    void givenANameWhenGetTeamByNameThenReturnTheTeam() {
        when(repository.findTeamByName("Real Madrid")).thenReturn(Optional.of(team1));
        when(mapper.toDto(team1)).thenReturn(teamDto1);

        TeamDto result = teamService.getTeamByName("Real Madrid");

        assertAll(
                () -> assertEquals(result.getId(),team1.getId()),
                () -> assertEquals(result.getName(),team1.getName()),
                () -> assertEquals(result.getLeague(),team1.getLeague()),
                () -> assertEquals(result.getCountry(),team1.getCountry())
        );
    }

    @Test
    @DisplayName("Given a team then save it")
    void givenATeamThenSaveIt() {
        when(mapper.fromDto(teamDto1)).thenReturn(team1);
        when(mapper.toDto(team1)).thenReturn(teamDto1);
        when(repository.save(team1)).thenReturn(team1);

        TeamDto result = teamService.saveTeam(teamDto1);

        assertAll(
                () -> assertEquals(result.getId(),team1.getId()),
                () -> assertEquals(result.getName(),team1.getName()),
                () -> assertEquals(result.getLeague(),team1.getLeague()),
                () -> assertEquals(result.getCountry(),team1.getCountry())
        );
    }

    @Test
    @DisplayName("Given a team that is wrong then throw invalid team dto exception")
    void givenATeamThatIsWrongWhenSaveTeamThenThrowInvalidTeamDtoException() {
        teamDto1.setLeague("");

        assertThrows(InvalidTeamDtoException.class, () -> teamService.saveTeam(teamDto1));
    }


    @Test
    @DisplayName("Given a team then update it")
    void givenATeamWhenUpdateThenUpdateIt() {
        when(repository.findById(anyInt())).thenReturn(Optional.of(team1));
        when(mapper.toDto(any())).thenReturn(teamDto1);

        TeamDto result = teamService.updateTeam(1, teamDto1);

        assertAll(
                () -> assertEquals(result.getId(),team1.getId()),
                () -> assertEquals(result.getName(),team1.getName()),
                () -> assertEquals(result.getLeague(),team1.getLeague()),
                () -> assertEquals(result.getCountry(),team1.getCountry())
        );
    }

    @Test
    @DisplayName("Given an id of an existent team then throw team not found exception")
    void givenAnIdWhenUpdateTeamThenThrowTeamNotFoundException() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(TeamNotFoundException.class, () -> teamService.updateTeam(1, teamDto1));
    }

    @Test
    @DisplayName("Given an id of an existent team then delete it")
    void givenAnIdWhenDeleteTeamThenDeleteIt() {
        when(repository.existsById(1)).thenReturn(true);
        teamService.deleteTeam(1);
        verify(repository).deleteById(any());
    }

    @Test
    @DisplayName("Given an id of an unexistent team then throw team not found exception")
    void givenAnIdWhenDeleteTeamThenThrowTeamNotFoundException() {
        when(repository.existsById(1)).thenReturn(false);
        assertThrows(TeamNotFoundException.class, () -> teamService.deleteTeam(1));
    }
}