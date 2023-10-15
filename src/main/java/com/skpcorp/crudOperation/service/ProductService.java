package com.skpcorp.crudOperation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.skpcorp.crudOperation.model.ProductDTO;


public interface ProductService {
  
	    Page<ProductDTO> findAll(final Pageable pageable);

	    ProductDTO get(Long id);

	    Long create(ProductDTO productDTO);

	    void update(Long id, ProductDTO productDTO);

	    void delete(Long id);

	    boolean nameExists(String name);
}
