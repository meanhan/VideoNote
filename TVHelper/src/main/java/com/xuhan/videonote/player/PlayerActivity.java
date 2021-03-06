package com.xuhan.videonote.player;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.dl7.player.media.IjkPlayerView;
import com.xuhan.videonote.R;
import com.xuhan.videonote.entity.LocalMediaEntity;

/**
 * @author meanhan
 */

public class PlayerActivity extends AppCompatActivity {

    private IjkPlayerView mPlayerView;
    private LocalMediaEntity media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        mPlayerView = findViewById(R.id.player_view);
        media = getIntent().getExtras().getParcelable("media");
        initPlayer();
    }

    private void initPlayer() {
        // 初始化，必须先调用
        mPlayerView.init()
                // 设置标题，全屏时显示
                .setTitle(media.getTitle())
                // 设置跳转提示
//                .setSkipTip(1000*60*1)
                // 使能重力翻转
                .enableOrientation()
                // 固定全屏
//                .alwaysFullScreen()
                // 设置视频Url，单个视频源可用这个
                .setVideoPath(media.getPath())
                // 设置视频Url，多个视频源用这个
//                .setVideoSource(null, VIDEO_URL, VIDEO_URL, VIDEO_URL, null)
                // 指定初始视频源
                .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH)
                // 使用弹幕功能(不开启弹幕功能,截图会报错)
                .enableDanmaku()
                // 是否显示弹幕
//                .showOrHideDanmaku(false)
                // 添加弹幕资源，必须在enableDanmaku()后调用
//                .setDanmakuSource(getResources().openRawResource(R.raw.comments))
                // 启动播放
                .start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPlayerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPlayerView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayerView.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mPlayerView.configurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mPlayerView.handleVolumeKey(keyCode)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (mPlayerView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
