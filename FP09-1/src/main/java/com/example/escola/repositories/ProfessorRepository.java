package com.example.escola.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.escola.entities.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, Integer> {

}
