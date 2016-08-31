package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.meidp.crmim.model.TeamMembers;
import com.meidp.crmim.utils.Constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyCrowdActivity extends BasicActivity implements View.OnClickListener, AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {

    private TextView title;
    private int pageIndex = 1;
    private String keyword = "";

    private List<TeamMembers> mDatas;
    private MyCrowdAdapter mAdapter;
    private PullToRefreshListView mListView;

    private EditText searchEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_crowd);
        initView();
        initData(pageIndex, keyword);
        initEvent();
    }

    public void initView() {
        searchEdittext = (EditText) findViewById(R.id.search_edittext);
        mListView = (PullToRefreshListView) findViewById(R.id.listview);
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        title = (TextView) findViewById(R.id.title_tv);
        title.setText("我的一伙人");
        mDatas = new ArrayList<>();
        mAdapter = new MyCrowdAdapter(mDatas, MyCrowdActivity.this);
        mListView.setAdapter(mAdapter);
    }

    public void initEvent() {
        findViewById(R.id.right_img).setOnClickListener(this);//添加图标按钮
        findViewById(R.id.back_arrows).setOnClickListener(this);//返回
        mListView.setOnItemClickListener(this);
        findViewById(R.id.search_btn).setOnClickListener(this);
        mListView.setOnRefreshListener(this);

    }

    public void initData(int pageIndex, String keyword) {
        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("sType", 1);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 12);
        HttpRequestUtils.getmInstance().send(MyCrowdActivity.this, Constant.TEAM_LINKMAN_URL, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("我的一伙人", result);
                AppDatas<TeamMembers> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<TeamMembers>>() {
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_img:
                Intent intent = new Intent(this, AddCrowdActivity.class);
                intent.putExtra("TeamMembers", (Serializable) mDatas);
                startActivityForResult(intent, 1028);
                break;
            case R.id.back_arrows:
                finish();
                break;
            case R.id.search_btn:
                keyword = searchEdittext.getText().toString().trim();
                pageIndex = 1;
                mDatas.clear();
                initData(pageIndex, keyword);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TeamMembers teamMembers = mDatas.get(position - 1);
        Intent intent = new Intent(this, TeamMemberDetsilsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("TeamMembers", teamMembers);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex = 1;
        keyword = "";
        mDatas.clear();
        initData(pageIndex, keyword);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex++;
        initData(pageIndex, keyword);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1028) {
            pageIndex = 1;
            keyword = "";
            mDatas.clear();
            initData(pageIndex, keyword);
        }
    }
}
