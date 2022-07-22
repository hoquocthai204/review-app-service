package net.sparkminds.review.service;

import org.springframework.http.HttpHeaders;

import net.sparkminds.review.dto.request.LoginRequestDto;
import net.sparkminds.review.dto.response.LoginResponseDto;

public interface AuthenticationService {
    LoginResponseDto login(LoginRequestDto dto) throws Exception;

    void logout(HttpHeaders headers);
}
