package com.example.escola.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.escola.entities.Professor;
import com.example.escola.repositories.DepartamentoRepository;
import com.example.escola.repositories.ProfessorRepository;

@Controller
public class ProfessorController {

	@Autowired
	private ProfessorRepository profRepository;
	
	@Autowired
	private DepartamentoRepository deptRepository;
	
	@GetMapping(path = "/profs")
	public String getAllDeps(Model model) {
		model.addAttribute("ListProfs", profRepository.findAll());
		return "profs";
	}
	
	@GetMapping("/showNewProfForm")
	public String showNewProfForm(Model model) {
		// create model attribute to bind form data
		Professor prof = new Professor();
		model.addAttribute("newProf", prof);
		model.addAttribute("depts", deptRepository.findAll());
		return "new_prof";
	}
	
	@PostMapping("/saveProf")
	public String saveProf(@ModelAttribute("newProf") Professor prof) {
		// save prof to database
		profRepository.save(prof);
		return "redirect:/profs";
	}
	
	@GetMapping("/deleteProf/{id}")
	public String deleteDep(@PathVariable(value = "id") Integer id) {
		profRepository.deleteById(id);
		return "redirect:/profs";
	}
	
	@GetMapping("/showUpdateProfForm/{id}")
	public String showUpdateProfForm(@PathVariable(value = "id") Integer id, Model model) {
		Optional<Professor> optional = profRepository.findById(id);
		Professor prof = null;
		if (optional.isPresent()) {
			prof = optional.get();
		} else {
			throw new RuntimeException("Professor not found for id :: " + id);
		}
		// set professor as a model attribute to pre-populate the form
		model.addAttribute("prof", prof);
		model.addAttribute("depts", deptRepository.findAll());
		return "update_prof";
	}
}
