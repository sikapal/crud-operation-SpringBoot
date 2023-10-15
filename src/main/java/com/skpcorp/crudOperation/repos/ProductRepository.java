package com.skpcorp.crudOperation.repos;

import org.springframework.data.repository.CrudRepository;

import com.skpcorp.crudOperation.domain.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long>{
	 boolean existsByNameIgnoreCase(String name);
}
