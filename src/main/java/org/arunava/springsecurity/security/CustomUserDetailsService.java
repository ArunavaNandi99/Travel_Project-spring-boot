package org.arunava.springsecurity.security;

import java.util.ArrayList;

import org.arunava.springsecurity.entity.User;
import org.arunava.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	
		User user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("user not found"));
		
		return new  org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), new ArrayList<>());
	}


}
