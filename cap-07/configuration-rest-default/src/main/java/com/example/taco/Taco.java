package com.example.taco;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.Data;

@Data
@Entity
public class Taco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date createdAt;

	@NotNull
	@Size(min = 5, message = "El nombre el minimo debe de ser de cinco caracteres")
	private String name;

	@ManyToMany(targetEntity = Ingredient.class)
	@Size(min = 1, message = "Debe de contener al menos un ingrediente")
	private List<Ingredient> ingredients = new ArrayList<>();
	
	 @PrePersist
	  void createdAt() {
	    this.createdAt = new Date();
	 }
}
