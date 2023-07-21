package com.example.escola.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Departamento {

	@Id
	private Integer depId;
	private String nome;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="dept")
	private List<Professor> professores;
	
	public Integer getDepId() {
		return depId;
	}
	
	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Departamento [depId=" + depId + ", nome=" + nome + "]";
	}
	
	
}
