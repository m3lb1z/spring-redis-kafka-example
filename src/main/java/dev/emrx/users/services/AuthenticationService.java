package dev.emrx.users.services;

import dev.emrx.users.entities.User;
import dev.emrx.users.entities.UserInRole;
import dev.emrx.users.repositories.UserInRoleRepository;
import dev.emrx.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInRoleRepository userInRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> searched = userRepository.findByUsername(username);

        if(searched.isPresent()) {
            User user = searched.get();
            List<UserInRole> userInRoles = userInRoleRepository.findByUser(user);
            String[] roles = userInRoles.stream().map(userInRole -> userInRole.getRole().getName()).toArray(String[]::new);
            return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .roles(roles)
                    .build();
        } else {
            throw new UsernameNotFoundException("Username %s not found".formatted(username));
        }
    }
}
