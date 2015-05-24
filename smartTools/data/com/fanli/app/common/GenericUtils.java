package com.fanli.app.common;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
/**
 * 
 * @author leo.chen
 *
 */
public abstract class GenericUtils {
	
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
	
}
