package com.muscu.carnetMusculation.controllers;

import java.security.Principal;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muscu.carnetMusculation.entities.User;
import com.muscu.carnetMusculation.services.impl.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = { "*" })
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@RequestMapping("/login")
	public boolean login(@RequestBody User user) {
		return userService.findByUsernameOrEmail(user);
	}
	
	@RequestMapping("/user")
	public Principal user(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization")
				.substring("Basic".length()).trim();
		return () -> new String(Base64.getDecoder()
				.decode(authToken)).split(":")[0];
	}
}
