package dev.emrx.users.controllers;


import dev.emrx.users.entities.User;
import dev.emrx.users.entities.UserInRole;
import dev.emrx.users.services.RoleInUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles/{roleId}/users")
public class RoleInUsersController {

    @Autowired
    private RoleInUsersService roleInUsersService;


    @GetMapping
    public ResponseEntity<List<User>> getUsersInRole(@PathVariable("roleId") Integer roleId) {
        return ResponseEntity.ok(roleInUsersService.getAllUsersInRole(roleId));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<UserInRole> assignUserToRole(
            @PathVariable("roleId") Integer roleId,
            @PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(roleInUsersService.assignUserToRole(roleId, userId));
    }

}
