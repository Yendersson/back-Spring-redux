package com.api.rest.RestService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.rest.RestService.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	@Query("FROM Person p WHERE  p.active = true")
	List<Person> findAllActive();
	
/*	@Modifying
	@Query("UPDATE Person p SET p.active = false WHERE p.id = :id")
	void deleteOneLogic(@Param("id") Long id);*/
}
