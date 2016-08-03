package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_convention_apply_for)
public class ConventionApplyForActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_right)
    private TextView titleRight;

    @Override
    public void onInit() {
        title.setText("地方参展申请单");
        titleRight.setText("申请");
        titleRight.setVisibility(View.VISIBLE);
    }

    @Event({R.id.back_arrows, R.id.title_right})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                sendMsg();
                break;
        }
    }

    private void sendMsg() {
        Intent intent = new Intent();
        intent.setClass(this, ConventionArrangeActivity.class);
        startActivity(intent);
    }
}
