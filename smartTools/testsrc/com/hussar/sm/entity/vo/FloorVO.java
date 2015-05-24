package com.hussar.sm.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.hussar.sm.entity.dto.FloorDTO;
import com.hussar.sm.entity.dto.ImageDTO;

/**
 * 
 * @author leo.chen
 * 
 */
public class FloorVO {
	
	private Integer id;
	private String name;
	private List<ImageModel> imageModelList;
	private ImageModel image;
	
	
	public FloorVO(FloorDTO floorDTO){
		this.setId(floorDTO.getId());
		this.setName(floorDTO.getName());
		if(floorDTO.getImageList() != null){
			imageModelList = new ArrayList<ImageModel>();
			for(ImageDTO imageDTO : floorDTO.getImageList()){
				imageModelList.add(new ImageModel(imageDTO));
			}
		}
		this.setImage(new ImageModel(floorDTO.getImage()));
	}

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

	public List<ImageModel> getImageModelList() {
		return imageModelList;
	}

	public void setImageModelList(List<ImageModel> imageModelList) {
		this.imageModelList = imageModelList;
	}

	public ImageModel getImage() {
		return image;
	}

	public void setImage(ImageModel image) {
		this.image = image;
	}

}
