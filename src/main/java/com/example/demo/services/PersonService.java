package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;

	// methodes du CRUD

	public Person create(@Valid Person personToCreate) {

		if (personToCreate.getId() != null) {

			throw new RuntimeException("erreur : id est deja present");
		}

		return this.personRepository.save(personToCreate);
	}

	public Person update(@Valid Person personUpdate) {

		if (personUpdate.getId() == null) {
			throw new RuntimeException("erreur : id ne pas encore créer");
		}
		return this.personRepository.save(personUpdate);
	}

	public List<Person> findAll() {
		return (List<Person>) personRepository.findAll();
	}

	public Person findById(Integer id) {
		return this.personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	
	public void delete(Integer id) { //supprimer une pers selon son id
		
		if( id  == null) {
			throw new RuntimeException("erreur : id pas present");
		}
		 this.personRepository.deleteById(id);
	}

	
	
	
	//methode pagination qui marche
	public List<Person> findPeage(Pageable peageable) {
		 
		Page<Person> page = personRepository.findAll(peageable);
        return page.getContent();
	}
}
