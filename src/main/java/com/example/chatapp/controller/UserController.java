package com.example.chatapp.controller;

import com.example.chatapp.model.User;
import com.example.chatapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Anonymous login: creates or returns existing user
    @PostMapping("/join")
    public User joinChat(@RequestParam String username) {
        return userService.findByUsername(username)
                .orElseGet(() -> userService.save(User.builder()
                        .username(username)
                        .chattedWithUserIds(new ArrayList<>())
                        .build()));
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}