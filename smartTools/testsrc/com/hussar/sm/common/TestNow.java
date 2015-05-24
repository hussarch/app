package com.hussar.sm.common;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.hussar.sm.entity.dto.FloorDTO;

public class TestNow {
	
	public static Field[] getHierarchyDeclaredFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<Field>();
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				Field[] fs = clazz.getDeclaredFields();
				for (Field f : fs) {
					fields.add(f);
				}
			} catch (Exception e) {
				// do nothing ,if throw e or print e,clazz =
				// clazz.getSuperclass() will not execute.
			}
		}
		return fields.toArray(new Field[] {});
	}
	
	public static Class<?> getGenericTypeFromList(Field field){
		Type type = field.getGenericType(); 
		if (type instanceof ParameterizedType) {     
		   ParameterizedType paramType = (ParameterizedType) type;     
		   Type[] actualTypes = paramType.getActualTypeArguments();     
		   for (Type aType : actualTypes) {         
		       if (aType instanceof Class) {         
		           Class<?> clz = (Class<?>) aType;             
		           return clz;
		       }     
		   } 
		}
		return null; 
	}
	
	public static void testField(){
		Field[] destFields = getHierarchyDeclaredFields(FloorDTO.class);
		for (int i = 0; i < destFields.length; i++) {
			Field field = destFields[i];
			Class<?> typeClass = field.getType();
			if (List.class.isAssignableFrom(typeClass)) {
				Class<?> clz = getGenericTypeFromList(field);
				System.out.println(clz);
			}
			
		}
	}
	
	 public static void main(String[] args) {
		 testField();
	}
}
