package net.sparkminds.review.service;

import javax.servlet.http.HttpServletRequest;

import net.sparkminds.review.dto.request.LoginRequestDto;
import net.sparkminds.review.dto.response.LoginResponseDto;

public interface LoginService {
    LoginResponseDto login(LoginRequestDto dto) throws Exception;

    void logout(HttpServletRequest request);
}
