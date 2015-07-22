package com.hussar.sm.cg.core.pair;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hussar.sm.cg.core.pair.PairClassGetter;
import com.hussar.sm.cg.core.pair.PairClassInfo;
import com.hussar.sm.entity.dto.BrandDTO;
import com.hussar.sm.entity.dto.FloorDTO;
import com.hussar.sm.entity.dto.ImageDTO;
import com.hussar.sm.entity.dto.ImageHDDTO;
import com.hussar.sm.entity.dto.MooDTO;
import com.hussar.sm.entity.dto.ProductDTO;
import com.hussar.sm.entity.dto.ScoreDTO;
import com.hussar.sm.entity.modul.ScoreModel;
import com.hussar.sm.entity.vo.BrandVO;
import com.hussar.sm.entity.vo.FloorVO;
import com.hussar.sm.entity.vo.ImageHDVO;
import com.hussar.sm.entity.vo.ImageVO;
import com.hussar.sm.entity.vo.MooVO;
import com.hussar.sm.entity.vo.ProductVO;

/**
 * @author yi.xiao
 *
 */
public class PairClassGetterTest {

    private PairClassGetter getter;
    
    @Before
    public void setUp() throws Exception {
        this.getter = new PairClassGetter();
    }

    @Test
    public void test_find_Score_entity() {
        List<PairClassInfo> actual = this.getter.find(new PairClassInfo(ScoreDTO.class, ScoreModel.class));
        assertEquals(getExpectedScoreList(), actual);
    }

    private List<PairClassInfo> getExpectedScoreList() {
        List<PairClassInfo> list = new ArrayList<PairClassInfo>();
        list.add(new PairClassInfo(ScoreDTO.class, ScoreModel.class));
        return list;
    }
    
    @Test
    public void test_find_Floor_entity() {
        List<PairClassInfo> actual = this.getter.find(new PairClassInfo(FloorVO.class, FloorDTO.class));
        assertEquals(getExpectedFloorList(), actual);
    }

    private Object getExpectedFloorList() {
        List<PairClassInfo> list = new ArrayList<PairClassInfo>();
        list.add(new PairClassInfo(FloorVO.class, FloorDTO.class));
        list.add(new PairClassInfo(ImageVO.class, ImageDTO.class));
        list.add(new PairClassInfo(ScoreDTO.class, ScoreModel.class));
        return list;
    }
    
    @Test
    public void test_find_Product_entity() {
        List<PairClassInfo> actual = this.getter.find(new PairClassInfo(ProductVO.class, ProductDTO.class));
        assertEquals(getExpectedProductList(), actual);
    }

    private List<PairClassInfo> getExpectedProductList() {
        List<PairClassInfo> list = new ArrayList<PairClassInfo>();
        list.add(new PairClassInfo(ProductVO.class, ProductDTO.class));
        list.add(new PairClassInfo(FloorVO.class, FloorDTO.class));
        list.add(new PairClassInfo(BrandVO.class, BrandDTO.class));
        list.add(new PairClassInfo(ImageVO.class, ImageDTO.class));
        list.add(new PairClassInfo(ScoreDTO.class, ScoreModel.class));
        return list;
    }
    
    @Test
    public void test_find_super_image(){
        List<PairClassInfo> actual = this.getter.find(new PairClassInfo(ImageHDVO.class, ImageHDDTO.class));
        assertEquals(getExpectedImageHDList(), actual);
    }

    private List<PairClassInfo> getExpectedImageHDList() {
        List<PairClassInfo> list = new ArrayList<PairClassInfo>();
        list.add(new PairClassInfo(ImageHDVO.class, ImageHDDTO.class));
        list.add(new PairClassInfo(ImageVO.class, ImageDTO.class));
        return list;
    }
    
    @Test
    public void test_find_Moo_entity(){
        List<PairClassInfo> actual = this.getter.find(new PairClassInfo(MooVO.class, MooDTO.class));
        assertEquals(getExpectedMooList(), actual);
    }

    private List<PairClassInfo> getExpectedMooList() {
        List<PairClassInfo> list = new ArrayList<PairClassInfo>();
        list.add(new PairClassInfo(MooVO.class, MooDTO.class));
        list.add(new PairClassInfo(ProductVO.class, ProductDTO.class));
        list.add(new PairClassInfo(ImageHDVO.class, ImageHDDTO.class));
        list.add(new PairClassInfo(FloorVO.class, FloorDTO.class));
        list.add(new PairClassInfo(BrandVO.class, BrandDTO.class));
        list.add(new PairClassInfo(ImageVO.class, ImageDTO.class));
        list.add(new PairClassInfo(ScoreDTO.class, ScoreModel.class));
        return list;
    }
    
}
