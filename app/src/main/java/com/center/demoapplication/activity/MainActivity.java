package com.center.demoapplication.activity;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.center.demoapplication.R;
import com.center.demoapplication.fragment.LocalAutioFragment;
import com.center.demoapplication.fragment.LocalVideoFragment;
import com.center.demoapplication.fragment.NetAutioFragment;
import com.center.demoapplication.fragment.NetVideoFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private FrameLayout frameLayout;
    private RadioGroup radioGroup;
    private ArrayList<Fragment> fragments;
    private int position;
    /**
     * 缓存的Fragment
     */
    private Fragment tempFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.fl_content);
        radioGroup = findViewById(R.id.rg_bottom_tag);
        //Android6.0动态获取权限
        isGrantExternalRW(this);
        fragments = new ArrayList<>();
        fragments.add(new LocalVideoFragment());
        fragments.add(new LocalAutioFragment());
        fragments.add(new NetVideoFragment());
        fragments.add(new NetAutioFragment());
        radioGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        radioGroup.check(R.id.rb_one);

    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                default:
                    position = 0;
                    break;
                case R.id.rb_two:
                    position = 1;
                    break;
                case R.id.rb_three:
                    position = 2;
                    break;
                case R.id.rb_four:
                    position = 3;
                    break;
            }
            Fragment currentFragment  = fragments.get(position);
            setFragment(currentFragment);
        }
    }

    //把页面添加到fragment中去
    private void setFragment(Fragment currentFragment) {
       if (tempFragment != currentFragment){
           FragmentManager manager = getSupportFragmentManager();
           //开启事务
           FragmentTransaction ft = manager.beginTransaction();
           //切换
           if (currentFragment!=null){
               //是否添加过
               if (!currentFragment.isAdded()){
                   //把之前显示的给隐藏
                   if (tempFragment!=null){
                       ft.hide(tempFragment);
                   }
                   //如果没有添加就添加
                   ft.add(R.id.fl_content,currentFragment);
               }else {
                   //把之前的隐藏
                   if (tempFragment!=null){
                       ft.hide(tempFragment);
                   }
                   //如果添加了就直接显示
                   ft.show(currentFragment);
               }
               //最后一步，提交事务
               ft.commit();
           }
           tempFragment = currentFragment;
       }
    }

    /**
     * 解决安卓6.0以上版本不能读取外部存储权限的问题
     * @param activity
     * @return
     */
    public static boolean isGrantExternalRW(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
            return false;
        }
        return true;
    }


}
