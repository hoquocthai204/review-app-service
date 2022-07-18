package net.sparkminds.review.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.review.dto.request.LoginRequestDto;
import net.sparkminds.review.dto.response.LoginResponseDto;
import net.sparkminds.review.service.LoginService;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
@Validated
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto dto) throws Exception {
        return ResponseEntity.ok(loginService.login(dto));
    }
    
    @GetMapping
    public ResponseEntity<?> logout(HttpServletRequest request){
        loginService.logout(request);
        return ResponseEntity.noContent().build();
    }
}
