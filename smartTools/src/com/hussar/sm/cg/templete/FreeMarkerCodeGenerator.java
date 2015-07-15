package com.hussar.sm.cg.templete;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

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
    
    public String getGeneratedCode(String templatName, FieldModuleInfo fieldModuleInfo){
        return getMergedValue(templatName, fieldModuleInfo);
    }
    
    public String create(String templateString, FieldModuleInfo fieldModuleInfo) {
        return processTemplate(this.templateGetter.getTemplate("no_name", templateString), fieldModuleInfo);
    }
    
    private String getMergedValue(String templetName, FieldModuleInfo fieldModuleInfo){
        return processTemplate(this.templateGetter.getTemplate(templetName), fieldModuleInfo);
    }
    
    private String processTemplate(Template template, FieldModuleInfo fieldModuleInfo){
        StringWriter out = new StringWriter(); 
        try {
            template.process(fieldModuleInfo, out);
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
