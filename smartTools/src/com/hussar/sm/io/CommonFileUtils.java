package com.hussar.sm.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author yi.xiao
 *
 */
public class CommonFileUtils {

    public static List<String> getContentList(String path){
        return getContentList(new File(path));
    }
    
    public static List<String> getContentList(File file){
        List<String> list = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            close(reader);
        }
        return list;
    }
    
    public static void close(Closeable closeable){
        if(closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public static void writeFile(String pathname, List<String> contentList){
        writeFile(new File(pathname), contentList);
    }
    
    public static void writeFile(File file, List<String> contentList){
        BufferedWriter writer = null;
        try {
            if(!file.exists()){
                File parentPath = file.getParentFile();
                if(!parentPath.exists()){
                    parentPath.mkdirs();
                }
                file.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter( file));
            for(String line : contentList){
                writer.write(line);
                writer.newLine();
            }
        } catch(Exception e){
            throw new RuntimeException(e); 
        }finally{
            close(writer);
        }
    }
    
}  
