package com.dream.will.floral_life.inter;

import com.dream.will.floral_life.apiall.ApiManger;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Author：Will on 2016/12/16 15:48
 * Mail：heheheqin.will@gmail.com
 */

public interface ICommunity {

    /**
     * banner
     /servlet/SysAdvertisingServlet
     action=getAdList
     */
    @FormUrlEncoded
    @POST(ApiManger.COMMUNITY_JINGXUAN_BANNER)
    Call<String> getCommunityBanner(@Field("action") String action);
}
