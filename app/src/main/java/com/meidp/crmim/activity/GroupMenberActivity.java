package com.meidp.crmim.activity;

import android.net.Uri;
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
import com.meidp.crmim.model.User;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.UserInfo;

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

    private UserInfo users = null;
    private List<Menber> menbers;

    @Override
    public void onInit() {
        title.setText("群成员");
        mTargetId = getIntent().getStringExtra("mTargetId");
        mDatas = new ArrayList<>();
        RongIM.getInstance().getDiscussion(mTargetId, new RongIMgetDiscussionName());
        menbers = new ArrayList<>();
        mListView.setOnItemClickListener(this);
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String userId) {
                return findUserById(userId);//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
            }
        }, true);

        mAdapter = new GroupMenberAdapter(menbers, GroupMenberActivity.this);

    }

    private UserInfo findUserById(final String userId) {
        HashMap params = new HashMap();
        params.put("Id", Integer.valueOf(userId));
        HttpRequestUtils.getmInstance().post(GroupMenberActivity.this, Constant.GET_PERSON_INFORMATION, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBean<User> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<User>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    String avatar = appBean.getData().getPhotoURL();
                    String name = appBean.getData().getEmployeeName();

                    users = new UserInfo(userId, name, Uri.parse(avatar));
//                    RongIM.getInstance().refreshUserInfoCache(users);//刷新用户数据
                }
            }
        });
        return users;
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
        GroupMenberAdapter.ViewHolder holder = (GroupMenberAdapter.ViewHolder) view.getTag();
        if (holder != null) {
            mTargetName = holder.userName.getText().toString();
        }
        RongIM.getInstance().startPrivateChat(this, mDatas.get(position), mTargetName);
    }

    private class RongIMgetDiscussionName extends RongIMClient.ResultCallback<Discussion> {
        @Override
        public void onSuccess(Discussion discussion) {
            mDatas.addAll(discussion.getMemberIdList());
            for (int i = 0; i < mDatas.size(); i++) {
                findUserById2(mDatas.get(i));
            }
//            mAdapter = new GroupMenberAdapter(menbers, GroupMenberActivity.this);
//            mListView.setAdapter(mAdapter);
        }

        @Override
        public void onError(RongIMClient.ErrorCode errorCode) {

        }
    }


    private void findUserById2(String userId) {
        Log.e("userId", userId);
        HashMap params = new HashMap();
        params.put("Id", Integer.valueOf(userId));
        HttpRequestUtils.getmInstance().post(GroupMenberActivity.this, Constant.GET_PERSON_INFORMATION, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBean<User> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<User>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    String avatar = appBean.getData().getPhotoURL();
                    String name = appBean.getData().getEmployeeName();
                    Menber menber = new Menber();
                    menber.setName(name);
                    menber.setPhoto(avatar);
                    menbers.add(menber);
                    mListView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
