package com.center.demoapplication.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.center.demoapplication.base.BasePager;

/**
 * Created by aaa on 2019/3/19.
 */

public class VideoPager extends BasePager {

    private TextView textView;
    public VideoPager(Context context) {
        super(context);
    }

    @Override
    public View initViews() {
        textView = new TextView(context);
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("本地视频初始化");
    }
}
