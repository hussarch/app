package com.hussar.sm.cg;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hussar.sm.cg.common.ClassFieldModuleReader;
import com.hussar.sm.cg.templete.FieldModuleTypeInfo;
import com.hussar.sm.cg.templete.TemplateType;
import com.hussar.sm.cg.templete.entity.FieldModuleAppendInfo;
import com.hussar.sm.cg.templete.entity.FieldModuleInfo;
import com.hussar.sm.entity.dto.FloorDTO;
import com.hussar.sm.entity.dto.ScoreDTO;
import com.hussar.sm.entity.modul.ScoreModel;
import com.hussar.sm.entity.vo.FloorVO;

/**
 * @author yi.xiao
 *
 */
public class ClassFieldModuleReaderTest {

    
    private ClassFieldModuleReader reader;
    
    @Before
    public void setUp() throws Exception {
        reader = new ClassFieldModuleReader();
    }

    @Test
    public void test_getCommonFields() {
        reader.init(ScoreDTO.class, ScoreModel.class);
        List<FieldModuleTypeInfo> actual = reader.getFieldModuleInfoList();
        assertEquals(getExpectedScoreFieldModuleList(), actual);
    }

    private List<FieldModuleTypeInfo> getExpectedScoreFieldModuleList() {
        List<FieldModuleTypeInfo> list = new ArrayList<FieldModuleTypeInfo>();
        list.add(new FieldModuleTypeInfo(new FieldModuleInfo("scoreModel", "delivery")));
        list.add(new FieldModuleTypeInfo(new FieldModuleInfo("scoreModel", "item")));
        list.add(new FieldModuleTypeInfo(new FieldModuleInfo("scoreModel", "service")));
        return list;
    }
    
    @Test
    public void test_getComplexFields() {
        reader.init(FloorVO.class, FloorDTO.class);
        List<FieldModuleTypeInfo> actual = reader.getFieldModuleInfoList();
        assertEquals(getExpectedFloorFieldModuleList(), actual);
    }

    private List<FieldModuleTypeInfo> getExpectedFloorFieldModuleList() {
        List<FieldModuleTypeInfo> list = new ArrayList<FieldModuleTypeInfo>();
        list.add(new FieldModuleTypeInfo(new FieldModuleInfo("floorDTO", "id")));
        list.add(new FieldModuleTypeInfo(new FieldModuleInfo("floorDTO", "name")));
        list.add(new FieldModuleTypeInfo(new FieldModuleInfo("floorDTO", "locationIdSet")));
        list.add(new FieldModuleTypeInfo(TemplateType.LIST_ENTITY, new FieldModuleAppendInfo("ImageVO", "ImageDTO", "floorDTO", "imageList")));
        list.add(new FieldModuleTypeInfo(TemplateType.ENTITY, new FieldModuleAppendInfo("ImageVO", "ImageDTO", "floorDTO", "image")));
        return list;
    }
    
    

}
