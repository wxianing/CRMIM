package com.meidp.crmim.imkit;


import android.content.Intent;
import android.provider.Contacts;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.activity.CustomerListActivity;
import com.meidp.crmim.activity.GroupActivity;
import com.meidp.crmim.activity.SearchFriendActivity;
import com.meidp.crmim.adapter.FriendsAdapter;
import com.meidp.crmim.fragment.BaseFragment;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.Friends;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.rong.imkit.RongIM;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_friend_list)
public class ContactsFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.back_arrows)
    private ImageView backImg;
    private List<String> userIds;
    @ViewInject(R.id.listview)
    private ListView mListVeiw;
    private FriendsAdapter mAdapter;

    private List<Friends> mDatas;

    public ContactsFragment() {

    }

    @Override
    public void onInit() {
        backImg.setVisibility(View.GONE);
        title.setText("联系人");
        userIds = new ArrayList<>();
        userIds.add("1");
        userIds.add("3");
        mDatas = new ArrayList<>();
        mAdapter = new FriendsAdapter(mDatas, getActivity());
        mListVeiw.setAdapter(mAdapter);
        mListVeiw.setOnItemClickListener(this);
    }

    @Override
    public void onInitData() {

        HashMap params = new HashMap();
        params.put("sType", 1);

        HttpRequestUtils.getmInstance().send(getActivity(), Constant.FRIEND_LIST_URL, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                AppDatas<Friends> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<Friends>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Event(value = {R.id.search_edittext, R.id.create_group, R.id.group_layout, R.id.contacts_layout, R.id.customer_layout})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.search_edittext:
                intent = new Intent();
                intent.setClass(getActivity(), SearchFriendActivity.class);
                startActivity(intent);
                break;
            case R.id.group_layout:
//                ToastUtils.shows(getActivity(), "创建群组");
//                RongIM.getInstance().createDiscussionChat(getActivity(), userIds, "移动办公软件", mCallBack);
                startActivity(new Intent(getActivity(), GroupActivity.class));
                break;
            case R.id.contacts_layout:
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Contacts.People.CONTENT_URI);
                startActivity(intent);
                break;
            case R.id.customer_layout:
                intent = new Intent();
                intent.setClass(getActivity(), CustomerListActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RongIM.getInstance().startPrivateChat(getActivity(), Integer.toString(mDatas.get(position).getUserID()), mDatas.get(position).getLoginName());
    }

}
