package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class ApprovalTypeActivity extends BasicActivity implements View.OnClickListener {

    private TextView title;
    private TextView feeUnReader;
    private TextView modelUnReader;
    private TextView stockupUnreader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_type);
        onInit();
        initEvent();
    }

    private void initEvent() {
        findViewById(R.id.back_arrows).setOnClickListener(this);
        findViewById(R.id.cost).setOnClickListener(this);
        findViewById(R.id.prototype).setOnClickListener(this);
        findViewById(R.id.stockup_layout).setOnClickListener(this);
    }


    public void onInit() {
        title = (TextView) findViewById(R.id.title_tv);
        feeUnReader = (TextView) findViewById(R.id.fee_unreader);
        modelUnReader = (TextView) findViewById(R.id.model_unreader);
        stockupUnreader = (TextView) findViewById(R.id.stockup_unreader);


        title.setText("审批管理");
    }

    public void onClick(View v) {
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
            case R.id.stockup_layout:
                intent = new Intent(this, StockUpActivity.class);
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

                    BadgeView stockUpView = new BadgeView(ApprovalTypeActivity.this);
                    stockUpView.setBadgeCount(appBean.getData().getNoCheckCount_Order());
                    stockUpView.setTargetView(stockupUnreader);
                }
            }
        });
    }
}
