package dev.emrx.users.services;

import com.github.javafaker.Faker;
import dev.emrx.users.models.UserList;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceLists {

    @Autowired
    private Faker faker;

    private List<UserList> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        for (int i = 0; i < 20; i++)
            users.add(new UserList(faker.funnyName().name(), faker.name().username(), faker.internet().password()));
    }

    public List<UserList> findAllUsers() {
        return users;
    }

    public List<UserList> findUsersStartWith(String startWith) {
        if(startWith != null) {
            return users.stream().filter(user -> user.getUsername().startsWith(startWith)).toList();
        } else {
            return users;
        }
    }

    public UserList findUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User %s not found.".formatted(username)));
    }


    public UserList createUser(UserList input) {
        if(users.stream().anyMatch(user -> user.getUsername().equals(input.getUsername())))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User %s already exists.".formatted(input.getUsername()));

        users.add(input);

        return input;
    }

    public UserList updateUser(String username, UserList user) {
        UserList updated = findUserByUsername(username);
        updated.setNickname(user.getNickname());
        updated.setPassword(user.getPassword());

        return updated;
    }

    public void deleteUser(String username) {
        UserList searched = findUserByUsername(username);
        users.remove(searched);
    }
}
