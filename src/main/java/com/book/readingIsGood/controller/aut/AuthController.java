package com.book.readingIsGood.controller.aut;

import com.book.readingIsGood.model.auth.AuthRequest;
import com.book.readingIsGood.service.auth.CustomUserDetailService;
import com.book.readingIsGood.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/login")
    public String creteToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorret username or password", ex);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return jwt;
    }
}
