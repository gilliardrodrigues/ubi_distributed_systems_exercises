package com.example.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Fp083Application {

	private static final Logger log = LoggerFactory.getLogger(Fp083Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Fp083Application.class);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});

			log.info("");

			// fetch customers by first name
			log.info("Customer found with findByFirstName('Chloe'):");
			log.info("--------------------------------------------");
			repository.findByFirstName("Chloe").forEach(chloe -> {
				log.info(chloe.toString());
			});

			log.info("");

			// delete customer
			log.info("Deleting customer with id 1");
			log.info("--------------------------------------------");
			repository.deleteById(1L);

			log.info("");

			// fetch all customers again
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer c : repository.findAll()) {
				log.info(c.toString());
			}
			log.info("");
			
			Customer c2 = repository.findById(2);
			c2.setFirstName("Cams");
			repository.save(c2);
		};
	}

}
