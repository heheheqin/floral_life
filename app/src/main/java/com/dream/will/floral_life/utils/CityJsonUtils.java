package com.dream.will.floral_life.utils;

import com.dream.will.floral_life.bean.CityBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Will on 2016/11/22 09:35
 * Mail：heheheqin.will@gmail.com
 */

public class CityJsonUtils {
     static final String[] lette = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public static List<CityBean> getCityByJson(String value) throws JSONException {
        ArrayList<CityBean> city = new ArrayList<>();
        JSONObject json = new JSONObject(value);
        JSONObject json1 = json.getJSONObject("cities");
        //取首字母
        for (int i = 0; i < lette.length; i++) {
            JSONArray jsonArray = json1.optJSONArray(lette[i]);
            //有些字母没有  需要判断跳过
            if (jsonArray!=null) {
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(j);
                    //取出 城市信心  并且标记城市 添加到数组
                    city.add(new CityBean(jsonObject, i, lette[i]));
                }
            }
        }


        return city;
    }
}
