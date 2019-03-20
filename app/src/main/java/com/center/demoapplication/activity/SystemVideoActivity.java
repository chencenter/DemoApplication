package com.center.demoapplication.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.center.demoapplication.R;

public class SystemVideoActivity extends AppCompatActivity {

    private VideoView videoView;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_video);
        videoView = findViewById(R.id.videoview);

        setOnListener();

        uri = getIntent().getData();
        if (uri!=null){
            videoView.setVideoURI(uri);
        }

    }

    private void setOnListener() {
        //准备
        videoView.setOnPreparedListener(new MyOnPreparedListener());
        //出错
        videoView.setOnErrorListener(new MyOnErrorListener());
        //播放完成
        videoView.setOnCompletionListener(new MyOnCompletionListener());
        //控制面板
        videoView.setMediaController(new MediaController(this));
    }

    class MyOnPreparedListener implements MediaPlayer.OnPreparedListener{

        @Override
        public void onPrepared(MediaPlayer mp) {
            videoView.start();
        }
    }
    class MyOnErrorListener implements MediaPlayer.OnErrorListener{

        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            Toast.makeText(SystemVideoActivity.this,"播放错误",Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    class MyOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(SystemVideoActivity.this,"播放完成",Toast.LENGTH_SHORT).show();
        }
    }

}
