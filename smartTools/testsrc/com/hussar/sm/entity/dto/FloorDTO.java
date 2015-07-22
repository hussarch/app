package com.hussar.sm.entity.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hussar.sm.entity.modul.ScoreModel;
/**
 * 
 * @author leo.chen
 *
 */
public class FloorDTO {
	
	private Integer id;
	private String name;
	private Set<Long> locationIdSet = new HashSet<Long>();
	private List<ImageDTO> imageList;
	private List<ScoreModel> scoreList;
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
    public Set<Long> getLocationIdSet() {
        return locationIdSet;
    }
    public void setLocationIdSet(Set<Long> locationIdSet) {
        this.locationIdSet = locationIdSet;
    }
	
	
}
