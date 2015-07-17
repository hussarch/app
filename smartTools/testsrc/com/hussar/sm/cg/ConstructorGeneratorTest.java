package com.hussar.sm.cg;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hussar.sm.cg.core.construtor.ConstructorGenerator;
import com.hussar.sm.cg.templete.FieldModuleTypeInfo;
import com.hussar.sm.cg.templete.TemplateType;
import com.hussar.sm.cg.templete.entity.FieldModuleAppendInfo;
import com.hussar.sm.cg.templete.entity.FieldModuleInfo;

/**
 * @author yi.xiao
 *
 */
public class ConstructorGeneratorTest {

    private ConstructorGenerator generator;
    
    @Before
    public void setUp() throws Exception {
        generator = new ConstructorGenerator("ScoreDTO", "ScoreModel");
    }

    @Test
    public void test_notSet() {
        String actual = generator.createEmpty();
        assertEquals(getExpectedEmptyConstructor(), actual);
    }

    private String getExpectedEmptyConstructor() {
        return "    public ScoreDTO(){\n    }";
    }
    
    @Test
    public void test_common_set(){
        String actual = generator.createVoluation(getCommonModuleList());
        assertEquals(getExpectedSetConstructor(), actual);
    }
    
    private List<FieldModuleTypeInfo> getCommonModuleList(){
        List<FieldModuleTypeInfo> list = new ArrayList<FieldModuleTypeInfo>();
        list.add(new FieldModuleTypeInfo(TemplateType.COMMON, new FieldModuleInfo("scoreModel", "delivery")));
        list.add(new FieldModuleTypeInfo(TemplateType.COMMON, new FieldModuleInfo("scoreModel", "item")));
        list.add(new FieldModuleTypeInfo(TemplateType.COMMON, new FieldModuleInfo("scoreModel", "service")));
        return list;
    }
    
    private String getExpectedSetConstructor() {
        return "    public ScoreDTO(ScoreModel scoreModel){\n" +
               "        this.delivery = scoreModel.getDelivery();\n" +
               "        this.item = scoreModel.getItem();\n" +
               "        this.service = scoreModel.getService();\n" +
               "    }";
    }
    
    @Test
    public void test_common_set_complex(){
        String actual = generator.createVoluation(getComplexModuleList());
        assertEquals(getExpectedComplexConstructor(), actual);
    }

    private List<FieldModuleTypeInfo> getComplexModuleList(){
        List<FieldModuleTypeInfo> list = new ArrayList<FieldModuleTypeInfo>();
        list.add(new FieldModuleTypeInfo(TemplateType.COMMON, new FieldModuleInfo("scoreModel", "delivery")));
        list.add(new FieldModuleTypeInfo(TemplateType.ENTITY, new FieldModuleAppendInfo("ItemDTO", "ItemModel", "scoreModel", "item")));
        list.add(new FieldModuleTypeInfo(TemplateType.LIST_ENTITY, new FieldModuleAppendInfo("ImageDTO", "ImageModel", "scoreModel", "imageList")));
        return list;
    }
    
    private String getExpectedComplexConstructor() {
        return  "    public ScoreDTO(ScoreModel scoreModel){\n" +
                "        this.delivery = scoreModel.getDelivery();\n" +
                "        if(scoreModel.getItem() != null){\n" +
                "            this.item = new ItemDTO(scoreModel.getItem());\n"+
                "        }\n" +
                "        if(scoreModel.getImageList() != null){\n" +
                "            this.imageList = new ArrayList<ImageDTO>();\n" +
                "            for(ImageModel imageModel : scoreModel.getImageList()){\n" +
                "                this.imageList.add(new ImageDTO(imageModel));\n" +
                "            }\n" +
                "        }\n" +
                "    }";
    }
}
