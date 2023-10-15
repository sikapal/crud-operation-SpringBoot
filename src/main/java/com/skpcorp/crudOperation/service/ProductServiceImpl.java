package com.skpcorp.crudOperation.service;

import java.util.List;

import com.skpcorp.crudOperation.domain.Product;
import com.skpcorp.crudOperation.model.ProductDTO;
import com.skpcorp.crudOperation.repos.ProductRepository;
import com.skpcorp.crudOperation.util.NotFoundException;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> findAll() {
        final List<Product> products = (List<Product>) productRepository.findAll();
        return products.stream()
                .map(product -> mapToDTO(product, new ProductDTO()))
                .toList();
    }

    @Override
    public ProductDTO get(final Long id) {
        return productRepository.findById(id)
                .map(product -> mapToDTO(product, new ProductDTO()))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Long create(final ProductDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        return productRepository.save(product).getId();
    }

    @Override
    public void update(final Long id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(productDTO, product);
        productRepository.save(product);
    }

    @Override
    public void delete(final Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDiscount(product.getDiscount());
        productDTO.setStockStatus(product.getStockStatus());
        productDTO.setSku(product.getSku());
        return productDTO;
    }

    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());
        product.setStockStatus(productDTO.getStockStatus());
        product.setSku(productDTO.getSku());
        return product;
    }

    @Override
    public boolean nameExists(final String name) {
        return productRepository.existsByNameIgnoreCase(name);
    }

}
