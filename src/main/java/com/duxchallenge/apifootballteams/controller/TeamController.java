package com.duxchallenge.apifootballteams.controller;

import com.duxchallenge.apifootballteams.data.dto.TeamDto;
import com.duxchallenge.apifootballteams.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("equipos")
public class TeamController {
    @Autowired
    private TeamService service;

    @GetMapping
    public ResponseEntity<?> getAllTeams() {
        return ResponseEntity.ok(service.getAllTeams());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTeamById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(service.getTeamById(id));
    }

    @GetMapping("buscar")
    public ResponseEntity<?> getTeamById(@RequestParam(name = "nombre") String name) {
        return ResponseEntity.ok(service.getTeamByName(name));
    }

    @PostMapping
    public ResponseEntity<?> saveTeam(@Valid @RequestBody TeamDto teamDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateTeam(@PathVariable(name = "id") int id, @RequestBody TeamDto teamDto) {
        return ResponseEntity.ok(service.updateTeam(id, teamDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable(name = "id") int id) {
        service.deleteTeam(id);
        return ResponseEntity
                .noContent()
                .build();
    }


}
