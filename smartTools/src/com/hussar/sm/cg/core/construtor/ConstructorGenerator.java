package com.hussar.sm.cg.core.construtor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hussar.sm.cg.common.ClassFieldModuleReader;
import com.hussar.sm.cg.templete.FieldModuleTypeInfo;
import com.hussar.sm.cg.templete.FreeMarkerCodeGenerator;
import com.hussar.sm.cg.templete.TemplateType;

/**
 * @author yi.xiao
 *
 */
public class ConstructorGenerator {
    
    private String dstClazzName;
    private String orignClazzName;
    private static final String TAB_SPACE = "    ";
    private static final String NEW_LINE = "\n";
    
    private ClassFieldModuleReader classFieldModuleReader;
    private FreeMarkerCodeGenerator codeGenerator;
    
    public ConstructorGenerator(Class<?> destClass, Class<?> orginClass) {
        this(destClass.getSimpleName(), orginClass.getSimpleName());
        classFieldModuleReader = new ClassFieldModuleReader(destClass, orginClass);
    }
    
    public ConstructorGenerator(String dstClazzName, String orignClazzName) {
        this.dstClazzName = dstClazzName;
        this.orignClazzName = orignClazzName;
        this.codeGenerator = new FreeMarkerCodeGenerator();
        this.codeGenerator.loadTemplateFile();
    }

    public String getMethod(){
        return this.createVoluation(classFieldModuleReader.getFieldModuleInfoList());
    }
    
    public String createEmpty() {
        List<Object> list = new ArrayList<Object>();
        list.add(getBuilder("public ").append(this.dstClazzName).append("()").append("{"));
        list.add("}");
        return create(list);
    }
    
    private StringBuilder getBuilder(String val){
        return new StringBuilder(val);
    }
    
    private String create(List<?> list){
        StringBuilder builder = new StringBuilder();
        int size = list.size();
        for(int i = 0; i < size; i++){
            builder.append(TAB_SPACE).append(list.get(i));
            if(i < size - 1){
                builder.append(NEW_LINE);
            }
        }
        return builder.toString();
    }

    public String createVoluation(List<FieldModuleTypeInfo> moduleList) {
        List<String> list = new ArrayList<String>();
        list.addAll(codeGenerator.getGeneratedCode(TemplateType.CONSTRUCTOR, getConstructorModule()));
        for(FieldModuleTypeInfo moduleTypeInfo : moduleList){
            list.addAll(getGeneratedCode(moduleTypeInfo, 1));
        }
        list.add("}");
        return create(list);
    }
    
    private List<String> getGeneratedCode(FieldModuleTypeInfo moduleTypeInfo, int tabSize){
        List<String> list = codeGenerator.getGeneratedCode(moduleTypeInfo);
        int size = list.size();
        String tab = getTabs(tabSize);
        for(int i = 0; i < size; i++){
            list.set(i, tab + list.get(i));
        }
        return list;
    }

    private String getTabs(int tabSize) {
        StringBuilder builder = new StringBuilder();
        for(int i =0; i < tabSize; i++){
            builder.append(TAB_SPACE);
        }
        return builder.toString();
    }

    private Map<String, String> getConstructorModule() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("dstClazzName", this.dstClazzName);
        map.put("orignClazzName", this.orignClazzName);;
        return map;
    }
    
    
}
