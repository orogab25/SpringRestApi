package com.example.restapi.repositories;

import com.example.restapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User,Long> {

    Boolean existsByUsername(String username);
    User findByUsername(String username);
}
