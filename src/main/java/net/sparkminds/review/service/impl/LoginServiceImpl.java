package net.sparkminds.review.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.review.dto.request.LoginRequestDto;
import net.sparkminds.review.dto.response.LoginResponseDto;
import net.sparkminds.review.service.LoginService;
import net.sparkminds.review.util.JwtTokenUtil;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsServiceImpl detailsServiceImpl;

    @Override
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

    @Override
    public void logout(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, null, null);
        }
    }
}
