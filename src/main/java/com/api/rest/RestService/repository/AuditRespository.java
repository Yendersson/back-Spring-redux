package com.api.rest.RestService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.RestService.entities.Audit;

@Repository
public interface AuditRespository extends JpaRepository<Audit, Long>{
	
}
