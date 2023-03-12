/**
 * 
 */
package com.example.taco.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.taco.TacoOrder;
import com.example.taco.User;

/**
 * @author clabrada
 *
 */
public interface OrderRepository extends CrudRepository<TacoOrder, Long>{

	 List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
	
	 /*
	List<TacoOrder> findByUserOrderByPlacedAtDesc(User user);
	*/
}
