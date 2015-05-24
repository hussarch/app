package com.hussar.sm.common;

import static org.junit.Assert.assertEquals;

import java.awt.Image;
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
    
    @Test public void testGenerateConstructionMethod(){
        List<String> actual = generator.getConstructionMethod1("FooTDO", "FooModule", getMockMethodList());
        assertEquals(getExpectedConstructionMethodList(), actual);
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
    
    @Test
    public void test_generateConstructionMethod(){
    	 List<String> actual = generator.getConstructionMethod("FooTDO", "FooModule", getMockFieldInfoList());
         assertEquals(getExpectedConstructionMethodList1(), actual);
    }
    
    private List<FieldTypeInfo> getMockFieldInfoList(){
    	List<FieldTypeInfo> fieldList = new ArrayList<>();
    	fieldList.add(new FieldTypeInfo(String.class, "id"));
    	fieldList.add(new FieldTypeInfo(String.class, "name"));
    	fieldList.add(new FieldTypeInfo(ImageDTO.class, "image"));
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
    
    @Test
    public void test_generateConstructionMethod_withList(){
    	List<FieldTypeInfo> fieldList = getMockFieldInfoList();
    	List<Image> imgeList1 = new ArrayList<>();
    	List<ImageDTO> imgeList = new ArrayList<>();
     	fieldList.add(new FieldTypeInfo(imgeList.getClass(), imgeList1.getClass(), "imageList"));
     	
    	List<String> actual = generator.getConstructionMethod("FooTDO", "FooModule", fieldList);
        assertEquals(getExpectedConstructionMethodList2(), actual);
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
    	String actual = generator.getGeneratedCode("fooModule", new FieldTypeInfo(String.class, "id"));
    	assertEquals("this.setId(fooModule.getId());", actual);
    	
    	actual = generator.getGeneratedCode("fooModule", new FieldTypeInfo(ImageDTO.class, "image"));
    	assertEquals("this.setImage(new ImageDTO(fooModule.getImage()));", actual);
    	
    	List<String> strList = new ArrayList<>();
    	actual = generator.getGeneratedCode("fooModule", new FieldTypeInfo(strList.getClass(), "positionList"));
    	assertEquals(getStrList(), actual);
    }
    
    private String getStrList(){
    	StringBuilder builder = new StringBuilder();
    	builder.append("if(fooModule.getPositionList() != null){").append("\n");
    	builder.append("    this.setPositionList(new ArrayList<String>();").append("\n");
    	builder.append("    this.getPositionList().addAll(fooModule.getPositionList())").append("\n");
    	builder.append("}");
    	return builder.toString();
    }
    
}
