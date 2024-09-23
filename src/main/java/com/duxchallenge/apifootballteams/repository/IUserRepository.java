package com.duxchallenge.apifootballteams.repository;

import com.duxchallenge.apifootballteams.data.model.User;
import com.duxchallenge.apifootballteams.data.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, UserId> {
}
