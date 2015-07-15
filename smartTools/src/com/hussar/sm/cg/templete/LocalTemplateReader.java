package com.hussar.sm.cg.templete;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hussar.sm.io.CommonFileUtils;

/**
 * @author yi.xiao
 *
 */
public class LocalTemplateReader {
    
    public static final String END_TEMPLAT_TAG = "";
    
    private List<String> templetContent;
    private Map<String, String> templateMap;
    
    public LocalTemplateReader(){
    }

    public LocalTemplateReader(File file){
        this.setTemplateContent(CommonFileUtils.getContentList(file));
        this.parse();
    }
    
    public void setTemplateContent(List<String> templetContent) {
        this.templetContent = templetContent;
    }

    public String getTemplate(String name) {
        return templateMap.get(name);
    }

    public List<String[]> getTempletDataList() {
        List<String[]> templetList = new ArrayList<String[]>();
        Iterator<String> it = this.templetContent.iterator();
        String line;
        while(it.hasNext()){
            String[] templats = new String[2];
            line = it.next();
            if(END_TEMPLAT_TAG.equals(line)){
                continue;
            }
            templats[0] = line;
            StringBuilder builder = new StringBuilder();
            while(it.hasNext()){
                line = it.next();
                if(END_TEMPLAT_TAG.equals(line)){
                    break;
                }
                if(builder.length() > 0){
                    builder.append("\n");
                }
                builder.append(line);
                
            }
            templats[1] = builder.toString();
            templetList.add(templats);
        }
        return templetList;
    }

    public void parse() {
        List<String[]> list = getTempletDataList();
        int size = list.size();
        templateMap = new HashMap<String, String>();
        String[] data = null;
        for(int i = 0; i < size; i++){
            data = list.get(i);
            templateMap.put(getTempletName(data[0]), data[1]);
        }
    }
    
    private String getTempletName(String name){
        int index = name.indexOf(":");
        if(index > 0){
            return name.substring(0, index);
        }else{
            return name;
        }
    }
}
