package com.meidp.crmim.activity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.http.HttpTask;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.Versions;
import com.meidp.crmim.utils.AppUtils;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

@ContentView(R.layout.activity_about)
public class AboutActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.version_name)
    private TextView versionName;
    @ViewInject(R.id.latest_version)
    private TextView latestVersion;

    @Override
    public void onInit() {
        title.setText("关于");
        versionName.setText("当前版本：" + AppUtils.getVersionName(AboutActivity.this));
    }

    @Event({R.id.back_arrows, R.id.version_name})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.version_name:
                HttpTask.detectionNewAppVersion(this, true, true);//检查版本更新
                break;
        }
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("AppId", 102);

        HttpRequestUtils.getmInstance().send(AboutActivity.this, Constant.VERSION_UPDATE, params, new HttpRequestCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("Versions", result);
                        final AppBean<Versions> v = JSONObject.parseObject(result, new TypeReference<AppBean<Versions>>() {
                        });
                        if (v != null && v.getEnumcode() == 0) {
                            latestVersion.setText("最新版本：" + v.getData().getVersionName());
                        }
                    }
                }
        );
    }
}
