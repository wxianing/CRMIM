package com.meidp.crmim.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.meidp.crmim.R;
import com.meidp.crmim.utils.StatusBarUtils;

import org.xutils.x;

/**
 * Package： com.meidp.crmim.activity
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/31
 */
public class BasicActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        StatusBarUtils.setStatusBarColor(this, R.color.black);
        onInit();
        onInitData();
        onInitEvent();
    }

    public void onInit() {

    }

    public void onInitData() {

    }

    public void onInitEvent() {

    }
}
