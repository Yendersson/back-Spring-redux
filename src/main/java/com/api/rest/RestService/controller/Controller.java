package com.api.rest.RestService.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.RestService.entities.Audit;
import com.api.rest.RestService.entities.Category;
import com.api.rest.RestService.entities.Person;
import com.api.rest.RestService.entities.Product;
import com.api.rest.RestService.repository.AuditRespository;
import com.api.rest.RestService.repository.PersonRepository;
import com.api.rest.RestService.utils.DataContainer;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin(origins = "*")
public class Controller {
	
	private final Logger log = LoggerFactory.getLogger(Person.class);
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AuditRespository auditRepository;
	
	public Controller(PersonRepository personRepository, AuditRespository auditRepository) {
			this.personRepository = personRepository;
			this.auditRepository = auditRepository;
	}
	
	@ApiResponse(responseCode = "200", description = "get all the items until to date")
	@GetMapping("/api/persons")
		public ResponseEntity<DataContainer<Person>> getPersons(){
			List<Person> persons =personRepository.findAll();
			DataContainer<Person> data = new DataContainer<>(persons);
			log.info("Good");
			return  ResponseEntity.ok(data);
		}
	
	@GetMapping("/api/persons/{id}")
	public ResponseEntity<Person> getOnePerson(@PathVariable Long id){
		if (!personRepository.existsById(id)) return ResponseEntity.notFound().build();
		
		Optional<Person> person = personRepository.findById(id);
		return ResponseEntity.ok(person.get());
	}
	
	@ApiResponse(responseCode = "200", description = "Insert a new item")
	@PostMapping("/api/persons")
	public ResponseEntity<Person> insertPersons(@RequestBody Person person){
		if (person.getId() != null) return ResponseEntity.badRequest().build();
		Person postPerson = personRepository.save(person);
		Audit audit = new Audit();
		audit.setMethod("CREATE");
		auditRepository.save(audit);
		
		return ResponseEntity.ok(postPerson);
	}
	
	@ApiResponse(responseCode = "200", description = "Modified an item")
	@PutMapping("/api/persons")
	public ResponseEntity<Person> updatePersons(@RequestBody Person person){
		
		if (person.getId() == null) return ResponseEntity.badRequest().build();
		
		Person putPerson = personRepository.save(person);
		Audit audit = new Audit();
		audit.setMethod("UPDATE");
		auditRepository.save(audit);
		
		return ResponseEntity.ok(putPerson);
	}
	
	@ApiResponse(responseCode = "204", description = "delete a item")
	@DeleteMapping("/api/persons/{id}")
	public ResponseEntity<Person> deletePerson(@PathVariable Long id){
		if (!personRepository.existsById(id)) return ResponseEntity.notFound().build();
		personRepository.deleteById(id);
		Audit audit = new Audit();
		audit.setMethod("DELETE");
		auditRepository.save(audit);
		
		return ResponseEntity.noContent().build();
	}
	
	/*****************************************************************/
	//AUDITLOGS
	@GetMapping("api/logs")
	public ResponseEntity<DataContainer<Audit>> getAllAudit() {
		List<Audit> audits = auditRepository.findAll();
		DataContainer<Audit> aditoLog = new DataContainer<>(audits);
		return  ResponseEntity.ok(aditoLog);
	}
	
	//Controlar commandos desde la terminal a travez de un endpoint
	@GetMapping("api/persons/comand")
	public String executeComand() {
		try {
			Process process = Runtime.getRuntime().exec("pwd");
			
			BufferedReader lector = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			 String linea;
			 StringBuilder salida = new StringBuilder();

            while ((linea = lector.readLine()) != null) {
                salida.append(linea).append("\n");
            }

            int estadoSalida = process.waitFor();
	            
            return "Salida del comando:\n" + salida.toString() + "\nEstado de salida: " + estadoSalida;
            
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//PRUEBA SIN BASE DE DATOS....
	Collection<Product> products = new ArrayList<>();
	Category category1 = new Category("Categoria 1");
	{
		products.add(new Product(1L,"title1", 2.55, "description", category1));
		products.add(new Product(2L,"title2", 3.55, "description", category1));
	}
	
	@GetMapping("/api/product")
	public Collection<Product> getProducts() {
		return products;
	}
	
	@GetMapping("/api/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id) {
		
		Product product = null;
		
		for (Product p : products) {
			if (p.getId().equals(id)) {
				product = p;
			}
		}
		return ResponseEntity.ok(product);
	}
	
}
