package com.example.escola.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Professor {

	@Id
	private Integer profId;
	private String nome;
	
	@ManyToOne
    @JoinColumn(name = "deptId")
	private Departamento dept;

	public Integer getProfId() {
		return profId;
	}

	public void setProfId(Integer profId) {
		this.profId = profId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDept() {
		return dept;
	}

	public void setDept(Departamento dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Professor [profId=" + profId + ", nome=" + nome + ", dept=" + dept + "]";
	}
	
}
