package com.skpcorp.crudOperation.model;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name = "product")
public class ProductDTO {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    private Integer price;

    private Integer discount;

    @Size(max = 25)
    private String stockStatus;

    @Size(max = 12)
    private String sku;

}
