package com.hussar.sm.entity.dto;

/**
 * @author yi.xiao
 *
 */
public class ProductDTO {
    
    private Integer Id;
    private String name;
    private FloorDTO floor;
    private BrandDTO brand;
    
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public FloorDTO getFloor() {
        return floor;
    }
    public void setFloor(FloorDTO floor) {
        this.floor = floor;
    }
    public BrandDTO getBrand() {
        return brand;
    }
    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }
    
}
