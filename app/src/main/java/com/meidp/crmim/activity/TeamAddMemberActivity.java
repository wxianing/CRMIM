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
import com.meidp.crmim.model.Contact;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

@ContentView(R.layout.activity_add_member_layout)
public class TeamAddMemberActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.linkman_name)
    private EditText linkmanName;

    @ViewInject(R.id.e_mail)
    private EditText emailEt;

    @ViewInject(R.id.remark)
    private EditText remarkEt;

    @ViewInject(R.id.sex_et)
    private EditText sexEt;
    @ViewInject(R.id.department)
    private EditText departmentEt;
    @ViewInject(R.id.qq_et)
    private EditText qqEt;
    @ViewInject(R.id.age_et)
    private EditText ageEt;
    @ViewInject(R.id.company_et)
    private EditText companyEt;
    private String custNo;
    @ViewInject(R.id.positions)
    private EditText positionEt;
    @ViewInject(R.id.phone_num)
    private EditText phoneNum;//手机号码
    @ViewInject(R.id.company_phone)
    private EditText companyPhone;

    @Override
    public void onInit() {
        title.setText("添加团队成员");
        sexEt.setText("男");
    }

    @Event(value = {R.id.back_arrows, R.id.save_btn, R.id.sex_et, R.id.company_et, R.id.select_img})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.save_btn:
                sendMsg();
                break;
            case R.id.sex_et:
                intent = new Intent(this, SexSelectActivity.class);
                startActivityForResult(intent, 1010);
                break;
            case R.id.company_et:
//                intent = new Intent(this, HospitalListActivity.class);
//                startActivityForResult(intent, 1012);
                break;
            case R.id.select_img:
                intent = new Intent(this, AddTeamActivity.class);
                startActivityForResult(intent, 1022);
                break;
        }
    }

    private void sendMsg() {
        String linkmanNames = linkmanName.getText().toString().trim();//姓名
        String phoneNums = phoneNum.getText().toString().trim();//手机号码
        String companyPhones = companyPhone.getText().toString().trim();//公司电话
        String email = emailEt.getText().toString().trim();
        final String remark = remarkEt.getText().toString().trim();
        String department = departmentEt.getText().toString().trim();
        String qq = qqEt.getText().toString().trim();
        String age = ageEt.getText().toString().trim();
        String position = positionEt.getText().toString().trim();
        String company = companyEt.getText().toString().trim();

        if (NullUtils.isNull(linkmanNames)) {
            if (NullUtils.isNull(linkmanNames)) {

                HashMap params = new HashMap();
                params.put("LinkmanName", linkmanNames);//姓名
                params.put("MobilePhone", phoneNums);//手机号码
                params.put("CompanyPhone", companyPhones);//公司电话

                params.put("email", email);
                params.put("Remark", remark);
                params.put("Sex", sexFlag);
                params.put("QQ", qq);
                params.put("Age", age);
                params.put("Position", position);
                params.put("CompanyName", company);//公司名称

                HttpRequestUtils.getmInstance().send(TeamAddMemberActivity.this, Constant.SAVE_TEAMLINKMAN_URL, params, new HttpRequestCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("New Customer", result);
                        AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                        });
                        if (appMsg != null && appMsg.getEnumcode() == 0) {
                            ToastUtils.shows(TeamAddMemberActivity.this, "保存成功");
                            Intent intent = new Intent();
                            setResult(1023, intent);
                            finish();
                        } else {
                            ToastUtils.shows(TeamAddMemberActivity.this, appMsg.getMsg());
                        }
                    }
                });
            } else {
                ToastUtils.shows(TeamAddMemberActivity.this, "请填写手机号码");
            }
        } else {
            ToastUtils.shows(TeamAddMemberActivity.this, "请填写客户名称");
        }
    }

    int sexFlag = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Constant.RESULT_OK == 1) {
        }
        if (resultCode == 1010) {
            sexFlag = data.getIntExtra("SEX", 0);
            String sexString = data.getStringExtra("SEXTYPE");

            sexEt.setText(sexString);
        }
        if (resultCode == 1012) {
            custNo = getIntent().getStringExtra("CustNo");
            String custName = data.getStringExtra("CustName");
            Log.e("cus", "cust" + custName + ">>>>>>>>>>" + custNo);
            custName = (String) SPUtils.get(this, "CustName", "");
            custNo = (String) SPUtils.get(this, "CustNo", "");
            companyEt.setText(custName);
        }
        if (resultCode == 1022) {
            Contact.UsersBean user = (Contact.UsersBean) data.getSerializableExtra("USERS");
            Log.e("user", ">>>>>>>>>" + user.getEmployeeName());
            linkmanName.setText(user.getEmployeeName());
            phoneNum.setText(user.getMobile());
            companyPhone.setText(user.getMobile());
            positionEt.setText(user.getQuarterName());
        }
    }
}
