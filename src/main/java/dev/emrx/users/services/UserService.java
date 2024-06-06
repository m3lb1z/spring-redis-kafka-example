package dev.emrx.users.services;

import dev.emrx.users.entities.User;
import dev.emrx.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public Page<User> findAllUsers(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public User findUserById(Integer userId) {
        return repository
                .findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User %d not found.".formatted(userId)));
    }

    public User findUserByUsername(String username) {
        return repository
                .findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User %s not found.".formatted(username)));
    }

    public User authenticationUser(String username, String password) {
        return repository
                .findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials."));
    }

    public Page<String> findAllUsernames(Integer page, Integer size) {
        return repository.findUsernames(PageRequest.of(page, size));
    }
}
