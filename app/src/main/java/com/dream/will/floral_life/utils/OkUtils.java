package com.dream.will.floral_life.utils;

import android.os.Environment;

import com.dream.will.floral_life.MyApp;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author：Will on 2016/12/17 17:31
 * Mail：heheheqin.will@gmail.com
 */

public class OkUtils {
    public static OkHttpClient genericClient(String CacheFileName) {
        //缓存路径
        File cacheFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath(), CacheFileName);
        Cache cache = new Cache(cacheFile, 10<<20);//缓存文件为10MB

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        int maxAge = 1; // 有网络时 设置缓存超时时间1分钟
                        int maxStale = 60 * 60 * 24 * 7; // 无网络时，设置超时为1周
                        Request request = chain.request();
                        if (InternetUtils.isNetworkReachable(MyApp.getInstance())) {
                            request= request.newBuilder()
//                                    .addHeader("apikey", "2ffc3e48c7086e0e1faa003d781c0e69")
                                    .cacheControl(CacheControl.FORCE_NETWORK)//有网络时只从网络获取
                                    .build();
                        }else {
                            request= request.newBuilder()
                                    .cacheControl(CacheControl.FORCE_CACHE)//无网络时只从缓存中读取
                                    .build();
                        }
                        Response response = chain.proceed(request);
                        //先判断网络，网络好的时候，移除header后添加haunch失效时间为1分钟
                        if (InternetUtils.isNetworkReachable(MyApp.getInstance())) {
                            response= response.newBuilder()
                                    .removeHeader("Pragma")
                                    .header("Cache-Control", "public, max-age=" + maxAge)
                                    .build();
                        } else {
                            response= response.newBuilder()
                                    .removeHeader("Pragma")
                                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                    .build();
                        }
                        return response;
                    }

                })
                .connectTimeout(5000, TimeUnit.SECONDS)
                .cache(cache)
                .build();
        return httpClient;
    }
}
