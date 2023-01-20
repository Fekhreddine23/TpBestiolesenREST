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
import com.example.demo.repository.AnimalRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.SpeciesRepository;
import com.example.demo.services.AnimalService;
import com.example.demo.services.PersonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/animal")
public class AnimalController {
	
	@Autowired
	AnimalService animalService;
	
	@GetMapping("/{id}")//affiche une bete
	public Animal findOne(@PathVariable("id") Integer id) {
		
		return this.animalService.findById(id);
	}
	
	
	@GetMapping()
	public List<Animal> getListAnimals(){
		return animalService.findAll();
	}
	
	
	@PostMapping()//creer un animal
	public Animal createAnimal(@RequestBody @Valid Animal createAnimal) {
		
		return this.animalService.create(createAnimal);
	}
	
	
	@PutMapping("/{id}") // update
	public Animal updateAnimal(@PathVariable("id") Integer id, @RequestBody @Valid Animal updateAnimal) {
		if (!id.equals(updateAnimal.getId())) {

			throw new RuntimeException("probleme : animal existe pas");
		}
		return this.animalService.update(updateAnimal);
	}
	
	
	@DeleteMapping("/{id}")//delete
	public void deleteAnimal(@PathVariable("id")Integer id, @RequestBody @Valid Animal deleteAnimal) {
		if(!id.equals(deleteAnimal.getId())) {
			throw new RuntimeException("Erreur : pas present en bdd");
		}
		     this.animalService.delete(id);
		 
	}

}
