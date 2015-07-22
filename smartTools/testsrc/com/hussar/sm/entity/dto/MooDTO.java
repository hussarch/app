package com.hussar.sm.entity.dto;

public class MooDTO extends ImageHDDTO {

    private String name;
    private ProductDTO product;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

}
