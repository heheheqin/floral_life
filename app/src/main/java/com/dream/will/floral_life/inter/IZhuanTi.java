package com.dream.will.floral_life.inter;

import com.dream.will.floral_life.apiall.ApiManger;
import com.dream.will.floral_life.bean.Article;
import com.dream.will.floral_life.bean.MenuBean;
import com.dream.will.floral_life.bean.SpecialColumnBean;
import com.dream.will.floral_life.bean.Video;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Author：Will on 2016/12/12 15:32
 * Mail：heheheqin.will@gmail.com
 */

public interface IZhuanTi {
    //    post  action=mainList_NewVersion&isVideo=false&currentPageIndex=0&cateId=

    @FormUrlEncoded
    @POST(ApiManger.ARTICLE)
    Call<Article> getArticle(@Field("action") String action,
                             @Field("isVideo") boolean isVideo,
                             @Field("currentPageIndex") int currentPageIndex,
                             @Field("cateId") String cateId);
    @FormUrlEncoded
    @POST(ApiManger.VIDEO)
    Call<Video> getVideo(@Field("action") String action,
                           @Field("isVideo") boolean isVideo,
                           @Field("currentPageIndex") int currentPageIndex,
                           @Field("cateId") String cateId);
    @FormUrlEncoded
    @POST(ApiManger.MENU)
    Call<MenuBean> getMenu(@Field("action") String action);


    //post  pageSize=10 & action=topContents & currentPageIndex=0
    @FormUrlEncoded
    @POST(ApiManger.JINGXUAN)
    Call<SpecialColumnBean> getSpecialColumn(@Field("action") String action,
                                             @Field("pageSize") int pageSize,
                                             @Field("currentPageIndex") int currentPageIndex);
  /* menu_detail
 花艺学堂
	/servlet/SysArticleServlet
	post  action=mainList_NewVersion & currentPageIndex=0 & cateId=8dba5958-7da0-4ce9-b1e9-5b92343519a7
 */
  @FormUrlEncoded
  @POST(ApiManger.MENU_DETAIL)
  Call<Article> getMenuDetail(@Field("action") String action,
                       @Field("currentPageIndex") int currentPageIndex,
                       @Field("cateId") String cateId);



}
