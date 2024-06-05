package dev.emrx.users.controllers;

import dev.emrx.users.models.User;
import dev.emrx.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> findUsers(@RequestParam("startWith") String startWith) {
        return ResponseEntity.ok().body(userService.findUsersStartWith(startWith));
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.findUserByUsername(username));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder uriBuilder) {
        URI url = uriBuilder.path("/users/{username}").buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity.created(url).body(userService.createUser(user));
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user) {
        return ResponseEntity.ok().body(userService.updateUser(username, user));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

}
