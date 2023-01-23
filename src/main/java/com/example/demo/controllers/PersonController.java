package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.hibernate.event.internal.DefaultPersistOnFlushEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.services.PersonService;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/person")
public class PersonController {

	@Autowired
	PersonService personService;

	// methode pagination
	@GetMapping("/pages")
	public List<Person> findAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {

		// methode PageRequest.of()
		// 2 arguments num de la page et taille qu l'on veut
		return personService.findPeage(PageRequest.of(pageNum, pageSize)); //

		// throw new RuntimeException("");

	}

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

		if (createToPerson.getId() != null) {
			throw new IllegalArgumentException(" erreur la personne ne doit pas avoir id lors de la creation");
		}
		return this.personService.create(createToPerson);

	}

	@PutMapping("/{id}") // update
	public Person updatePerson(@PathVariable("id") Integer id, @RequestBody @Valid Person updatePerson) {
		if (!id.equals(updatePerson.getId())) {

			throw new RuntimeException("probleme");
		}

		if (updatePerson.getId() == null) {
			throw new IllegalArgumentException("besoin de l'id");
		}
		return this.personService.update(updatePerson);
	}

	@DeleteMapping("/{id}") // delete
	public void deletePerson(@PathVariable("id") Integer id, @RequestBody @Valid Person deletePerson) {
		if (!id.equals(deletePerson.getId())) {
			throw new RuntimeException("Erreur : pas present en bdd");
		}
		this.personService.delete(id);

	}

}
