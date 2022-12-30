/**
 * 
 */
package com.example.taco.data;

import org.springframework.data.repository.CrudRepository;

import com.example.taco.TacoOrder;

/**
 * @author clabrada
 *
 */
public interface OrderRepository extends CrudRepository<TacoOrder, Long>{

}
