package com.dream.will.floral_life.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Author：Will on 2016/11/22 14:13
 * Mail：heheheqin.will@gmail.com
 * 字母选择
 */

public class SlideView extends View {

    SlideClickCallback slideClickCallback;
    //定义点击回调接口
    public  interface  SlideClickCallback{
            //回传点击位置和字母
          void  slideOnClick(int position, String str);
            //手抬起时候调用
          void  slideUp();
    }

    public  void  setOnSlideClike(SlideClickCallback slideClickCallback){
        this.slideClickCallback = slideClickCallback;
    }

    static final String[] lette = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };
    Paint paint;
    /**
     * 文字宽高
     */
    float textW;
    float textH;
    /**
     *
     */
    int currentPosition = -1;

    public SlideView(Context context) {
        super(context);
        init();
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setTextSize(32);
        paint.setColor(Color.parseColor("#161313"));
        paint.setAntiAlias(true);
        textH = paint.descent() - paint.ascent();
        textW = paint.measureText(lette[0]);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasure(widthMeasureSpec, 1), getMeasure(heightMeasureSpec, 2));
        textH = (getMeasuredHeight() - getPaddingBottom() -getPaddingTop())/26;
        paint.setTextSize(textH-3);
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
                //宽度是测量宽度  高度是父控件大小
                if (type == 1) { //宽
                    return (int) textW + getPaddingRight() + getPaddingLeft();
                } else {
                    return size;
                }
            }
        }
        return size;
    }

    /**
     * 阻止事件分发    点击的时候不给事件 传递到下方控件
     * 设置自己到点击事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: { //按下
                eventLable(event.getY());
            }
            break;
            case MotionEvent.ACTION_MOVE: { //移动
                eventLable(event.getY());
            }
            break;
            case MotionEvent.ACTION_UP: { //抬起
                if (slideClickCallback != null){
                    slideClickCallback.slideUp();
                    invalidate();
                }
            }
            break;
        }
        return true;
    }

    /**
     * 处理点击事件
     * @param y
     */
    private void eventLable(float y) {
        y -= getPaddingTop();
        int index = (int) (y / textH);

        if (index < 0) {  //防止越界
            index = 0;
        }
        if (index > lette.length - 1) {  //防止越界
            index = lette.length - 1;
        }
        //防止重复绘制
        if (index != currentPosition)
        {
//            L.d("当前点击"+index + lette[index]);
            //设置点击位置
            currentPosition = index;
            invalidate();
            //调用接口
            if (slideClickCallback != null){
                slideClickCallback.slideOnClick(index,lette[index]);
            }
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        for (int i = 0; i < lette.length; i++) {

            float x = getMeasuredWidth() - paint.measureText(lette[i]);
            float y = i * textH - paint.ascent() + getPaddingTop();
            //被选中 变颜色
            if (i == currentPosition){
                //加粗
                paint.setFakeBoldText(true);
                paint.setColor(Color.parseColor("#FF0C8FAB"));
            }else {
                paint.setFakeBoldText(false);
                paint.setColor(Color.parseColor("#000000"));
            }
            canvas.drawText(lette[i], x / 2, y, paint);
        }
    }
}
