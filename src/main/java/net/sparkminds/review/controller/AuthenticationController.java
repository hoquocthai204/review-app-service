package net.sparkminds.review.controller;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.review.dto.request.LoginRequestDto;
import net.sparkminds.review.dto.response.LoginResponseDto;
import net.sparkminds.review.service.AuthenticationService;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto dto) throws Exception {
        return ResponseEntity.ok(authenticationService.login(dto));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader HttpHeaders headers) {
        authenticationService.logout(headers);
        return ResponseEntity.noContent().build();
    }
}
