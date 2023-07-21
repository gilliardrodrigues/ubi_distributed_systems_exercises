package com.example.escola.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.escola.entities.Departamento;

public interface DepartamentoRepository extends CrudRepository<Departamento, Integer> {
	
}
