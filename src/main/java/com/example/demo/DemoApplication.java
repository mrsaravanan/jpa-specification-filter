package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJpaRepositories("com.example.demo")
@EntityScan("com.example.demo")
@RestController
public class DemoApplication {
	
	@Autowired
	private MyRepo repo;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@GetMapping("/test")
	public ResponseEntity<Object> test() {
		
		List<Catalog> list = repo.findAll();
		System.out.println("size-->" + list.size());
//		for(Catalog c: list) {
//			System.out.println(c.getTower().getId());
//			System.out.println(c.getCategory().getName());
//			System.out.println(c.toString());
//		}
		
		String searchText = "s";
		Map<String, String> filters = new HashMap<>();
		filters.put("description","dell");
		CatalogSpecification spec = new CatalogSpecification( filters, searchText);
		
		List<Catalog> newList = repo.findAll(spec);
		for(Catalog c: newList) {
			System.out.println("------------HEYYYYYYYYYYYYYY---------------------------");
		}
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(newList.size());
	}
	
	

}
