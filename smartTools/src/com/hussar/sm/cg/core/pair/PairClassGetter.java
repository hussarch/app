package com.hussar.sm.cg.core.pair;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yi.xiao
 *
 */
public class PairClassGetter {
    
    private static final String ALLOWED_PACKAGE = "com.hussar.sm";

    public List<PairClassInfo> find(PairClassInfo pairClassInfo) {
        List<PairClassInfo> list = new ArrayList<PairClassInfo>();
        list.add(pairClassInfo);
        Field[] destfs = pairClassInfo.getDestClass().getDeclaredFields();
        List<Field> orginfs = new ArrayList<Field>(Arrays.asList(pairClassInfo.getOrginClass().getDeclaredFields()));
        for (Field dest : destfs) {
            int size = orginfs.size();
            for (int i = 0; i < size; i++) {
                if (orginfs.get(i).getName().equals(dest.getName())) {
                    PairClassInfo info = getFieldPairClassInfo(dest, orginfs.get(i));
                    if(info != null && !list.contains(info)){
                        list.add(info);
                    }
                    orginfs.remove(i);
                    break;
                }
            }
        }
        PairClassInfo parent = getParentPairClassInfo(pairClassInfo);
        if(parent != null){
            if(!list.contains(parent)){
                list.add(parent);
            }
            pairClassInfo.setHasSuperClass(true);
        }
        List<PairClassInfo> innerList = new ArrayList<PairClassInfo>();
        int size = list.size();
        for(int i = 1; i < size; i++){
            innerList.addAll(find(list.get(i)));
        }
        for(PairClassInfo info : innerList){
            if(!list.contains(info)){
                list.add(info);
            }
        }
        return list;
    }

    private PairClassInfo getFieldPairClassInfo(Field dest, Field orgin) {
        if(dest.getGenericType().equals(orgin.getGenericType())){
            return null;
        }else{
            Type destType = dest.getGenericType(); 
            Type orignType = orgin.getGenericType(); 
            if ((destType instanceof ParameterizedType) && (orignType instanceof ParameterizedType)) {
                ParameterizedType destParameterizedType = (ParameterizedType) destType;  
                ParameterizedType orginParameterizedType = (ParameterizedType) orignType;
                if(destParameterizedType.getRawType().equals(List.class) 
                        && (orginParameterizedType.getRawType().equals(List.class))){
                    return new PairClassInfo((Class<?>) destParameterizedType.getActualTypeArguments()[0], 
                                              (Class<?>) orginParameterizedType.getActualTypeArguments()[0]);
                }else{
                    throw new RuntimeException("not support");
                }
            }else{
                return new PairClassInfo(((Class<?>) destType), ((Class<?>) orignType));
            }
        }
    }
    
    private PairClassInfo getParentPairClassInfo(PairClassInfo pairClassInfo){
        Class<?> destClass = pairClassInfo.getDestClass();
        Class<?> orginClass = pairClassInfo.getOrginClass(); 
        if(valid(destClass.getSuperclass()) && valid(orginClass.getSuperclass())){
            return new PairClassInfo(destClass.getSuperclass(), orginClass.getSuperclass());
        }else{
            return null;
        }
    }
    
    private boolean valid(Class<?> clazz){
        if(Object.class.equals(clazz)){
            return false;
        }else{
            return clazz.getName().startsWith(ALLOWED_PACKAGE);
        }
    }

}
