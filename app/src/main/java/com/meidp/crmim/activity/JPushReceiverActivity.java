package com.meidp.crmim.activity;

import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_jpush_receiver)
public class JPushReceiverActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.content_tv)
    private TextView contentTv;

    @Override
    public void onInit() {
        title.setText("消息");
        String content = getIntent().getStringExtra("content");
        contentTv.setText(content);
    }

    @Event({R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
