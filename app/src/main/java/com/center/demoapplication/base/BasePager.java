package com.center.demoapplication.base;

import android.content.Context;
import android.view.View;

/**
 * Created by aaa on 2019/3/19.
 */

public abstract class BasePager {

    //上下文对象
    public final Context context;
    //基类视图
    public View rootView;

    public BasePager(Context context){
        this.context = context;
        rootView = initViews();
    }

    //强制孩子实现特定效果
    public abstract View initViews() ;

    //当子页面需要初始化数据，连网请求数据或者绑定数据时要重写该方法
    public void initData(){

    }
}
