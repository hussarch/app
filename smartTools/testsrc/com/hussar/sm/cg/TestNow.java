package com.hussar.sm.cg;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

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
	
	private static int getSaleRate(int saleNum, int inventory){
        if(saleNum >= inventory){
            return 99;
        }else{
            float rate = Float.valueOf(saleNum) / Float.valueOf(inventory);
            System.out.println(rate);
            return new BigDecimal(rate).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue();
        }
    }
	
	public static void  ff(){
	    BigDecimal val = (new BigDecimal(175.0)).subtract(new BigDecimal(49));
	    val = val.divide(new BigDecimal(259.0), 3, BigDecimal.ROUND_HALF_DOWN).setScale(5, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(10));
	    System.out.println(val);
	    val = val.setScale(1, BigDecimal.ROUND_HALF_UP);
	    System.out.println(val);
	}

	public static void match(){
	    Pattern pattern = Pattern.compile("\\d+:\\d+.*");
	    String msg = "今日10:00开抢";
	    char[] array = msg.toCharArray();
	    StringBuilder builder = new StringBuilder();
	    System.out.println(builder);
	    
	    SimpleDateFormat formate = new SimpleDateFormat("12月12日\nHH:mm:ss开抢");
        System.out.println(formate.format(new Date()));
	}
	
	
	public static void test_ff(){
	    BigDecimal fanliRate = (new BigDecimal(20)).divide(new BigDecimal(100));
        BigDecimal newFanliRate = fanliRate.multiply(new BigDecimal(1.0625)).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(newFanliRate.toString());
        
        BigDecimal newFanli = (new BigDecimal(208)).multiply(newFanliRate).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(newFanli.toString());
        
        BigDecimal discount = (new BigDecimal(208)).subtract(newFanli).divide(new BigDecimal(260), 3, BigDecimal.ROUND_HALF_DOWN)
                .setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(10)).setScale(1, BigDecimal.ROUND_HALF_UP);
        
        System.out.println(discount.toString());
        
        BigDecimal val = new BigDecimal(43.100).setScale(2, BigDecimal.ROUND_HALF_UP);
        DecimalFormat format = new DecimalFormat("0.##");
        
        System.out.println(format.format(val));
	}
	
	public static void test_fb(){
	    BigDecimal price = new BigDecimal(125);
        BigDecimal discout = null;
        String fanli = "5200F币";
        int index = fanli.indexOf("F币");
        if(index > 0){
            discout = new BigDecimal(fanli.substring(0, index));
            discout = discout.divide(new BigDecimal(100));
        }else{
            discout = new BigDecimal(fanli);
        }
        BigDecimal val = price.subtract(discout);
        System.out.println(val);
	}
	
	
	 public static void main(String[] args) {
	     System.out.println(StringUtils.isNumeric("1.0"));
	     
	 }
}
