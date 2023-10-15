package com.skpcorp.crudOperation.repos;

import org.springframework.data.repository.CrudRepository;

import com.skpcorp.crudOperation.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
	 boolean existsByNameIgnoreCase(String name);
}
