package com.dream.will.floral_life.inter;

import com.dream.will.floral_life.apiall.ApiManger;
import com.dream.will.floral_life.bean.StoreBanner;
import com.dream.will.floral_life.bean.StoreFirtItemList;
import com.dream.will.floral_life.bean.StoreRecommendBanner;
import com.dream.will.floral_life.bean.StoreThemeBanner;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author：Will on 2016/12/14 20:27
 * Mail：heheheqin.will@gmail.com
 */

public interface IStore {

    /* @Query("cityid") String cityid
    banner
	GET  /cactus/index/getBannerList

		title=圣诞蛋筒花
		url=2ab0e8b9-0401-4a0d-bcfd-ae1c75c7187e
		jumpId=2ab0e8b9-0401-4a0d-bcfd-ae1c75c7187e

		跳转 webview  可以搞定
		app.htxq.net/shop/PGoodsAction/goodsDetail.do  ?goodsId=2ab0e8b9-0401-4a0d-bcfd-ae1c75c7187e
     */
    @GET(ApiManger.STORE_BANNER)
    Call<StoreBanner> getBannerBean();

    /**
     *  /cactus/index/getFirtItemList  ?type=1
     * @return
     */

    @GET(ApiManger.STORE_FIRTITEMLIST)
    Call<StoreFirtItemList> getFirtItemList(@Query("type") String type);


    /**
     *   ThemeBanner
     */
    @GET(ApiManger.STORE_THEMEBANNER)
    Call<StoreThemeBanner> getThemeBannerBean();

    /**
     *   RecommendBanner
     *
         跳转  webview  直接显示
        /shop/PGoodsAction/goodsDetail.do  ?goodsId=3e9f0f8c-39c1-48af-9797-c19013f0e174
     */
    @GET(ApiManger.STORE_RECOMMERD_BANNER)
    Call<StoreRecommendBanner> getRecommedBannerBean();

    /**
     *   商城  listview
     *

     http://api.htxq.net/cactus/index/getThemeGoods?city=%E5%85%A8%E5%9B%BD
     */
    @GET(ApiManger.STORE_LISTVIEW)
    Call<String> getThemeGoodsData(@Query("city") String city);




}
