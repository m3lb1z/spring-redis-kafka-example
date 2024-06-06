package dev.emrx.users;

import com.github.javafaker.Faker;
import dev.emrx.users.entities.User;
import dev.emrx.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersAppApplication implements ApplicationRunner {

    @Autowired
    private Faker faker;

    @Autowired
    private UserRepository repository;


    public static void main(String[] args) {
        SpringApplication.run(UsersAppApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int ind = 0; ind < 200; ind++) {
            User user = new User();
            user.setUsername(faker.name().username());
            user.setPassword(faker.dragonBall().character());
            user.setProfile(null);
            repository.save(user);
        }
    }
}
