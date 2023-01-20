package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Animal;
import com.example.demo.model.Species;
import com.example.demo.repository.SpeciesRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class SpeciesService {
	
	@Autowired
	SpeciesRepository speciesRepository;
	
	
	public Species create(@Valid Species specieToCreate) {

		if (specieToCreate.getId() != null) {

			throw new RuntimeException("erreur : id est deja present");
		}

		return this.speciesRepository.save(specieToCreate);
	}
	
	
	public Species update(@Valid Species speUpdate) {

		if (speUpdate.getId() == null) {
			throw new RuntimeException("erreur : id ne pas encore créer");
		}
		return this.speciesRepository.save(speUpdate);
	}
	
	
	public List<Species> findAll() {
		return (List<Species>) speciesRepository.findAll();
	}
	
	
	public Species findById(Integer id) {
		return this.speciesRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}
	
	
	public void delete(Integer id) { //renvoi rien

		if (id == null) {
			throw new RuntimeException("erreur : id pas present");
		}
		this.speciesRepository.deleteById(id);
	}


}
