package com.meidp.crmim.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.meidp.crmim.model.Friends;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_select_empolyee)
public class SelectEmpolyeeActivity extends BaseActivity implements AdapterView.OnItemClickListener {

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
    private List<String> empolyeeNames;

    private int checkNum;

    private SelectFriendAdapter.ViewHolder holder;

    @Override
    public void onInit() {
        titleRight.setText("保存");
        titleRight.setVisibility(View.VISIBLE);
        title.setText("添加团队成员");
        mDatas = new ArrayList<>();
        empolyees = new ArrayList<>();
        empolyeeNames = new ArrayList<>();
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

        HttpRequestUtils.getmInstance().send(SelectEmpolyeeActivity.this, Constant.FRIEND_LIST_URL, params, new HttpRequestCallBack<String>() {
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
            empolyeeNames.add(mDatas.get(position).getEmployeeName());
            checkedLists.add(mDatas.get(position));
            checkedAdapter.notifyDataSetChanged();
        } else {
            if (checkNum > 0) {
                checkNum--;
                empolyees.remove(checkNum);
                empolyeeNames.remove(checkNum);
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
                String empolyeeString = "";
                for (int i = 0; i < empolyees.size(); i++) {
                    empolyeeString += empolyees.get(i) + ",";
                }

                empolyeeString = empolyeeString.substring(0, empolyeeString.length() - 1);
                String empolyeeNameStr = "";
                for (int i = 0; i < empolyeeNames.size(); i++) {
                    empolyeeNameStr += empolyeeNames.get(i) + "、";
                }
                empolyeeNameStr = empolyeeNameStr.substring(0, empolyeeNameStr.length() - 1);

                Intent intent = new Intent();
                intent.putExtra("EmpolyeeId", empolyeeString);
                intent.putExtra("EmpolyeeName", empolyeeNameStr);
                setResult(1013, intent);
                finish();
                break;
        }
    }
}
