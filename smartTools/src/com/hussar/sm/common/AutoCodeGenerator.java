/**
 * 
 */
package com.hussar.sm.common;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yi.xiao
 *
 */
public class AutoCodeGenerator {

    public List<String> getRowList(String content) {
        InputStream stream = new ByteArrayInputStream(content.getBytes());
        return getRowList(new BufferedReader(new InputStreamReader(stream)));
    }

    public List<String> getRowList(BufferedReader reader){
        List<String> list = new ArrayList<String>();
        String row = null;
        try {
            while((row = reader.readLine()) != null){
                list.add(row);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            closeReader(reader);
        }
        return list;
    }

    private void closeReader(BufferedReader reader) {
        try {
            if(reader != null){
                reader.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getGetMethodName(String fieldName) {
        return getMethodName("get", fieldName) + "()";
    }
    
    public String getSetMethodName(String fieldName) {
        return getMethodName("set", fieldName);
    }
    
    public String getMethodName(String start, String fieldName) {
        StringBuilder methodName = new StringBuilder(start);
        methodName.append(Character.toUpperCase(fieldName.charAt(0)));
        for(int i = 1; i < fieldName.length(); i++){
            methodName.append(fieldName.charAt(i));
        }
        return methodName.toString();
    }

    public String getInstanceName(String className) {
        StringBuilder instanceName = new StringBuilder();
        instanceName.append(Character.toLowerCase(className.charAt(0)));
        for(int i = 1; i < className.length(); i++){
            instanceName.append(className.charAt(i));
        }
        return instanceName.toString();
    }

    public List<String> getConstructionMethod1(String clazzName, String decoratedClassName, List<String> fieldNameList) {
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        String instanceName = getInstanceName(decoratedClassName);
        builder.append(getTab(1)).append("public ").append(clazzName).append("(").append(decoratedClassName).append(" ").append(instanceName).append("){");
        list.add(builder.toString());
        for(String fieldName : fieldNameList){
            list.add(getSetter(instanceName, fieldName));
        }
        list.add(getTab(1) + "}");
        return list;
    }
    
    
    private String getTab(int size){
    	StringBuilder builder = new StringBuilder();
    	builder.append("    ");
    	for(int i = 1; i < size; i++){
    		builder.append("    ");
    	}
    	return builder.toString();
    	
    }

    private String getSetter(String instanceName, String fieldName){
        StringBuilder builder = new StringBuilder();
        builder.append(getTab(2)).append("this.").append(getSetMethodName(fieldName)).append("(")
        .append(instanceName).append(".").append(getGetMethodName(fieldName)).append(");");
        return builder.toString();
    }
    
    private String getSetter(String instanceName, FieldTypeInfo fieldTypeInfo) {
    	StringBuilder builder = new StringBuilder();
        builder.append(getTab(2)).append("this.").append(getSetMethodName(fieldTypeInfo.getName())).append("(")
        .append("new ").append(fieldTypeInfo.getOrigFieldType().getSimpleName()).append("(")
        .append(instanceName).append(".").append(getGetMethodName(fieldTypeInfo.getName())).append("));");
        return builder.toString();
	}

	public List<String> getConstructionMethod(String clazzName, String decoratedClassName, List<FieldTypeInfo> fieldTypeList) {
		List<String> list = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		String instanceName = getInstanceName(decoratedClassName);
		builder.append(getTab(1)).append("public ").append(clazzName).append("(").append(decoratedClassName).append(" ").append(instanceName)
				.append("){");
		list.add(builder.toString());
		for (FieldTypeInfo fieldTypeInfo : fieldTypeList) {
			if(isPrimitive(fieldTypeInfo.getOrigFieldType())){
				list.add(getSetter(instanceName, fieldTypeInfo.getName()));
			}else{
				list.add(getSetter(instanceName, fieldTypeInfo));
			}
		}
		list.add(getTab(1) + "}");
		return list;
	}

	private boolean isPrimitive(Class<?> clazz) {
		if (clazz.isPrimitive()){
			return true;
		}
		if (String.class.isAssignableFrom(clazz)) {
			return true;
		} else if (Long.class.isAssignableFrom(clazz)) {
			return true;
		} else if (Integer.class.isAssignableFrom(clazz)) {
			return true;
		} else if (Boolean.class.isAssignableFrom(clazz)) {
			return true;
		} else if (Double.class.isAssignableFrom(clazz)) {
			return true;
		} else if (Number.class.isAssignableFrom(clazz)) {
			return true;
		} else if (Date.class.isAssignableFrom(clazz)) {
			return true;
		}
		return false;
	}

	public String getGeneratedCode(String originInstanceName, FieldTypeInfo fieldTypeInfo) {
		StringBuilder builder = new StringBuilder("this.");
		builder.append(getSetMethodName(fieldTypeInfo.getName())).append("(");
		if(isPrimitive(fieldTypeInfo.getDestFieldType())){
			builder.append(getInstanceValue(originInstanceName, fieldTypeInfo.getName()));
		}else{
			builder.append("new ").append(fieldTypeInfo.getDestFieldType().getSimpleName())
			.append("(").append(getInstanceValue(originInstanceName, fieldTypeInfo.getName())).append(")");
		}
		builder.append(");");
		return builder.toString();
	}
	
	private String getInstanceValue(String originInstanceName, String fieldName){
		StringBuilder builder = new StringBuilder();
		builder.append(originInstanceName).append(".").append(getGetMethodName(fieldName));
		return builder.toString();
	}
    
}
