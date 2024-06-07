package dev.emrx.users.services;

import dev.emrx.users.entities.Profile;
import dev.emrx.users.entities.User;
import dev.emrx.users.repositories.ProfileRepository;
import dev.emrx.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;


    public Profile createProfile(Integer userId, Profile profile) {
        Optional<User> result = userRepository.findById(userId);
        if (result.isPresent()) {
            profile.setUser(result.get());
            return profileRepository.save(profile);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User %d not found".formatted(userId));
        }
    }

    public Profile getByUserIdAndProfileId(Integer userId, Integer profileId) {
        return profileRepository
                .findByUserIdAndProfileId(userId, profileId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Profile %d not found for user %d".formatted(profileId, userId)));
    }
}
