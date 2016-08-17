package com.meidp.crmim.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
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
public class GroupMenberActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;
    private String mTargetId;

    @ViewInject(R.id.listview)
    private ListView mListView;

    private List<Menber.UsersBean> mDatas;

    private GroupMenberAdapter mAdapter;
    private String mTargetName;

    private List<Menber> menbers;

    private String groupName;

    @Override
    public void onInit() {
        title.setText("群成员");
        mTargetId = getIntent().getStringExtra("mTargetId");
        Log.e("mTargetId", mTargetId);
        mDatas = new ArrayList<>();
        menbers = new ArrayList<>();
        mListView.setOnItemClickListener(this);
        mAdapter = new GroupMenberAdapter(mDatas, GroupMenberActivity.this);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onInitData() {
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
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GroupMenberAdapter.ViewHolder holder = (GroupMenberAdapter.ViewHolder) view.getTag();
        if (holder != null) {
            mTargetName = holder.userName.getText().toString();
        }
        RongIM.getInstance().startPrivateChat(this, Integer.toString(mDatas.get(position).getUserID()), mTargetName);
    }
}
