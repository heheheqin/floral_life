package com.dream.will.floral_life.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author：Will on 2016/11/22 16:04
 * Mail：heheheqin.will@gmail.com
 *
 *
 * 字母显示
 */

public class SlideLetterView extends View {
    Paint paint;
    String text;
    int textW;
    int textH;
    RectF rectF;


    public SlideLetterView(Context context) {
        super(context);
        init();
    }

    public SlideLetterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(120);
        textW = (int) paint.measureText("W");
        textH = (int) (paint.descent() - paint.ascent());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasure(widthMeasureSpec, 1), getMeasure(heightMeasureSpec, 2));
        rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    private int getMeasure(int wh, int type) {
        int mode = MeasureSpec.getMode(wh);
        int size = MeasureSpec.getSize(wh);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.EXACTLY: {
                return size;
            }
            case MeasureSpec.AT_MOST: {
                //宽度是测量宽度  高度是所有文本测量之和
                if (type == 1) { //宽
                    return textW + getPaddingLeft() + getPaddingRight();
                } else {
                    return textH + getPaddingBottom() + getPaddingTop();
                }
            }
        }
        return size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制背景
        paint.setColor(Color.parseColor("#e7cb3f"));
        canvas.drawRoundRect(rectF, 20, 20, paint);
        //在绘制文本
        if (text != null) {
            paint.setColor(Color.WHITE);
            float v = getMeasuredWidth() - paint.measureText(text);
            int i = (getMeasuredHeight() - textH) / 2;
            canvas.drawText(text, v / 2, i - paint.ascent(), paint);
        }
    }

    /**
     * @param s 外部传入的文本
     */
    public void setText(String s) {
        text = s;
        invalidate();
    }
}
