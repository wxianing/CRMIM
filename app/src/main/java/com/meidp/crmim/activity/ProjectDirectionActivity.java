package com.meidp.crmim.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.ProjectDirectionAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBeans;
import com.meidp.crmim.model.ProjectDirections;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_project_direction)
public class ProjectDirectionActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener<ListView>, AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.listview)
    private PullToRefreshListView mListView;
    private List<ProjectDirections> mDatas;

    private ProjectDirectionAdapter mAdapter;

    @Override
    public void onInit() {
        title.setText("请选择项目领域");
        mDatas = new ArrayList<>();
        mAdapter = new ProjectDirectionAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnRefreshListener(this);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onInitData() {
        loadData();
    }

    private void loadData() {
        HashMap params = new HashMap();
        params.put("Id", 1);
        params.put("ChildId", 7);
        HttpRequestUtils.getmInstance().send(ProjectDirectionActivity.this, Constant.PROJECT_DIRECTION_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("directuin", result);
                AppBeans<ProjectDirections> appBeans = JSONObject.parseObject(result, new TypeReference<AppBeans<ProjectDirections>>() {
                });
                if (appBeans != null && appBeans.getEnumcode() == 0) {
                    mDatas.addAll(appBeans.getData());
                    mAdapter.notifyDataSetChanged();
                    mListView.onRefreshComplete();
                }
            }
        });
    }

    @Event({R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {
        mDatas.clear();
        loadData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("ProjectDirectionId", mDatas.get(position - 1).getID());
        intent.putExtra("ProjectDirectionName", mDatas.get(position - 1).getTypeName());
        setResult(1016, intent);
        finish();
    }
}
