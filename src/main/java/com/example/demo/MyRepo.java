package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepo extends JpaRepository<Catalog,Long> {
	
	List<Catalog> findAll(Specification<Catalog> spec);
}
