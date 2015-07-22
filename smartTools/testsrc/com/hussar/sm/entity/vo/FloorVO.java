package com.hussar.sm.entity.vo;

import java.util.List;
import java.util.Set;

import com.hussar.sm.entity.dto.ScoreDTO;

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
	private Set<Long> locationIdSet;
	private List<ImageVO> imageList;
	private List<ScoreDTO> scoreList;
	private ImageVO image;
	
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

	public ImageVO getImage() {
		return image;
	}

	public void setImage(ImageVO image) {
		this.image = image;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (flag ? 1231 : 1237);
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        result = prime * result + ((imageList == null) ? 0 : imageList.hashCode());
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
        if (imageList == null) {
            if (other.imageList != null)
                return false;
        } else if (!imageList.equals(other.imageList))
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

    public List<ImageVO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageVO> imageList) {
        this.imageList = imageList;
    }

    public Set<Long> getLocationIdSet() {
        return locationIdSet;
    }

    public void setLocationIdSet(Set<Long> locationIdSet) {
        this.locationIdSet = locationIdSet;
    }


}
