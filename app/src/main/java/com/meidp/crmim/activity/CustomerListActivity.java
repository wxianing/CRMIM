package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.CustomerListAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.CustomerLists;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NullUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_customer_list)
public class CustomerListActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    public static CustomerListActivity activity;
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_right)
    private TextView titleRight;

    @ViewInject(R.id.listview)
    private ListView mListView;


    private List<CustomerLists> mDatas;
    private CustomerListAdapter mAdapter;
    private String flag;

    @Override
    public void onInit() {
        activity = this;
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setText("新增");
        title.setText("客户列表");
        mDatas = new ArrayList<>();
        mAdapter = new CustomerListAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        flag = getIntent().getStringExtra("FLAG");
    }

    @Event(value = {R.id.back_arrows, R.id.title_right})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                Intent intent = new Intent();
                intent.setClass(this, NewClientActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onInitData() {
    }

    private void loadData() {
        HashMap params = new HashMap();
        params.put("PageIndex", 1);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(CustomerListActivity.this, Constant.CUSTOMER_LIST_URL, params, new HttpRequestCallBack<String>() {
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
    protected void onResume() {
        super.onResume();
        mDatas.clear();
        loadData();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (NullUtils.isNull(flag) && "Apply".equals(flag)) {
            Intent intent = new Intent();
            intent.putExtra("CustName", mDatas.get(position).getCustName());
            intent.putExtra("OID", mDatas.get(position).getID());
            setResult(1003, intent);
            finish();
        } else {
            Intent intent = new Intent();
            intent.setClass(CustomerListActivity.this, CustomContactActivity.class);
            intent.putExtra("OID", mDatas.get(position).getID());
            intent.putExtra("CustNo", mDatas.get(position).getCustNo());
            startActivityForResult(intent, 1001);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1001) {
            String customName = data.getStringExtra("customName");
            int customerId = data.getIntExtra("CustomerId", 0);
            Intent intent = new Intent();
            intent.putExtra("customName", customName);
            intent.putExtra("customerId", customerId);
            setResult(1001, intent);
            finish();
        }
    }
}
