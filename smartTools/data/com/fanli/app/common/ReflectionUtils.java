package com.fanli.app.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author leo.chen
 *
 */
public class ReflectionUtils {

	public static Method[] getHierarchyDeclaredMethods(Class<?> clazz) {
		List<Method> methods = new ArrayList<Method>();
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				Method[] ms = clazz.getDeclaredMethods();
				for (Method m : ms) {
					methods.add(m);
				}
			} catch (Exception e) {
				// do nothing ,if throw e or print e,clazz =
				// clazz.getSuperclass() will not execute.
			}
		}
		return methods.toArray(new Method[] {});
	}

	/**
	 * 
	 * 循环向上转型, 获取当前类和父类的中出现[methodName]所有的 DeclaredMethod
	 * 
	 * @param methodName
	 *            : 父类中的方法名
	 * @param parameterTypes
	 *            : 父类中的方法参数类型
	 */

	public static Method getHierarchyDeclaredMethod(Class<?> clazz,
			String methodName, Class<?>... parameterTypes) {
		Method method = null;
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				method = clazz.getDeclaredMethod(methodName, parameterTypes);
				return method;
			} catch (Exception e) {
				// do nothing ,if throw e or print e,clazz =
				// clazz.getSuperclass() will not execute.
			}
		}
		return null;
	}

	/**
	 * 直接调用对象方法, 而忽略修饰符(private, protected, default)
	 * 
	 * @param object
	 *            : 子类对象
	 * @param methodName
	 *            : 父类中的方法名
	 * @param parameterTypes
	 *            : 父类中的方法参数类型
	 * @param parameters
	 *            : 父类中的方法参数
	 */

	public static Object invokeMethod(Object object, String methodName,
			Class<?>[] parameterTypes, Object[] parameters) {
		Method method = getHierarchyDeclaredMethod(object.getClass(),
				methodName, parameterTypes);
		method.setAccessible(true);
		try {
			if (null != method) {
				return method.invoke(object, parameters);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * 循环向上转型, 获取当前类和父类的所有的 DeclaredField
	 * 
	 */
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

	/**
	 * 循环向上转型, 获取当前类和父类的中出现[fieldName]所有的 DeclaredField
	 */

	public static Field getHierarchyDeclaredField(Class<?> clazz,
			String fieldName) {
		Field[] fields = getHierarchyDeclaredFields(clazz);
		for (Field f : fields) {
			if (f.getName().equals(fieldName))
				return f;
		}
		return null;
	}

	/**
	 * 直接设置对象属性值, 忽略 private/protected 修饰符, 也不经过 setter
	 * 
	 * @param object
	 *            : 子类对象
	 * @param fieldName
	 *            : 父类中的属性名
	 * @param value
	 *            : 将要设置的值
	 */

	public static void setFieldValue(Object object, String fieldName,
			Object value) {
		Field field = getHierarchyDeclaredField(object.getClass(), fieldName);
		field.setAccessible(true);
		try {
			field.set(object, value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 直接读取对象的属性值, 忽略 private/protected 修饰符, 也不经过 getter
	 * 
	 * @param object
	 *            : 子类对象
	 * @param fieldName
	 *            : 父类中的属性名
	 */

	public static Object getFieldValue(Object object, String fieldName) {
		Field field = getHierarchyDeclaredField(object.getClass(), fieldName);
		field.setAccessible(true);
		try {
			return field.get(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
