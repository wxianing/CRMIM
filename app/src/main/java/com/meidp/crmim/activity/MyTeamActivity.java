package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.TeamsAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBeans;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.Feedbacks;
import com.meidp.crmim.model.Teams;
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
public class MyTeamActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;

    private int pageIndex = 1;
    @ViewInject(R.id.listview)
    private ListView mListView;

    private List<Teams> mDatas;

    private TeamsAdapter mAdapter;

    @Override
    public void onInit() {
        title.setText("我的团队");
        mDatas = new ArrayList<>();
        mAdapter = new TeamsAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        loadData(pageIndex);
    }


    private void loadData(int pageIndex) {
        HashMap params = new HashMap();
        params.put("Keyword", "");
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(MyTeamActivity.this, Constant.MY_TEAM_LIST, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBeans<Teams> appDatas = JSONObject.parseObject(result, new TypeReference<AppBeans<Teams>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData());
                    mAdapter.notifyDataSetChanged();
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
                Intent intent = new Intent(this, AddTeamActivity.class);
                startActivity(intent);
                break;
        }
    }
}
