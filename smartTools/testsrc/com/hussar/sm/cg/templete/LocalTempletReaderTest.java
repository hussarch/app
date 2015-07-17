package com.hussar.sm.cg.templete;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author yi.xiao
 *
 */
public class LocalTempletReaderTest {

    
    private static final String END_TEMPLAT_TAG = "";
    private LocalTemplateReader reader;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        reader = new LocalTemplateReader();
    }

    @Test
    public void parsertTemplet_only_1(){
        reader.setTemplateContent(getTempletContent());
        assertArrayInListEquals(getCommonExpectedTempletData(), reader.getTempletDataList());
    }
    
    @Test
    public void parsertTemplet_with_2(){
        reader.setTemplateContent(getTempletContent2());
        assertArrayInListEquals(getCommonExpectedTempletData2(), reader.getTempletDataList());
    }
    
    @Test
    public void parsertTemplet_with_3(){
        reader.setTemplateContent(getTempletContent3());
        assertArrayInListEquals(getExpectedTempletContent3(), reader.getTempletDataList());
    }
    

    private <T> void assertArrayInListEquals(List<T[]> expected, List<T[]> actual){
        if(expected == actual){
            assertTrue(true);
        }else{
            if(expected == null || actual == null){
                assertTrue(false);
            }else{
                int size = expected.size();
                if(size != actual.size()){
                    assertTrue(false);
                }else{
                    for(int i = 0; i < size; i++){
                        assertTrue(Arrays.equals(expected.get(i), actual.get(i)));
                    }
                }
            }
        }
    }
    
    private List<String[]> getCommonExpectedTempletData(){
        List<String[]> list = new ArrayList<String[]>();
        list.add(new String[]{"common:", getCommonTemplet()});
        return list;
    }
    
    
    private List<String[]> getCommonExpectedTempletData2(){
        List<String[]> list = getCommonExpectedTempletData();
        list.add(new String[]{"entity:", getEntityTemplet()});
        return list;
    }
    
    private List<String[]> getExpectedTempletContent3() {
        List<String[]> list = getCommonExpectedTempletData2();
        list.add(new String[]{"List<entity>:", getListTemplet()});
        return list;
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
    
    private List<String> getTempletContent3(){
        List<String> list = getTempletContent2();
        list.add("List<entity>:");
        list.add("if(${origInstanceName}.get${fieldName?cap_first}() != null){");
        list.add("    List<${destObjName}> list = new ArrayList<${destObjName}>();");
        list.add("    for(${origObjName} ${origObjName?uncap_first} : ${origInstanceName}.get${fieldName?cap_first}()){");
        list.add("        list.add(new ${destObjName}(${origObjName?uncap_first}));");
        list.add("    }");
        list.add("    this.set${fieldName?cap_first}(list);");
        list.add("}");
        list.add(END_TEMPLAT_TAG);
        return list;
    }
    
    @Test 
    public void test_getCommonTemplet() {
        reader.setTemplateContent(getTempletContent());
        reader.parse();
        assertEquals(getCommonTemplet(), reader.getTemplate("common"));
    }
    
    private List<String> getTempletContent(){
        List<String> list = new ArrayList<String>();
        list.add("common:");
        list.add(getCommonTemplet());
        list.add(END_TEMPLAT_TAG);
        return list;
    }
    
    private List<String> getTempletContent2(){
        List<String> list = getTempletContent();
        list.add("entity:");
        list.add("if(${origInstanceName}.get${fieldName?cap_first}() != null){");
        list.add("    this.set${fieldName?cap_first}(${origInstanceName}.get${fieldName?cap_first}());");
        list.add("}");
        list.add(END_TEMPLAT_TAG);
        return list;
    }
    
    private String getEntityTemplet(){
        return "if(${origInstanceName}.get${fieldName?cap_first}() != null){\n" +
                "    this.set${fieldName?cap_first}(${origInstanceName}.get${fieldName?cap_first}());\n" +
                "}";
    }
    
    private String getCommonTemplet() {
        return "this.set${fieldName?cap_first}(${origInstanceName}.get${fieldName?cap_first}());";
    }
    
    
}
