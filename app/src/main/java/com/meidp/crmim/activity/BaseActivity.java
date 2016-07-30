package com.meidp.crmim.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.meidp.crmim.R;
import com.meidp.crmim.utils.StatusBarUtils;

import org.xutils.x;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setStatusBarColor(this, R.color.statusbar_bg);
        x.view().inject(this);
        onInit();
        onInitData();
    }

    public void onInit() {
    }

    public void onInitData() {
    }
}