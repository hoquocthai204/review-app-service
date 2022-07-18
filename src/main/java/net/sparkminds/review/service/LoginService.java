package net.sparkminds.review.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.review.config.JwtTokenUtil;
import net.sparkminds.review.dto.request.LoginRequestDto;
import net.sparkminds.review.dto.response.LoginResponseDto;
import net.sparkminds.review.service.impl.UserDetailsServiceImpl;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsServiceImpl detailsServiceImpl;

    public LoginResponseDto login(LoginRequestDto dto) throws Exception {

        authenticate(dto.getEmail(), dto.getPassword());
        final UserDetails userDetails = detailsServiceImpl.loadUserByUsername(dto.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
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

}
