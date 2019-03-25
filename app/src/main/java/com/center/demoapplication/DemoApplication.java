package com.center.demoapplication;

import android.app.Application;

import org.xutils.x;

/**
 * Created by DemoApplication on 2019/3/25.
 * 代表整个文件
 */

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        //是否输出debug日志，开启debug会影响性能
        x.Ext.setDebug(true);
    }
}
