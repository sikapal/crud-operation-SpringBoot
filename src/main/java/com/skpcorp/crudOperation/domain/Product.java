 package com.skpcorp.crudOperation.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Product {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    
	    @Column(nullable = false, unique = true, length = 100)
	    private String name;
	    
	    @Column(nullable = false)
	    private Integer price;
	    
	    @Column
	    private Integer discount;
	    
	    @Column(length = 25)
	    private String stockStatus;
	    
	    @Column(length = 12)
	    private String sku;
	
}
