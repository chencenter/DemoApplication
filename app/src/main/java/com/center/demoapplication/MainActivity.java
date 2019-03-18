package com.center.demoapplication;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.fl_content);
        radioGroup = findViewById(R.id.rg_bottom_tag);
//        radioGroup.check(R.id.rb_one);

    }
}
