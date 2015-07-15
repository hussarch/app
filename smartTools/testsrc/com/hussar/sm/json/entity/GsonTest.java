package com.hussar.sm.json.entity;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * @author yi.xiao
 *
 */
public class GsonTest {
    
    public void testMap(){
        Gson gson = new Gson();
        String content = gson.toJson(getDataMap());
        System.out.println(content);
    }
    
    
    
    public Map<String, Object> getDataMap(){
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("appbanner0", getMockBannerInfos(5));
        data.put("appbanner1", getMockBannerInfo(100, 199958));
        data.put("appbanner2", "this a test");
        return data;
    }
    
    private BannerInfo[] getMockBannerInfos(int size){
        BannerInfo[] bannerInfos = new BannerInfo[size];
        for(int i = 0; i < size; i++){
            bannerInfos[i] = getMockBannerInfo(i, 1435766340);
        }
        return bannerInfos;
    }
    
    private BannerInfo getMockBannerInfo(int id, int expire){
        BannerInfo bannerInfo = new BannerInfo();
        bannerInfo.setId(id);
        bannerInfo.setName("name" + id);
        bannerInfo.setFile("file" + id);
        bannerInfo.setLink("link" + id);
        bannerInfo.setTarget("target" + id);
        bannerInfo.setExpire(expire);
        bannerInfo.setStyle("style" + id);
        bannerInfo.setTime("time" + id);
        bannerInfo.setSpace("space" + id);
        bannerInfo.setTags("tags" + id);
        return bannerInfo;
    }
    
    public static void main(String[] args) {
        GsonTest gsonTest = new GsonTest();
        gsonTest.testMap();
    }
    
}
