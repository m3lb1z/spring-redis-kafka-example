package dev.emrx.users.repositories;

import dev.emrx.users.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("SELECT a FROM Address a WHERE a.profile.user.id = :userId AND a.profile.id = :profileId")
    List<Address> findAllByProfile(Integer userId, Integer profileId);
}
