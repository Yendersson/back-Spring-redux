package com.api.rest.RestService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.RestService.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
