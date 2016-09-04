package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class AddLinkManActivity extends BasicActivity implements View.OnClickListener {

    private TextView title;
    private EditText customerNameEt;
    private EditText linkmanPhoneEt;
    private EditText emailEt;
    private EditText remarkEt;
    private EditText sexEt;
    private EditText departmentEt;
    private EditText qqEt;
    private EditText ageEt;
    private EditText companyEt;
    private String custNo;
    private EditText positionEt;
    private int teamId;
    private String teamName;
    private List<TeamDetails.UsersBean> userLists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_link_man);
        onInit();
        initEvent();
    }

    private void initEvent() {

        findViewById(R.id.back_arrows).setOnClickListener(this);
        findViewById(R.id.save_btn).setOnClickListener(this);
        findViewById(R.id.sex_et).setOnClickListener(this);
        findViewById(R.id.company_et).setOnClickListener(this);
        findViewById(R.id.add_img).setOnClickListener(this);
    }


    public void onInit() {
        title = (TextView) findViewById(R.id.title_tv);
        customerNameEt = (EditText) findViewById(R.id.customer_name);
        linkmanPhoneEt = (EditText) findViewById(R.id.linkman_phone);
        emailEt = (EditText) findViewById(R.id.e_mail);
        remarkEt = (EditText) findViewById(R.id.remark);
        sexEt = (EditText) findViewById(R.id.sex_et);
        departmentEt = (EditText) findViewById(R.id.department);
        qqEt = (EditText) findViewById(R.id.qq_et);
        ageEt = (EditText) findViewById(R.id.age_et);
        companyEt = (EditText) findViewById(R.id.company_et);
        positionEt = (EditText) findViewById(R.id.positions);

        title.setText("添加团队成员");
        teamId = getIntent().getIntExtra("teamId", 0);
        teamName = getIntent().getStringExtra("TeamName");//接收过来的团队名称
        userLists = (List<TeamDetails.UsersBean>) getIntent().getSerializableExtra("UsersBean");//接收传过来的对象
    }

    public void onClick(View v) {
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
