package com.hussar.sm.cg;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

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
	
	public static void testException(){
	    try {
            System.out.println("start");
	        throw new Exception();
        } catch (Exception e) {
            System.out.println("in the exception");
        }finally{
            System.out.println("in the finally");
        }
	}
	
	public static void testAppend(){
	    List<String> pidList = new ArrayList<String>();
	    Random random = new Random();
	    for(int i = 0; i < 1556; i++){
	        pidList.add("4216558" + random.nextInt(10000));
	    }
	    int i = 0;
	    while(i < 100){
	        long start = System.currentTimeMillis();
	        StringBuilder sb = new StringBuilder();
	        for(String pid : pidList){
	            sb.append(pid).append(",");
	        }
	        if(sb.length() >1){
	            sb.deleteCharAt(sb.length()-1);
	        }
	        long end = System.currentTimeMillis();
	        System.out.println("used time: " + (end - start));
	    }
	}
	
	 public static void main(String[] args) {
	     testAppend();
	}
}
