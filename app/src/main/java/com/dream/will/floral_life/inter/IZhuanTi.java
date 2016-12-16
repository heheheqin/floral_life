package com.dream.will.floral_life.inter;

import com.dream.will.floral_life.apiall.ApiManger;
import com.dream.will.floral_life.bean.Article;
import com.dream.will.floral_life.bean.MenuBean;
import com.dream.will.floral_life.bean.SpecialColumnBean;
import com.dream.will.floral_life.bean.Video;
import com.dream.will.floral_life.bean.VideoDetailBean;

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

    /**  文章接口列表
     * @param action
     * @param isVideo
     * @param currentPageIndex
     * @param cateId
     * @return
     */
    @FormUrlEncoded
    @POST(ApiManger.ARTICLE)
    Call<Article> getArticle(@Field("action") String action,
                             @Field("isVideo") boolean isVideo,
                             @Field("currentPageIndex") int currentPageIndex,
                             @Field("cateId") String cateId);


    /**  视频列表接口
     * @param action
     * @param isVideo
     * @param currentPageIndex
     * @param cateId
     * @return
     */
    @FormUrlEncoded
    @POST(ApiManger.VIDEO)
    Call<Video> getVideo(@Field("action") String action,
                           @Field("isVideo") boolean isVideo,
                           @Field("currentPageIndex") int currentPageIndex,
                           @Field("cateId") String cateId);


    /**
     * http://app.htxq.net/servlet/SysArticleServlet
     action=getArticleDetail&articleId=7c8c95b1-7d95-427d-b8f2-36ed8761e8a0
     * @param action       获视频文章取详细信息
     * @param articleId
     * @return
     */
    @FormUrlEncoded
    @POST(ApiManger.VIDEO)
    Call<VideoDetailBean> getVideoArticleDetail(@Field ("action") String action,
                                                @Field("articleId") String articleId);

    /**  专题 菜单 接口
     * @param action
     * @return
     */
    @FormUrlEncoded
    @POST(ApiManger.MENU)
    Call<MenuBean> getMenu(@Field("action") String action);


    /**   菜单  专栏接口
     * @param action
     * @param pageSize
     * @param currentPageIndex
     * @return
     */
    //post  pageSize=10 & action=topContents & currentPageIndex=0
    @FormUrlEncoded
    @POST(ApiManger.JINGXUAN)
    Call<SpecialColumnBean> getSpecialColumn(@Field("action") String action,
                                             @Field("pageSize") int pageSize,
                                             @Field("currentPageIndex") int currentPageIndex);

    /**   菜单接口
     * @param action
     * @param currentPageIndex
     * @param cateId
     * @return
     */
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
