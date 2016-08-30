package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.CustomerListAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.ClientContacts;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NullUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_customer_list)
public class CustomerListActivity extends BaseActivity implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {
    public static CustomerListActivity activity;
    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.listview)
    private PullToRefreshListView mListView;

    private List<ClientContacts> mDatas;
    private CustomerListAdapter mAdapter;
    private String flag;
    private int pageIndex = 1;
    @ViewInject(R.id.search_edittext)
    private EditText searchEdittext;
    private String keyword = "";

    @Override
    public void onInit() {
        activity = this;
        title.setText("联系人");
        String titleName = getIntent().getStringExtra("TitleName");
        if (NullUtils.isNull(titleName)) {
            title.setText(titleName);
        }
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mDatas = new ArrayList<>();
        mAdapter = new CustomerListAdapter(mDatas, CustomerListActivity.this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        flag = getIntent().getStringExtra("FLAG");
        mListView.setOnRefreshListener(this);
    }

    @Event(value = {R.id.back_arrows, R.id.right_img, R.id.search_btn})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.right_img:
                Intent intent = new Intent();
                intent.setClass(this, NewClientActivity.class);
                startActivityForResult(intent, 1017);
                break;
            case R.id.search_btn:
                keyword = searchEdittext.getText().toString().trim();
                mDatas.clear();
                loadData(pageIndex, keyword);
                keyword = "";
                break;
        }
    }

    @Override
    public void onInitData() {
        loadData(pageIndex, keyword);
    }

    private void loadData(int pageIndex, String keyword) {
        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(CustomerListActivity.this, Constant.CUSTOMER_CONTACTS_URL, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                AppDatas<ClientContacts> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<ClientContacts>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData().getDataList());

                    mAdapter.notifyDataSetChanged();
                    mListView.onRefreshComplete();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ClientContacts contacts = mDatas.get(position - 1);
        if (NullUtils.isNull(flag) && "Apply".equals(flag)) {//直接返回客户
            Intent intent = new Intent();
            intent.putExtra("CustId", mDatas.get(position - 1).getCustID());//单位Id
            intent.putExtra("CustName", mDatas.get(position - 1).getCustName());//单位名称
            intent.putExtra("CustContact", mDatas.get(position - 1).getLinkManName());//客户联系人
            intent.putExtra("CustPhone", mDatas.get(position - 1).getWorkTel());//联系电话
            intent.putExtra("CustContactId", mDatas.get(position - 1).getID());//联系人Id
            intent.putExtra("OID", mDatas.get(position - 1).getID());//联系人Id
            intent.putExtra("Department", mDatas.get(position - 1).getDepartment());//部门
            intent.putExtra("Position", mDatas.get(position - 1).getPosition());//职位
            setResult(1001, intent);
            finish();
        } else {
//            Intent intent = new Intent();
//            intent.setClass(CustomerListActivity.this, CustomContactActivity.class);
//            intent.putExtra("CustName", mDatas.get(position - 1).getCustName());
//            intent.putExtra("OID", mDatas.get(position - 1).getID());
//            intent.putExtra("CustNo", mDatas.get(position - 1).getCustNo());
//            startActivityForResult(intent, 1001);

            Intent intent = new Intent(this, ClientDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("ClientContacts", contacts);
            intent.putExtra("OID", mDatas.get(position - 1).getID());
            intent.putExtras(bundle);
            startActivity(intent);

        }
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
