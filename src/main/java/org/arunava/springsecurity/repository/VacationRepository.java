package org.arunava.springsecurity.repository;

import org.arunava.springsecurity.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRepository extends JpaRepository<Vacation, Long> {

}
