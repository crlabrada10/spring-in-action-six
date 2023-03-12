package com.example.taco.data;

import org.springframework.data.repository.CrudRepository;

import com.example.taco.User;


public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
}
