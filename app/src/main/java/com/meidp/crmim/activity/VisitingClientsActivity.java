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
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

/**
 * Package： com.meidp.crmim.activity
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/1
 */
@ContentView(R.layout.activity_visit_client)
public class VisitingClientsActivity extends BaseActivity {

    @ViewInject(R.id.title_right)
    private TextView titleRight;
    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.customer_name)
    private TextView customerName;
    @ViewInject(R.id.content_tv)
    private EditText contentTv;
    @ViewInject(R.id.phone_num)
    private EditText phoneNumEt;

    private int custId;
    private String custName;
    private double latitude;
    private double longitude;
    private String address;
    private String contactPhone;
    private int projectId;

    @ViewInject(R.id.project_name)
    private EditText projectNameEt;
    private String custContact;
    @ViewInject(R.id.hospital_name)
    private EditText hospitalName;

    @Override
    public void onInit() {
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setText("确定");
        title.setText("确认拜访");
        longitude = getIntent().getDoubleExtra("LONGITUDE", 0);
        latitude = getIntent().getDoubleExtra("LATITUDE", 0);
        address = getIntent().getStringExtra("Address");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {

        } else if (requestCode == 1003 && data != null) {
            custId = data.getIntExtra("OID", -1);
            custName = data.getStringExtra("CustName");
            customerName.setText(custName);
        } else if (requestCode == 1001 && data != null) {
            custId = data.getIntExtra("OID", -1);
            custName = data.getStringExtra("CustName");
            contactPhone = data.getStringExtra("ContactPhone");
            custContact = data.getStringExtra("CustContact");//客户名称
            String custPhone = data.getStringExtra("CustPhone");
//            Log.e("contact", contactPhone);
            phoneNumEt.setText(custPhone);
            customerName.setText(custContact);
            hospitalName.setText(custName);
        } else if (resultCode == 1004) {
            projectId = data.getIntExtra("ProjectId", -1);
            String projectName = data.getStringExtra("ProjectName");
            projectNameEt.setText(projectName);
        }
    }

    @Event(value = {R.id.back_arrows, R.id.customer_name, R.id.title_right, R.id.add_project})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.address_trim:
                intent = new Intent();
                intent.setClass(VisitingClientsActivity.this, LocalTrimmingActivity.class);
                startActivityForResult(intent, Constant.RESULTCODE);
                break;
            case R.id.customer_name:
                intent = new Intent();
                intent.setClass(this, CustomerListActivity.class);
                intent.putExtra("FLAG", "Apply");
                startActivityForResult(intent, 1001);
                break;
            case R.id.title_right:
                if (NullUtils.isNull(address)) {
                    String content = contentTv.getText().toString().trim();
                    ToastUtils.shows(this, "正在提交");
                    HashMap params = new HashMap();
                    params.put("Contents", content);//拜访内容
                    params.put("CustID", custId);//客户Id
                    params.put("Title", "拜访" + custName);
                    params.put("Lat", latitude);//维度
                    params.put("Lon", longitude);//经度
                    params.put("LocationAddress", address);//地址
                    HttpRequestUtils.getmInstance().send(VisitingClientsActivity.this, Constant.SAVE_VISIT_CUSTOMER, params, new HttpRequestCallBack<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Log.e("visit", result);
                            AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                            });
                            if (appMsg != null && appMsg.getEnumcode() == 0) {
                                Intent intent = new Intent(VisitingClientsActivity.this, VisitRecordActivity.class);
                                ToastUtils.shows(VisitingClientsActivity.this, "拜访成功");
                                startActivity(intent);
                                finish();
                            } else {
                                ToastUtils.shows(VisitingClientsActivity.this, "拜访失败");
                            }
                        }
                    });
                } else {
                    ToastUtils.shows(this, "定位失败");
                }
                break;
            case R.id.add_project:
                intent = new Intent(this, ProjectManagerActivity.class);
                intent.putExtra("FLAG", "Apply");
                startActivityForResult(intent, 1004);
                break;
        }
    }
}
