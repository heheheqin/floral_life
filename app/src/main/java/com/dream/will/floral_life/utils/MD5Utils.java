package com.dream.will.floral_life.utils;

import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Author：Will on 2016/12/13 10:39
 * Mail：heheheqin.will@gmail.com
 */

public class MD5Utils {

    //md5  生成消息摘要   不可逆
    public static String md5(String string)  {
        //获取生成再要方法   md5
        MessageDigest messageDigest = null;
        byte[] digest = null;
        try {
            messageDigest = MessageDigest.getInstance("md5");
            messageDigest.update(string.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //显示摘要
        String string1 = Base64.encodeToString(digest, Base64.DEFAULT);
        Log.i("TAG", "md5: ---------" + string1);
        return string1;
    }
}
