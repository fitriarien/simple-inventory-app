package com.fitriarien.inventory.controller;

import com.fitriarien.inventory.config.JwtBlacklistFilter;
import com.fitriarien.inventory.config.JwtTokenUtil;
import com.fitriarien.inventory.entity.User;
import com.fitriarien.inventory.model.*;
import com.fitriarien.inventory.repository.UserRepository;
import com.fitriarien.inventory.service.AuthService;
import com.fitriarien.inventory.service.TokenBlacklistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
public class AuthController {
	@Autowired
	private AuthService authService;
	@Autowired
	private TokenBlacklistService tokenBlacklistService;
	@Autowired
	private JwtBlacklistFilter jwtBlacklistFilter;

	@PostMapping(
			path = "/api/users",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public WebResponse<UserResponse> register(@RequestBody RegisterUserRequest request) {
		UserResponse userResponse = authService.register(request);
		return WebResponse.<UserResponse>builder().data(userResponse).build();
	}

	@PostMapping(
			path = "/api/auth/login",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public WebResponse<TokenResponse> login(@RequestBody LoginUserRequest request) throws Exception {
		TokenResponse tokenResponse = authService.login(request);
		return WebResponse.<TokenResponse>builder().data(tokenResponse).build();
	}

	@PostMapping(
			path = "/api/auth/logout",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public WebResponse<String> logout(HttpServletRequest request) {
		String token = jwtBlacklistFilter.extractToken(request);
		tokenBlacklistService.addToBlacklist(token);
		return WebResponse.<String>builder().data("success").build();
	}
}