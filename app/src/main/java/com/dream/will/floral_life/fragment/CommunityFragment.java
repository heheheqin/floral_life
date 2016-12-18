package com.dream.will.floral_life.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dream.will.floral_life.R;

/**
 * Author：Will on 2016/12/12 14:13
 * Mail：heheheqin.will@gmail.com
 */

public class CommunityFragment extends Fragment implements View.OnClickListener {

    private ViewPager viewpaget;
    private TabLayout tabLayout;
    String[] titles = {
            "精选",
            "圈子",
            "订阅"
    };
    private Fragment[] fragments;
    private ImageView add;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Community", "onCreate: -CommunityFragment--------");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);

        Log.i("Community", "onCreateView: -CommunityFragment" +
                "--------" );
        return  view;
    }

    private void initData() {
        fragments = new Fragment[titles.length];
        CommunityJingxuanFragment mCommunityJingxuanFragment = new CommunityJingxuanFragment();
        fragments[0] = mCommunityJingxuanFragment;
        CommunityQuanZiFragment mCommunityQuanZiFragment = new CommunityQuanZiFragment();
        fragments[1] = mCommunityQuanZiFragment;
        CommunityDingyueFragment mCommunityDingyueFragment = new CommunityDingyueFragment();
        fragments[2] = mCommunityDingyueFragment;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //1 初始化shuju
        viewpaget = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        add = (ImageView) view.findViewById(R.id.add);
        initData();
        Log.i("Community", "onViewCreated: -CommunityFragment--------" );
    }
    String community =  "Community";
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.i(community, "onActivityCreated: -CommunityFragment--------");
        add.setOnClickListener(this);

        //2 设置适配器
        viewpaget.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
            // 4. 设置标题
            @Override
            public CharSequence getPageTitle(int position) {return titles[position];
            }
        });
        //3 联动
        tabLayout.setupWithViewPager(viewpaget);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(community, "onResume: -CommunityFragment--------");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(community, "onPause: --CommunityFragment-------");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(community, "onDestroyView: -CommunityFragment--------");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(community, "onDestroy: -CommunityFragment--------");
    }

    @Override
    public void onClick(View v) {

    }
}
