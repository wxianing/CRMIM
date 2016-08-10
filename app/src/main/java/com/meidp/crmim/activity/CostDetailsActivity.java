package com.meidp.crmim.activity;

import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.CostDetails;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

@ContentView(R.layout.activity_cost_details)
public class CostDetailsActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;


    @ViewInject(R.id.title_name)
    private TextView titleName;
    @ViewInject(R.id.apply_person)
    private TextView applyPerson;
    @ViewInject(R.id.classify)
    private TextView classify;
    @ViewInject(R.id.relevance_project)
    private TextView relevanceProject;
    @ViewInject(R.id.total_money)
    private TextView totalMoney;
    @ViewInject(R.id.reason)
    private TextView reason;
    @ViewInject(R.id.remark)
    private TextView remark;
    @ViewInject(R.id.department)
    private TextView department;
    @ViewInject(R.id.check_time)
    private TextView checkTime;
    private int id;


    @Override
    public void onInit() {
        title.setText("报销详情");
        id = getIntent().getIntExtra("OID", -1);
    }

    @Override
    public void onInitData() {
        HashMap hashMap = new HashMap();
        hashMap.put("Id", id);
        HttpRequestUtils.getmInstance().send(CostDetailsActivity.this, Constant.FEELAPPLY_URL, hashMap, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBean<CostDetails> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<CostDetails>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    bindView(appBean);
                }
            }
        });
    }

    private void bindView(AppBean<CostDetails> appBean) {
        titleName.setText("标题：" + appBean.getData().getTitle());
        applyPerson.setText("申请人：" + appBean.getData().getCreatorName());
        reason.setText("申请原因：" + appBean.getData().getReason());

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
