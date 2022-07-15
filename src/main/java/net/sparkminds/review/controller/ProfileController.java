package net.sparkminds.review.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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

import com.lowagie.text.DocumentException;

import lombok.RequiredArgsConstructor;
import net.sparkminds.review.dto.request.ProfileRequestDto;
import net.sparkminds.review.entity.Profile;
import net.sparkminds.review.export.ProfilePDFExporter;
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

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Profile> listUsers = profileService.getAllProfile();
         
        ProfilePDFExporter exporter = new ProfilePDFExporter(listUsers);
        exporter.export(response);
    }

    @PostMapping
    public ResponseEntity<Profile> addNewProfile(@Valid @RequestBody ProfileRequestDto addProfileRequestDto) {
        return ResponseEntity.ok(profileService.addNewProfile(addProfileRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id,
            @Valid @RequestBody ProfileRequestDto profileRequestDto) {
        profileService.updateProfile(id, profileRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
