package com.dream.will.floral_life.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.TextView;

import com.dream.will.floral_life.R;
import com.dream.will.floral_life.bean.StoreThemeGoods;
import com.dream.will.floral_life.customview.StoreListViewItemSingle;

import java.util.List;

/**
 * Author：Will on 2016/12/15 17:46
 * Mail：heheheqin.will@gmail.com
 */

public class StoreListViewAdaper extends BaseAdapter {

    List<StoreThemeGoods.DataBean >  data;
    Context context;
    LayoutInflater inflater;

    public StoreListViewAdaper(Context context, List<StoreThemeGoods.DataBean> data) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.fragment_store_list_item1, parent, false);
            view = new ViewHolder(context,convertView);
            convertView.setTag(view);
        }else {
            view = (ViewHolder) convertView.getTag();
        }
        StoreThemeGoods.DataBean dataBean = data.get(position);
        view.textView.setText(dataBean.getThemeName());
//        view.storeListViewItemGroup.setGrid(data.get(position).getThemeGoodsViews());

        List<StoreThemeGoods.DataBean.ThemeGoodsViewsBean> themeGoodsViews = dataBean.getThemeGoodsViews();
        int size = themeGoodsViews.size();
        int wide = (int) (context.getResources().getDisplayMetrics().widthPixels*0.95);
        int high = wide/2;
        if (size != 1) {
            wide = wide / 2;
        }
        view.mGridLayout.removeAllViews();
        for (int i = 0; i < size; i++) {
            StoreListViewItemSingle storeListViewItemSingle = new StoreListViewItemSingle(context);
            storeListViewItemSingle.setRes(themeGoodsViews.get(i), wide,high);
            view.mGridLayout.addView(storeListViewItemSingle);
        }
        return convertView;
    }

    public static class ViewHolder{
        //保存的控件 是需要设置值的控件
//        private StoreListViewItemGroup storeListViewItemGroup;
        private TextView textView;
        GridLayout mGridLayout;


        public ViewHolder(Context context,View c){
            textView = (TextView) c.findViewById(R.id.themeName);
            mGridLayout = (GridLayout) c.findViewById(R.id.content);
//            this.storeListViewItemGroup = new StoreListViewItemGroup(context);
        }


    }


}
