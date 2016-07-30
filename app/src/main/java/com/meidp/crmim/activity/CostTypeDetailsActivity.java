package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.CostTypeDetailsAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBeans;
import com.meidp.crmim.model.CostTypeDetails;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_cost_type_details)
public class CostTypeDetailsActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;
    private int oid;
    @ViewInject(R.id.listview)
    private ListView mListView;
    private List<CostTypeDetails> mDatas;
    private CostTypeDetailsAdapter mAdapter;

    @Override
    public void onInit() {
        title.setText("费用类别");
        oid = getIntent().getIntExtra("OID", -1);
        mDatas = new ArrayList<>();
        mAdapter = new CostTypeDetailsAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Event(value = {R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("Id", oid);
        HttpRequestUtils.getmInstance().send(CostTypeDetailsActivity.this, Constant.COST_TYPE_DETAIL, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                AppBeans<CostTypeDetails> appBeans = JSONObject.parseObject(result, new TypeReference<AppBeans<CostTypeDetails>>() {
                });
                if (appBeans != null && appBeans.getEnumcode() == 0) {
                    mDatas.addAll(appBeans.getData());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("CodeName", mDatas.get(position).getCodeName());
        intent.putExtra("ExpID", mDatas.get(position).getID());
        intent.putExtra("OID", oid);
        setResult(1002, intent);
        finish();
    }
}
