package org.arunava.springsecurity.repository;

import java.util.Optional;

import org.arunava.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

}