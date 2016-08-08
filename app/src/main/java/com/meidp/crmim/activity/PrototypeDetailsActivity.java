package com.meidp.crmim.activity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

/**
 * 样机详情
 */
@ContentView(R.layout.activity_prototype_details)
public class PrototypeDetailsActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;
    private int id;

    @Override
    public void onInit() {
        title.setText("详情");
        id = getIntent().getIntExtra("ID", -1);
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("Id", id);
        HttpRequestUtils.getmInstance().send(PrototypeDetailsActivity.this, Constant.PROTOTYPE_DETAILS_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", result);
            }
        });
    }

    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
