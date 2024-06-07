package dev.emrx.users.services;

import dev.emrx.users.entities.Address;
import dev.emrx.users.entities.Profile;
import dev.emrx.users.repositories.AddressRepository;
import dev.emrx.users.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAddressesByProfileIdAndUserId(Integer userId, Integer profileId) {
        return addressRepository.findAllByProfile(userId, profileId);
    }

    public Address createAddress(Integer userId, Integer profileId, Address address) {
        Optional<Profile> result = profileRepository.findByUserIdAndProfileId(userId, profileId);

        if (result.isPresent()) {
            address.setProfile(result.get());
            return addressRepository.save(address);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile %d and User %d not found".formatted(profileId, userId));
        }

    }
}
