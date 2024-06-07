package dev.emrx.users.services;

import dev.emrx.users.entities.Profile;
import dev.emrx.users.entities.Role;
import dev.emrx.users.entities.User;
import dev.emrx.users.entities.UserInRole;
import dev.emrx.users.repositories.RoleRepository;
import dev.emrx.users.repositories.UserInRoleRepository;
import dev.emrx.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RoleInUsersService {

    @Autowired
    private UserInRoleRepository userInRoleRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public UserInRole assignUserToRole(Integer roleId, Integer userId) {
        Optional<Role> searchedRole = roleRepository.findById(roleId);
        Optional<User> searchedUser = userRepository.findById(userId);

        if(searchedRole.isPresent() && searchedUser.isPresent()) {
            User user = searchedUser.get();
            Role role = searchedRole.get();
            UserInRole userInRole = new UserInRole();
            userInRole.setRole(role);
            userInRole.setUser(user);
            userInRoleRepository.save(userInRole);

            return userInRoleRepository.save(userInRole);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role %d or User %d not found".formatted(roleId, userId));
        }
    }

    public List<User> getAllUsersInRole(Integer roleId) {
        return userInRoleRepository.findAllUsersInRole(roleId);
    }
}
