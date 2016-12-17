package com.dream.will.floral_life.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dream.will.floral_life.R;
import com.dream.will.floral_life.bean.CityBean;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Author：Will on 2016/11/22 10:59
 * Mail：heheheqin.will@gmail.com
 *
 * StickyListAdapter
 */

public class CityChoiceAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private List<CityBean> data;
    private List<CityBean> allData;
    private LayoutInflater inflater;

    public CityChoiceAdapter(Context context, List<CityBean> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.allData = new ArrayList<>();
    }

    public  void setAllData(List<CityBean> data){
        allData.clear();
        allData.addAll(data);
    }
    ///////////////////////////////////////////////////////////////////////////
    //    ⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️️️  StickyListHeadersAdapter  ⬇️⬇️⬇️⬇️⬇️
    ///////////////////////////////////////////////////////////////////////////

    /** 头部view
     * @param position  位置
     * @param convertView 缓存视图
     * @param parent 父
     * @return item视图
     */
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeadViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.city_item_layout2, parent, false);
            viewHolder = new HeadViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HeadViewHolder) convertView.getTag();
        }
        //关键：：： 绑定数据   首字母
        CityBean cityBean = data.get(position);
        viewHolder.tv_cityHead.setText(cityBean.getLetter());
        return convertView;
    }

    /**
     * @param position  返回头部 头部
     * @return  返回头部 类型  便于分组
     */
    @Override
    public long getHeaderId(int position) {
        return data.get(position).getTypeId();
    }

    class  HeadViewHolder{
        TextView tv_cityHead;
        public HeadViewHolder(View view) {
            tv_cityHead = (TextView) view.findViewById(R.id.cityLetter);
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // BaseAdapter
    ///////////////////////////////////////////////////////////////////////////
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
        BeanViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.city_item_layout, parent, false);
            viewHolder = new BeanViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (BeanViewHolder) convertView.getTag();
        }
        CityBean cityBean = data.get(position);
        viewHolder.tv_cityName.setText(cityBean.getCityname());
        return convertView;
    }
    class  BeanViewHolder{
        TextView tv_cityName;
        public BeanViewHolder(View view) {
            tv_cityName = (TextView) view.findViewById(R.id.cityName);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // 检索方法  把结果设置给自己
    ///////////////////////////////////////////////////////////////////////////
    public  void search(String strSearch){
        data.clear();
        if (strSearch == null|| strSearch.length() == 0){
            data.addAll(allData);
        }else {
            for (CityBean cityBean : allData) {
                if (cityBean.getCityname().contains(strSearch)) {
                    data.add(cityBean);
                }
            }
        }
        notifyDataSetChanged();
    }

}
