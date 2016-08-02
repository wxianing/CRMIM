package com.meidp.crmim.activity;

import android.content.Intent;
import android.util.Log;
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


@ContentView(R.layout.activity_add_new_client)
public class NewClientActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_right)
    private TextView titleRight;
    @ViewInject(R.id.customer_name)
    private EditText customerNameEt;
    @ViewInject(R.id.customer_phone)
    private EditText customerPhoneEt;
    @ViewInject(R.id.province)
    private EditText provinceEt;
    @ViewInject(R.id.city_name)
    private EditText cityNameEt;
    @ViewInject(R.id.linkman)
    private EditText linkmanEt;
    @ViewInject(R.id.linkman_phone)
    private EditText linkmanPhoneEt;
    @ViewInject(R.id.e_mail)
    private EditText emailEt;
    @ViewInject(R.id.customer_address)
    private EditText customerAddressEt;
    @ViewInject(R.id.sellArea)
    private EditText sellAreaEt;
    @ViewInject(R.id.remark)
    private EditText remarkEt;


    @Override
    public void onInit() {
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setText("保存");
        title.setText("新建客户档案");
    }

    @Event(value = {R.id.back_arrows, R.id.title_right, R.id.province})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                ToastUtils.shows(this, "正在保存");
                String customeerName = customerNameEt.getText().toString().trim();
                String customerPhone = customerPhoneEt.getText().toString().trim();
                String province = provinceEt.getText().toString().trim();
                String cityName = cityNameEt.getText().toString().trim();
                String linkman = linkmanEt.getText().toString().trim();
                String linkmanPhone = linkmanPhoneEt.getText().toString().trim();
                String email = emailEt.getText().toString().trim();
                String customerAddress = customerAddressEt.getText().toString().trim();
                String sellArea = sellAreaEt.getText().toString().trim();
                final String remark = remarkEt.getText().toString().trim();

                HashMap params = new HashMap();
                params.put("CustName", customeerName);
                params.put("Tel", customerPhone);
                params.put("Province", province);
                params.put("City", cityName);
                params.put("ContactName", linkman);
                params.put("Mobile", linkmanPhone);
                params.put("email", email);
                params.put("Address", customerAddress);
                params.put("SellArea", sellArea);
                params.put("Remark", remark);

                HttpRequestUtils.getmInstance().send(NewClientActivity.this, Constant.NEW_CUSTOMEER_URL, params, new HttpRequestCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("New Customer", remark);
                        AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                        });
                        if (appMsg != null && appMsg.getEnumcode() == 0) {
                            finish();
                        }
                    }
                });

                break;
            case R.id.province:
                Intent intent = new Intent(NewClientActivity.this, CityListActivity.class);
                startActivityForResult(intent, Constant.RESULT_OK);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constant.RESULT_OK == 1) {
            provinceEt.setText(data.getStringExtra("cityName"));
        }
    }
}
