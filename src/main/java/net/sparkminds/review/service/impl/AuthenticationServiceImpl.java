package net.sparkminds.review.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.review.dto.request.LoginRequestDto;
import net.sparkminds.review.dto.response.LoginResponseDto;
import net.sparkminds.review.service.AuthenticationService;
import net.sparkminds.review.service.SendLogService;
import net.sparkminds.review.util.JwtTokenUtil;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl detailsServiceImpl;
    private final RedisTemplate<String, String> redisTemplate;
    private final SendLogService sendLogService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public LoginResponseDto login(LoginRequestDto dto) throws Exception {

        authenticate(dto.getEmail(), dto.getPassword());
        final UserDetails userDetails = detailsServiceImpl.loadUserByUsername(dto.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        sendLogService.sendingMessage(dto.getEmail(), "Logged In");
        return new LoginResponseDto(token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Override
    public void logout(HttpHeaders headers) {
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        token = token.split(" ")[1];
        redisTemplate.opsForValue().set(token, token);
        redisTemplate.expire(token, 8, TimeUnit.HOURS);
        sendLogService.sendingMessage(jwtTokenUtil.getUsernameFromToken(token), "Log out");
    }

}
