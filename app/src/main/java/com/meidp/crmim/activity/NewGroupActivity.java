package com.meidp.crmim.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.SelectFriendAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.Friends;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

@ContentView(R.layout.activity_new_group)
public class NewGroupActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_right)
    private TextView titleRight;
    private List<Friends> mDatas;
    private SelectFriendAdapter mAdapter;
    @ViewInject(R.id.listview)
    private ListView mListView;
    private int checkNum;

    private List<String> userIds;
    private RongCreateGroupCallBack mCallBack;
    private String groupName;
    private String keyWord = "";
    @ViewInject(R.id.search_edittext)
    private EditText searchEditText;

    @Override
    public void onInit() {
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setText("确定");
        title.setText("新建群组");
        mDatas = new ArrayList<>();
        mAdapter = new SelectFriendAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        userIds = new ArrayList<>();
        mCallBack = new RongCreateGroupCallBack();

    }

    @Override
    public void onInitData() {
        loadData(keyWord);
    }

    private void loadData(String keyWord) {
        HashMap params = new HashMap();
        params.put("Keyword", keyWord);
        params.put("sType", 1);

        HttpRequestUtils.getmInstance().send(NewGroupActivity.this, Constant.FRIEND_LIST_URL, params, new HttpRequestCallBack<String>() {
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

    @Event(value = {R.id.back_arrows, R.id.title_right, R.id.search_btn})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                final View dialogView = LayoutInflater.from(this).inflate(R.layout.edittext_layout, null);
                new AlertDialog.Builder(this).setView(
                        dialogView).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) dialogView.findViewById(R.id.edit_text);
                        groupName = editText.getText().toString().trim();
                        if (NullUtils.isNull(groupName)) {
                            RongIM.getInstance().createDiscussionChat(NewGroupActivity.this, userIds, groupName, mCallBack);
                        }
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

                break;
            case R.id.search_btn:
                keyWord = searchEditText.getText().toString().trim();
                mDatas.clear();
                loadData(keyWord);
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SelectFriendAdapter.ViewHolder holder = (SelectFriendAdapter.ViewHolder) view.getTag();
        holder.checkBox.toggle();
        SelectFriendAdapter.getIsSelected().put(position, holder.checkBox.isChecked());
        if (holder.checkBox.isChecked() == true) {
            checkNum++;
            userIds.add(Integer.toString(mDatas.get(position).getUserID()));
        } else {
            checkNum--;
            userIds.remove(checkNum);
        }
        for (int i = 0; i < userIds.size(); i++) {
            Log.e("userId", userIds.get(i));
            System.out.println(userIds.get(i));
        }
    }

    private class RongCreateGroupCallBack extends RongIMClient.CreateDiscussionCallback {

        @Override
        public void onSuccess(String s) {

            Log.e("讨论组：", "创建成功" + s);
            String userStr = "";
            for (int i = 0; i < userIds.size(); i++) {
                userStr += userIds.get(i) + ",";
            }
            userStr = userStr.substring(0, userStr.length() - 1);//删除最后的，号
            Log.e("userStr", userStr);
            HashMap params = new HashMap();
            params.put("discussionId", s);
            params.put("discussionName", groupName);
            params.put("userstrs", userStr);
            HttpRequestUtils.getmInstance().send(NewGroupActivity.this, Constant.CREATE_GROUP_URL, params, new HttpRequestCallBack<String>() {
                @Override
                public void onSuccess(String result) {
                    AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                    });
                    if (appMsg != null && appMsg.getEnumcode() == 0) {
                        ToastUtils.shows(NewGroupActivity.this, "创建成功");
                        finish();
                    }
                }
            });
        }

        @Override
        public void onError(RongIMClient.ErrorCode errorCode) {
            Log.e("讨论组：", "创建失败");
        }
    }
}
