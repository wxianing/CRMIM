package com.meidp.crmim.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.meidp.crmim.R;

import cn.jpush.android.api.JPushInterface;

public class WelcomeActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        // 获得当前窗体对象
        Window window = WelcomeActivity.this.getWindow();
        // 设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.activity_welcome);

        new Handler() {
            public void handleMessage(android.os.Message msg) {

                Intent intent = new Intent(WelcomeActivity.this,
                        LoginActivity.class);
                startActivity(intent);

                finish();
            }
        }.sendEmptyMessageDelayed(0, 0);
    }


    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
