package com.meidp.crmim.activity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.PrototypeDetails;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
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

    @ViewInject(R.id.title_name)
    private TextView titleName;
    @ViewInject(R.id.prototype_name)
    private TextView prototypeName;
    @ViewInject(R.id.count_tv)
    private TextView count;
    @ViewInject(R.id.apply_time)
    private TextView applyTime;
    @ViewInject(R.id.apply_person)
    private TextView applyPerson;
    @ViewInject(R.id.phone_num)
    private TextView phoneNum;

    @Override
    public void onInit() {
        title.setText("详情");
        id = getIntent().getIntExtra("OID", -1);
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("Id", id);
        HttpRequestUtils.getmInstance().send(PrototypeDetailsActivity.this, Constant.PROTOTYPE_DETAILS_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", result);
                AppBean<PrototypeDetails> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<PrototypeDetails>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    bindView(appBean);
                }
            }
        });
    }

    private void bindView(AppBean<PrototypeDetails> appBean) {
        titleName.setText("标题：" + appBean.getData().getTitle());
        if (appBean.getData().getDetails().size() > 0) {
            prototypeName.setText("样机名称：" + appBean.getData().getDetails().get(0).getProductName());
            count.setText("数量：" + Integer.toString(appBean.getData().getDetails().get(0).getProductCount()));
        }
        applyTime.setText("申请时间：" + appBean.getData().getCreateDate());
        applyPerson.setText("申请人：" + appBean.getData().getApplyer());
        phoneNum.setText("联系电话：" + appBean.getData().getCustTel());
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
