package com.meidp.crmim.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.FriendsAdapter;
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

@ContentView(R.layout.activity_search_msg)
public class SearchMsgActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.listview)
    private ListView mListVeiw;
    private FriendsAdapter mAdapter;
    private List<Friends> mDatas;
    private String keyWord = "";
    @ViewInject(R.id.search_edittext)
    private EditText searchEditText;

    @Override
    public void onInit() {
        title.setText("搜索");
        mDatas = new ArrayList<>();
        mAdapter = new FriendsAdapter(mDatas, this);
        mListVeiw.setAdapter(mAdapter);
        mListVeiw.setOnItemClickListener(this);
    }

    @Event({R.id.back_arrows, R.id.search_btn})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.search_btn:
                keyWord = searchEditText.getText().toString().trim();
                mDatas.clear();
                loadData(keyWord);
                break;
        }
    }

    @Override
    public void onInitData() {

        loadData(keyWord);
    }

    private void loadData(String keyWord) {
        HashMap params = new HashMap();
        params.put("Keyword", keyWord);
        params.put("sType", 1);
        HttpRequestUtils.getmInstance().send(SearchMsgActivity.this, Constant.FRIEND_LIST_URL, params, new HttpRequestCallBack<String>() {
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
        RongIM.getInstance().startPrivateChat(this, Integer.toString(mDatas.get(position).getUserID()), mDatas.get(position).getEmployeeName());
    }
}
