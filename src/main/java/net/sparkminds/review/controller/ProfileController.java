package net.sparkminds.review.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.review.dto.request.ProfileRequestDto;
import net.sparkminds.review.entity.Profile;
import net.sparkminds.review.service.ProfileService;

@RestController
@RequestMapping("/api/profiles")
@Validated
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfile() {
        return ResponseEntity.ok(profileService.getAllProfile());
    }
    
    @GetMapping("/export")
    public ResponseEntity<?> exportProfile(){
        return ResponseEntity.ok(profileService.exportProfile());
    }

    @PostMapping
    public ResponseEntity<Profile> addNewProfile(@Valid @RequestBody ProfileRequestDto addProfileRequestDto) {
        return ResponseEntity.ok(profileService.addNewProfile(addProfileRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @Valid @RequestBody ProfileRequestDto profileRequestDto) {
        profileService.updateProfile(id, profileRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
