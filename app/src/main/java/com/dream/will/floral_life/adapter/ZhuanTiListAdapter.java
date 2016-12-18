package com.dream.will.floral_life.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.dream.will.floral_life.MyApp;
import com.dream.will.floral_life.R;
import com.dream.will.floral_life.bean.Article;

import java.util.List;

/**
 * Author：Will on 2016/12/12 15:59
 * Mail：heheheqin.will@gmail.com
 */

public class ZhuanTiListAdapter extends  AbsBaseAdapter2<Article.ResultBean> {

    List<Article.ResultBean>  data;
    Context context;

    /**
     * @param context  上下文
     * @param data  数据源
     * @param layoutId   布局  文件
     *
     */
    public ZhuanTiListAdapter(Context context, List<Article.ResultBean> data, int... layoutId) {
        super(context, data, layoutId);
        this.data = data;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return MyApp.getInstance().itemViewType;
    }

    @Override
    public void bindData(int position, ViewHolder viewHolder) {
        Article.ResultBean resultBean = data.get(position);
        if (MyApp.getInstance().itemViewType == 0){
            ImageView list_image = (ImageView) viewHolder.findViewBid(R.id.list_image);
            ImageView showVideoIcon = (ImageView) viewHolder.findViewBid(R.id.showVideoIcon);
            TextView title = (TextView) viewHolder.findViewBid(R.id.title);
            TextView name = (TextView) viewHolder.findViewBid(R.id.name);
            title.setText(resultBean.getTitle());
            name.setText("#"+resultBean.getCategory().getName()+"#");
            Glide.with(context)
                    .load(resultBean.getSmallIcon())
                    .asBitmap()
                    .error(R.drawable.banner_zhanwei)
                    .placeholder(R.drawable.banner_zhanwei)
                    .dontAnimate()
                    .into(list_image);
            if (resultBean.isVideo()) {
                showVideoIcon.setVisibility(View.VISIBLE);
            }else {
                showVideoIcon.setVisibility(View.GONE);
            }
        }else {
            ImageView list_image1 = (ImageView) viewHolder.findViewBid(R.id.list_image1);
            final ImageView headImg = (ImageView) viewHolder.findViewBid(R.id.headImg);
            ImageView menu_v = (ImageView) viewHolder.findViewBid(R.id.menu_v);
            TextView userName = (TextView) viewHolder.findViewBid(R.id.userName);
            TextView identity = (TextView) viewHolder.findViewBid(R.id.identity);
            TextView name1 = (TextView) viewHolder.findViewBid(R.id.name1);
            TextView title1 = (TextView) viewHolder.findViewBid(R.id.title1);
            TextView desc = (TextView) viewHolder.findViewBid(R.id.desc);
            TextView fnCommentNum = (TextView) viewHolder.findViewBid(R.id.fnCommentNum);
            TextView share = (TextView) viewHolder.findViewBid(R.id.share);
            TextView read = (TextView) viewHolder.findViewBid(R.id.read);
            userName.setText(resultBean.getAuthor().getUserName());
            identity.setText(resultBean.getAuthor().getIdentity());
            name1.setText("["+resultBean.getCategory().getName()+"]");
            title1.setText(resultBean.getTitle());
            desc.setText(resultBean.getDesc());
            fnCommentNum.setText(""+resultBean.getFnCommentNum());
            share.setText(resultBean.getShare()+"");
            read.setText(resultBean.getRead()+"");

            ImageView showVideoIcon = (ImageView) viewHolder.findViewBid(R.id.showVideoIcon);
            if (resultBean.isVideo()) {
                showVideoIcon.setVisibility(View.VISIBLE);
            }else {
                showVideoIcon.setVisibility(View.GONE);
            }
            //获取listview大图
            Glide.with(context)
                    .load(resultBean.getSmallIcon())
                    .error(R.drawable.banner_zhanwei)
                    .placeholder(R.drawable.banner_zhanwei)
                    .into(list_image1);
            //获取用户头像
            String headImg1 = resultBean.getAuthor().getHeadImg();
            Glide.with(context).load(headImg1)
                    .asBitmap()
                    .error(R.drawable.personal_default_head)
                    .placeholder(R.drawable.personal_default_head)
                    .centerCrop().into(new BitmapImageViewTarget(headImg) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    headImg.setImageDrawable(circularBitmapDrawable);
                }
            });

        }
    }
}
