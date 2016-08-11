package com.meidp.crmim.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.CheckedAdapter;
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

@ContentView(R.layout.activity_add_team)
public class AddTeamActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_right)
    private TextView titleRight;

    @ViewInject(R.id.listview)
    private ListView mListVIew;

    private List<Friends> mDatas;
    private SelectFriendAdapter mAdapter;

    private String keyWord = "";

    @ViewInject(R.id.gridview)
    private GridView gridView;
    private CheckedAdapter checkedAdapter;
    private List<Friends> checkedLists;

    private List<String> empolyees;

    private int checkNum;

    private SelectFriendAdapter.ViewHolder holder;

    @Override
    public void onInit() {
        titleRight.setText("保存");
        titleRight.setVisibility(View.VISIBLE);
        title.setText("添加团队成员");
        mDatas = new ArrayList<>();
        empolyees = new ArrayList<>();
        checkedLists = new ArrayList<>();
        mAdapter = new SelectFriendAdapter(mDatas, this);
        mListVIew.setAdapter(mAdapter);
        checkedAdapter = new CheckedAdapter(checkedLists, this);
        gridView.setAdapter(checkedAdapter);
        mListVIew.setOnItemClickListener(this);

    }

    @Override
    public void onInitData() {
        loadData(keyWord);
    }

    private void loadData(String keyWord) {
        HashMap params = new HashMap();
        params.put("Keyword", keyWord);
        params.put("sType", 1);

        HttpRequestUtils.getmInstance().send(AddTeamActivity.this, Constant.FRIEND_LIST_URL, params, new HttpRequestCallBack<String>() {
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        holder = (SelectFriendAdapter.ViewHolder) view.getTag();
        holder.checkBox.toggle();
        SelectFriendAdapter.getIsSelected().put(position, holder.checkBox.isChecked());
        if (holder.checkBox.isChecked() == true) {
            checkNum++;
            empolyees.add(Integer.toString(mDatas.get(position).getEmployeeID()));
            checkedLists.add(mDatas.get(position));
            checkedAdapter.notifyDataSetChanged();
        } else {
            if (checkNum > 0) {
                checkNum--;

                empolyees.remove(checkNum);

                for (int i = 0; i < checkedLists.size(); i++) {
                    int checkId = checkedLists.get(i).getEmployeeID();
                    if (mDatas.get(position).getEmployeeID() == checkId) {
                        checkedLists.remove(i);
                    }
                }
                checkedAdapter.notifyDataSetChanged();
            }
        }
        for (int i = 0; i < empolyees.size(); i++) {
            Log.e("userId", empolyees.get(i));
            System.out.println(empolyees.get(i));
        }

    }

    @Event({R.id.back_arrows, R.id.title_right})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:

                final View dialogView = LayoutInflater.from(this).inflate(R.layout.edittext_layout, null);
                TextView titleName = (TextView) dialogView.findViewById(R.id.title_name);
                titleName.setText("请输入团队名称");
                new AlertDialog.Builder(this).setView(
                        dialogView).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) dialogView.findViewById(R.id.edit_text);

                        String teamName = editText.getText().toString().trim();
                        sendMsg(teamName);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
        }
    }

    private void sendMsg(String teamName) {
        String empolyeeStr = "";
        for (int i = 0; i < empolyees.size(); i++) {
            empolyeeStr += empolyees.get(i) + ",";
        }
        empolyeeStr = empolyeeStr.substring(0, empolyeeStr.length() - 1);

        HashMap params = new HashMap();
        params.put("Employees", empolyeeStr);
        params.put("TeamName", teamName);
        HttpRequestUtils.getmInstance().send(AddTeamActivity.this, Constant.SAVE_TEAM_LIST, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    ToastUtils.shows(AddTeamActivity.this, "添加成功");
                    finish();
                } else {
                    ToastUtils.shows(AddTeamActivity.this, appMsg.getMsg());
                }
            }
        });
    }
}
