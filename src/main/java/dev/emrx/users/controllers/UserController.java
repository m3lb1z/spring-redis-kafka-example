package dev.emrx.users.controllers;

import dev.emrx.users.entities.User;
import dev.emrx.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


//    @GetMapping
//    public ResponseEntity<List<User>> findAllUsers() {
//        return ResponseEntity.ok(userService.findAllUsers());
//    }

    @GetMapping
    public ResponseEntity<Page<User>> findAllUsers(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return ResponseEntity.ok(userService.findAllUsers(page, size));
    }

    @GetMapping("/usernames")
    public ResponseEntity<Page<String>> findAllUsernames(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return ResponseEntity.ok(userService.findAllUsernames(page, size));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }

    @PostMapping("/auth")
    public ResponseEntity<User> authenticationUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.authenticationUser(user.getUsername(), user.getPassword()));
    }

}

