package com.meidp.crmim.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.meidp.crmim.R;
import com.meidp.crmim.utils.StatusBarUtils;

import org.xutils.x;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        StatusBarUtils.setStatusBarColor(this, R.color.black);
        x.view().inject(this);
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