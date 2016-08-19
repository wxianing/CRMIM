package com.meidp.crmim.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.StockFollowAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.StockUpDetails;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_stock_up_details)
public class StockUpDetailsActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;
    private int oid = 0;
    @ViewInject(R.id.title_name)
    private TextView titleName;
    @ViewInject(R.id.produce_name)
    private TextView produceName;
    @ViewInject(R.id.count_tv)
    private TextView count_tv;
    @ViewInject(R.id.total_money)
    private TextView totalMoney;
    @ViewInject(R.id.company_name)
    private TextView companyName;
    @ViewInject(R.id.applyer)
    private TextView applyer;
    @ViewInject(R.id.create_time)
    private TextView createTime;
    @ViewInject(R.id.listview)
    private ListView mListView;

    private List<StockUpDetails.FlowStepsBean> mDatas;

    private StockFollowAdapter mAdapter;
    private String billNo;

    @Override
    public void onInit() {
        title.setText("备货详情");
        oid = getIntent().getIntExtra("OID", 0);
        mDatas = new ArrayList<>();
        mAdapter = new StockFollowAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("Id", oid);
        HttpRequestUtils.getmInstance().send(StockUpDetailsActivity.this, Constant.GET_STOCKUP_DETAILS, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("Tag", result);
                AppBean<StockUpDetails> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<StockUpDetails>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    bindView(appBean);
                }
            }
        });
    }

    private void bindView(AppBean<StockUpDetails> appBean) {
        titleName.setText("标题：" + appBean.getData().getTitle());
        produceName.setText("货物名：" + appBean.getData().getTitle());
        count_tv.setText("数量：" + appBean.getData().getCountTotal());
        totalMoney.setText("总额（￥）：" + appBean.getData().getTotalFee());
        if (NullUtils.isNull(appBean.getData().getCustName())) {
            companyName.setText("单位名：" + appBean.getData().getCustName());
        }
        applyer.setText("申请人：" + appBean.getData().getCreatorName());
        createTime.setText("申请时间：" + appBean.getData().getCreateDate());
        mDatas.addAll(appBean.getData().getFlowSteps());
        mAdapter.notifyDataSetChanged();
        billNo = appBean.getData().getOrderNo();
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
        params.put("BillTypeCode", 3);
        params.put("State", state);
        params.put("Note", "");
        HttpRequestUtils.getmInstance().send(StockUpDetailsActivity.this, Constant.CHECKFOR_SAVE_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    ToastUtils.shows(StockUpDetailsActivity.this, "审批成功");
                    Intent intent = new Intent();
                    setResult(1019, intent);
                    finish();
                } else {
                    ToastUtils.shows(StockUpDetailsActivity.this, appMsg.getMsg());
                }
            }
        });
    }
}
