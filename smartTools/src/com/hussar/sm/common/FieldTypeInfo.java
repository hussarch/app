package com.hussar.sm.common;

public class FieldTypeInfo {
	
	private Class<?> destFieldType;
	private Class<?> origFieldType;
	private String name;
	
	public FieldTypeInfo(Class<?> destFieldType, String name){
		this.destFieldType = destFieldType;
		this.name = name;
	}
	
	public FieldTypeInfo(Class<?> destFieldType, Class<?> origFieldType, String name){
		this.destFieldType = destFieldType;
		this.origFieldType = origFieldType;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getDestFieldType() {
		return destFieldType;
	}

	public void setDestFieldType(Class<?> destFieldType) {
		this.destFieldType = destFieldType;
	}

	public Class<?> getOrigFieldType() {
		return origFieldType;
	}

	public void setOrigFieldType(Class<?> origFieldType) {
		this.origFieldType = origFieldType;
	}

}
