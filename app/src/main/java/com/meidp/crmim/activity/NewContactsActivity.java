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

@ContentView(R.layout.activity_new_contacts)
public class NewContactsActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_right)
    private TextView titleRight;

    @ViewInject(R.id.contact_name)
    private EditText contactNameEt;
    @ViewInject(R.id.company_name)
    private EditText companyNameEt;
    @ViewInject(R.id.department_et)
    private EditText departmentEt;
    @ViewInject(R.id.phone_num)
    private EditText phoneNumEt;
    @ViewInject(R.id.sex_et)
    private EditText sexEt;
    @ViewInject(R.id.age_et)
    private EditText ageEt;
    @ViewInject(R.id.email_et)
    private EditText emailEt;
    @ViewInject(R.id.qq_et)
    private EditText qqEt;
    private String custNo;


    @Override
    public void onInit() {
        titleRight.setText("保存");
        titleRight.setVisibility(View.VISIBLE);
        title.setText("添加联系人");

        custNo = getIntent().getStringExtra("CustNo");
    }

    @Event(value = {R.id.back_arrows, R.id.title_right})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                ToastUtils.shows(this, "正在保存");
                String contactName = contactNameEt.getText().toString().trim();
                String companyName = companyNameEt.getText().toString().trim();
                String department = departmentEt.getText().toString().trim();
                String phoneNum = phoneNumEt.getText().toString().trim();
                String sex = sexEt.getText().toString().trim();
                String age = ageEt.getText().toString().trim();
                String email = emailEt.getText().toString().trim();
                String qq = qqEt.getText().toString().trim();
                HashMap params = new HashMap();
                int sexTag = 0;
                if (sex.equals("男")) {
                    sexTag = 1;
                } else if (sex.equals("女")) {
                    sexTag = 2;
                } else {
                    sexTag = 0;
                }

                params.put("CustNo", custNo);
                params.put("LinkManName", contactName);
                params.put("Company", companyName);
                params.put("Department", department);
                params.put("WorkTel", phoneNum);
                params.put("Sex", sexTag);
                params.put("Age", age);
                params.put("MailAddress", email);
                params.put("QQ", qq);

                HttpRequestUtils.getmInstance().send(NewContactsActivity.this, Constant.SAVE_CUSTOMER_CONTACT, params, new HttpRequestCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                        });
                        if (appMsg != null && appMsg.getEnumcode() == 0) {
                            finish();
                        }
                    }
                });
                break;
        }
    }
}
