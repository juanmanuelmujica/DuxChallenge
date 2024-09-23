package com.duxchallenge.apifootballteams.repository;

import com.duxchallenge.apifootballteams.data.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITeamRepository extends JpaRepository<Team,Integer>{

    Optional<Team> findTeamByName(String name);
}
