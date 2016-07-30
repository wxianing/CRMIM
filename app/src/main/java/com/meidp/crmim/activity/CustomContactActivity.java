package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.CustomerContactAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBeans;
import com.meidp.crmim.model.CustomContacts;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_custom_contact)
public class CustomContactActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_right)
    private TextView titleRight;

    private int oid;
    @ViewInject(R.id.listview)
    private ListView mListView;
    private List<CustomContacts> mDatas;
    private CustomerContactAdapter mAdapter;
    private String custNo;

    @Override
    public void onInit() {
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setText("新增");
        title.setText("客户联系人");
        oid = getIntent().getIntExtra("OID", -1);
        custNo = getIntent().getStringExtra("CustNo");
        mDatas = new ArrayList<>();
        mAdapter = new CustomerContactAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onInitData() {

    }

    private void loadData(int oid) {
        HashMap params = new HashMap();
        params.put("Id", oid);
        HttpRequestUtils.getmInstance().send(CustomContactActivity.this, Constant.CUSTOMER_CONTACT_URL, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                AppBeans<CustomContacts> appBean = JSONObject.parseObject(result, new TypeReference<AppBeans<CustomContacts>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    mDatas.addAll(appBean.getData());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        mDatas.clear();
        loadData(oid);
        super.onResume();
    }

    @Event(value = {R.id.back_arrows, R.id.title_right})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                Intent intent = new Intent(this, NewContactsActivity.class);
                intent.putExtra("CustNo", custNo);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("customName", mDatas.get(position).getLinkManName());
        intent.putExtra("CustomerId", oid);
        setResult(1001, intent);
//        Intent intent = new Intent(this, SigninMainActivity.class);
//        intent.putExtra("customName", mDatas.get(position).getLinkManName());
//        intent.putExtra("CustomerId", oid);
//        if (CustomerListActivity.activity != null) {
//            CustomerListActivity.activity.finish();
//            CustomerListActivity.activity = null;
//        }
//        startActivity(intent);
        finish();
    }
}
