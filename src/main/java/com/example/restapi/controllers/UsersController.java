package com.example.restapi.controllers;

import com.example.restapi.models.User;
import com.example.restapi.repositories.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.bind.ValidationException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
public class UsersController {

    final private UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostMapping("/user")
    public Boolean create(@RequestBody Map<String, String> body) throws NoSuchAlgorithmException, ValidationException {
        String username = body.get("username");

        if (usersRepository.existsByUsername(username)){
            throw new ValidationException("Username already existed");
        }
        String password = body.get("password");
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        String fullname = body.get("fullname");
        usersRepository.save(new User(username, encodedPassword, fullname));

        return true;
    }
}
