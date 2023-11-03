package com.api.rest.RestService.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

import com.api.rest.RestService.entities.Category;
import com.api.rest.RestService.entities.Person;
import com.api.rest.RestService.entities.Product;
import com.api.rest.RestService.repository.PersonRepository;
import com.api.rest.RestService.utils.DataContainer;

@RestController
@CrossOrigin(origins = "*")
public class Controller {
	
	private final Logger log = LoggerFactory.getLogger(Person.class);
	
	@Autowired
	private PersonRepository personRepository;
	
	public Controller(PersonRepository personRepository) {
			this.personRepository = personRepository;
	}
	
	@GetMapping("/api/persons")
		public ResponseEntity<DataContainer<Person>> getPersons(){
			List<Person> persons =personRepository.findAll();
			DataContainer<Person> data = new DataContainer<>(persons);
			return  ResponseEntity.ok(data);
		}
	
	@GetMapping("/api/persons/{id}")
	public ResponseEntity<Person> getOnePerson(@PathVariable Long id){
		if (!personRepository.existsById(id)) return ResponseEntity.notFound().build();
		Optional<Person> person = personRepository.findById(id);
		return ResponseEntity.ok(person.get());
	}
	
	@PostMapping("/api/persons")
	public ResponseEntity<Person> insertPersons(@RequestBody Person person){
		Person postPerson = personRepository.save(person);
		return ResponseEntity.ok(postPerson);
	}
	
	@PutMapping("/api/persons")
	public ResponseEntity<Person> updatePersons(@RequestBody Person person){
		
		if (person.getId() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		Person putPerson = personRepository.save(person);
		return ResponseEntity.ok(putPerson);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping("/api/persons/{id}")
	public ResponseEntity<Person> deletePerson(@PathVariable Long id){
		if (!personRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		personRepository.deleteById(id);
		return ResponseEntity.noContent().build();
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
