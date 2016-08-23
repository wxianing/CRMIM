package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.meidp.crmim.adapter.TeamMemberAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.TeamMembers;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 我的团队
 */
@ContentView(R.layout.activity_my_team)
public class MyTeamActivity extends BaseActivity implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {

    @ViewInject(R.id.title_tv)
    private TextView title;

    private int pageIndex = 1;
    @ViewInject(R.id.listview)
    private PullToRefreshListView mListView;
    private List<TeamMembers> mDatas;
    private TeamMemberAdapter mAdapter;

    @Override
    public void onInit() {
        title.setText("我的团队");
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mDatas = new ArrayList<>();
        mAdapter = new TeamMemberAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        loadData(pageIndex);
        mListView.setOnItemClickListener(this);
        mListView.setOnRefreshListener(this);
    }

    private void loadData(int pageIndex) {
        HashMap params = new HashMap();
        params.put("Keyword", "");
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 12);
        HttpRequestUtils.getmInstance().send(MyTeamActivity.this, Constant.TEAM_LINKMAN_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("Teams", result);
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

    @Event({R.id.back_arrows, R.id.right_img})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.right_img:
                Intent intent = new Intent(this, TeamAddMemberActivity.class);
                startActivityForResult(intent, 1023);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1023) {
            mDatas.clear();
            loadData(pageIndex);
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
}
