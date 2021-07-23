package com.luv2code.springboot.cruddemo.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.luv2code.springboot.cruddemo.exception.myexceptions.*;
import com.luv2code.springboot.cruddemo.entity.ERole;
import com.luv2code.springboot.cruddemo.entity.Role;
import com.luv2code.springboot.cruddemo.entity.User;
import com.luv2code.springboot.cruddemo.dao.RoleRepository;
import com.luv2code.springboot.cruddemo.dao.UserRepository;
import com.luv2code.springboot.cruddemo.security.jwt.JwtUtils;
import com.luv2code.springboot.cruddemo.dao.UserDetailsImpl;
import com.luv2code.springboot.cruddemo.rest.request.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserRestController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public JwtResponse authenticateUser(@RequestBody User user)  {
        
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return new JwtResponse(jwt, 
								userDetails.getId(), 
								userDetails.getUsername(), 
								userDetails.getEmail(), 
								roles);
	}

	@PostMapping("/signup")
	public User registerUser( @RequestBody SignupRequest signUpRequest) {
		System.out.println(signUpRequest);
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			throw new RegisterException("Username Already Exist");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new RegisterException("Email Already Exist");
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() ->  new RegisterException("No such Role Exist"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RegisterException("No such Role Exist"));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RegisterException("No such Role Exist"));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RegisterException("No such Role Exist"));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return user;
	}
}