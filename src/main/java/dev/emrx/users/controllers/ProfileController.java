package dev.emrx.users.controllers;


import dev.emrx.users.entities.Profile;
import dev.emrx.users.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users/{userId}/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;


    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getByProfileId(
            @PathVariable("userId") Integer userId,
            @PathVariable("profileId") Integer profileId) {
        return ResponseEntity.ok(profileService.getByUserIdAndProfileId(userId, profileId));
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@PathVariable Integer userId, @RequestBody Profile profile, UriComponentsBuilder uriBuilder) {
        Profile createdProfile = profileService.createProfile(userId, profile);
        URI url = uriBuilder.path("/users/{userId}/profiles/{profileId}").buildAndExpand(userId, createdProfile.getId()).toUri();

        return ResponseEntity.created(url).body(createdProfile);
    }

}
