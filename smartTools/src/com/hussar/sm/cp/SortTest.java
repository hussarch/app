package com.hussar.sm.cp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yi.xiao
 *
 */
public class SortTest {

    public static void sortList(){
        List<AppActivityModel> list = new ArrayList<>();
        list.add(new AppActivityModel(12));
        list.add(new AppActivityModel(8));
        list.add(new AppActivityModel(19));
        list.add(new AppActivityModel(1));
        list.add(new AppActivityModel(9));
        Collections.sort(list);
        System.out.println(list);
    }
    
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(8);
        list.add(18);
        list.add(1);
        list.add(14);
        Collections.sort(list);
        System.out.println(list);
        
        sortList();
    }
    
}
