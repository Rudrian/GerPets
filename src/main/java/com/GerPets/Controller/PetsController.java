package com.GerPets.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GerPets.Entity.Pets;
import com.GerPets.Service.PetsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pets")

public class PetsController {

	private final PetsService petsService;

	@Autowired
	public PetsController(PetsService petsService) {
		this.petsService = petsService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pets> getPetsById(@PathVariable Long id) {
		Pets pets = petsService.getPetsById(id);
		if (pets != null) {
			return ResponseEntity.ok(pets);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Pets>> getAllPets() {
		List<Pets> pets = petsService.getAllPets();
		return ResponseEntity.ok(pets);
	}

	@PostMapping("/")
	public ResponseEntity<Pets> criarPets(@RequestBody @Valid Pets pets) {
		Pets criarPets = petsService.salvarPets(pets);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarPets);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pets> updatePets(@PathVariable Long id, @RequestBody @Valid Pets pets) {
		Pets updatedPets = petsService.updatePets(id, pets);
		if (updatedPets != null) {
			return ResponseEntity.ok(updatedPets);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Pets> deletePets(@PathVariable Long id) {
		boolean deleted = petsService.deletePets(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}