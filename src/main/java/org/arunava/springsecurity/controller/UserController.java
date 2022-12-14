package org.arunava.springsecurity.controller;

import org.arunava.springsecurity.dto.LoginDto;
import org.arunava.springsecurity.entity.User;
import org.arunava.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<?> register (@RequestBody User user){
		User userDetails = new User(); 
		userDetails.setFname(user.getFname());
		userDetails.setLname(user.getLname());
		userDetails.setEmail(user.getEmail());
		userDetails.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User saveUser = userRepository.save(userDetails);
		return  new ResponseEntity<>(saveUser,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) throws Exception{
		Authentication authentication;
		try {
			 authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (Exception e) {
			throw new Exception("Credentials Invalid");
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "profilePage";	
	}

}
