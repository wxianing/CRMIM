package com.meidp.crmim.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.meidp.crmim.R;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.IMkitConnectUtils;
import com.meidp.crmim.utils.StatusBarUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.x;

import io.rong.imkit.RongIM;
import io.rong.imkit.RongIMClientWrapper;

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
//        ToastUtils.shows(this, "我运行了11111111");
    }

    public void onInitData() {
//        IMkitConnectUtils
//        new IMkitConnectUtils().connect(Constant.getTOKEN(), this);
//        ToastUtils.shows(this, "我运行了22222222");
    }

    public void onInitEvent() {

    }
}