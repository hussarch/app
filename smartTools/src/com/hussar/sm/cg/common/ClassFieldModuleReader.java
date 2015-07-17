package com.hussar.sm.cg.common;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hussar.sm.cg.templete.FieldModuleTypeInfo;
import com.hussar.sm.cg.templete.TemplateType;
import com.hussar.sm.cg.templete.entity.FieldModuleAppendInfo;
import com.hussar.sm.cg.templete.entity.FieldModuleInfo;

/**
 * @author yi.xiao
 *
 */
public class ClassFieldModuleReader {

    private Class<?> destClass;
    private Class<?> orginClass;
    private String orginClassInstanceName;

    public ClassFieldModuleReader(Class<?> destClass, Class<?> orginClass){
        init(destClass, orginClass);
    }
    
    public ClassFieldModuleReader() {
    }

    public void init(Class<?> destClass, Class<?> orginClass) {
        this.destClass = destClass;
        this.orginClass = orginClass;
        this.orginClassInstanceName = getIntanceName(orginClass.getSimpleName());
    }

    public List<FieldModuleTypeInfo> getFieldModuleInfoList() {
        List<FieldModuleTypeInfo> list = new ArrayList<FieldModuleTypeInfo>();
        Field[] destfs = destClass.getDeclaredFields();
        List<Field> orginfs = new ArrayList<Field>(Arrays.asList(orginClass.getDeclaredFields()));
        for(Field dest : destfs){
            int size = orginfs.size();
            for(int i =0; i < size; i++){
                if(orginfs.get(i).getName().equals(dest.getName())){
                    FieldModuleTypeInfo fieldModuleTypeInfo = getFieldModuleTypeInfo(dest, orginfs.get(i));
                    if(fieldModuleTypeInfo != null){
                        list.add(fieldModuleTypeInfo);
                    }
                    orginfs.remove(i);
                    break;
                }
            }
        }
        return list;
    }

    private FieldModuleTypeInfo getFieldModuleTypeInfo(Field dest, Field orgin) {
        FieldModuleTypeInfo fieldModuleTypeInfo = null;
        if(dest.getGenericType().equals(orgin.getGenericType())){
            fieldModuleTypeInfo = new FieldModuleTypeInfo(new FieldModuleInfo(this.orginClassInstanceName, orgin.getName()));
        }else{
            Type destType = dest.getGenericType(); 
            Type orignType = orgin.getGenericType(); 
            if ((destType instanceof ParameterizedType) && (orignType instanceof ParameterizedType)) {
                ParameterizedType destParameterizedType = (ParameterizedType) destType;  
                ParameterizedType orginParameterizedType = (ParameterizedType) orignType;
                if(destParameterizedType.getRawType().equals(List.class) 
                        && (orginParameterizedType.getRawType().equals(List.class))){
                    Class<?> actualDestParameterizedType = (Class<?>) destParameterizedType.getActualTypeArguments()[0];
                    Class<?> actualOrginParameterizedType = (Class<?>) orginParameterizedType.getActualTypeArguments()[0];
                    FieldModuleInfo fieldModuleInfo = new FieldModuleAppendInfo(actualDestParameterizedType.getSimpleName(), 
                                                                                actualOrginParameterizedType.getSimpleName(), 
                                                                                this.orginClassInstanceName, orgin.getName()); 
                    fieldModuleTypeInfo = new FieldModuleTypeInfo(TemplateType.LIST_ENTITY, fieldModuleInfo);
                }else{
                    throw new RuntimeException("not support");
                }
            }else{
                FieldModuleInfo fieldModuleInfo = new FieldModuleAppendInfo(((Class<?>) destType).getSimpleName(), 
                                                                            ((Class<?>) orignType).getSimpleName(), 
                                                                            this.orginClassInstanceName, orgin.getName()); 
                fieldModuleTypeInfo = new FieldModuleTypeInfo(TemplateType.ENTITY, fieldModuleInfo);
            }
        }
        return fieldModuleTypeInfo;
    }

    private String getIntanceName(String className){
        return Character.toLowerCase(className.charAt(0)) + className.substring(1);
    }
    
}
