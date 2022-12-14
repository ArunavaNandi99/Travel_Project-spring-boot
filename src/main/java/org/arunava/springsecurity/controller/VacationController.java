package org.arunava.springsecurity.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.LongFunction;

import org.arunava.springsecurity.entity.Vacation;
import org.arunava.springsecurity.exception.UserNotFoundException;
import org.arunava.springsecurity.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import antlr.ActionTransInfo;

@RestController
public class VacationController {
	
	@Autowired
	private VacationRepository vacationRepository;
	
	@PostMapping("/vacation")
	public ResponseEntity<?> saveVacation(@RequestBody Vacation vacation){
		Vacation saveVacation = vacationRepository.save(vacation);
		
		return new ResponseEntity<>(saveVacation,HttpStatus.CREATED);
	}
	
	@GetMapping("/vacation")
	public ResponseEntity<?>getVacations(){
		List<Vacation> vacations = vacationRepository.findAll();
		return new ResponseEntity<>(vacations,HttpStatus.OK);
	}
	
	@GetMapping("/vacation/{id}")
	public ResponseEntity<?> getVaacationById(@PathVariable Long id){
		Vacation vacation = vacationRepository.findById(id).get();
		
		if(vacation == null) {
			throw new UserNotFoundException("user not found by id :"+id);
		}
		return new ResponseEntity<>(vacation,HttpStatus.OK);
		
	}
	
	public ResponseEntity<?> deleteVcationById(@PathVariable Long id){
		Vacation vacation = vacationRepository.findById(id).get();
		
		if(vacation == null) {
			throw new UserNotFoundException("id : "+id);
		}
		vacationRepository.deleteById(id);
		return new ResponseEntity<>("delete successfullt",HttpStatus.OK);
		
	}

	
}
