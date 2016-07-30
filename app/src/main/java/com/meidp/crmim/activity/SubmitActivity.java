package com.meidp.crmim.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

@ContentView(R.layout.activity_submit)
public class SubmitActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.title_right)
    private TextView titleRight;

    @ViewInject(R.id.edittext_project_name)
    private EditText projectNameEt;
    @ViewInject(R.id.edittext_project_address)
    private EditText projectAddressEt;
    @ViewInject(R.id.edittext_customer_name)
    private EditText CustomerNameEt;
    @ViewInject(R.id.edittext_customer_phone)
    private EditText CustomerPhoneEt;
    @ViewInject(R.id.edittext_total_price)
    private EditText projectTotalPriceEt;
    @ViewInject(R.id.edittext_success_rate)
    private EditText projectSuccessRateEt;
//    @ViewInject(R.id.remark)
//    private EditText remarkEt;

    @Override
    public void onInit() {
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setText("保存");
        title.setText("项目上报");
    }

    @Event(value = {R.id.back_arrows, R.id.title_right, R.id.edittext_customer_name})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.edittext_customer_name:
                break;
            case R.id.title_right:
                String projectName = projectNameEt.getText().toString().trim();
                String projectAddress = projectAddressEt.getText().toString().trim();
                String CustomerName = CustomerNameEt.getText().toString().trim();
                String CustomerPhone = CustomerPhoneEt.getText().toString().trim();
                String projectTotalPrice = projectTotalPriceEt.getText().toString().trim();
                String projectSuccessRate = projectSuccessRateEt.getText().toString().trim();
                HashMap params = new HashMap();

                params.put("ProjectName", projectName);//项目名称
                params.put("Address", projectAddress);//项目地址
                params.put("CustLinkMan", CustomerName);//客户联系人
                params.put("CustLinkTel", CustomerPhone);//客户号码
                params.put("Investment", projectTotalPrice);//总价钱
                params.put("SuccessRate", projectSuccessRate);//成功率

                HttpRequestUtils.getmInstance().send(SubmitActivity.this, Constant.SAVE_PROJECT, params, new HttpRequestCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                        });
                        if (appMsg != null && appMsg.getEnumcode() == 0) {
                            ToastUtils.shows(SubmitActivity.this, "保存成功");
                            finish();
                        } else {
                            ToastUtils.shows(SubmitActivity.this, "保存失败");
                        }
                    }
                });
                break;
        }
    }
}
