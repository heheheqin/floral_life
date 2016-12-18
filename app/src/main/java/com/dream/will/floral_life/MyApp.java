package com.dream.will.floral_life;

import android.app.Application;

/**
 * Author：Will on 2016/12/12 09:11
 * Mail：heheheqin.will@gmail.com
 */

public class MyApp extends Application {

    public    int  itemViewType = 0;
    public  int CHECKBOX_CHECKED = 0;
    public  int CHECKBOX_DIS_CHECKED = 1;
    private static MyApp myApp;
    @Override
    public void onCreate() {
        super.onCreate();
        //
        myApp = this;


    }

    public static MyApp getInstance(){
        return  myApp;
    }


}
