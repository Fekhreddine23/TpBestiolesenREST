package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

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

import com.example.demo.model.Animal;
import com.example.demo.model.Person;
import com.example.demo.model.Species;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.SpeciesRepository;
import com.example.demo.services.PersonService;
import com.example.demo.services.SpeciesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/specie")
public class SpeciesController {
	
	@Autowired
	SpeciesService specieService;
	
	@GetMapping("/{id}") // affiche une personne
	public Species findOne(@PathVariable("id") Integer id) {

		return this.specieService.findById(id);
	}
	
	
	@GetMapping() // listes personnes
	public List<Species> getListSpecies() {
		return specieService.findAll();
	}
	
	
	@PostMapping() // crée une Specie
	public Species createSpecie(@RequestBody @Valid Species createToSpecie) {
		return this.specieService.create(createToSpecie);

	}
	
	
	
	@PutMapping("/{id}") // update
	public Species updateSpecies(@PathVariable("id") Integer id, @RequestBody @Valid Species updtSpecies) {
		if (!id.equals(updtSpecies.getId())) {

			throw new RuntimeException("probleme");
		}
		return this.specieService.update(updtSpecies);
	} 
	
	
	
	@DeleteMapping("/{id}")//delete
	public void deleteSpecie(@PathVariable("id")Integer id, @RequestBody @Valid Species deleteSpecies) {
		if(!id.equals(deleteSpecies.getId())) {
			throw new RuntimeException("Erreur : pas present en bdd");
		}
		     this.specieService.delete(id);
		 
	}



}
