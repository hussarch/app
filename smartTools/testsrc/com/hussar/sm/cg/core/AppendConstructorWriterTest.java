package com.hussar.sm.cg.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.hussar.sm.cg.core.pair.PairClassInfo;
import com.hussar.sm.entity.dto.MooDTO;
import com.hussar.sm.entity.dto.ScoreDTO;
import com.hussar.sm.entity.modul.ScoreModel;
import com.hussar.sm.entity.vo.MooVO;

/**
 * @author yi.xiao
 *
 */
public class AppendConstructorWriterTest {

    private AppendConstructorWriter writer;
    
    @Before
    public void setUp() throws Exception {
        writer = new AppendConstructorWriter("E:/git_repositories/app/smartTools/testsrc", "D:/tmp/new_codes");
    }

    @Test
    public void test_getEntityFile() {
        File actual = writer.getCurrentEntityFile(ScoreDTO.class);
        assertEquals(new File("E:/git_repositories/app/smartTools/testsrc/com/hussar/sm/entity/dto/ScoreDTO.java"), actual);
    }
    
    @Test
    public void test_constructorExist(){
        boolean actual = writer.constructorExist(ScoreDTO.class, ScoreModel.class);
        assertFalse(actual);
    }
    
    
    @Test
    public void testCreateFile(){
        writer.create(new PairClassInfo(MooVO.class, MooDTO.class));
        assertFalse(false);
    }
    
}
