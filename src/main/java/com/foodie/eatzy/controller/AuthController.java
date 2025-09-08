package com.foodie.eatzy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.eatzy.dto.JwtResponse;
import com.foodie.eatzy.dto.LoginRequest;
import com.foodie.eatzy.dto.RefreshdToken;
import com.foodie.eatzy.dto.UserDto;
import com.foodie.eatzy.security.JwtService;
import com.foodie.eatzy.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private UserService userService;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
            JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword());
        authenticationManager.authenticate(authentication);

        String jwtToken = jwtService.generateToken(loginRequest.getEmail(), true);
        String refreshToken = jwtService.generateToken(loginRequest.getEmail(), false);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        UserDto userDto = userService.getUserByEmail(loginRequest.getEmail());

        JwtResponse build = JwtResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).userDto(userDto)
                .build();

        return ResponseEntity.ok(build);

    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshdToken refreshToken) {

        // if (!jwtService.isAccessToken(refreshToken.getRefreshToken())) {
        // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh
        // token");
        // }

        if (jwtService.validateToken(refreshToken.getRefreshToken())
                && jwtService.isRefreshToken(refreshToken.getRefreshToken())) {

            String usernameFromRefreshToken = jwtService.getUsername(refreshToken.getRefreshToken());
            System.out.println("......................" + usernameFromRefreshToken);
            UserDto userDto = userService.getUserByEmail(usernameFromRefreshToken);

            String accessToken = jwtService.generateToken(userDto.getEmail(), true);
            String refreToken = jwtService.generateToken(userDto.getEmail(), false);

            JwtResponse build = JwtResponse.builder().accessToken(accessToken).refreshToken(refreToken).userDto(userDto)
                    .build();
            return ResponseEntity.ok(build);

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid token");
        }
    }

}
