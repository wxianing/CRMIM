package com.meidp.crmim.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.TeamDetailsAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.TeamDetails;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.rong.imkit.RongIM;

@ContentView(R.layout.activity_team_details)
public class TeamDetailsActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;
    private String teamName;
    private int teamId;
    @ViewInject(R.id.listview)
    private ListView mListView;

    private List<TeamDetails.UsersBean> mDatas;
    private TeamDetailsAdapter mAdapter;

    @Override
    public void onInit() {
        teamName = getIntent().getStringExtra("TeamName");
        title.setText(teamName);
        teamId = getIntent().getIntExtra("teamId", 0);
        mDatas = new ArrayList<>();
        mAdapter = new TeamDetailsAdapter(mDatas,this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("Id", teamId);
        HttpRequestUtils.getmInstance().send(TeamDetailsActivity.this, Constant.TEAM_DETAILS_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBean<TeamDetails> appbean = JSONObject.parseObject(result, new TypeReference<AppBean<TeamDetails>>() {
                });
                if (appbean != null && appbean.getEnumcode() == 0) {
                    mDatas.addAll(appbean.getData().getUsers());
                    mAdapter.notifyDataSetChanged();
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        RongIM.getInstance().startPrivateChat(this, Integer.toString(mDatas.get(position).getUserID()), mDatas.get(position).getEmployeeName());
    }
}
