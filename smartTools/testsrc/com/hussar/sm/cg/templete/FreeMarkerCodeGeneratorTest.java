package com.hussar.sm.cg.templete;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.hussar.sm.cg.templete.entity.FieldModuleAppendInfo;
import com.hussar.sm.cg.templete.entity.FieldModuleInfo;

public class FreeMarkerCodeGeneratorTest {

    private FreeMarkerCodeGenerator generator;
    
    @Before
    public void setUp() throws Exception {
        generator = new FreeMarkerCodeGenerator();
    }

    @Test
    public void test_getCommonFieldModuleInfo() {
        String actual = generator.create(getCommonTemplet(), getCommonFieldModuleInfo());
        assertEquals(getCommonMockGenratedCode(), actual);
    }
    
    private FieldModuleInfo getCommonFieldModuleInfo(){
        return new FieldModuleInfo("image", "url");
    }

    private String getCommonTemplet(){
        return "this.set${fieldName?cap_first}(${origInstanceName}.get${fieldName?cap_first}());";
    }
    
    private String getCommonMockGenratedCode(){
        return "this.setUrl(image.getUrl());";
    }
    
    @Test
    public void test_getIfNullFieldModuleInfo() {
        String actual = generator.create(getIfNullTemplet(), getIfNullFieldModuleInfo());
        assertEquals(getIfNullGenratedCode(), actual);
    }
    
    private String getIfNullTemplet(){
        return "if(${origInstanceName}.get${fieldName?cap_first}() != null){\n" +
                "    this.set${fieldName?cap_first}(${origInstanceName}.get${fieldName?cap_first}());\n" +
                "}";
    }
    
    private FieldModuleInfo getIfNullFieldModuleInfo(){
        FieldModuleInfo info = new FieldModuleInfo();
        info.setFieldName("urlList");
        info.setOrigInstanceName("image");
        info.setPrimitiveFlag(false);
        return info;
    }
    
    private String getIfNullGenratedCode(){
        return "if(image.getUrlList() != null){\n" +
                "    this.setUrlList(image.getUrlList());\n" +
                "}";
    }
    
    @Test
    public void test_genarateListArrayList(){
        String actual = generator.create(getListTemplet(), getFieldModuleAppendInfo());
        assertEquals(getListExpectedGenerateCode(), actual);
        
    }
    
    private FieldModuleAppendInfo getFieldModuleAppendInfo(){
        FieldModuleAppendInfo info = new FieldModuleAppendInfo();
        info.setFieldName("actionList");
        info.setOrigInstanceName("image");
        info.setOrigObjName("ActionModule");
        info.setDestObjName("ActionDTO");
        return info;
    }
    
    private String getListTemplet(){
        return "if(${origInstanceName}.get${fieldName?cap_first}() != null){\n" +
               "    List<${destObjName}> list = new ArrayList<${destObjName}>();\n" +
               "    for(${origObjName} ${origObjName?uncap_first} : ${origInstanceName}.get${fieldName?cap_first}()){\n" +
               "        list.add(new ${destObjName}(${origObjName?uncap_first}));\n" +
               "    }\n" +
               "    this.set${fieldName?cap_first}(list);\n" +
               "}";
    }
    
    private String getListExpectedGenerateCode(){
        return "if(image.getActionList() != null){\n" +
                "    List<ActionDTO> list = new ArrayList<ActionDTO>();\n" +
                "    for(ActionModule actionModule : image.getActionList()){\n" +
                "        list.add(new ActionDTO(actionModule));\n" +
                "    }\n" +
                "    this.setActionList(list);\n" +
                "}";
    }
    
}
