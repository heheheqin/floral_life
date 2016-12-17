package com.dream.will.floral_life.inter;

import com.dream.will.floral_life.apiall.ApiManger;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Author：Will on 2016/12/16 17:20
 * Mail：heheheqin.will@gmail.com
 */

public interface ICity {


    @GET(ApiManger.CITY_SELECT)
    Call<String> getCity();
}
