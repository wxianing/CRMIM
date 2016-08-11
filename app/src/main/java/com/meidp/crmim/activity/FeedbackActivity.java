package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.FeedbackAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.Feedbacks;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_feedback)
public class FeedbackActivity extends BaseActivity implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {

    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.listview)
    private PullToRefreshListView mListView;

    private List<Feedbacks> mDatas;
    private FeedbackAdapter mAdapter;

    private int pageIndex = 1;


    @Override
    public void onInit() {
        title.setText("意见反馈");
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mDatas = new ArrayList<>();
        mAdapter = new FeedbackAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnRefreshListener(this);
    }

    /**
     * "Keyword": "sample string 1",
     * "sType": 2,
     * "PageIndex": 3,
     * "PageSize": 4
     */

    @Override
    protected void onResume() {
        super.onResume();
        mDatas.clear();
        loadData(pageIndex);
    }

    private void loadData(int pageIndex) {
        HashMap params = new HashMap();
        params.put("Keyword", "");
        params.put("sType", -1);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(FeedbackActivity.this, Constant.GET_FEEDBACK_LIST_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppDatas<Feedbacks> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<Feedbacks>>() {
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
                Intent intent = new Intent(this, AddFeedbackActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Feedbacks feedbacks = mDatas.get(position - 1);
        Intent intent = new Intent(this, FeedbackDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Feedbacks", feedbacks);
        intent.putExtras(bundle);
        startActivity(intent);
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
        mDatas.clear();
        loadData(pageIndex);
    }
}
