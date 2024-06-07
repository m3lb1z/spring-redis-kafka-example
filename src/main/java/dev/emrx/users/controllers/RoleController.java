package dev.emrx.users.controllers;

import dev.emrx.users.entities.Role;
import dev.emrx.users.entities.User;
import dev.emrx.users.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role input, UriComponentsBuilder builder) {
        Role created = roleService.createRole(input);
        URI url = builder.path("/roles/{id}").buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(url).body(created);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable("roleId") Integer roleId, @RequestBody Role input) {
        return ResponseEntity.ok(roleService.updateRole(roleId, input));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable("roleId") Integer roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/roleName/{roleName}/users")
    public ResponseEntity<List<User>> getUsersByRoleName(@PathVariable("roleName") String roleName) {
        return ResponseEntity.ok(roleService.findAllUsersByRoleName(roleName));
    }

}
