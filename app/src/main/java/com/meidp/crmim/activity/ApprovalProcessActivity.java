package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.meidp.crmim.adapter.ApprovalAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.CheckforApply;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 审批
 */
@ContentView(R.layout.activity_approval_process)
public class ApprovalProcessActivity extends BaseActivity implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {
    @ViewInject(R.id.title_tv)
    private TextView title;

    private String keyword = "";
    private int pageIndex = 1;

    @ViewInject(R.id.listview)
    private PullToRefreshListView mListView;
    private List<CheckforApply> mDatas;
    private ApprovalAdapter mAdapter;
    @ViewInject(R.id.search_edittext)
    private EditText search;

    @Override
    public void onInit() {
        title.setText("审批样机");
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mDatas = new ArrayList<>();
        mAdapter = new ApprovalAdapter(mDatas, this);
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
                loadData(pageIndex, keyword);
                keyword = "";
                break;
        }
    }


    @Override
    public void onInitData() {
        loadData(pageIndex, keyword);
    }

    private void loadData(int pageIndex, String keyword) {
        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("sType", 1);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(ApprovalProcessActivity.this, Constant.FORCHECK_LIST, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppDatas<CheckforApply> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<CheckforApply>>() {
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
        CheckforApply apply = mDatas.get(position - 1);
        Intent intent = new Intent(this, ApprovalDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("CheckforApply", apply);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1018);
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
        if (requestCode == 1018) {
            mDatas.clear();
            loadData(pageIndex, keyword);
        }
    }
}
