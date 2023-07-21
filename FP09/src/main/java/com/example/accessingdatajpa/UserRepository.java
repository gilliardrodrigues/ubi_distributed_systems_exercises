package com.example.accessingdatajpa;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

	//public List<User> findByNameAndEmail(String name, String email);
}
