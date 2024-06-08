package dev.emrx.users.controllers;

import dev.emrx.users.entities.Role;
import dev.emrx.users.entities.User;
import dev.emrx.users.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private static final Logger log = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Name {}", authentication.getName());
        log.info("Principal {}", authentication.getPrincipal());
        log.info("Credentials {}", authentication.getCredentials());
        log.info("Roles {}", authentication.getAuthorities().toString());

        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/roleName/{roleName}/users")
    public ResponseEntity<List<User>> getUsersByRoleName(@PathVariable("roleName") String roleName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Name {}", authentication.getName());
        log.info("Principal {}", authentication.getPrincipal());
        log.info("Credentials {}", authentication.getCredentials());
        log.info("Roles {}", authentication.getAuthorities().toString());

        return ResponseEntity.ok(roleService.findAllUsersByRoleName(roleName));
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

}
