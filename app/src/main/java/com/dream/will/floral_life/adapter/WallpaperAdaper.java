package com.dream.will.floral_life.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dream.will.floral_life.R;
import com.dream.will.floral_life.bean.WallpaperList;

import java.util.List;

/**
 * Author：Will on 2016/12/14 17:19
 * Mail：heheheqin.will@gmail.com
 */

public class WallpaperAdaper extends  AbsBaseAdapter2<WallpaperList.ResultBean> {

    private ImageView image4;
    private ImageView image3;
    private ImageView image2;
    private ImageView image1;
    List<WallpaperList.ResultBean> dataList;
    private  Context context;
    private  OnClick mOnclick;

    public void setmOnclick(OnClick mOnclick){
        this.mOnclick = mOnclick;
    }

    public interface OnClick{
        void  OnClickItem(String position,String name);
    }


    public WallpaperAdaper(Context context, List<WallpaperList.ResultBean> data, int... layoutId) {
        super(context, data, layoutId);
        dataList = data;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public void bindData(final int position, ViewHolder viewHolder) {
        final WallpaperList.ResultBean resultBean = dataList.get(position);
        image1 = (ImageView) viewHolder.findViewBid(R.id.image1);
        image2 = (ImageView) viewHolder.findViewBid(R.id.image2);
        image3 = (ImageView) viewHolder.findViewBid(R.id.image3);
        image4 = (ImageView) viewHolder.findViewBid(R.id.image4);
        // TODO: 2016/12/18 glide在图片加载过程可能出现第一次加载 图片宽高和拉伸不适应  需要讲动画去掉
        Glide.with(context)
                .load(resultBean.getWallpapers().get(0).getSnapImg())
                .placeholder(R.drawable.banner_zhanwei)
                .error(R.drawable.banner_zhanwei)
                .dontAnimate()
                .into(image1);
        Glide.with(context)
                .load(resultBean.getWallpapers().get(1).getSnapImg())
                .placeholder(R.drawable.banner_zhanwei)
                .error(R.drawable.banner_zhanwei)
                .dontAnimate()
                .into(image2);
        Glide.with(context)
                .load(resultBean.getWallpapers().get(2).getSnapImg())
                .placeholder(R.drawable.banner_zhanwei)
                .error(R.drawable.banner_zhanwei)
                .dontAnimate()
                .into(image3);
        Glide.with(context)
                .load(resultBean.getWallpapers().get(3).getSnapImg())
                .placeholder(R.drawable.banner_zhanwei)
                .error(R.drawable.banner_zhanwei)
                .dontAnimate()
                .into(image4);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnclick.OnClickItem(resultBean.getWallpapers().get(0).getBigImg(),
                        resultBean.getWallpapers().get(0).getKeyWord());
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnclick.OnClickItem(resultBean.getWallpapers().get(1).getBigImg(),
                        resultBean.getWallpapers().get(1).getKeyWord());
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnclick.OnClickItem(resultBean.getWallpapers().get(2).getBigImg(),
                        resultBean.getWallpapers().get(2).getKeyWord());
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnclick.OnClickItem(resultBean.getWallpapers().get(3).getBigImg(),
                        resultBean.getWallpapers().get(3).getKeyWord());
            }
        });
//        image1.setTag(position);
//        image2.setTag(position);
//        image3.setTag(position);
//        image4.setTag(position);
    }
}
