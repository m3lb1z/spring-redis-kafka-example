package dev.emrx.users.repositories;

import dev.emrx.users.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {
}
