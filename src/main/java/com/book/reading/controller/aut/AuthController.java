package com.book.reading.controller.aut;

import com.book.reading.model.auth.AuthRequest;
import com.book.reading.service.auth.CustomUserDetailService;
import com.book.reading.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author senanurmacit
 * @version 1.1
 * @since 1.1
 */
@RestController
public class AuthController {

    private JwtUtil jwtUtil;
    private CustomUserDetailService userDetailsService;
    private AuthenticationManager authenticationManager;

    public AuthController(JwtUtil jwtUtil,
                          AuthenticationManager authenticationManager,
                          CustomUserDetailService customUserDetailService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = customUserDetailService;
    }

    /**
     * This method created for login process
     * When user send any request for the login. It will be check from database
     * the user is trying to connect exist in database or not
     * if user is exist in database all request will successfully pass and return valid
     * Bearer token for session
     *
     * @param authRequest
     * @return userDetails as a token
     * @throws Exception
     */
    @PostMapping("/login")
    public String creteToken(@RequestBody AuthRequest authRequest) throws Exception {

        try {
            authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorret username or password", ex);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        return jwtUtil.generateToken(userDetails);
    }
}
