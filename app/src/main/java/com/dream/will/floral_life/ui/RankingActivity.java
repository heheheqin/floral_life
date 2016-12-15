package com.dream.will.floral_life.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.will.floral_life.R;
import com.dream.will.floral_life.fragment.RankingAuthorFragment;
import com.dream.will.floral_life.fragment.RankingSpecialColumnFragment;

public class RankingActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewpaget;
    private TabLayout tabLayout;
    String[] titles = {
            "专栏",
            "作者"
    };
    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        initView();
        //1 初始化shuju
        initData();
        //2 设置适配器
        viewpaget.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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

    private void initData() {
        fragments = new Fragment[titles.length];
        //循环遍历标题创建新的Fragment填充数组

        RankingSpecialColumnFragment rankingSpecialColumnFragment = new RankingSpecialColumnFragment();
        fragments[0] = rankingSpecialColumnFragment;
         Bundle bundle = new Bundle();
         bundle.putString("key", titles[0]);
        fragments[0].setArguments(bundle);
        RankingAuthorFragment rankingAuthorFragment = new RankingAuthorFragment();
        fragments[1] = rankingAuthorFragment;
        Bundle bundle1 = new Bundle();
        bundle1.putString("key", titles[1]);
        fragments[1].setArguments(bundle1);

    }

    private void initView() {
        TextView title = (TextView) findViewById(R.id.zhuanti_title);
        title.setText("本周排行TOP10");
        viewpaget = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        ImageView backImage = (ImageView) findViewById(R.id.image_menu);
        backImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //回退到 专题
            case  R.id.image_menu:{
                finish();
            }
            break;
        }
    }
}
