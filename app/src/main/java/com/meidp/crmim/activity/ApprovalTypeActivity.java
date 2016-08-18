package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jauker.widget.BadgeView;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.NoReaders;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

@ContentView(R.layout.activity_approval_type)
public class ApprovalTypeActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.fee_unreader)
    private TextView feeUnReader;

    @ViewInject(R.id.model_unreader)
    private TextView modelUnReader;

    @Override
    public void onInit() {
        title.setText("审批管理");
    }

    @Event({R.id.back_arrows, R.id.cost, R.id.prototype})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.cost:
                intent = new Intent(this, ApprovalCostActivity.class);
                startActivity(intent);
                break;
            case R.id.prototype:
                intent = new Intent(this, ApprovalProcessActivity.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        HashMap params = new HashMap();
        HttpRequestUtils.getmInstance().send(ApprovalTypeActivity.this, Constant.GETNOCHECKTOTAL_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBean<NoReaders> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<NoReaders>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    BadgeView feeBadgeView = new BadgeView(ApprovalTypeActivity.this);
                    feeBadgeView.setBadgeCount(appBean.getData().getNoCheckCount_Fee());
                    feeBadgeView.setTargetView(feeUnReader);

                    BadgeView modelBadgeView = new BadgeView(ApprovalTypeActivity.this);
                    modelBadgeView.setBadgeCount(appBean.getData().getNoCheckCount_Sample());
                    modelBadgeView.setTargetView(modelUnReader);
                }
            }
        });
    }
}
