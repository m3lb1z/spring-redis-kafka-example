package dev.emrx.users.repositories;

import dev.emrx.users.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {

    @Query("SELECT p FROM Profile p WHERE p.user.id = :userId AND p.id = :profileId")
    Optional<Profile> findByUserIdAndProfileId(Integer userId, Integer profileId);
}
