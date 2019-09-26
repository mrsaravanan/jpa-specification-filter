package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="catalogs")

public class Catalog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="description")
	private String description; 
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Tower getTower() {
		return tower;
	}


	public void setTower(Tower tower) {
		this.tower = tower;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	@Column(name="name")
	private String name;
	
	@ManyToOne(optional=false, fetch = FetchType.LAZY)
	@JoinColumn(name="tower_id", nullable=false)
	private Tower tower;
	
	
	@ManyToOne(optional=false, fetch = FetchType.LAZY)
	@JoinColumn(name="category_id", nullable=false)
	private Category category;
	
	

}
