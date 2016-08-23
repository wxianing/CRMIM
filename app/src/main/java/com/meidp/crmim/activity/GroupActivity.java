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
import com.meidp.crmim.adapter.GroupAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBeans;
import com.meidp.crmim.model.Groups;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.rong.imkit.RongIM;

@ContentView(R.layout.activity_group)
public class GroupActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_right)
    private TextView titleRight;
    @ViewInject(R.id.listview)
    private ListView mListVew;
    private List<Groups> mDatas;
    private GroupAdapter mAdapter;

    @Override
    public void onInit() {
        titleRight.setText("创建");
        titleRight.setVisibility(View.VISIBLE);
        title.setText("群聊");
        mDatas = new ArrayList<>();
        mAdapter = new GroupAdapter(mDatas, this);
        mListVew.setAdapter(mAdapter);
        mListVew.setOnItemClickListener(this);
    }

    @Override
    public void onInitData() {
        loadData();
    }

    private void loadData() {
        HashMap params = new HashMap();
        HttpRequestUtils.getmInstance().send(GroupActivity.this, Constant.GROUP_LIST_URL, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("reslut", result);
                AppBeans<Groups> appDatas = JSONObject.parseObject(result, new TypeReference<AppBeans<Groups>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Event(value = {R.id.back_arrows, R.id.title_right})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                Intent intent = new Intent(this, NewGroupActivity.class);
                startActivityForResult(intent, 1023);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RongIM.getInstance().startDiscussionChat(this, mDatas.get(position).getDiscussionId(), mDatas.get(position).getDiscussionName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1023) {
            mDatas.clear();
            loadData();
        }
    }
}
