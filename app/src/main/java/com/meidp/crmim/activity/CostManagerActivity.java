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
import com.meidp.crmim.adapter.MyCostAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.CostLists;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_cost_manager)
public class CostManagerActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<ListView>, AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.listview)
    private PullToRefreshListView mListView;
    private List<CostLists> mDatas;
    private MyCostAdapter mAdapter;
    @ViewInject(R.id.search_edittext)
    private EditText search;

    private int pageIndex = 1;
    private String keyword = "";

    @Override
    public void onInit() {
        title.setText("我的成本");
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mDatas = new ArrayList<>();
        mAdapter = new MyCostAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnRefreshListener(this);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onInitData() {
        loadData(pageIndex, keyword);
    }

    private void loadData(int pageIndex, String keyword) {
        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(CostManagerActivity.this, Constant.GET_MY_COSTLIST_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("result", result);
                AppDatas<CostLists> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<CostLists>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
                    mListView.onRefreshComplete();
                }
            }
        });
    }

    @Event(value = {R.id.back_arrows, R.id.right_img, R.id.search_btn})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.right_img:
                intent = new Intent();
                intent.setClass(this, CostReimbursementActivity.class);
                startActivityForResult(intent, 1021);
                break;
            case R.id.search_btn:
                keyword = search.getText().toString().trim();
                pageIndex = 1;
                mDatas.clear();
                loadData(pageIndex, keyword);
                break;
        }
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, CostDetailsActivity.class);
        intent.putExtra("OID", mDatas.get(position - 1).getID());

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1021) {
            mDatas.clear();
            loadData(pageIndex, keyword);
        }
    }
}
