/**
 * 
 */
package com.example.taco.data;

import org.springframework.data.repository.CrudRepository;

import com.example.taco.Ingredient;

/**
 * @author clabrada
 *
 */

public interface IngredientRepository extends CrudRepository<Ingredient, String>{

}
