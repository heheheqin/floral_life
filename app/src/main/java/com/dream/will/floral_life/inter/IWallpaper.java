package com.dream.will.floral_life.inter;

import com.dream.will.floral_life.apiall.ApiManger;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Author：Will on 2016/12/14 15:21
 * Mail：heheheqin.will@gmail.com
 */

public interface IWallpaper {

     /*
    listview
	/servlet/SysWallpaperServlet
	     type=click & action=wallpaperLog
	action=getList & phoneType=android & imgScale=1.0 & pageIndex=0

     */
     @FormUrlEncoded
     @POST(ApiManger.WALLPAPER)
     Call<String> getWallpaper(@Field("action") String action,
                                 @Field("pageIndex") int pageIndex,
                                 @Field("imgScale") double imgScale,
                                @Field("phoneType") String phoneType);

    /*
    banner
	/servlet/SysWallpaperServlet
	action=getBanner
     */
    @FormUrlEncoded
    @POST(ApiManger.WALLPAPER)
    Call<String> getWallpaperBanner(@Field("action") String action);


}
