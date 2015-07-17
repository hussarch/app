package com.hussar.sm.cg.templete;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import com.hussar.sm.cg.templete.entity.FieldModuleInfo;
import com.hussar.sm.io.CommonFileUtils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author yi.xiao
 *
 */
public class FreeMarkerCodeGenerator {
    
    private TemplateGetter templateGetter;
    
    public FreeMarkerCodeGenerator(){
        this.templateGetter = new TemplateGetter(); 
    }
    
    public FreeMarkerCodeGenerator(File file){
        this.templateGetter = new TemplateGetter(file); 
    }
    
    public void loadTemplateFile(){
        this.templateGetter = new TemplateGetter(new File(this.getClass().getResource("codes_template.ftl").getPath())); 
    }
    
    public List<String> getGeneratedCode(FieldModuleTypeInfo moduleTypeInfo){
        return this.getGeneratedCode(moduleTypeInfo.getType(), moduleTypeInfo.getFieldModuleInfo());
    }
    
    public List<String> getGeneratedCode(TemplateType templateType, Object dataModel){
        String[] array = getMergedValue(templateType.getTag(), dataModel).split("\n");
        return Arrays.asList(array);
    }
    
    public String create(String templateString, FieldModuleInfo fieldModuleInfo) {
        return processTemplate(this.templateGetter.getTemplate("no_name", templateString), fieldModuleInfo);
    }
    
    private String getMergedValue(String templetName, Object dataModel){
        return processTemplate(this.templateGetter.getTemplate(templetName), dataModel);
    }
    
    private String processTemplate(Template template, Object dataModel){
        StringWriter out = new StringWriter(); 
        try {
            template.process(dataModel, out);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            CommonFileUtils.close(out);
        }
        return out.toString();
    }
    
}
