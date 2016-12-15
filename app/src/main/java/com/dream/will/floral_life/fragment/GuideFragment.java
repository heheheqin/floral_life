package com.dream.will.floral_life.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dream.will.floral_life.R;
import com.dream.will.floral_life.content.GuideContent;
import com.dream.will.floral_life.ui.HomeActivity;

/**
 * Author：Will on 2016/12/12 10:02
 * Mail：heheheqin.will@gmail.com
 */

public class GuideFragment extends Fragment {

    private ImageView imageView;
    private int imgId;
    private ImageView guide_img;
    private int layoutId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        imgId = bundle.getInt(GuideContent.KEY_GUIDEFRAGMENT);
        layoutId = bundle.getInt(GuideContent.KEY_GUIDE_LAYOUT);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = null;
        if (layoutId != 2){
            inflate = inflater.inflate(R.layout.fragment_guide, container,false);
        }else {
            inflate = inflater.inflate(R.layout.fragment_guide_1, container,false);
            Button btn = (Button) inflate.findViewById(R.id.start);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivite();
                }
            });
        }
        guide_img = (ImageView) inflate.findViewById(R.id.guide_img);
        Glide.with(getActivity())
                .load(imgId)
                .into(guide_img);

        return inflate;
    }

    private void startActivite() {
        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
