package com.meidp.crmim.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
import com.meidp.crmim.model.TeamDetails;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_add_link_man)
public class AddLinkManActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.customer_name)
    private EditText customerNameEt;

    @ViewInject(R.id.linkman_phone)
    private EditText linkmanPhoneEt;
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
    private int teamId;
    private String teamName;
    private List<TeamDetails.UsersBean> userLists;

    @Override
    public void onInit() {
        title.setText("添加团队成员");
        teamId = getIntent().getIntExtra("teamId", 0);
        teamName = getIntent().getStringExtra("TeamName");//接收过来的团队名称
        userLists = (List<TeamDetails.UsersBean>) getIntent().getSerializableExtra("UsersBean");//接收传过来的对象
    }

    @Event(value = {R.id.back_arrows, R.id.save_btn, R.id.sex_et, R.id.company_et, R.id.add_img})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.save_btn:
                String customeerName = customerNameEt.getText().toString().trim();
                String linkmanPhone = linkmanPhoneEt.getText().toString().trim();
                String email = emailEt.getText().toString().trim();
                final String remark = remarkEt.getText().toString().trim();
                String department = departmentEt.getText().toString().trim();
                String qq = qqEt.getText().toString().trim();
                String age = ageEt.getText().toString().trim();
                String position = positionEt.getText().toString().trim();

                if (NullUtils.isNull(customeerName)) {
                    if (NullUtils.isNull(linkmanPhone)) {

                        HashMap params = new HashMap();
                        params.put("LinkManName", customeerName);
                        params.put("WorkTel", linkmanPhone);
                        params.put("email", email);
                        params.put("Remark", remark);
                        params.put("Sex", sexFlag);
                        params.put("Department", department);
                        params.put("QQ", qq);
                        params.put("Age", age);
                        params.put("CustNo", custNo);
                        params.put("Position", position);

                        HttpRequestUtils.getmInstance().send(AddLinkManActivity.this, Constant.SAVE_CUSTOMER_CONTACT_URL, params, new HttpRequestCallBack<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Log.e("New Customer", result);
                                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                                });
                                if (appMsg != null && appMsg.getEnumcode() == 0) {
                                    ToastUtils.shows(AddLinkManActivity.this, "保存成功");
                                    finish();
                                } else {
                                    ToastUtils.shows(AddLinkManActivity.this, appMsg.getMsg());
                                }
                            }
                        });
                    } else {
                        ToastUtils.shows(AddLinkManActivity.this, "请填写手机号码");
                    }
                } else {
                    ToastUtils.shows(AddLinkManActivity.this, "请填写客户名称");
                }

                break;
            case R.id.sex_et:
                intent = new Intent(this, SexSelectActivity.class);
                startActivityForResult(intent, 1010);
                break;
            case R.id.company_et:
                intent = new Intent(this, HospitalListActivity.class);
                startActivityForResult(intent, 1012);
                break;
            case R.id.add_img:
                intent = new Intent(this, AddTeamActivity.class);
                intent.putExtra("UsersBean", (Serializable) userLists);
                intent.putExtra("teamId", teamId);
                intent.putExtra("TeamName", teamName);
                startActivity(intent);
                break;
        }
    }

    int sexFlag = 0;

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
    }

}
