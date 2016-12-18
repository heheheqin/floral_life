package com.dream.will.floral_life.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.dream.will.floral_life.R;
import com.dream.will.floral_life.adapter.AbsBaseAdapter2;
import com.dream.will.floral_life.apiall.ApiManger;
import com.dream.will.floral_life.bean.QuanZi;
import com.dream.will.floral_life.inter.ICommunity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Author：Will on 2016/12/12 14:13
 * Mail：heheheqin.will@gmail.com
 */

public class CommunityQuanZiDetailFragment extends Fragment implements AbsListView.OnScrollListener {

    private PtrClassicFrameLayout refresh;
    private ListView listView;
    private LinearLayout inflate;
    List<QuanZi.ResultBean> data;

    private int currentPageIndex = 0;
    private AbsBaseAdapter2<QuanZi.ResultBean> stringArrayAdapter;
    private boolean isAddMore;
    private boolean isRefresh;
    private String type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<>();
        Bundle arguments = getArguments();
        type = arguments.getString("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_jignxuan, container, false);
        refresh = (PtrClassicFrameLayout) view.findViewById(R.id.refresh);
        listView = (ListView) view.findViewById(R.id.communityJingxuanlistView);
        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stringArrayAdapter = new AbsBaseAdapter2<QuanZi.ResultBean>(getActivity(),data,R.layout.fragment_community_quanzi_list_item){
            int wide = (int) (getResources().getDisplayMetrics().widthPixels * 0.9);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(wide, wide);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(wide/2, wide/2);
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(wide/3, wide/3);

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public void bindData(int position, ViewHolder viewHolder) {
                QuanZi.ResultBean resultBean = data.get(position);
                final ImageView headImage = (ImageView) viewHolder.findViewBid(R.id.headImage);
                TextView userName = (TextView) viewHolder.findViewBid(R.id.userName);
                TextView author_content = (TextView) viewHolder.findViewBid(R.id.author_content);
                TextView content = (TextView) viewHolder.findViewBid(R.id.content);
                TextView time = (TextView) viewHolder.findViewBid(R.id.time);
                TextView pinglun = (TextView) viewHolder.findViewBid(R.id.pinglun);
                TextView collect = (TextView) viewHolder.findViewBid(R.id.collect);
                userName.setText(resultBean.getCustomer().getUserName());
                content.setText(resultBean.getContent());
                String content1 = resultBean.getCustomer().getContent();
                if ("".equals(content1)){
                    author_content.setText("这家伙很懒，什么也没有留下");
                }else {
                    author_content.setText(content1);
                }
                time.setText(resultBean.getCreateDate().substring(5,10));
                pinglun.setText(resultBean.getRelease()+"");
                collect.setText(resultBean.getRead()+"");
                String headImageUrl = resultBean.getCustomer().getHeadImg();
               //圆形用户头像
                Glide.with(getActivity()).load(headImageUrl)
                        .asBitmap()
                        .error(R.drawable.personal_default_head)
                        .placeholder(R.drawable.personal_default_head)
                        .centerCrop().into(new BitmapImageViewTarget(headImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        headImage.setImageDrawable(circularBitmapDrawable);
                    }
                });
                GridLayout gridLayout = (GridLayout) viewHolder.findViewBid(R.id.gridLayout);
                gridLayout.removeAllViews();
                String urls = resultBean.getAttachmentSnap();
                String[] split = urls.split(",");
                int length = split.length;
                if (length ==1){
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Glide.with(getActivity()).load(split[0]).into(imageView);
                    gridLayout.addView(imageView,params);
                }else if (length ==2){
                    gridLayout.setColumnCount(2);
                    for (int i = 0; i < length; i++) {
                        ImageView imageView = new ImageView(getActivity());
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        params1.setMargins(5,5,5,5);
                        Glide.with(getActivity()).load(split[i]).into(imageView);
                        gridLayout.setColumnCount(2);
                        gridLayout.addView(imageView,params1);
                    }
                }else {
                    for (int i = 0; i < length; i++) {
                        ImageView imageView = new ImageView(getActivity());
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        params2.setMargins(5,5,5,5);
                        Glide.with(getActivity()).load(split[i]).into(imageView);
                        gridLayout.setColumnCount(3);
                        gridLayout.addView(imageView,params2);
                    }

                }

            }
        };
        listView.setAdapter(stringArrayAdapter);
        listView.setOnScrollListener(this);

        refresh.setLastUpdateTimeRelateObject(this);

        refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getNetListData();
                isRefresh = true;
            }
        });
        //设置进入页面自动刷新
        refresh.post(new Runnable() {
            @Override
            public void run() {
                refresh.autoRefresh();
            }
       });
    }


    /**
     * 获取网络数据
     */
    public void getNetListData() {
        if (isRefresh){
            currentPageIndex = 0;
        }

            final Retrofit re = new Retrofit.Builder()
                    .baseUrl(ApiManger.HOST_POST)//★这里最后面必须能带“/”
                    .addConverterFactory(ScalarsConverterFactory.create())//设置将json解析为javabean所用的方式
                    .build();
        ICommunity iCommunity = re.create(ICommunity.class);
        //type=55e7d2bb-5c61-40ca-a82b-00d0d4f74535 &pageSize=20 &action=getBbsCircle &currentPageIndex=0
        Call<String> call = iCommunity.getCommunityJingxuanList("getBbsCircle",type,20,currentPageIndex);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (isRefresh){
                        data.clear();
                        isRefresh = false;
                    }
                    String body = response.body();
                    Log.i("TAG", "onResponse: --quanzi list-------" + body);
//                    SDUtils.saveFile(body.getBytes(),"quanzi.txt", Conten.KEY_APP_NAME);
                    Gson gson = new Gson();
                    QuanZi mBean = gson.fromJson(body, new TypeToken<QuanZi>() {
                    }.getType());
                    List<QuanZi.ResultBean> result = mBean.getResult();
                    data.addAll(result);
                    stringArrayAdapter.notifyDataSetChanged();
                    refresh.refreshComplete();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.i("TAG", "onResponse: --quanzi-----error--"  );
                    Toast.makeText(getActivity(), "请检查网络", Toast.LENGTH_SHORT).show();
                }
            });

    }

    ///////////////////////////////////////////////////////////////////////////
    // 设置滑动监听
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (isAddMore && scrollState == 0) {
            currentPageIndex++;
            getNetListData();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        isAddMore = firstVisibleItem + visibleItemCount == totalItemCount;
    }
}
