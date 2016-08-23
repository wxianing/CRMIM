package com.meidp.crmim.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
import com.meidp.crmim.view.WheelView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

@ContentView(R.layout.activity_submit)
public class SubmitActivity extends BaseActivity {

    private static final String[] PLANETS = new String[]{"10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};

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
    @ViewInject(R.id.remark)
    private EditText remarkEt;
    @ViewInject(R.id.project_area)
    private EditText projectArea;
    @ViewInject(R.id.related_personnel)
    private EditText relatedPersonnel;
    private String empolyeeId;
    @ViewInject(R.id.direction)
    private TextView directionEt;

    @ViewInject(R.id.company_name)
    private EditText companyNameEt;

    private List<String> userIds;

    private int projectDirectionId;
    private double successRate;
    private double rate = 10;
    @ViewInject(R.id.zhiwu)
    private EditText zhiwuEt;
    private String groupName = "";
    private String projectName;

    @Override
    public void onInit() {
        titleRight.setText("保存");
        title.setText("申报项目");
        mCallBack = new RongCreateGroupCallBack();
        userIds = new ArrayList<>();
    }

    @Event(value = {R.id.edittext_success_rate, R.id.back_arrows, R.id.save_btn, R.id.edittext_customer_name, R.id.project_area, R.id.related_personnel, R.id.direction})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.edittext_customer_name:
                intent = new Intent(this, CustomerListActivity.class);
                intent.putExtra("FLAG", "Apply");
                startActivityForResult(intent, 1001);
                break;
            case R.id.project_area:
                intent = new Intent(this, CityListActivity.class);
                startActivityForResult(intent, Constant.RESULT_OK);
                break;
            case R.id.related_personnel:
                intent = new Intent(this, SelectEmpolyeeActivity.class);
                startActivityForResult(intent, 1013);
                break;
            case R.id.save_btn://保存
                sendMsg();
//                    RongIM.getInstance().createDiscussionChat(SubmitActivity.this, userIds, projectName, mCallBack);
                    RongIM.getInstance().getRongIMClient().createDiscussion(projectName, userIds, new RongIMClient.CreateDiscussionCallback() {
                        @Override
                        public void onSuccess(String s) {
                            Log.e("讨论组：", "创建成功" + s);
                            String userStr = "";
                            for (int i = 0; i < userIds.size(); i++) {
                                userStr += userIds.get(i) + ",";
                            }
                            userStr = userStr.substring(0, userStr.length() - 1);//删除最后的，号
                            Log.e("userStr", userStr);
                            HashMap params = new HashMap();
                            params.put("discussionId", s);
                            params.put("discussionName", projectName);
                            params.put("userstrs", userStr);
                            HttpRequestUtils.getmInstance().send(SubmitActivity.this, Constant.CREATE_GROUP_URL, params, new HttpRequestCallBack<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                                    });
                                    if (appMsg != null && appMsg.getEnumcode() == 0) {
                                        ToastUtils.shows(SubmitActivity.this, "创建成功");
                                        finish();
                                    }
                                }
                            });
                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {

                        }
                    });
