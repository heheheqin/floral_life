package com.dream.will.floral_life.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dream.will.floral_life.MyApp;

/**
 * Author：Will on 2016/11/21 09:33
 * Mail：heheheqin.will@gmail.com
 */

public class SharedUtils {
    /**
     * 共享参数文件名
     */
    private static final String SHARED_NAME = "htxq";
    /**
     * 第一次启动保存key
     */
    private static final String FIRST_RUN = "FIRST_RUN";

    /**
     * @param context
     * @return  返回是够第一次启动
     */
    public  static  boolean isFirstRun(Context context){
        SharedPreferences sharedPreferences = MyApp.getInstance().getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getBoolean(FIRST_RUN,true);
    }

    /**进入主页调用
     * @param context
     */
    public  static  void saveFirstRun(Context context){
        SharedPreferences sharedPreferences = MyApp.getInstance().getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(FIRST_RUN,false);
        edit.commit();
    }
    /**保存 字段
     * @param string
     */
    public  static  void saveString(String string){
        SharedPreferences sharedPreferences = MyApp.getInstance().getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(string,true);
        edit.commit();

    }

    /**判断字符串是否存在
     * @param string
     * @return  返回是够第一次启动
     */
    public  static  boolean isString(String string){
        SharedPreferences sharedPreferences = MyApp.getInstance().getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getBoolean(string,false);
    }


}
