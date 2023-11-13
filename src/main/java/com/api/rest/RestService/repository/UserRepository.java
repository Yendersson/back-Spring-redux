package com.api.rest.RestService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.rest.RestService.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("FROM User u WHERE u.username = :username")
	User findByUsername(@Param("username") String username);
}
