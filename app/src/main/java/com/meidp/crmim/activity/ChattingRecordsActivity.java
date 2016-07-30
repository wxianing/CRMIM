package com.meidp.crmim.activity;

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
public class ChattingRecordsActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;
    private String userId;

    @Override
    public void onInit() {
        title.setText("聊天记录");
        userId = getIntent().getStringExtra("userId");

    }

    @Override
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
}
