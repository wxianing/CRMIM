package com.meidp.crmim.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.Rules;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

/**
 * 规章制度
 */
@ContentView(R.layout.activity_regulatory_framework)
public class RegulatoryFrameworkActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.content_tv)
    private TextView content;

    @Override
    public void onInit() {
        title.setText("公司制度");
    }

    /**
     * "Keyword": "",
     * "sType": 1,
     * "PageIndex": 1,
     * "PageSize": 4
     */
    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("Keyword", "");
        params.put("sType", 2);
        params.put("PageIndex", 1);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(RegulatoryFrameworkActivity.this, Constant.REGULATORY_FRAMEWORK_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppDatas<Rules> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<Rules>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    if (appDatas.getData().getDataList().size() > 0) {
                        String contents = appDatas.getData().getDataList().get(0).getCulturetent();
                        content.setText(contents);
                    }
                }
            }
        });
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
