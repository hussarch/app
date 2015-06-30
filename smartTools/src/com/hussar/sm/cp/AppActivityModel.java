package com.hussar.sm.cp;

/**
 * @author yi.xiao
 */
public class AppActivityModel implements Comparable<AppActivityModel>{

    private Integer id;
    private String title;

    public AppActivityModel(int id){
        this.id = id;
    }
    
    @Override
    public int compareTo(AppActivityModel model) {
        if(model.id > this.id){
            return 1;
        }else if(model.id < this.id){
            return -1;
        }else{
            return 0;
        }
    }
    
    public String toString(){
        return id.toString();
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
