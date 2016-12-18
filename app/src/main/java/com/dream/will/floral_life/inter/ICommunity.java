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


    /**
     * 每日精选 list
     /servlet/UserBbsServlet
     type=%E8%8D%90&pageSize=4&action=getJianOrJingList&currentPageIndex=0
     type=%E8%8D%90&pageSize=4&action=getJianOrJingList&currentPageIndex=5
     */
    @FormUrlEncoded
    @POST(ApiManger.COMMUITY_JINGXUAN_LIST)
    Call<String> getCommunityJingxuanList(@Field("action") String action,
                                          @Field("type") String type,
                                          @Field("pageSize") int pageSize,
                                          @Field("currentPageIndex") int currentPageIndex);
    /**
     * 圈子 list
     /servlet/UserBbsServlet
     type=55e7d2bb-5c61-40ca-a82b-00d0d4f74535 &pageSize=20 &action=getBbsCircle &currentPageIndex=0
     */
    @FormUrlEncoded
    @POST(ApiManger.COMMUITY_JINGXUAN_LIST)
    Call<String> getCommunityQuanZiList(@Field("action") String action,
                                          @Field("type") String type,
                                          @Field("pageSize") String pageSize,
                                          @Field("currentPageIndex") int currentPageIndex);


}
