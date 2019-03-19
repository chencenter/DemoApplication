package com.center.demoapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by aaa on 2019/3/19.
 */

public abstract class BaseFragment extends Fragment {
    /**
     * 上下文
     */
    public Context context;
    /**
     * 当系统创建当前BaseFragment类的时候回调
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    /**
     * 当系统要创建Fragment的视图的时候回调这个方法
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initViews();
    }

    /**
     * 抽象方法，让孩子实现-强制子类实现
     *
     * @return
     */
    public abstract View initViews() ;
    /**
     * 当Activty创建成功的时候回调该方法
     * 初始化数据：
     * 联网请求数据
     * 绑定数据
     * @param savedInstanceState
     */

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initOnClick();
    }

    /**
     *当子类需要：
     * 1.联网请求网络，的时候重写该方法
     * 2.绑定数据
     */
    public void initData() {
    }
    public void initOnClick() {
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            onRefrshData();
        }
    }
    /**
     * 当子类要刷新数据的时候重写该方法
     */
    public void onRefrshData(){

    }
}
