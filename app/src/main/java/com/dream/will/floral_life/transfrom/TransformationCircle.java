package com.dream.will.floral_life.transfrom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dream.will.floral_life.content.Conten;
import com.dream.will.floral_life.utils.MD5Utils;
import com.dream.will.floral_life.utils.SDUtils;
import com.squareup.picasso.Transformation;

import java.io.File;

/**
 * Author：Will on 2016/12/12 20:38
 * Mail：heheheqin.will@gmail.com
 */

public class TransformationCircle implements Transformation {

    public String url;
    private String key;
    private float size;

    public TransformationCircle(float size, String key, String url) {
        this.size = size;
        this.key = key;
        this.url = url;
    }

    //参数为下载到到原图
    @Override
    public Bitmap transform(Bitmap source) {
        String rename = MD5Utils.md5(url);
        Log.i("TAG", "transform: ---------" + rename);
        File file = new File(SDUtils.getSDcardPath() + Conten.KEY_APP_NAME, rename+".png");
        if (SDUtils.isMount() && file.exists()) {
            source.recycle();
            Log.i("TAG", "transform: -----重复利用----");
            return BitmapFactory.decodeFile(file.getAbsolutePath());
        }
        //1  。图片裁剪成 正方形
//        Bitmap target = getBitmapSquare(source);

        //2。裁剪圆形图片
        Bitmap target = getBitmapCircle(source);
        //3.保存图片
       SDUtils.saveFile(target,Conten.KEY_APP_NAME,rename+".png");
        //原图片回收
        if (!source.isRecycled()) {
            source.recycle();
        }
        return target;
    }

    private Bitmap getBitmapSquare(Bitmap source) {
        int height = source.getHeight();
        int width = source.getWidth();
        Bitmap target = null;
        Matrix matrix = new Matrix();
        //宽高相等
        if (height == width) {
            float scale = 1.0f * size / width;
            //设置x，y缩放比例
            matrix.setScale(scale, scale);
            target = Bitmap.createBitmap(source, 0, 0, width, height, matrix, false);
            //宽高不相等
        } else {
            int fromX = 0, fromY = 0;
            int fSize = 0;
            //确定正方形变长
            fSize = Math.min(width, height);
            //计算正方形在原图  的开始裁剪点
            fromX = ((width - fSize) / 2);
            fromY = ((height - fSize) / 2);
            //先剪出一个正方形
            Bitmap bitmap = Bitmap.createBitmap(source, fromX, fromY, fSize, fSize);
            float scale = 1.0f * size / fSize;
            //设置x，y缩放比例
            matrix.setScale(scale, scale);
            Log.i("TAG", "transform: ---------" + fSize);
            target = Bitmap.createBitmap(bitmap, 0, 0, fSize, fSize, matrix, false);
            bitmap.recycle();
        }
        return target;
    }

    @NonNull
    private Bitmap getBitmapCircle(Bitmap source) {
        int width = source.getWidth();
        int height = source.getHeight();
        //圆半径  取宽高最小边／2
        int radius = Math.min(width, height) >> 1;
        //设置空白bitmap 加载到画布上面  最后显示时bitmap 半径的两倍
        int zhijing = radius << 1;
        //添加一个透明矩阵
//        int[] color = new int[zhijing * zhijing];
//        for (int i = 0; i < zhijing; i++) {
//            for (int j = 0; j < zhijing; j++) {
//                //返回点是否有数据 有数据存白色
//                    color[i * zhijing + j] = Color.TRANSPARENT;
//            }
//        }
        Bitmap blankBitmap = Bitmap.createBitmap(radius << 1, radius << 1, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(blankBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //第一次画一个圆
        canvas.drawCircle(radius, radius, radius, paint);
        //第二次取交集模式 其他方式见下图
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //使用 原图 与圆相交
        canvas.drawBitmap(source, 0, 0, paint);
        return blankBitmap;
    }

    @Override
    public String key() {
        return key; //任意字符串
    }
}
