package com.meidp.crmim.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.ClientContacts;
import com.meidp.crmim.model.CustomerLists;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.SPUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_hospital_list)
public class HospitalListActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.listview)
    private ListView mListView;
    private List<CustomerLists> mDatas;
    private HospitalAadapter mAdapter;
    private int pageIndex = 1;
    private String keyword = "";
    @ViewInject(R.id.search_edittext)
    private EditText search;


    @Override
    public void onInit() {
        title.setText("请选择");
        mDatas = new ArrayList<>();
        mAdapter = new HospitalAadapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Event({R.id.back_arrows, R.id.search_btn})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.search_btn:
                keyword = search.getText().toString().trim();
                mDatas.clear();
                loadData(pageIndex, keyword);
                keyword = "";
                break;
        }
    }

    @Override
    public void onInitData() {
        super.onInitData();
        loadData(pageIndex, keyword);
    }

    private void loadData(int pageIndex, String keyword) {
        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(HospitalListActivity.this, Constant.CUSTOMER_LIST_URL, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                AppDatas<CustomerLists> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<CustomerLists>>() {
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

        Log.e("getCustName", ">>>>>>>>>>>>" + mDatas.get(position).getCustName());
        Intent intent = new Intent();
        intent.putExtra("CustName", mDatas.get(position).getCustName());
        intent.putExtra("CustNo", mDatas.get(position).getCustNo());
        SPUtils.save(this, "CustName", mDatas.get(position).getCustName());
        SPUtils.save(this, "CustNo", mDatas.get(position).getCustNo());
        setResult(1012, intent);
        finish();
    }
}
