package com.skpcorp.crudOperation.service;

import com.skpcorp.crudOperation.model.ProductDTO;
import java.util.List;

public interface ProductService {
  
	    List<ProductDTO> findAll();

	    ProductDTO get(Long id);

	    Long create(ProductDTO productDTO);

	    void update(Long id, ProductDTO productDTO);

	    void delete(Long id);

	    boolean nameExists(String name);
}
