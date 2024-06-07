package dev.emrx.users.controllers;

import dev.emrx.users.entities.User;
import dev.emrx.users.services.UserService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "The User API")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Timed("get.users")
    @Operation(summary = "Get all users", description = "Get all users from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Users not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Page<User>> findAllUsers(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return ResponseEntity.ok(userService.findAllUsers(page, size));
    }

    @GetMapping("/usernames")
    @Operation(summary = "Get all usernames", description = "Get all usernames from the database")
    public ResponseEntity<Page<String>> findAllUsernames(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return ResponseEntity.ok(userService.findAllUsernames(page, size));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by ID", description = "Get a specific user by their ID")
    public ResponseEntity<User> findUserById(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @DeleteMapping("/username/{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable("username") String username) {
        userService.deleteUserByUsername(username);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Get user by username", description = "Get a specific user by their username")
    public ResponseEntity<User> findUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }

    @PostMapping("/auth")
    @Operation(summary = "Authenticate user", description = "Authenticate a user with their username and password")
    public ResponseEntity<User> authenticationUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.authenticationUser(user.getUsername(), user.getPassword()));
    }

}