//                showDialog();
                break;
            case R.id.edittext_success_rate:
                View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
                wv.setOffset(1);
                wv.setItems(Arrays.asList(PLANETS));
                wv.setSeletion(0);
                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {

                        rate = Double.valueOf(item) / 100;
                        projectSuccessRateEt.setText(item + "%");
                    }
                });
                new AlertDialog.Builder(this)
                        .setTitle("请选择成功率(%)")
                        .setView(outerView)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(SubmitActivity.this, ">>>>>>>>", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();

//                intent = new Intent(this, RateSelectActivity.class);
//                startActivityForResult(intent, 1015);
                break;
            case R.id.direction:
                intent = new Intent(this, ProjectDirectionActivity.class);
                startActivityForResult(intent, 1016);
                break;
        }
    }

    private void sendMsg() {
        projectName = projectNameEt.getText().toString().trim();
        String projectAddress = projectAddressEt.getText().toString().trim();
        String CustomerName = CustomerNameEt.getText().toString().trim();
        String CustomerPhone = CustomerPhoneEt.getText().toString().trim();
        String projectTotalPrice = projectTotalPriceEt.getText().toString().trim();
        String projectSuccessRate = projectSuccessRateEt.getText().toString().trim();
//                projectSuccessRate = projectSuccessRate.substring(0, projectSuccessRate.length() - 1);
//                double successRate = Integer.valueOf(projectSuccessRate) / 100;
//                Log.e("successRate", ">>>>>>>>>>" + successRate);

        String remark = remarkEt.getText().toString().trim();

        HashMap params = new HashMap();

        params.put("ProjectName", projectName);//项目名称
        params.put("Address", projectAddress);//项目地址
        params.put("CustLinkMan", CustomerName);//客户联系人
        params.put("CustLinkTel", CustomerPhone);//客户号码
        params.put("Investment", projectTotalPrice);//总价钱
        params.put("SuccessRate", rate);//成功率
        params.put("Remark", remark);//备注
        params.put("CanViewUser", empolyeeId);//相关人员
        params.put("ProjectDirectionId", projectDirectionId);

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
    }

    private RongCreateGroupCallBack mCallBack;

    private void showDialog() {
        final Dialog dialog = new Dialog(this, R.style.Dialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.edittext_dialog, null);
        TextView titleName = (TextView) contentView.findViewById(R.id.title);
        final EditText editText = (EditText) contentView.findViewById(R.id.message);
        titleName.setText("请输入相关人员群名称");
        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(true);
        Button negativeButton = (Button) contentView.findViewById(R.id.negativeButton);
        negativeButton.setClickable(true);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button positiveButton = (Button) contentView.findViewById(R.id.positiveButton);
        positiveButton.setClickable(true);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg();
                groupName = editText.getText().toString().trim();
                if (NullUtils.isNull(groupName)) {
                    RongIM.getInstance().createDiscussionChat(SubmitActivity.this, userIds, projectName, mCallBack);
                } else {
                    ToastUtils.shows(SubmitActivity.this, "请输入群名称");
                }
                dialog.dismiss();
            }
        });
        dialog.show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1001) {
            String customName = data.getStringExtra("CustContact");//联系人
            String ContactPhone = data.getStringExtra("CustPhone");//联系电话
            String companyName = data.getStringExtra("CustName");
            String zhiwu = data.getStringExtra("Position");


            zhiwuEt.setText(data.getStringExtra(zhiwu));
            companyNameEt.setText(companyName);

            CustomerNameEt.setText(data.getStringExtra("CustContact"));
            CustomerPhoneEt.setText(data.getStringExtra("CustPhone"));

            Log.e("TAG", customName + ">>>>>" + ContactPhone);
        }
        if (resultCode == Constant.RESULT_OK) {
            String cityName = data.getStringExtra("cityName");
            projectArea.setText(cityName);
        }
        if (resultCode == 1013) {
            empolyeeId = data.getStringExtra("EmpolyeeId");
            String empolyeeNames = data.getStringExtra("EmpolyeeName");
            relatedPersonnel.setText(empolyeeNames);
            userIds = data.getStringArrayListExtra("UserIds");
            Log.e("empolyeeId", empolyeeId);
            Log.e("empolyeeName", empolyeeNames);
        }
        if (resultCode == 1015) {
            String rate = data.getStringExtra("Reta");
            projectSuccessRateEt.setText(rate);
            successRate = Double.valueOf(rate) / 100;
            Log.e("successRate", ">>>>>>>>" + successRate);
        }
        if (resultCode == 1016) {
            projectDirectionId = data.getIntExtra("ProjectDirectionId", 0);
            String directionName = data.getStringExtra("ProjectDirectionName");
            directionEt.setText(directionName);
        }
    }


    private class RongCreateGroupCallBack extends RongIMClient.CreateDiscussionCallback {

        @Override
        public void onSuccess(String s) {

            Log.e("讨论组：", "创建成功" + s);
            String userStr = "";
            for (int i = 0; i < userIds.size(); i++) {
                userStr += userIds.get(i) + ",";
            }
            userStr = userStr.substring(0, userStr.length() - 1);//删除最后的，号
            Log.e("userStr", userStr);
            HashMap params = new HashMap();
            params.put("discussionId", s);
            params.put("discussionName", groupName);
            params.put("userstrs", userStr);
            HttpRequestUtils.getmInstance().send(SubmitActivity.this, Constant.CREATE_GROUP_URL, params, new HttpRequestCallBack<String>() {
                @Override
                public void onSuccess(String result) {
                    AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                    });
                    if (appMsg != null && appMsg.getEnumcode() == 0) {
                        ToastUtils.shows(SubmitActivity.this, "创建成功");
                        finish();
                    }
                }
            });
        }

        @Override
        public void onError(RongIMClient.ErrorCode errorCode) {
            Log.e("讨论组：", "创建失败");
            ToastUtils.shows(SubmitActivity.this, errorCode.getMessage());
        }

        @Override
        public void onFail(RongIMClient.ErrorCode errorCode) {
            super.onFail(errorCode);
        }
    }
}
