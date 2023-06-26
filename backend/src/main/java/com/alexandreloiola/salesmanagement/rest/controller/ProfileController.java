package com.alexandreloiola.salesmanagement.rest.controller;

import com.alexandreloiola.salesmanagement.rest.dto.ProfileDto;
import com.alexandreloiola.salesmanagement.rest.form.*;
import com.alexandreloiola.salesmanagement.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/register")
    public ResponseEntity<ProfileDto> registerProfile(
            @Valid @RequestBody ProfileRegisterForm profileRegisterForm
    ) {
        ProfileDto profileDto = profileService.registerProfile(profileRegisterForm);
        return ResponseEntity.ok().body(profileDto);
    }

    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAllProfiles() {
        List<ProfileDto> profileDtoList = profileService.getAllProfile();
        return ResponseEntity.ok().body(profileDtoList);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable("cpf") String cpf) {
        ProfileDto profileDto = profileService.getProfileByCpf(cpf);
        return ResponseEntity.ok().body(profileDto);
    }

    @PostMapping
    public ResponseEntity<ProfileDto> insertProfile(@Valid @RequestBody ProfileForm profileForm) {
        ProfileDto profileDto = profileService.insertProfile(profileForm);
        return ResponseEntity.ok().body(profileDto);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ProfileDto> updateProfile(
            @PathVariable("cpf") String cpf,
            @Valid @RequestBody ProfileUpdateForm profileUpdateForm
    ) {
        ProfileDto profileDto = profileService.updateProfile(cpf, profileUpdateForm);
        return ResponseEntity.ok().body(profileDto);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteProfile(@PathVariable("cpf") String cpf) {
        profileService.deleteProfile(cpf);
        return ResponseEntity.noContent().build();
    }
}
