package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.hibernate.event.internal.DefaultPersistOnFlushEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.services.PersonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/person")
public class PersonController {
    
	
	@Autowired
	PersonService personService;

	@GetMapping("/{id}") // affiche une personne
	public Person findOne(@PathVariable("id") Integer id) {

		return this.personService.findById(id);
	}

	@GetMapping() // listes personnes
	public List<Person> getListPersons() {
		return personService.findAll();
	}

	@PostMapping() // crée une personne
	public Person createPerson(@RequestBody @Valid Person createToPerson) {
		return this.personService.create(createToPerson);

	}

	@PutMapping("/{id}") // update
	public Person updatePerson(@PathVariable("id") Integer id, @RequestBody @Valid Person updatePerson) {
		if (!id.equals(updatePerson.getId())) {

			throw new RuntimeException("probleme");
		}
		return this.personService.update(updatePerson);
	}
	
	
	
	@DeleteMapping("/{id}")//delete
	public void deletePerson(@PathVariable("id")Integer id, @RequestBody @Valid Person deletePerson) {
		if(!id.equals(deletePerson.getId())) {
			throw new RuntimeException("Erreur : pas present en bdd");
		}
		     this.personService.delete(id);
		 
	}

}
