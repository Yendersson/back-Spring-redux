package com.api.rest.RestService.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.RestService.entities.Audit;
import com.api.rest.RestService.entities.Login;
import com.api.rest.RestService.entities.Person;
import com.api.rest.RestService.entities.User;
import com.api.rest.RestService.jwt.JsonWebToken;
import com.api.rest.RestService.repository.AuditRespository;
import com.api.rest.RestService.repository.UserRepository;
import com.api.rest.RestService.utils.Error;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
	
	private final Logger log = LoggerFactory.getLogger(Person.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuditRespository auditRepo;
	
	public AuthController(AuditRespository auditRespo, UserRepository userRepository) {
		this.auditRepo = auditRespo;
		this.userRepository = userRepository;
	}
	
	@ApiResponse(responseCode = "204", description = "Create a User")
	@PostMapping("api/register")
	public ResponseEntity<?> register(@RequestBody User user) throws URISyntaxException {
		if (user.getId() != null) return ResponseEntity.badRequest().body(new Error("No se puede crear un campo con Id incluido"));
		
		User userPost = userRepository.save(user);
		Audit audit = new Audit();
		audit.setMethod("Create");
		audit.setTipo(userPost.getClass().getSimpleName());
		auditRepo.save(audit);
		
		return ResponseEntity.created(new URI("api/register")).body(userPost);
	}
	
	@GetMapping("api/users")
	public List<User> getAllUsers(){
		List<User> users = userRepository.findAll();
		return users;
	}
	
	@PostMapping("api/auth")
	public ResponseEntity<?> auth(@RequestBody Login login){
		
		User userAuth = userRepository.findByUsername(login.getUsername());
//		System.out.println(userAuth);
		if (userAuth == null) {
			return ResponseEntity.badRequest().body(new Error("Username incorrect"));
		} 
		
		if (login.getPassword().equals(userAuth.getPassword())) {
			return ResponseEntity.ok(JsonWebToken.generateToken(userAuth));			
		} 
		
		return ResponseEntity.badRequest().body(new Error("password bad"));
		
	}
	
	
	
	
}
