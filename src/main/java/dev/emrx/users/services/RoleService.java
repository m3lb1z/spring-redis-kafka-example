package dev.emrx.users.services;

import dev.emrx.users.configs.annotation.IsAdminOrUserRule;
import dev.emrx.users.entities.Role;
import dev.emrx.users.entities.User;
import dev.emrx.users.repositories.RoleRepository;
import dev.emrx.users.repositories.UserInRoleRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
//@IsAdminOrUserRule
public class RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleService.class);
    @Autowired
    private RoleRepository repository;

    @Autowired
    private UserInRoleRepository userInRoleRepository;

    @PostConstruct
    public void init() {
        repository.save(new Role("ADMIN"));
        repository.save(new Role("USER"));
        repository.save(new Role("SUPPORT"));
    }

    public List<Role> getAllRoles() {
        return repository.findAll();
    }

    public Role createRole(Role input) {
        return repository.save(input);
    }

    public Role updateRole(Integer roleId, Role input) {
        Optional<Role> updated = repository.findById(roleId);
        if(updated.isPresent()) {
            return repository.save(input);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role id %d doesn't exits.".formatted(roleId));
        }
    }

    public void deleteRole(Integer roleId) {
        Optional<Role> deleted = repository.findById(roleId);
        if(deleted.isPresent()) {
            repository.deleteById(roleId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role id %d doesn't exits.".formatted(roleId));
        }
    }

    @IsAdminOrUserRule
    public List<User> findAllUsersByRoleName(String roleName) {
        log.info("Finding all users by role name {}", roleName);
        return userInRoleRepository.findAllUsersByRoleName(roleName);
    }
}
