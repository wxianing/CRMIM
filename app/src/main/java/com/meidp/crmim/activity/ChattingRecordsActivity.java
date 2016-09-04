package com.meidp.crmim.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.LogUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;


@ContentView(R.layout.activity_chatting_records)
public class ChattingRecordsActivity extends BasicActivity implements View.OnClickListener {

    @ViewInject(R.id.title_tv)
    private TextView title;
    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_records);
        onInit();
        onInitData();
        initEvent();
    }

    private void initEvent() {
        findViewById(R.id.back_arrows).setOnClickListener(this);
    }

    public void onInit() {
        title = (TextView) findViewById(R.id.title_tv);
        title.setText("聊天记录");
        userId = getIntent().getStringExtra("userId");
    }

    public void onInitData() {
        HashMap params = new HashMap();
        params.put("Id", userId);
        params.put("PageIndex", 1);
        params.put("PageSize", 10);

        HttpRequestUtils.getmInstance().send(ChattingRecordsActivity.this, Constant.CHATTING_RECODE_URL, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.e("CHATTING_RECODE_URL", result);
            }
        });

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
