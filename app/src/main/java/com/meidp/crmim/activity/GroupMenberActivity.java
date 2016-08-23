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
import com.meidp.crmim.adapter.GroupMenberAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.Menber;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.rong.imkit.RongIM;

@ContentView(R.layout.activity_group_menber)
public class GroupMenberActivity extends BaseActivity implements AdapterView.OnItemClickListener,PullToRefreshBase.OnRefreshListener2<ListView> {
    @ViewInject(R.id.title_tv)
    private TextView title;
    private String mTargetId;

    @ViewInject(R.id.listview)
    private PullToRefreshListView mListView;

    private List<Menber.UsersBean> mDatas;

    private GroupMenberAdapter mAdapter;
    private String mTargetName;

    private List<Menber> menbers;

    private String groupName;

    @Override
    public void onInit() {
        title.setText("群成员");
        mListView.setMode(PullToRefreshBase.Mode.BOTH);

        mTargetId = getIntent().getStringExtra("mTargetId");
        Log.e("mTargetId", mTargetId);
        mDatas = new ArrayList<>();
        menbers = new ArrayList<>();
        mListView.setOnItemClickListener(this);
        mAdapter = new GroupMenberAdapter(mDatas, GroupMenberActivity.this);
        mListView.setAdapter(mAdapter);
        mListView.setOnRefreshListener( this);
    }

    @Override
    public void onInitData() {
        loadData();
    }

    private void loadData() {
        final HashMap params = new HashMap();
        params.put("Id", mTargetId);
        HttpRequestUtils.getmInstance().send(GroupMenberActivity.this, Constant.GROUP_MEMBER_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBean<Menber> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<Menber>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    mDatas.addAll(appBean.getData().getUsers());
                    groupName = appBean.getData().getDiscussionName();
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
                Log.e("discussionId", "discussionId>>>>>>>>" + mTargetId);
                Intent intent = new Intent(this, NewGroupActivity.class);
                intent.putExtra("discussionId", mTargetId);
                intent.putExtra("GroupName", groupName);
                startActivityForResult(intent,1023);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GroupMenberAdapter.ViewHolder holder = (GroupMenberAdapter.ViewHolder) view.getTag();
        if (holder != null) {
            mTargetName = holder.userName.getText().toString();
        }
        RongIM.getInstance().startPrivateChat(this, Integer.toString(mDatas.get(position-1).getUserID()), mTargetName);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        mDatas.clear();
        loadData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        mDatas.clear();
        loadData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==1023){
            mDatas.clear();
            loadData();
        }
    }
}
