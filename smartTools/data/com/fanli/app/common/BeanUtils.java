package com.fanli.app.common;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author leo.chen
 * 
 */
public class BeanUtils {

	/**
	 * Bean 属性拷贝
	 * 当orig的属性为 null 的就不拷贝到 dest
	 * @param dest
	 * @param orig
	 * @throws AppBizException 
	 */
	private static void copyProperties(Object dest, Object orig, Class<?>...genType) throws AppBizException {
		if (orig == null) {
			CheckUtils.checkNotEmpty(orig, "orig");
		}
		if (dest == null) {
			CheckUtils.checkNotEmpty(dest, "orig");
		}
		try {
			// 得到两个Class 的所有成员变量
//			Field[] destFields = dest.getClass().getDeclaredFields();
//			Field[] origFields = orig.getClass().getDeclaredFields();
			Field[] destFields = ReflectionUtils.getHierarchyDeclaredFields(dest.getClass());
			Field[] origFields = ReflectionUtils.getHierarchyDeclaredFields(orig.getClass());
			// 设置访问权限
			AccessibleObject.setAccessible(destFields, true);
			AccessibleObject.setAccessible(origFields, true);
			Object value = null;
			String name = null;
			String returnType = null;
			for (int i = 0; i < origFields.length; i++) {
				name = origFields[i].getName();
				returnType = origFields[i].getType().getName();
				for (int j = 0; j < destFields.length; j++) {
					if (!isFinal(destFields[j].getModifiers())
							&& name.equals(destFields[j].getName())) {
						Field oriField = origFields[i];
						Field destField = destFields[j];
						value = oriField.get(orig);
						Class<?> typeClass = oriField.getType();
						// Primitive type
						if(returnType.equals(destFields[j].getType().getName())){
							if (List.class.isAssignableFrom(typeClass)) {
								Class<?> clz = GenericUtils.getGenericTypeFromList(destField);
								if(clz == null){
									if(genType == null) throw new RuntimeException("please check Generic Type.");
									clz = genType[0];
								}
								if(value != null && !isPrimitive(clz)){
									List<?> list = convertArray(clz,(List<?>)value);
									destField.set(dest, list);
									break;
								}
								if(value != null && isPrimitive(clz)){
									destField.set(dest,(List<?>)value);
									break;
								}
							}
							if (value != null) {
								destField.set(dest, value);
								break;
							}
						}else{
							Class<?> destTypeClass = destField.getType();
							if (value != null) {
								value = convert(destTypeClass,value);
								destField.set(dest, value);
								break;
							}
						}
						
					}
				}
			}
		} catch (Exception e) {
			throw new AppBizException(e);
		}
	}

	// 判断是否为final修饰的属性
	private static boolean isFinal(int mod) {
		return (mod & Modifier.FINAL) != 0;
	}

	public static <D extends Object, M extends Object> D convert(Class<D> destClazz, M srcObj, Class<?>...genType) {
		if (srcObj == null) {
			return null;
		}
		D d = null;
		try {
			d = destClazz.newInstance();
			copyProperties(d, srcObj,genType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return d;
	}

	public static <D extends Object, M extends Object> List<D> convertArray(Class<D> destClazz, List<M> srcObjs) {
		List<D> dtos = new ArrayList<D>();
		if (srcObjs == null) {
			return dtos;
		}
		for (M m : srcObjs) {
			try {
				D d = destClazz.newInstance();
				copyProperties(d, m);
				dtos.add(d);
			} catch (Exception e) {
			    throw new RuntimeException(e);
			}
		}
		return dtos;
	}
	
	public static boolean isPrimitive(Class<?> clazz){
		if(clazz.isPrimitive()) return true;
		if(String.class.isAssignableFrom(clazz)){
			return true;
		}else if(Long.class.isAssignableFrom(clazz)){
			return true;
		}else if(Integer.class.isAssignableFrom(clazz)){
			return true;
		}else if(Boolean.class.isAssignableFrom(clazz)){
			return true;
		}else if(Double.class.isAssignableFrom(clazz)){
			return true;
		}else if(Number.class.isAssignableFrom(clazz)){
			return true;
		}else if(Date.class.isAssignableFrom(clazz)){
			return true;
		}
		return false;
	}

}