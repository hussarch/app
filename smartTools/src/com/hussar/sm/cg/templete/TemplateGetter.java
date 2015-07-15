package com.hussar.sm.cg.templete;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * @author yi.xiao
 *
 */
public class TemplateGetter {

    private Configuration cfg;
    private LocalTemplateReader templetReader;
    private Map<String, Template> templateMap;
    
    public TemplateGetter() {
    }
    
    public TemplateGetter(File file) {
        init(file);
    }

    public void init(File file){
        templateMap = new HashMap<String, Template>();
        cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        templetReader = new LocalTemplateReader(file);
    }
    
    public Template getTemplate(String name){
        Template template = this.templateMap.get(name);
        if(template == null){
            template = getTemplate(name, this.templetReader.getTemplate(name));
            this.templateMap.put(name, template);
        }
        return template;
    }
    
    public Template getTemplate(String name, String sourceCode){
        try {
            return new Template(name, sourceCode, cfg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        
}
