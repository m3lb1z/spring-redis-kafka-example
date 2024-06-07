package dev.emrx.users.controllers;

import dev.emrx.users.entities.Address;
import dev.emrx.users.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/{userId}/profiles/{profileId}/addresses")
public class AddressesController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> findAddressesByProfileIdAndUserId(
              @PathVariable("userId") Integer userId,
              @PathVariable("profileId") Integer profileId) {
        return ResponseEntity.ok(addressService.findAddressesByProfileIdAndUserId(userId, profileId));
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(
            @PathVariable("userId") Integer userId,
            @PathVariable("profileId") Integer profileId,
            @RequestBody Address address) {
        return ResponseEntity.ok(addressService.createAddress(userId, profileId, address));
    }

}
