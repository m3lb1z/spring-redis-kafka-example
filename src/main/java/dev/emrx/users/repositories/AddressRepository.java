package dev.emrx.users.repositories;

import dev.emrx.users.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {

}
