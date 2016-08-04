package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.Product;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 样机申请
 */
@ContentView(R.layout.activity_model_machine_apply)
public class ModelMachineApplyActivity extends BaseActivity {

    @ViewInject(R.id.title_right)
    private TextView titleRight;
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_et)
    private EditText titleEt;//标题
    @ViewInject(R.id.cust_tel)
    private EditText custTelEt;
    @ViewInject(R.id.count_et)
    private EditText countEt;
    @ViewInject(R.id.remark_et)
    private EditText remarkEt;

    @ViewInject(R.id.customer_et)
    private EditText customerEt;
    @ViewInject(R.id.project_et)
    private EditText projectEt;
    @ViewInject(R.id.prototype_et)
    private EditText prototypeEt;
    private int custId;
    private int projectId;
    private int productID;
    private String productName;

    @Override
    public void onInit() {
        titleRight.setText("保存");
        titleRight.setVisibility(View.VISIBLE);
        title.setText("样机申请");
    }

    @Event(value = {R.id.back_arrows, R.id.title_right, R.id.customer_et, R.id.project_et, R.id.prototype_et})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                sendMsg();
                break;
            case R.id.customer_et://选择客户
                intent = new Intent();
                intent.setClass(this, CustomerListActivity.class);
                intent.putExtra("FLAG", "Apply");
                startActivityForResult(intent, 1003);//客户
                break;
            case R.id.project_et://选择项目
                intent = new Intent(this, ProjectManagerActivity.class);
                intent.putExtra("FLAG", "Apply");
                startActivityForResult(intent, 1004);
                break;
            case R.id.prototype_et:
                intent = new Intent(this, PrototypeListActivity.class);
                startActivityForResult(intent, 1005);
                break;
        }
    }

    private void sendMsg() {
        String titleStr = titleEt.getText().toString().trim();
        String custTel = custTelEt.getText().toString().trim();
        String count = countEt.getText().toString().trim();
        String remark = remarkEt.getText().toString().trim();
        Product produce = new Product();
        produce.setProductID(productID);
        produce.setProductName(productName);
        produce.setRemark(remark);
        if (NullUtils.isNull(count)) {
            produce.setProductCount(Double.valueOf(count));
        } else {
            ToastUtils.shows(this, "请填写数量");
        }

        List<Product> produceLists = new ArrayList<>();
        produceLists.add(produce);

        HashMap params = new HashMap();
        params.put("Title", titleStr);
        params.put("CustID", custId);
        params.put("CustTel", custTel);
        params.put("ProjectID", projectId);
        params.put("details", produceLists);

        HttpRequestUtils.getmInstance().send(ModelMachineApplyActivity.this, Constant.PROTOTYPE_SAVE_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppMsg appMag = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMag != null && appMag.getEnumcode() == 0) {
                    ToastUtils.shows(ModelMachineApplyActivity.this, "保存成功");
                    finish();
                } else {
                    ToastUtils.shows(ModelMachineApplyActivity.this, "保存失败");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (resultCode) {
                case 1003:
                    custId = data.getIntExtra("OID", -1);
                    String custName = data.getStringExtra("CustName");
                    customerEt.setText(custName);
                    break;
                case 1004:
                    projectId = data.getIntExtra("ProjectId", -1);
                    String projectName = data.getStringExtra("ProjectName");
                    projectEt.setText(projectName);
                    break;
                case 1005:
                    productID = data.getIntExtra("ProductID", -1);
                    productName = data.getStringExtra("ProductName");
                    prototypeEt.setText(productName);
                    break;
            }
        }
    }
}
