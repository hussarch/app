package com.hussar.sm.entity.vo;

import com.hussar.sm.entity.dto.ImageDTO;

/**
 * 
 * @author leo.chen
 *
 */
public class ImageVO {
	private Long id;
	private String url;
	private String clickUrl ;
	private String w;
	private String h;
	private String urlLD;
	private String widthLD;
	private String heightLD;
	private String md5;
	
	public ImageVO(ImageDTO imageDTO) {
	}
	public String getClickUrl() {
		return clickUrl;
	}
	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getW() {
		return w;
	}
	public void setW(String w) {
		this.w = w;
	}
	public String getH() {
		return h;
	}
	public void setH(String h) {
		this.h = h;
	}
	public String getUrlLD() {
		return urlLD;
	}
	public void setUrlLD(String urlLD) {
		this.urlLD = urlLD;
	}
	public String getWidthLD() {
		return widthLD;
	}
	public void setWidthLD(String widthLD) {
		this.widthLD = widthLD;
	}
	public String getHeightLD() {
		return heightLD;
	}
	public void setHeightLD(String heightLD) {
		this.heightLD = heightLD;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
}
