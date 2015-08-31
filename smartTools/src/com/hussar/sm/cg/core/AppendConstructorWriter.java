package com.hussar.sm.cg.core;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hussar.sm.cg.common.LocationLineFilter;
import com.hussar.sm.cg.core.construtor.ConstructorGenerator;
import com.hussar.sm.cg.core.pair.PairClassGetter;
import com.hussar.sm.cg.core.pair.PairClassInfo;
import com.hussar.sm.io.CommonFileUtils;

/**
 * @author yi.xiao
 *
 */
public class AppendConstructorWriter {
    
    private String currentEntityRootPath;
    private String newEntityRootPath;
    private PairClassGetter pairClassGetter;
    
    public AppendConstructorWriter(String currentEntityRootPath, String newEntityRootPath){
        this.currentEntityRootPath = currentEntityRootPath;
        this.newEntityRootPath = newEntityRootPath;
        this.pairClassGetter = new PairClassGetter();
    }
    
    
    public void create(PairClassInfo pairClassInfo){
        List<PairClassInfo> list = this.pairClassGetter.find(pairClassInfo);
        ConstructorGenerator constructorGenerator = null;
        for(PairClassInfo classInfo : list){
            if(!constructorExist(classInfo.getDestClass(), classInfo.getOrginClass())){
                constructorGenerator = new ConstructorGenerator(classInfo);
                appendConstructor(classInfo.getDestClass(), "import " +  classInfo.getOrginClass().getName(), constructorGenerator.getMethod());
            }
        }
    }

    private void appendConstructor(Class<?> clazz, String importPackage , String method) {
        List<String> contentList = CommonFileUtils.getContentList(getCurrentEntityFile(clazz));
        int size = contentList.size();
        int i = 0;
        String line;
        LocationLineFilter importLocation = new LocationLineFilter(new String[]{"/**", "public class " + clazz.getSimpleName()});
        LocationLineFilter constructorLocation = new LocationLineFilter(new String[]{"()", "("});
        while(i < size){
            line = contentList.get(i);
            if(importLocation.match(line, i) && constructorLocation.match(line, i)){
                break;
            }
            i++;
        }
        appendCode(contentList, constructorLocation.getLine(), method);
        appendCode(contentList, importLocation.getLine(), importPackage);
        CommonFileUtils.writeFile(getNewEntityFile(clazz), contentList);
    }
    
    private void appendCode(List<String> contentList, int index, String code){
        if(StringUtils.isNotBlank(contentList.get(index - 1))){
            contentList.add(index - 1, "");
        }
        if(StringUtils.isNotBlank(contentList.get(index))){
            contentList.add(index, "");
        }
        contentList.add(index, code);
    }


    public File getCurrentEntityFile(Class<?> clazz) {
        return getEntityFile(currentEntityRootPath, clazz);
    }

    public File getNewEntityFile(Class<?> clazz) {
        return getEntityFile(newEntityRootPath, clazz);
    }
    
    public File getEntityFile(String rootPath, Class<?> clazz) {
        StringBuilder builder = new StringBuilder();
        builder.append(rootPath).append(File.separator);
        builder.append(clazz.getName().replace(".", File.separator)).append(".java");
        return new File(builder.toString());
    }

    public boolean constructorExist(Class<?> destClass, Class<?> orginClass) {
        try {
            Constructor<?> constructor = destClass.getConstructor(orginClass);
            return constructor != null;
        } catch (NoSuchMethodException e) {
            return false;
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        }
    }


}
