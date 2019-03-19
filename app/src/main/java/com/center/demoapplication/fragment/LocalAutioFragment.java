package com.center.demoapplication.fragment;

import android.view.View;
import android.widget.TextView;

import com.center.demoapplication.R;
import com.center.demoapplication.base.BaseFragment;

/**
 * Created by aaa on 2019/3/19.
 */

public class LocalAutioFragment extends BaseFragment {
    TextView textView;
    @Override
    public View initViews() {
        View view = View.inflate(context, R.layout.fragment_local_autio,null);
        textView = view.findViewById(R.id.tv_show);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("本地音乐");
    }
}
