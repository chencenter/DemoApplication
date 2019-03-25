package com.center.demoapplication.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.center.demoapplication.R;
import com.center.demoapplication.base.BaseFragment;

/**
 * Created by aaa on 2019/3/19.
 */

public class NetAutioFragment extends BaseFragment {
    TextView textView;
//    @Override
//    public View initViews() {
//        View view = View.inflate(context, R.layout.fragment_local_video,null);
//        textView = view.findViewById(R.id.tv_show);
//        return view;
//    }

    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container) {
        View view = View.inflate(context, R.layout.fragment_local_video,null);
        textView = view.findViewById(R.id.tv_show);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
//        textView.setText("网络音乐");
    }
}
