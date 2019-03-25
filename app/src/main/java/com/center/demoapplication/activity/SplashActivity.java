package com.center.demoapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.center.demoapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

@ContentView(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity {


    private static final String TAG = SplashActivity.class.getSimpleName();
    private Handler handler = new Handler();
    private boolean flag = false;//设置标签防止多次跳转到主页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
        x.view().inject(this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //休眠2秒钟后跳转到主页面
                startActivity();

            }
        },2000);

    }

    //跳转到主页面
    private void startActivity(){
        if (!flag){
            flag = true;//方法二：防止多次跳转到主页面
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startActivity();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
