package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
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
import com.meidp.crmim.utils.ToastUtils;
import com.meidp.crmim.view.ListViewForScrollView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_approval_cost_detail)
public class ApprovalCostDetailActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;
    private ApprovalCosts approvalCosts;
    @ViewInject(R.id.title_name)
    private TextView titleName;
    @ViewInject(R.id.curr_status)
    private TextView currStatus;
    @ViewInject(R.id.project_name)
    private TextView projectName;
    @ViewInject(R.id.cust_name)
    private TextView custName;
    @ViewInject(R.id.duty_name)
    private TextView dutyName;
    @ViewInject(R.id.listview)
    private ListViewForScrollView mListView;
    private CheckCostAdapter mAdapter;
    private String billNo;
    @ViewInject(R.id.apply_reason)
    private TextView applyReason;
    @ViewInject(R.id.apply_time)
    private TextView applyTime;
    private int id;

    @Override
    public void onInit() {
        title.setText("详情");
        id = getIntent().getIntExtra("OID", 0);
        approvalCosts = (ApprovalCosts) getIntent().getSerializableExtra("ApprovalCosts");

    }


    @Override
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
        currStatus.setText(appBean.getData().getFlowStatusName());
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

    @Event({R.id.back_arrows, R.id.agree_btn, R.id.refuse_btn})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.agree_btn:
                sendMsg(0, "同意");
                break;
            case R.id.refuse_btn:
                sendMsg(2, "拒绝");
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
