package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.VisitRecordAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.VisitRecords;
import com.meidp.crmim.utils.Constant;
import com.nostra13.universalimageloader.utils.L;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_visit_record)
public class VisitRecordActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<ListView>, AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.listview)
    private PullToRefreshListView mListView;

    private List<VisitRecords> mDatas;
    private VisitRecordAdapter mAdapter;
    private int pageIndex = 1;

    @Override
    public void onInit() {
        title.setText("拜访记录");
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mDatas = new ArrayList<>();
        mAdapter = new VisitRecordAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnRefreshListener(this);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onInitData() {
        loadData(pageIndex);
    }

    private void loadData(int pageIndex) {
        HashMap params = new HashMap();
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(VisitRecordActivity.this, Constant.VISIT_RECORD_URL, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                AppDatas<VisitRecords> appBean = JSONObject.parseObject(result, new TypeReference<AppDatas<VisitRecords>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    mDatas.addAll(appBean.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
                    mListView.onRefreshComplete();
                }
            }
        });
    }

    @Event(value = {R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex = 1;
        mDatas.clear();
        loadData(pageIndex);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex++;
        loadData(pageIndex);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        VisitRecords records = mDatas.get(position - 1);
        Intent intent = new Intent(this, VisitRecodeDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("VisitRecords", records);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
