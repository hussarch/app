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
	
    private int size;
    private boolean flag;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (flag ? 1231 : 1237);
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        result = prime * result + ((imageModelList == null) ? 0 : imageModelList.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + size;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FloorVO other = (FloorVO) obj;
        if (flag != other.flag)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (image == null) {
            if (other.image != null)
                return false;
        } else if (!image.equals(other.image))
            return false;
        if (imageModelList == null) {
            if (other.imageModelList != null)
                return false;
        } else if (!imageModelList.equals(other.imageModelList))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (size != other.size)
            return false;
        return true;
    }


}
