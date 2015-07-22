package com.hussar.sm.entity.vo;

/**
 * @author yi.xiao
 *
 */
public class ProductVO {
    
    private Integer Id;
    private String name;
    private FloorVO floor;
    private BrandVO brand;
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
    public FloorVO getFloor() {
        return floor;
    }
    public void setFloor(FloorVO floor) {
        this.floor = floor;
    }
    public BrandVO getBrand() {
        return brand;
    }
    public void setBrand(BrandVO brand) {
        this.brand = brand;
    }
    
}
