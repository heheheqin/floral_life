package com.dream.will.floral_life.transfrom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Author：Will on 2016/12/13 14:26
 * Mail：heheheqin.will@gmail.com
 */

public class CircleTransform extends BitmapTransformation {
    public String url;
    private String key;
    private  int size;

    public CircleTransform(Context context) {
        super(context);
    }


    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        int width = toTransform.getWidth();
        int height = toTransform.getHeight();
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
        canvas.drawBitmap(toTransform, 0, 0, paint);
        //原图片回收
        if (!toTransform.isRecycled()) {
            toTransform.recycle();
        }
        return blankBitmap;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }
}
