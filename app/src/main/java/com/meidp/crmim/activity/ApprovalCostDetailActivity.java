package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.CheckAdapter;
import com.meidp.crmim.adapter.CheckCostAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.ApprovalCosts;
import com.meidp.crmim.model.CostDetails;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;
import com.meidp.crmim.view.ListViewForScrollView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApprovalCostDetailActivity extends BasicActivity implements View.OnClickListener {

    private TextView title;
    private ApprovalCosts approvalCosts;
    private TextView titleName;
    private TextView currStatus;
    private TextView projectName;
    private TextView custName;
    private TextView dutyName;
    private ListViewForScrollView mListView;
    private CheckCostAdapter mAdapter;
    private String billNo;
    private TextView applyReason;
    private TextView applyTime;
    private int id;
    private EditText checkedOpinion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_cost_detail);
        onInit();
        onInitData();
        initEvent();
    }

    private void initEvent() {
        //R.id.back_arrows, R.id.agree_btn, R.id.refuse_btn
        findViewById(R.id.back_arrows).setOnClickListener(this);
        findViewById(R.id.agree_btn).setOnClickListener(this);
        findViewById(R.id.refuse_btn).setOnClickListener(this);
    }

    public void onInit() {

        title = (TextView) findViewById(R.id.title_tv);
        titleName = (TextView) findViewById(R.id.title_name);
        currStatus = (TextView) findViewById(R.id.curr_status);
        projectName = (TextView) findViewById(R.id.project_name);
        custName = (TextView) findViewById(R.id.cust_name);
        dutyName = (TextView) findViewById(R.id.duty_name);
        mListView = (ListViewForScrollView) findViewById(R.id.listview);
        applyReason = (TextView) findViewById(R.id.apply_reason);
        applyTime = (TextView) findViewById(R.id.apply_time);
        checkedOpinion = (EditText) findViewById(R.id.checked_opinion);

        title.setText("详情");
        id = getIntent().getIntExtra("OID", 0);
        approvalCosts = (ApprovalCosts) getIntent().getSerializableExtra("ApprovalCosts");

    }


    public void onInitData() {
        HashMap hashMap = new HashMap();
        hashMap.put("Id", id);
        HttpRequestUtils.getmInstance().send(ApprovalCostDetailActivity.this, Constant.FEELAPPLY_URL, hashMap, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBean<CostDetails> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<CostDetails>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    bindView(appBean);
                }
            }
        });
    }

    private void bindView(AppBean<CostDetails> appBean) {
        int status = Integer.valueOf(appBean.getData().getStatus());
        if (approvalCosts != null) {
            currStatus.setText(approvalCosts.getCheckStatusName());
        }
        titleName.setText(appBean.getData().getTitle());
        projectName.setText("项目名：" + appBean.getData().getProjectName());
//        custName.setText("客户名：" + appBean.getData().getC);
        dutyName.setText("申请人：" + appBean.getData().getCreatorName());
        applyReason.setText("申请原因：" + appBean.getData().getReason());
        applyTime.setText("申请时间：" + appBean.getData().getCreateDate());
        billNo = appBean.getData().getExpCode();

        List<CostDetails.FlowStepsBean> mDatas = new ArrayList<>();
        mDatas.addAll(appBean.getData().getFlowSteps());
        mAdapter = new CheckCostAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void onClick(View v) {
        String note = checkedOpinion.getText().toString().trim();
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.agree_btn:
                sendMsg(0, note);
                break;
            case R.id.refuse_btn:
                if (NullUtils.isNull(note)) {
                    sendMsg(2, note);
                } else {
                    ToastUtils.shows(this, "请填写审批意见");
                }
                break;
        }
    }

    private void sendMsg(int state, String note) {
        HashMap params = new HashMap();
        params.put("BillNo", billNo);
        params.put("BillTypeFlag", 1);
        params.put("BillTypeCode", 4);
        params.put("State", state);
        params.put("Note", note);
        HttpRequestUtils.getmInstance().send(ApprovalCostDetailActivity.this, Constant.CHECKFOR_SAVE_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    ToastUtils.shows(ApprovalCostDetailActivity.this, "审批成功");
                    Intent intent = new Intent();
                    setResult(1019);
                    finish();
                } else {
                    ToastUtils.shows(ApprovalCostDetailActivity.this, appMsg.getMsg());
                }
            }
        });
    }
}
