package com.meidp.crmim.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.ImportantDetails;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

@ContentView(R.layout.activity_important_details)
public class ImportantDetailsActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;
    private int oid;
    @ViewInject(R.id.title_name)
    private TextView titleName;
    @ViewInject(R.id.content_tv)
    private TextView content;
    @ViewInject(R.id.related_personnel)
    private TextView relatedPersonnel;
    @ViewInject(R.id.create_time)
    private TextView createTime;

    @Override
    public void onInit() {
        title.setText("重要事项");
        oid = getIntent().getIntExtra("OID", 0);
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("Id", oid);
        HttpRequestUtils.getmInstance().send(ImportantDetailsActivity.this, Constant.IMPORTANT_DETAILS_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBean<ImportantDetails> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<ImportantDetails>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    bindView(appBean);
                    titleName.setText(appBean.getData().getArrangeTItle());
                    content.setText("内容：" + appBean.getData().getContent());
                    relatedPersonnel.setText("相关人员：" + appBean.getData().getCanViewUserName());
                    createTime.setText(appBean.getData().getCreateDateStr());
                }
            }
        });
    }

    private void bindView(AppBean<ImportantDetails> appBean) {


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
