package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.ApprovalCostAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.ApprovalCosts;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApprovalCostActivity extends BasicActivity implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView>, View.OnClickListener {
    private TextView title;

    private PullToRefreshListView mListView;

    private List<ApprovalCosts> mDatas;
    private ApprovalCostAdapter mAdapter;

    private String keyword = "";
    private int pageIndex = 1;
    private int sType = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_cost);
        onInit();
        onInitData();
        initEvent();
    }

    private void initEvent() {
        findViewById(R.id.back_arrows).setOnClickListener(this);
    }

    public void onInit() {
        title = (TextView) findViewById(R.id.title_tv);
        mListView = (PullToRefreshListView) findViewById(R.id.listview);
        title.setText("审批费用");
        mDatas = new ArrayList<>();
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mAdapter = new ApprovalCostAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnRefreshListener(this);
    }

    public void onInitData() {
        loadData(pageIndex, keyword);
    }

    private void loadData(int pageIndex, String keyword) {
        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("sType", sType);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(ApprovalCostActivity.this, Constant.GET_COST_CHECK, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppDatas<ApprovalCosts> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<ApprovalCosts>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
                    mListView.onRefreshComplete();
                }
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ApprovalCosts apply = mDatas.get(position - 1);
        Intent intent = new Intent(this, ApprovalCostDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ApprovalCosts", apply);
        intent.putExtras(bundle);
        intent.putExtra("OID", apply.getID());
        startActivityForResult(intent, 1019);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex = 1;
        mDatas.clear();
        loadData(pageIndex, keyword);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex++;
        loadData(pageIndex, keyword);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1019) {
            mDatas.clear();
            loadData(pageIndex, keyword);
        }
    }
}
