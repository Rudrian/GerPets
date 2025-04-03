package com.GerPets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GerPets.Entity.Pets;

public interface PetsRepository extends JpaRepository<Pets, Long>{

}
