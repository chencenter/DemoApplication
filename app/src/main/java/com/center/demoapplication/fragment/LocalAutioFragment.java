package com.center.demoapplication.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.center.demoapplication.R;
import com.center.demoapplication.base.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by aaa on 2019/3/19.
 */

@ContentView(R.layout.fragment_local_autio)
public class LocalAutioFragment extends BaseFragment {
    @ViewInject(R.id.btn_show)
    Button button;
    @ViewInject(R.id.tv_show)
    TextView textView;

//    @Override
//    public View initViews() {
////        View view = View.inflate(context, R.layout.fragment_local_autio,null);
////        textView = view.findViewById(R.id.tv_show);
////        return view;
//
//    }

    @Override
    public View initViews(LayoutInflater inflater, ViewGroup container) {
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void initData() {
        super.initData();
//        textView.setText("本地音乐");
    }

//    @Override
//    public void initOnClick() {
//        super.initOnClick();
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.setText("本地音乐");
//            }
//        });
//    }

    @Event(value = R.id.btn_show)
    private void onEvent(View view){
        switch (view.getId()){
            case R.id.btn_show:
                textView.setText("本地音乐");
                break;
        }
    }
}
