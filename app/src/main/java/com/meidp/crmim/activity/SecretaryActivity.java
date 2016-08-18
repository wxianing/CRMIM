package com.meidp.crmim.activity;

import android.content.Intent;
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
import com.meidp.crmim.adapter.SecretaryAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.Secretary;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_secretary)
public class SecretaryActivity extends BaseActivity implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.listview)
    private PullToRefreshListView mListView;
    private List<Secretary> mDatas;

    private SecretaryAdapter mAdapter;
    private String keyWord = "";
    private int pageIndex = 1;
    @ViewInject(R.id.search_edittext)
    private EditText search;

    @Override
    public void onInit() {
        title.setText("巨烽小助手");
        mDatas = new ArrayList<>();
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mAdapter = new SecretaryAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnRefreshListener(this);
    }

    @Override
    public void onInitData() {
        loadData(pageIndex, keyWord);
    }

    @Event({R.id.back_arrows, R.id.search_btn})
    private void onClcik(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.search_btn:
                keyWord = search.getText().toString().trim();
                mDatas.clear();
                loadData(pageIndex, keyWord);
                keyWord = "";
                break;
        }
    }

    private void loadData(int pageIndex, String keyWord) {
        HashMap params = new HashMap();
        params.put("Keyword", keyWord);
        params.put("sType", -1);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(SecretaryActivity.this, Constant.GET_SYSTEM_MESSAGE, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppDatas<Secretary> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<Secretary>>() {
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
        int billId = mDatas.get(position - 1).getBillId();
        int billTypeFlag = mDatas.get(position - 1).getBillTypeFlag();
        int billTypeCode = mDatas.get(position - 1).getBillTypeCode();
        if (billTypeFlag == 1 && billTypeCode == 4) {//费用
            Intent intent = new Intent(SecretaryActivity.this, ApprovalCostDetailActivity.class);
            intent.putExtra("OID", mDatas.get(position - 1).getBillId());
            startActivity(intent);
        }
        if (billTypeFlag == 4 && billTypeCode == 4) {//客户拜访
            Intent intent = new Intent(SecretaryActivity.this, VisitRecordActivity.class);
            intent.putExtra("OID", mDatas.get(position - 1).getBillId());
            startActivity(intent);
        }
        if (billTypeFlag == 4 && billTypeCode == 3) {//客户拜访
            Intent intent = new Intent(SecretaryActivity.this, VisitRecordActivity.class);
            intent.putExtra("OID", mDatas.get(position - 1).getBillId());
            startActivity(intent);
        }
        if (billTypeFlag == 4 && billTypeCode == 7) {//客户联系人
            Intent intent = new Intent(SecretaryActivity.this, ClientDetailsActivity.class);
            intent.putExtra("OID", mDatas.get(position - 1).getBillId());
            startActivity(intent);
        }
        if (billTypeFlag == 5 && billTypeCode == 11) {//样机申请
            Intent intent = new Intent(SecretaryActivity.this, ApprovalDetailsActivity.class);
            intent.putExtra("OID", mDatas.get(position - 1).getBillId());
            startActivity(intent);
        }
        if (billTypeFlag == 13 && billTypeCode == 1) {//项目
            Intent intent = new Intent(SecretaryActivity.this, ProjecDetailsActivity.class);
            intent.putExtra("OID", mDatas.get(position - 1).getBillId());
            startActivity(intent);
        }
        if (billTypeFlag == 0 && billTypeCode == 1) {
            Intent intent = new Intent(SecretaryActivity.this, CustomerListActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex = 1;
        mDatas.clear();
        loadData(pageIndex, keyWord);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex++;
        loadData(pageIndex, keyWord);
    }
}
