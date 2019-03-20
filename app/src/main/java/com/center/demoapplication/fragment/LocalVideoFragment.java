package com.center.demoapplication.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.center.demoapplication.R;
import com.center.demoapplication.activity.SystemVideoActivity;
import com.center.demoapplication.adapter.LocalVideoAdapter;
import com.center.demoapplication.base.BaseFragment;
import com.center.demoapplication.bean.MediaItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaa on 2019/3/19.
 */

public class LocalVideoFragment extends BaseFragment {

    private ListView listView;
    private TextView textView;
    private ProgressBar progressBar;
    private List<MediaItemBean> list;
    private LocalVideoAdapter adapter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (list!=null && list.size()>0){
                //有数据
                //文本隐藏
                textView.setVisibility(View.GONE);
                //设置适配器
                adapter.add(list);
                listView.setAdapter(adapter);
            }else {
                //没有数据
                //文本显示
                textView.setVisibility(View.VISIBLE);
            }
            progressBar.setVisibility(View.GONE);
        }
    };

    @Override
    public View initViews() {
        View view = View.inflate(context, R.layout.fragment_local_autio,null);
        listView = view.findViewById(R.id.listview);
        textView = view.findViewById(R.id.tv_show);
        progressBar = view.findViewById(R.id.pb_loading);
        return view;
    }
    @Override
    public void initData() {
        super.initData();
        textView.setText("本地视频");
        adapter = new LocalVideoAdapter(context);
        //加载本地视频数据
        getDataFromLocal();
    }

    @Override
    public void initOnClick() {
        super.initOnClick();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MediaItemBean bean = list.get(position);
                //调用系统已经存在的播放器-隐式意图
//                Intent intent = new Intent();
//                intent.setDataAndType(Uri.parse(bean.getData()),"video/*");
//                context.startActivity(intent);
                Intent intent = new Intent(context, SystemVideoActivity.class);
                intent.setDataAndType(Uri.parse(bean.getData()),"video/*");
                context.startActivity(intent);
            }
        });
    }

    /*
         *从本地的sdCard得到数据
         * 1、遍历sdcard，后缀名
         * 2、从内容提供者里面获取数据
         * 3、6.0以后要动态获取sdcard权限
         */
    public void getDataFromLocal() {
        list = new ArrayList<>();
        new Thread(){
            @Override
            public void run() {
                super.run();
                ContentResolver resolver = context.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objs = {
                        MediaStore.Video.Media.DISPLAY_NAME,//视频文件在sdcar的名称
                        MediaStore.Video.Media.DURATION,//视频文件总时长
                        MediaStore.Video.Media.SIZE,//文件大小
                        MediaStore.Video.Media.DATA,//文件绝对地址
                        MediaStore.Video.Media.ARTIST,//歌曲演唱者
                };
                Cursor cursor =  resolver.query(uri,objs,null,null,null);
                if (cursor!=null){
                    while (cursor.moveToNext()){
                        MediaItemBean bean = new MediaItemBean();
                        list.add(bean);
                        String name = cursor.getString(0);
                        bean.setName(name);
                        long duration = cursor.getLong(1);
                        bean.setDuration(duration);
                        long size = cursor.getLong(2);
                        bean.setSize(size);
                        String data = cursor.getString(3);
                        bean.setData(data);
                        String artist = cursor.getString(4);
                        bean.setArtist(artist);
                    }
                    cursor.close();
                }
                //Handler发消息
                handler.sendEmptyMessage(10);
            }
        }.start();
    }
}
