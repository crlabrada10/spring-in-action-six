package com.example.taco.data;

import com.example.taco.Taco;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long>{

}
