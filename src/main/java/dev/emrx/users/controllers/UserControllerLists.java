package dev.emrx.users.controllers;

import dev.emrx.users.models.UserList;
import dev.emrx.users.services.UserServiceLists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users-lists")
public class UserControllerLists {

    @Autowired
    private UserServiceLists userService;

    @GetMapping
    public ResponseEntity<List<UserList>> getUsers() {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserList>> findUsers(@RequestParam("startWith") String startWith) {
        return ResponseEntity.ok().body(userService.findUsersStartWith(startWith));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserList> getUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.findUserByUsername(username));
    }

    @PostMapping
    public ResponseEntity<UserList> createUser(@RequestBody UserList user, UriComponentsBuilder uriBuilder) {
        URI url = uriBuilder.path("/users/{username}").buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity.created(url).body(userService.createUser(user));
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserList> updateUser(@PathVariable String username, @RequestBody UserList user) {
        return ResponseEntity.ok().body(userService.updateUser(username, user));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

}
