package com.meidp.crmim.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.adapter.GroupMenberAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Discussion;

@ContentView(R.layout.activity_group_menber)
public class GroupMenberActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;
    private String mTargetId;
    private List<String> mDatas;
    @ViewInject(R.id.listview)
    private ListView mListView;
    private GroupMenberAdapter mAdapter;
    private String mTargetName;

    @Override
    public void onInit() {
        title.setText("群成员");
        mTargetId = getIntent().getStringExtra("mTargetId");
        mDatas = new ArrayList<>();
        RongIM.getInstance().getDiscussion(mTargetId, new RongIMgetDiscussionName());
        mListView.setOnItemClickListener(this);
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

        if (mDatas.get(position).equals("1")) {
            mTargetName = "汪志红";
        } else if (mDatas.get(position).equals("2")) {
            mTargetName = "马云";
        } else if (mDatas.get(position).equals("3")) {
            mTargetName = "王显宁";
        } else if (mDatas.get(position).equals("4")) {
            mTargetName = "熊国和";
        } else if (mDatas.get(position).equals("7")) {
            mTargetName = "王健林";
        }
        RongIM.getInstance().startPrivateChat(this, mDatas.get(position), mTargetName);
    }

    private class RongIMgetDiscussionName extends RongIMClient.ResultCallback<Discussion> {
        @Override
        public void onSuccess(Discussion discussion) {
            mDatas.addAll(discussion.getMemberIdList());

            mAdapter = new GroupMenberAdapter(mDatas, GroupMenberActivity.this);
            mListView.setAdapter(mAdapter);
        }

        @Override
        public void onError(RongIMClient.ErrorCode errorCode) {

        }
    }
}
