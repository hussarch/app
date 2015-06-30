/**
 * 
 */
package com.hussar.sm.cg;

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
    
    private String getSetter(String instanceName, OrigDestFieldInfo fieldTypeInfo) {
    	StringBuilder builder = new StringBuilder();
        builder.append(getTab(2)).append("this.").append(getSetMethodName(fieldTypeInfo.getFieldName())).append("(")
        .append("new ").append(fieldTypeInfo.getDest().getSimpleName()).append("(")
        .append(instanceName).append(".").append(getGetMethodName(fieldTypeInfo.getFieldName())).append("));");
        return builder.toString();
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

	public List<String> getGeneratedCode(String originInstanceName, OrigDestFieldInfo fieldTypeInfo) {
	    List<String> list = new ArrayList<>();
		if(fieldTypeInfo.isPrimitiveField()){
			list.add(getEvaluation(originInstanceName, fieldTypeInfo.getFieldName()));
		}else{
		    list.addAll(getEvaluationList(fieldTypeInfo.getDest().getSimpleName(), originInstanceName, fieldTypeInfo.getFieldName()));
		}
		return list;
	}
	
	public String getEvaluation(String originInstanceName, String fieldName){
	    StringBuilder builder = new StringBuilder();
	    builder.append(getFieldSetterMethod("this", fieldName)).append("(");
        builder.append(getFieldGetterMethod(originInstanceName, fieldName));
        builder.append(");");
        return builder.toString();
	}
	
	public List<String> getEvaluationList(String targetFeildType, String originInstanceName, String fieldName) {
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append("if(").append(getFieldGetterMethod(originInstanceName, fieldName)).append(" != null){");
        list.add(builder.toString());
        builder.setLength(0);
        builder.append(getTab(1)).append(getFieldSetterMethod("this", fieldName))
        .append("(").append("new ").append(targetFeildType).append("(")
        .append(getFieldGetterMethod(originInstanceName, fieldName))
        .append("));");
        list.add(builder.toString());
        list.add("}");
        return list;
    }
	
	private String getImplement(String name){
	    if("List".equals(name)){
	        return "ArrayList";
	    }
	    return null;
	}
	
	private String getFieldGetterMethod(String instance, String fieldName){
		StringBuilder builder = new StringBuilder();
		builder.append(instance).append(".").append(getGetMethodName(fieldName));
		return builder.toString();
	}
	
	private String getFieldSetterMethod(String instance, String fieldName){
        StringBuilder builder = new StringBuilder();
        builder.append(instance).append(".").append(getSetMethodName(fieldName));
        return builder.toString();
    }

    public List<String> getEvaluationList(String originInstanceName, String fieldName) {
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append("if(").append(getFieldGetterMethod(originInstanceName, fieldName)).append(" != null){");
        list.add(builder.toString());
        builder.setLength(0);
        builder.append(getTab(1)).append(getFieldSetterMethod("this", fieldName))
        .append("(").append("new ArrayList<String>(")
        .append(getFieldGetterMethod(originInstanceName, fieldName))
        .append("));");
        list.add(builder.toString());
        list.add("}");
        return list;
    }
    
    
}
