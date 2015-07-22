package com.hussar.sm.entity.dto;

import com.hussar.sm.entity.modul.ScoreModel;

/**
 * 
 * @author wenjun.yu
 *
 */
public class ScoreDTO {
    
	private String delivery;
	private String item;
	private String service;
	
	public ScoreDTO(){
	}
	
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
}
