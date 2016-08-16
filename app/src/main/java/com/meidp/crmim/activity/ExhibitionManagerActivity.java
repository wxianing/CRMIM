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
import com.meidp.crmim.adapter.ExhibitionsAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.Exhibitions;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 展会列表
 */
@ContentView(R.layout.activity_exhibition_manager)
public class ExhibitionManagerActivity extends BaseActivity implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {

    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.listview)
    private PullToRefreshListView mListView;

    private String keyword = "";
    private int pageIndex = 1;

    private ExhibitionsAdapter mAdapter;

    private List<Exhibitions> mDatas;
    @ViewInject(R.id.search_edittext)
    private EditText searchEdittext;


    @Override
    public void onInit() {
        title.setText("展会管理");
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mDatas = new ArrayList<>();
        mAdapter = new ExhibitionsAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnRefreshListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(pageIndex, keyword);
    }

    private void loadData(int pageIndex, String keyword) {
        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(ExhibitionManagerActivity.this, Constant.CONVERSATION_LIST, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("result", result);
                AppDatas<Exhibitions> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<Exhibitions>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
                    mListView.onRefreshComplete();
                }
            }
        });
    }

    @Event({R.id.back_arrows, R.id.right_img, R.id.search_btn})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.right_img:
                intent = new Intent(this, ConventionApplyForActivity.class);
                startActivity(intent);
                break;
            case R.id.search_btn:
                keyword = searchEdittext.getText().toString().trim();
                mDatas.clear();
                loadData(pageIndex, keyword);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ExhibitionDetailsActivity.class);
        intent.putExtra("OID", mDatas.get(position-1).getID());
        startActivity(intent);
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
