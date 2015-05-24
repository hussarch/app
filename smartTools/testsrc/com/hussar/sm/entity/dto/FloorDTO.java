package com.hussar.sm.entity.dto;

import java.util.List;
/**
 * 
 * @author leo.chen
 *
 */
public class FloorDTO {
	
	private Integer id;
	private String name;
	private List<ImageDTO> imageList;
	private ImageDTO image;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ImageDTO> getImageList() {
		return imageList;
	}
	public void setImageList(List<ImageDTO> imageList) {
		this.imageList = imageList;
	}
	public ImageDTO getImage() {
		return image;
	}
	public void setImage(ImageDTO image) {
		this.image = image;
	}
	
	
}
