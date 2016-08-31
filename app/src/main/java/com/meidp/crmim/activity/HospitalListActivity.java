package com.meidp.crmim.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.CustomerLists;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_hospital_list)
public class HospitalListActivity extends BaseActivity implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {

    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.listview)
    private PullToRefreshListView mListView;
    private List<CustomerLists> mDatas;
    private HospitalAadapter mAdapter;
    private int pageIndex = 1;
    private String keyword = "";
    @ViewInject(R.id.search_edittext)
    private EditText search;

    @Override
    public void onInit() {
        title.setText("请选择");
        mDatas = new ArrayList<>();
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mAdapter = new HospitalAadapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnRefreshListener(this);
    }

    @Event({R.id.back_arrows, R.id.search_btn})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.search_btn:
                keyword = search.getText().toString().trim();
                mDatas.clear();
                pageIndex = 1;
                loadData(pageIndex, keyword);
                break;
        }
    }

    @Override
    public void onInitData() {
        super.onInitData();
        loadData(pageIndex, keyword);
    }

    private void loadData(int pageIndex, String keyword) {
        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 12);
        HttpRequestUtils.getmInstance().send(HospitalListActivity.this, Constant.CUSTOMER_LIST_URL, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                AppDatas<CustomerLists> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<CustomerLists>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
                    mListView.onRefreshComplete();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("getCustName", ">>>>>>>>>>>>" + mDatas.get(position-1).getCustName());
        Intent intent = new Intent();
        intent.putExtra("CustName", mDatas.get(position-1).getCustName());
        intent.putExtra("CustNo", mDatas.get(position-1).getCustNo());
        intent.putExtra("CustId", mDatas.get(position-1).getID());
        setResult(1012, intent);
        finish();
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
}
