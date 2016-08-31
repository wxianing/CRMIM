package com.meidp.crmim.activity;

import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.CheckedAdapter;
import com.meidp.crmim.adapter.ExpanListAdapter;
import com.meidp.crmim.adapter.SelectFriendAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBeans;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.Contact;
import com.meidp.crmim.model.Friends;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.CustomDialogUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.utils.ToastUtils;
import com.meidp.crmim.view.ExpListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Discussion;

@ContentView(R.layout.activity_new_group)
public class NewGroupActivity extends BaseActivity implements AdapterView.OnItemClickListener, ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupClickListener {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_right)
    private TextView titleRight;

    private List<Friends> mDatas;
    private SelectFriendAdapter mAdapter;

    private int checkNum;

    private ArrayList<String> userIds;
    private RongCreateGroupCallBack mCallBack;
    private String groupName;
    private String keyWord = "";

    private GridView gridView;
    private CheckedAdapter checkedAdapter;
    private List<Friends> checkedLists;

    private SelectFriendAdapter.ViewHolder holder;
    private int count;

    private List<Integer> positions = new ArrayList<>();
    private String discussionId;
    private String newGroupNames;

    @ViewInject(R.id.expListView)
    protected ExpListView expListView;

    private List<Contact> contactList;
    private ExpanListAdapter expandableAdapter;
    private static HashMap<Integer, Boolean> isSelected;
    private ArrayList<Integer> userIdLists;

    private Gson gson;

    @Override
    public void onInit() {

        View headerView = LayoutInflater.from(this).inflate(R.layout.header_new_group_layout, null);
        gridView = (GridView) headerView.findViewById(R.id.gridview);
        expListView.addHeaderView(headerView);

        gson = new Gson();
        userIdLists = new ArrayList<>();
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setText("确定");
        title.setText("创建群组");
        discussionId = getIntent().getStringExtra("discussionId");
        newGroupNames = getIntent().getStringExtra("GroupName");
        ArrayList<String> arrayList = getIntent().getStringArrayListExtra("userIds");
        if (arrayList != null && !arrayList.isEmpty()) {
            for (int i = 0; i < arrayList.size(); i++) {
                userIdLists.add(Integer.valueOf(arrayList.get(i)));
            }
        }
        if (NullUtils.isNull(discussionId)) {
            title.setText("添加群成员");
        }
        mDatas = new ArrayList<>();
        checkedLists = new ArrayList<>();
        mAdapter = new SelectFriendAdapter(mDatas, this);
//        mListView.setAdapter(mAdapter);
//        mListView.setOnItemClickListener(this);
        userIds = new ArrayList<>();
        mCallBack = new RongCreateGroupCallBack();
        checkedAdapter = new CheckedAdapter(checkedLists, this);
        gridView.setAdapter(checkedAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(">>>>>>>>>>>>>>>", "id" + checkedLists.get(position).getUserID());
                int checkId = checkedLists.get(position).getUserID();

                for (int i = 0; i < mDatas.size(); i++) {
                    if (mDatas.get(i).getUserID() == checkId) {
                        holder.checkBox.toggle();
                        checkNum--;
                        SelectFriendAdapter.getIsSelected().put(i, holder.checkBox.isChecked());//取消ListView  CheckBox选择
                        checkedLists.remove(position);
                        checkedAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        isSelected = new HashMap<>();
        contactList = new ArrayList<>();
        expListView.setOnChildClickListener(this);
        expListView.setOnGroupClickListener(this);
        expListView.setGroupIndicator(null);
    }

    /**
     * item点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        count = position;
        holder = (SelectFriendAdapter.ViewHolder) view.getTag();
        holder.checkBox.toggle();
        SelectFriendAdapter.getIsSelected().put(position, holder.checkBox.isChecked());
        if (holder.checkBox.isChecked() == true) {
            checkNum++;
            userIds.add(Integer.toString(mDatas.get(position).getUserID()));
            checkedLists.add(mDatas.get(position));
            checkedAdapter.notifyDataSetChanged();
            positions.add(position);
        } else {
            if (checkNum > 0) {
                checkNum--;
                userIds.remove(checkNum);
                for (int i = 0; i < checkedLists.size(); i++) {
                    int checkId = checkedLists.get(i).getUserID();
                    if (mDatas.get(position).getUserID() == checkId) {
                        checkedLists.remove(i);
                    }
                }
                checkedAdapter.notifyDataSetChanged();
                positions.remove(checkNum);
            }
        }
        for (int i = 0; i < userIds.size(); i++) {
            Log.e("userId", userIds.get(i));
        }
    }

    @Override
    public void onInitData() {
//        loadData(keyWord);
        /**
         * 保存通讯录
         */
        String contacts = (String) SPUtils.get(this, "Contacts", "");
        String cons = (String) SPUtils.get(this, "Contact", "");

        if (NullUtils.isNull(contacts)) {
            CustomDialogUtils.showProgressDialog(NewGroupActivity.this);
            AppBeans<Contact> appBean = JSONObject.parseObject(contacts, new TypeReference<AppBeans<Contact>>() {
            });
            if (appBean != null && appBean.getEnumcode() == 0) {
                CustomDialogUtils.cannelProgressDialog();
                contactList.clear();
                contactList.addAll(appBean.getData());
                try {
                    String contactString = SPUtils.SceneList2String(contactList);
                    SPUtils.save(NewGroupActivity.this, "Contact", "");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                expandableAdapter = new ExpanListAdapter(contactList, NewGroupActivity.this);
                expListView.setAdapter(expandableAdapter);
                expandableAdapter.notifyDataSetChanged();
                for (int i = 0; i < userIdLists.size(); i++) {
                    ExpanListAdapter.getIsSelected().put(userIdLists.get(i), true);
                }

                for (int i = 0; i < contactList.size(); i++) {
                    expListView.expandGroup(i);//默认展开选项
                }
            }
        } else {
            loadData();
        }
    }

    private void loadData() {
        HttpRequestUtils.getmInstance().send(this, Constant.GET_CONTACTS_URL, null, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) throws IOException {
                AppBeans<Contact> appBean = JSONObject.parseObject(result, new TypeReference<AppBeans<Contact>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    contactList.clear();
                    contactList.addAll(appBean.getData());

                    String contactString = SPUtils.SceneList2String(contactList);
                    SPUtils.save(NewGroupActivity.this, "Contact", "");

                    expandableAdapter = new ExpanListAdapter(contactList, NewGroupActivity.this);
                    expListView.setAdapter(expandableAdapter);

                    for (int i = 0; i < userIdLists.size(); i++) {
                        ExpanListAdapter.getIsSelected().put(userIdLists.get(i), true);
                    }

                    expandableAdapter.notifyDataSetChanged();
                    for (int i = 0; i < contactList.size(); i++) {
                        expListView.expandGroup(i);//默认展开选项
                    }
                }
            }
        });
    }

    private class discussionMember extends RongIMClient.ResultCallback<Discussion> {
        @Override
        public void onSuccess(Discussion discussion) {
            List<String> list = discussion.getMemberIdList();
            for (int i = 0; i < list.size(); i++) {
                String ids = list.get(i);
                Log.e("ids", ids);
                for (int j = 0; j < userIds.size(); j++) {
                    if (userIds.get(j).equals(ids)) {
                        userIds.remove(j);
                    }
                }
            }
            Log.e("suerIds", ">>>>>>>>>>" + userIds.size());
            RongIM.getInstance().addMemberToDiscussion(discussionId, userIds, new RongIMClient.OperationCallback() {
                @Override
                public void onSuccess() {
                    RongIM.getInstance().getDiscussion(discussionId, new discussionMembers());
//                            ToastUtils.shows(NewGroupActivity.this, "添加成功");
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }

        @Override
        public void onError(RongIMClient.ErrorCode errorCode) {

        }
    }

    private class discussionMembers extends RongIMClient.ResultCallback<Discussion> {
        @Override
        public void onSuccess(Discussion discussion) {
            String userStr = "";
            List<String> list = discussion.getMemberIdList();
            for (int i = 0; i < list.size(); i++) {
                userStr += list.get(i) + ",";
            }
            userStr = userStr.substring(0, userStr.length() - 1);//删除最后的，号
            Log.e("userStr", userStr);
            HashMap params = new HashMap();
            params.put("discussionId", discussionId);
            params.put("discussionName", newGroupNames);
            params.put("userstrs", userStr);
            HttpRequestUtils.getmInstance().send(NewGroupActivity.this, Constant.CREATE_GROUP_URL, params, new HttpRequestCallBack<String>() {
                @Override
                public void onSuccess(String result) {
                    AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                    });
                    if (appMsg != null && appMsg.getEnumcode() == 0) {
//                                        ToastUtils.shows(NewGroupActivity.this, "创建成功");
                        Intent intent = new Intent();
                        setResult(1023, intent);
                        finish();
                    } else {
                        ToastUtils.shows(NewGroupActivity.this, appMsg.getMsg());
                    }
                }
            });
        }

        @Override
        public void onError(RongIMClient.ErrorCode errorCode) {

        }
    }

    @Event(value = {R.id.back_arrows, R.id.title_right, R.id.search_btn})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                if (NullUtils.isNull(discussionId)) {
                    RongIM.getInstance().getDiscussion(discussionId, new discussionMember());
                } else {
                    showDialog();
                }

                break;
            case R.id.search_btn:
//                keyWord = searchEditText.getText().toString().trim();
//                mDatas.clear();
//                loadData(keyWord);
//                showDialog();
                break;
        }
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this, R.style.Dialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.edittext_dialog, null);
        TextView titleName = (TextView) contentView.findViewById(R.id.title);
        final EditText editText = (EditText) contentView.findViewById(R.id.message);
        titleName.setText("请输入群名称");
        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(true);
        Button negativeButton = (Button) contentView.findViewById(R.id.negativeButton);
        negativeButton.setClickable(true);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button positiveButton = (Button) contentView.findViewById(R.id.positiveButton);
        positiveButton.setClickable(true);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupName = editText.getText().toString().trim();
                if (NullUtils.isNull(groupName)) {
                    RongIM.getInstance().createDiscussionChat(NewGroupActivity.this, userIds, groupName, mCallBack);
                } else {
                    ToastUtils.shows(NewGroupActivity.this, "请输入群名称");
                }
                Log.e("positiveButton", keyWord);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    String userIdStr = "";

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Log.e("select userId", ">>>>>>>>>>>>>>" + contactList.get(groupPosition).getUsers().get(childPosition).getUserID());
        int userId = contactList.get(groupPosition).getUsers().get(childPosition).getUserID();
        ExpanListAdapter.ChildViewHolder holder = (ExpanListAdapter.ChildViewHolder) v.getTag();
        holder.mCheckBox.toggle();

        SelectFriendAdapter.getIsSelected().put(childPosition, holder.mCheckBox.isChecked());
        if (holder.mCheckBox.isChecked() == true) {
            checkNum++;
            userIds.add(Integer.toString(contactList.get(groupPosition).getUsers().get(childPosition).getUserID()));
            Friends friends = new Friends();
            friends.setEmployeeName(contactList.get(groupPosition).getUsers().get(childPosition).getEmployeeName());
            friends.setPhotoURL(contactList.get(groupPosition).getUsers().get(childPosition).getPhotoURL());
            friends.setUserID(contactList.get(groupPosition).getUsers().get(childPosition).getUserID());
            checkedLists.add(friends);
            checkedAdapter.notifyDataSetChanged();
            positions.add(childPosition);
        } else {
            if (checkNum > 0) {
                checkNum--;
                userIds.remove(checkNum);
                //根据UserId循环查找，如果选中的集合里面存在UserId就把它移除
                for (int i = 0; i < checkedLists.size(); i++) {
                    int checkId = checkedLists.get(i).getUserID();
                    Log.e("checkId", "<<<<>>>>" + checkId);
                    if (userId == checkId) {
                        checkedLists.remove(i);
                    }
                }
                checkedAdapter.notifyDataSetChanged();
                positions.remove(checkNum);
            }
        }
        for (int k = 0; k < userIds.size(); k++) {
            userIdStr += userIds.get(k) + ",";
        }

        Log.e("userIdLists", userIdStr);
        return true;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        ExpanListAdapter.GroupVieHolder holder = (ExpanListAdapter.GroupVieHolder) v.getTag();
        holder.gCheckBox.toggle();
        SelectFriendAdapter.getIsSelected().put(groupPosition, holder.gCheckBox.isChecked());
        if (holder.gCheckBox.isChecked() == true) {
            for (int i = 0; i < contactList.get(groupPosition).getUsers().size(); i++) {
                ExpanListAdapter.getIsSelected().put(contactList.get(groupPosition).getUsers().get(i).getUserID(), true);

                userIds.add(Integer.toString(contactList.get(groupPosition).getUsers().get(i).getUserID()));
                Friends friends = new Friends();
                friends.setEmployeeName(contactList.get(groupPosition).getUsers().get(i).getEmployeeName());
                friends.setPhotoURL(contactList.get(groupPosition).getUsers().get(i).getPhotoURL());
                friends.setUserID(contactList.get(groupPosition).getUsers().get(i).getUserID());
                checkedLists.add(friends);
                checkedAdapter.notifyDataSetChanged();
            }
        } else {
            for (int i = 0; i < contactList.get(groupPosition).getUsers().size(); i++) {
                ExpanListAdapter.getIsSelected().put(contactList.get(groupPosition).getUsers().get(i).getUserID(), false);
                for (int k = 0; k < checkedLists.size(); k++) {
                    int checkId = checkedLists.get(k).getUserID();
                    Log.e("checkId", "<<<<>>>>" + checkId);
                    if (contactList.get(groupPosition).getUsers().get(i).getUserID() == checkId) {
                        checkedLists.remove(k);
                        userIds.remove(k);
                    }
                    checkedAdapter.notifyDataSetChanged();
                }
            }
        }

        expandableAdapter.notifyDataSetChanged();
        return true;
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
            sendMsg(s, userStr);
        }

        @Override
        public void onError(RongIMClient.ErrorCode errorCode) {
            Log.e("讨论组：", "添加失败");
            ToastUtils.shows(NewGroupActivity.this, errorCode.getMessage());
        }

        @Override
        public void onFail(RongIMClient.ErrorCode errorCode) {
            super.onFail(errorCode);
            Log.e("讨论组：", "添加失败" + errorCode.getMessage());
        }
    }

    private void sendMsg(String s, String userStr) {
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
}
