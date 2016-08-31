package com.meidp.crmim.activity;

import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.CheckedAdapter;
import com.meidp.crmim.adapter.SelectFriendAdapter;
import com.meidp.crmim.adapter.TeamExpanAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBeans;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.Contact;
import com.meidp.crmim.model.Friends;
import com.meidp.crmim.model.TeamDetails;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;
import com.meidp.crmim.view.ExpListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_select_empolyee)
public class SelectEmpolyeeActivity extends BaseActivity implements ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_right)
    private TextView titleRight;

    private List<Friends> mDatas;
    private SelectFriendAdapter mAdapter;

    private String keyWord = "";

    private GridView gridView;
    private CheckedAdapter checkedAdapter;
    private List<Friends> checkedLists;//选中列表

    private List<String> empolyees;
    private List<String> userIds;

    private int checkNum;
    private EditText otherPersonEt;

    private SelectFriendAdapter.ViewHolder holder;
    private int teamId;
    private String teamName;
    private String teamNames;

    @ViewInject(R.id.expListView)
    protected ExpListView expListView;
    private List<Contact> contactList;
    private TeamExpanAdapter expandableAdapter;
    private String empolyeesIds = "";

    private List<TeamDetails.UsersBean> userLists;
    private List<TeamDetails.UsersBean> usersBeanList;

    @Override
    public void onInit() {
        View headerView = LayoutInflater.from(this).inflate(R.layout.header_new_group_layout, null);
        headerView.findViewById(R.id.remark).setVisibility(View.GONE);
        gridView = (GridView) headerView.findViewById(R.id.gridview);
        expListView.addHeaderView(headerView);

        titleRight.setText("确定");
        titleRight.setVisibility(View.VISIBLE);
        title.setText("添加团队成员");
        teamId = getIntent().getIntExtra("teamId", 0);
        teamNames = getIntent().getStringExtra("TeamName");//接收过来的团队名称
        usersBeanList = new ArrayList<>();
        userLists = (List<TeamDetails.UsersBean>) getIntent().getSerializableExtra("UsersBean");//接收传过来的对象
//        usersBeanList.addAll(userLists);
        userIds = new ArrayList<>();

        mDatas = new ArrayList<>();
        empolyees = new ArrayList<>();
        checkedLists = new ArrayList<>();
        mAdapter = new SelectFriendAdapter(mDatas, this);
//        mListVIew.setAdapter(mAdapter);
        checkedAdapter = new CheckedAdapter(checkedLists, this);
        gridView.setAdapter(checkedAdapter);
//        mListVIew.setOnItemClickListener(this);
        contactList = new ArrayList<>();
//        expandableAdapter = new ExpanListAdapter(contactList, this);
        expListView.setOnChildClickListener(this);
        expListView.setOnGroupClickListener(this);
        expListView.setAdapter(expandableAdapter);
        expListView.setGroupIndicator(null);
    }

    @Override
    public void onInitData() {
//        loadData(keyWord);

        HttpRequestUtils.getmInstance().send(this, Constant.GET_CONTACTS_URL, null, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBeans<Contact> appBean = JSONObject.parseObject(result, new TypeReference<AppBeans<Contact>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    contactList.clear();
                    contactList.addAll(appBean.getData());
                    expandableAdapter = new TeamExpanAdapter(contactList, SelectEmpolyeeActivity.this);
                    expListView.setAdapter(expandableAdapter);
                    if (userLists != null && !userLists.isEmpty()) {
                        for (int i = 0; i < userLists.size(); i++) {
                            TeamExpanAdapter.getIsSelected().put(userLists.get(i).getEmployeeId(), true);//默认吧原理已经有的成员选中
                            empolyees.add(Integer.toString(userLists.get(i).getEmployeeId()));
                        }
                    }
                    expandableAdapter.notifyDataSetChanged();
                    for (int i = 0; i < contactList.size(); i++) {
                        expListView.expandGroup(i);//默认展开选项
                    }
                }
            }
        });
    }

    @Event({R.id.back_arrows, R.id.title_right})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                String empolyeeId = "";
                String empolyeeName = "";
                for (int i = 0; i < checkedLists.size(); i++) {
                    empolyeeId += checkedLists.get(i).getEmployeeID() + ",";
                    empolyeeName += checkedLists.get(i).getEmployeeName() + "、";
                }
                Intent intent = new Intent();
                intent.putExtra("EmpolyeeId", empolyeeId);
                intent.putExtra("EmpolyeeName", empolyeeName);
                intent.putExtra("Friends", (Serializable) checkedLists);
                intent.putStringArrayListExtra("UserIds", (ArrayList<String>) userIds);
                setResult(1013, intent);
                finish();
                break;
        }
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this, R.style.Dialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.edittext_dialog, null);
        TextView titleName = (TextView) contentView.findViewById(R.id.title);
        final EditText editText = (EditText) contentView.findViewById(R.id.message);
        titleName.setText("请输入团队名称");
        editText.setHint("请输入团队名称");
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
                String teamName = editText.getText().toString().trim();
                sendMsg(teamName, 0);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void sendMsg(String teamName, int teamId) {
        String otherPersons = otherPersonEt.getText().toString().trim();
        String empolyeeStr = "";
        for (int i = 0; i < empolyees.size(); i++) {
            empolyeeStr += empolyees.get(i) + ",";
        }
        if (NullUtils.isNull(empolyeeStr)) {
            empolyeeStr = empolyeeStr.substring(0, empolyeeStr.length() - 1);
        }
        HashMap params = new HashMap();
        params.put("ID", teamId);
        params.put("Employees", empolyeeStr);
        params.put("TeamName", teamName);
        params.put("OtherPerson", otherPersons);
        HttpRequestUtils.getmInstance().send(SelectEmpolyeeActivity.this, Constant.SAVE_TEAM_LIST, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    ToastUtils.shows(SelectEmpolyeeActivity.this, "添加成功");
                    finish();
                } else {
                    ToastUtils.shows(SelectEmpolyeeActivity.this, appMsg.getMsg());
                }
            }
        });
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

        Log.e("select employeeID", ">>>>>>>>>>>>>>" + contactList.get(groupPosition).getUsers().get(childPosition).getEmployeeID());
        int employeeID = contactList.get(groupPosition).getUsers().get(childPosition).getEmployeeID();//
        TeamExpanAdapter.ChildViewHolder holder = (TeamExpanAdapter.ChildViewHolder) v.getTag();
        holder.mCheckBox.toggle();
        SelectFriendAdapter.getIsSelected().put(childPosition, holder.mCheckBox.isChecked());
        if (holder.mCheckBox.isChecked() == true) {
            checkNum++;
            userIds.add(Integer.toString(contactList.get(groupPosition).getUsers().get(childPosition).getUserID()));
            empolyees.add(Integer.toString(contactList.get(groupPosition).getUsers().get(childPosition).getEmployeeID()));
            Friends friends = new Friends();
            friends.setEmployeeName(contactList.get(groupPosition).getUsers().get(childPosition).getEmployeeName());
            friends.setPhotoURL(contactList.get(groupPosition).getUsers().get(childPosition).getPhotoURL());
            friends.setEmployeeID(contactList.get(groupPosition).getUsers().get(childPosition).getEmployeeID());
            checkedLists.add(friends);
            checkedAdapter.notifyDataSetChanged();
        } else {
            checkNum--;

            for (int i = 0; i < checkedLists.size(); i++) {
                int checkId = checkedLists.get(i).getEmployeeID();
                Log.e("checkId", "<<<<>>>>" + checkId);
                if (employeeID == checkId) {
                    checkedLists.remove(i);
                    empolyees.remove(i);
                    userIds.remove(i);
                }
            }
            checkedAdapter.notifyDataSetChanged();
//                positions.remove(checkNum);
        }
        for (int k = 0; k < empolyees.size(); k++) {
            empolyeesIds += empolyees.get(k) + ",";
        }
        Log.e("userIdLists", empolyeesIds);
        return true;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        TeamExpanAdapter.GroupVieHolder holder = (TeamExpanAdapter.GroupVieHolder) v.getTag();
        holder.gCheckBox.toggle();
        SelectFriendAdapter.getIsSelected().put(groupPosition, holder.gCheckBox.isChecked());
        if (holder.gCheckBox.isChecked() == true) {
            for (int i = 0; i < contactList.get(groupPosition).getUsers().size(); i++) {
                TeamExpanAdapter.getIsSelected().put(contactList.get(groupPosition).getUsers().get(i).getEmployeeID(), true);
                userIds.add(Integer.toString(contactList.get(groupPosition).getUsers().get(i).getUserID()));
                empolyees.add(Integer.toString(contactList.get(groupPosition).getUsers().get(i).getEmployeeID()));
                Friends friends = new Friends();
                friends.setEmployeeName(contactList.get(groupPosition).getUsers().get(i).getEmployeeName());
                friends.setPhotoURL(contactList.get(groupPosition).getUsers().get(i).getPhotoURL());
                friends.setUserID(contactList.get(groupPosition).getUsers().get(i).getUserID());
                checkedLists.add(friends);
                checkedAdapter.notifyDataSetChanged();
            }
        } else {
            for (int i = 0; i < contactList.get(groupPosition).getUsers().size(); i++) {
                TeamExpanAdapter.getIsSelected().put(contactList.get(groupPosition).getUsers().get(i).getEmployeeID(), false);
                for (int k = 0; k < checkedLists.size(); k++) {
                    int checkId = checkedLists.get(k).getUserID();
                    Log.e("checkId", "<<<<>>>>" + checkId);
                    if (contactList.get(groupPosition).getUsers().get(i).getUserID() == checkId) {
                        checkedLists.remove(k);
                        empolyees.remove(k);
                        userIds.remove(k);
                    }
                    checkedAdapter.notifyDataSetChanged();
                }
            }
        }
        expandableAdapter.notifyDataSetChanged();
        return true;
    }
}
