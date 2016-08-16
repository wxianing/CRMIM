package com.meidp.crmim.activity;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.CheckAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.CheckforApply;
import com.meidp.crmim.model.PrototypeDetails;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.ToastUtils;
import com.meidp.crmim.view.ListViewForScrollView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_approval_details)
public class ApprovalDetailsActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;
    private CheckforApply checkforApply;
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
    private List<CheckforApply.FlowStepsBean> mDatas;
    private CheckAdapter mAdapter;
    private String billNo;
    private int id;

    @Override
    public void onInit() {
        title.setText("详情");
        id = getIntent().getIntExtra("OID", 0);
        checkforApply = (CheckforApply) getIntent().getSerializableExtra("CheckforApply");
//        if (checkforApply != null) {
//            titleName.setText("标题：" + checkforApply.getTitle());
//            currStatus.setText("状态：" + checkforApply.getCheckStatusName());
//            projectName.setText("项目名：" + checkforApply.getProjectName());
//            custName.setText("客户名：" + checkforApply.getCustName());
//            dutyName.setText("负责人：" + checkforApply.getCreatorName());
//            billNo = checkforApply.getApplyNo();
//            mDatas = new ArrayList<>();
//            mDatas.addAll(checkforApply.getFlowSteps());
//            mAdapter = new CheckAdapter(mDatas, this);
//            mListView.setAdapter(mAdapter);
//            mAdapter.notifyDataSetChanged();
//        }

    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("Id", id);
        HttpRequestUtils.getmInstance().send(ApprovalDetailsActivity.this, Constant.PROTOTYPE_DETAILS_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", result);
                AppBean<PrototypeDetails> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<PrototypeDetails>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    bindView(appBean);
                }
            }
        });
    }

    private void bindView(AppBean<PrototypeDetails> appBean) {
        titleName.setText("标题：" + appBean.getData().getTitle());
        currStatus.setText("状态：" + appBean.getData().getFlowStatusName());
        projectName.setText("项目名：" + checkforApply.getProjectName());
        custName.setText("客户名：" + checkforApply.getCustName());
        dutyName.setText("负责人：" + checkforApply.getCreatorName());
        billNo = checkforApply.getApplyNo();
        mDatas = new ArrayList<>();
        mDatas.addAll(checkforApply.getFlowSteps());
        mAdapter = new CheckAdapter(mDatas, this);
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
                sendMsg(0);
                break;
            case R.id.refuse_btn:
                sendMsg(2);
                break;
        }
    }

    private void sendMsg(int state) {
        HashMap params = new HashMap();
        params.put("BillNo", billNo);
        params.put("BillTypeFlag", 5);
        params.put("BillTypeCode", 11);
        params.put("State", state);
        params.put("Note", "");
        HttpRequestUtils.getmInstance().send(ApprovalDetailsActivity.this, Constant.CHECKFOR_SAVE_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    ToastUtils.shows(ApprovalDetailsActivity.this, "审批成功");
                    finish();
                } else {
                    ToastUtils.shows(ApprovalDetailsActivity.this, appMsg.getMsg());
                }
            }
        });
    }
}
