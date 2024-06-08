package dev.emrx.users;

import com.github.javafaker.Faker;
import dev.emrx.users.entities.Role;
import dev.emrx.users.entities.User;
import dev.emrx.users.entities.UserInRole;
import dev.emrx.users.repositories.RoleRepository;
import dev.emrx.users.repositories.UserInRoleRepository;
import dev.emrx.users.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Random;

@SpringBootApplication
public class UsersAppApplication implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(UsersAppApplication.class);
    @Autowired
    private Faker faker;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserInRoleRepository userInRoleRepository;


    public static void main(String[] args) {
        SpringApplication.run(UsersAppApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Role> roles = roleRepository.findAll();

        for (int ind = 0; ind < 20; ind++) {
            User user = new User();
            user.setUsername(faker.name().username());
            user.setPassword(faker.dragonBall().character());
            repository.save(user);

            UserInRole userInRole = new UserInRole(user, roles.get(new Random().nextInt(roles.size())));
            userInRoleRepository.save(userInRole);

            log.info("User created username %s with password %s and role %s".formatted(user.getUsername(), user.getPassword(), userInRole.getRole().getName()));
        }
    }
}
