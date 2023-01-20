package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Animal;
import com.example.demo.repository.AnimalRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class AnimalService {

	@Autowired
	AnimalRepository animalRepository;

	public Animal create(@Valid Animal animalToCreate) {

		if (animalToCreate.getId() != null) {

			throw new RuntimeException("erreur : id est deja present");
		}

		return this.animalRepository.save(animalToCreate);
	}

	public Animal update(@Valid Animal animalUpdate) {

		if (animalUpdate.getId() == null) {
			throw new RuntimeException("erreur : id ne pas encore créer");
		}
		return this.animalRepository.save(animalUpdate);
	}

	public List<Animal> findAll() {
		return (List<Animal>) animalRepository.findAll();
	}

	public Animal findById(Integer id) {
		return this.animalRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}
	
	
	

	public void delete(Integer id) { // supprimer une pers selon son id

		if (id == null) {
			throw new RuntimeException("erreur : id pas present");
		}
		this.animalRepository.deleteById(id);
	}

}
