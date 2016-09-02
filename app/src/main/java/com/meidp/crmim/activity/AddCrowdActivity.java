package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.AddCrowdAdapter;
import com.meidp.crmim.adapter.SelectFriendAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.ClientContacts;
import com.meidp.crmim.model.PersonalLinkmans;
import com.meidp.crmim.model.TeamMembers;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.ToastUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddCrowdActivity extends BasicActivity implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView>, View.OnClickListener {
    public static CustomerListActivity activity;
    private TextView title;

    private TextView rightTile;

    private ListView mListView;

    private List<ClientContacts> mDatas;
    private AddCrowdAdapter mAdapter;
    private String flag;
    private int pageIndex = 1;

    private EditText searchEdittext;
    private String keyword = "";
    private int checkNum = 0;

    private List<PersonalLinkmans> checkLists;

    private List<Integer> userIds;
    private List<TeamMembers> members;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_crowd);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        members = (List<TeamMembers>) getIntent().getSerializableExtra("TeamMembers");
        checkLists = new ArrayList<>();
        userIds = new ArrayList<>();
        title = (TextView) findViewById(R.id.title_tv);
        title.setText("联系人");
        mListView = (ListView) findViewById(R.id.listview);
        searchEdittext = (EditText) findViewById(R.id.search_edittext);
//        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mDatas = new ArrayList<>();
        mAdapter = new AddCrowdAdapter(mDatas, AddCrowdActivity.this);
        mListView.setAdapter(mAdapter);
        rightTile = (TextView) findViewById(R.id.title_right);
        rightTile.setText("确定");
        rightTile.setVisibility(View.VISIBLE);
    }

    private void initEvent() {
        mListView.setOnItemClickListener(this);
//        mListView.setOnRefreshListener(this);
        findViewById(R.id.back_arrows).setOnClickListener(this);
        findViewById(R.id.search_btn).setOnClickListener(this);
        rightTile.setOnClickListener(this);

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AddCrowdAdapter.ViewHolder holder = (AddCrowdAdapter.ViewHolder) view.getTag();
        holder.checkBox.toggle();
        AddCrowdAdapter.getIsSelected().put(mDatas.get(position).getID(), holder.checkBox.isChecked());

        if (holder.checkBox.isChecked() == true) {
            checkNum++;
            userIds.add(mDatas.get(position).getID());
            PersonalLinkmans person = new PersonalLinkmans();
            person.setCompanyName(mDatas.get(position).getCustName());
            person.setLinkmanName(mDatas.get(position).getLinkManName());
            person.setLinkmanType(1);
//            person.setSex(mDatas.get(position).getSex());
            person.setPosition(mDatas.get(position).getPosition());
            person.setMobilePhone(mDatas.get(position).getWorkTel());
            person.setLinkEmployeeID(mDatas.get(position).getID());

            checkLists.add(person);
        } else {
            checkNum--;
            for (int i = 0; i < userIds.size(); i++) {
                int checkId = userIds.get(i);
                for (int j = 0; j < mDatas.size(); j++) {
                    if (mDatas.get(j).getID() == checkId) {
                        userIds.remove(i);
                        checkLists.remove(i);
                    }
                }
            }
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                sendMsg();
                break;
            case R.id.search_btn:
                keyword = searchEdittext.getText().toString().trim();
                mDatas.clear();
                loadData(pageIndex, keyword);
                break;
        }
    }

    private void sendMsg() {
        String userIdStr = "";
        for (int i = 0; i < userIds.size(); i++) {
            userIdStr += userIds.get(i) + ",";
        }
        HashMap params = new HashMap();
        String checkString = JSON.toJSONString(checkLists);
        params.put("Id", checkString);

        HttpRequestUtils.getmInstance().send(AddCrowdActivity.this, Constant.ADD_CROWD_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) throws IOException {
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    ToastUtils.shows(AddCrowdActivity.this, "添加成功");
                    Intent intent = new Intent();
                    setResult(1028, intent);
                    finish();
                } else {
                    ToastUtils.shows(AddCrowdActivity.this, appMsg.getMsg());
                }
            }
        });
    }

    private void initData() {
        loadData(pageIndex, keyword);
    }

    private void loadData(int pageIndex, String keyword) {
        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 1000);
        HttpRequestUtils.getmInstance().send(AddCrowdActivity.this, Constant.CUSTOMER_CONTACTS_URL, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                AppDatas<ClientContacts> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<ClientContacts>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData().getDataList());

                    mAdapter = new AddCrowdAdapter(mDatas, AddCrowdActivity.this);
                    mListView.setAdapter(mAdapter);
                    if (members != null && members.size() > 0) {
                        for (int i = 0; i < members.size(); i++) {
                            AddCrowdAdapter.getIsSelected().put(members.get(i).getLinkEmployeeID(), true);
                        }
                    }
                    mAdapter.notifyDataSetChanged();
//                    mListView.onRefreshComplete();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1001) {
            String customName = data.getStringExtra("customName");
            int customerId = data.getIntExtra("CustomerId", 0);
            Intent intent = new Intent();
            intent.putExtra("customName", customName);//联系人
            intent.putExtra("customerId", customerId);
            intent.putExtra("ContactPhone", data.getStringExtra("ContactPhone"));
            Log.e("ContactPhone>>>>>", data.getStringExtra("ContactPhone"));
            intent.putExtra("CustName", data.getStringExtra("CustName"));//公司
            setResult(1001, intent);
            finish();
        } else if (requestCode == 1003) {
            Intent intent = new Intent();
            intent.putExtra("CustName", "");
            intent.putExtra("OID", "");
            setResult(1003, intent);
            finish();
        } else if (requestCode == 1017) {
            mDatas.clear();
            loadData(pageIndex, keyword);
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex = 1;
        mDatas.clear();
        loadData(pageIndex, keyword);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex++;
        loadData(pageIndex, keyword);
    }
}
