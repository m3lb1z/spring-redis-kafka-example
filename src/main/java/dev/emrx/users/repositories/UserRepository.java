package dev.emrx.users.repositories;

import dev.emrx.users.entities.Profile;
import dev.emrx.users.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);

    @Query("SELECT u.username FROM User u")
    Page<String> findUsernames(Pageable paginacion);

}
