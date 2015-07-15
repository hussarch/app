package com.hussar.sm.cg;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hussar.sm.entity.dto.ImageDTO;



public class AutoCodeTest {
    
    private AutoCodeGenerator generator;

    @Before
    public void setUp() throws Exception {
        generator = new AutoCodeGenerator();
    }

    @Test
    public void test_get_row_list() {
        List<String> actual = generator.getRowList(getMockRow());
        assertEquals(getRowList(), actual);
    }

    private List<String> getRowList() {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("welcome");
        list.add("we have a mind");
        return list;
    }

    private String getMockRow() {
        return "abc\n" +
                "welcome\n" +
                "we have a mind";
    }
    
    @Test
    public void testGetMethod(){
        assertEquals("getClickUrl()", generator.getGetMethodName("clickUrl"));
    }
    
    @Test
    public void testSetMethod(){
        assertEquals("setClickUrl", generator.getSetMethodName("clickUrl"));
    }
    
    @Test
    public void testGetInstanceName(){
        assertEquals("clickUrl", generator.getInstanceName("ClickUrl"));
    }
    
    
    private List<String> getMockMethodList(){
        List<String> list = new ArrayList<>();
        list.add("id");
        list.add("url");
        list.add("name");
        list.add("md5");
        return list;
    }
    
    private List<String> getExpectedConstructionMethodList(){
        List<String> list = new ArrayList<>();
        list.add("    public FooTDO(FooModule fooModule){");
        list.add("        this.setId(fooModule.getId());");
        list.add("        this.setUrl(fooModule.getUrl());");
        list.add("        this.setName(fooModule.getName());");
        list.add("        this.setMd5(fooModule.getMd5());");
        list.add("    }");
        return list;
    }
    
    private List<OrigDestFieldInfo> getMockFieldInfoList(){
    	List<OrigDestFieldInfo> fieldList = new ArrayList<>();
    	fieldList.add(new OrigDestFieldInfo(String.class, "id"));
    	fieldList.add(new OrigDestFieldInfo(String.class, "name"));
    	fieldList.add(new OrigDestFieldInfo(ImageDTO.class, "image"));
    	return fieldList;
    }
    
    private List<String> getExpectedConstructionMethodList1(){
        List<String> list = new ArrayList<>();
        list.add("    public FooTDO(FooModule fooModule){");
        list.add("        this.setId(fooModule.getId());");
        list.add("        this.setName(fooModule.getName());");
        list.add("        this.setImage(new ImageDTO(fooModule.getImage()));");
        list.add("    }");
        return list;
    }
    
    private List<String> getExpectedConstructionMethodList2(){
        List<String> list = new ArrayList<>();
        list.add("    public FooTDO(FooModule fooModule){");
        list.add("        this.setId(fooModule.getId());");
        list.add("        this.setName(fooModule.getName());");
        list.add("        this.setImage(new ImageDTO(fooModule.getImage()));");
        list.add("        if(fooModule.getImageList() != null){");
        list.add("            this.setImageList(new ArrayList<ImageDTO>());");
        list.add("            for(Image image : fooModule.getImageList()){");
        list.add("                 this.getImageList().add(new ImageDTO(image))");
        list.add("            }");
        list.add("        }");
        list.add("    }");
        return list;
    }
    
    
    @Test
    public void test_getGeneratedCode(){
    	List<String> actual = generator.getGeneratedCode("fooModule", new OrigDestFieldInfo(String.class, "id"));
    	assertEquals("this.setId(fooModule.getId());", actual.get(0));
    	
    	actual = generator.getGeneratedCode("fooModule", new OrigDestFieldInfo(ImageDTO.class, "image"));
    	assertEquals(getMockImageDTO(), actual);
    }
    
    private List<String> getMockImageDTO(){
        List<String> list = new ArrayList<>();
        list.add("if(fooModule.getImage() != null){");
        list.add("    this.setImage(new ImageDTO(fooModule.getImage()));");
        list.add("}");
        return list;
    }
    
    private String getStrList(){
    	StringBuilder builder = new StringBuilder();
    	builder.append("if(fooModule.getPositionList() != null){").append("\n");
    	builder.append("    this.setPositionList(new ArrayList<String>(fooModule.getPositionList());").append("\n");
    	builder.append("}");
    	return builder.toString();
    }
    
    @Test
    public void test_getEvaluation(){
        String actual = generator.getEvaluation("moo", "name");
        assertEquals("this.setName(moo.getName());", actual);
    }
    
    @Test
    public void test_getEvaluationList(){
        List<String> actualList = generator.getEvaluationList("ImageDTO", "fooModule", "image");
        assertEquals(getMockImageDTO(), actualList);
    }
    
    @Test
    public void test_getEvaluationList_withStringList(){
        List<String> actualList = generator.getEvaluationList("fooModule", "urlList");
        assertEquals(getMockUrlList("String"), actualList);
    }

    private List<String> getMockUrlList(String type) {
        List<String> list = new ArrayList<>();
        list.add("if(fooModule.getUrlList() != null){");
        list.add("    this.setUrlList(new ArrayList<" + type +  ">(fooModule.getUrlList()));");
        list.add("}");
        return list;
    }
    
    @Test
    public void test_getEvaluationList_withIntList(){
        List<String> actualList = generator.getEvaluationList("Integer", "fooModule", "urlList");
        assertEquals(getMockUrlList("Integer"), actualList);
    }
    
}
