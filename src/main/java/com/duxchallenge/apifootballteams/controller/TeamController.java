package com.duxchallenge.apifootballteams.controller;

import com.duxchallenge.apifootballteams.data.dto.TeamDto;
import com.duxchallenge.apifootballteams.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("equipos")
public class TeamController {
    @Autowired
    private TeamService service;

    @Operation(summary = "Obtiene todos los equipos")
    @GetMapping
    public ResponseEntity<?> getAllTeams() {
        return ResponseEntity.ok(service.getAllTeams());
    }

    @Operation(summary = "Obtiene un equipo por su id")
    @GetMapping("{id}")
    public ResponseEntity<?> getTeamById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(service.getTeamById(id));
    }

    @Operation(summary = "Obtiene un equipo por su nombre")
    @GetMapping("buscar")
    public ResponseEntity<?> getTeamById(@RequestParam(name = "nombre") String name) {
        return ResponseEntity.ok(service.getTeamByName(name));
    }

    @Operation(summary = "Guarda un equipo")
    @PostMapping
    public ResponseEntity<?> saveTeam(@RequestBody TeamDto teamDto) throws BadRequestException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.saveTeam(teamDto));
    }

    @Operation(summary = "Actualiza un equipo por su id y se envía el objeto con los nuevos datos")
    @PutMapping("{id}")
    public ResponseEntity<?> updateTeam(@PathVariable(name = "id") int id, @RequestBody TeamDto teamDto) {
        return ResponseEntity.ok(service.updateTeam(id, teamDto));
    }

    @Operation(summary = "Elimina un equipo por su id")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable(name = "id") int id) {
        service.deleteTeam(id);
        return ResponseEntity
                .noContent()
                .build();
    }


}
