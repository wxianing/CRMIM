package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.CostTypeAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBeans;
import com.meidp.crmim.model.CostType;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_cost_type)
public class CostTypeActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.listview)
    private ListView mListView;
    private List<CostType> mDatas;
    private CostTypeAdapter mAdapter;

    @Override
    public void onInit() {
        mDatas = new ArrayList<>();
        mAdapter = new CostTypeAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("ChildId", 4);
        params.put("Id", 1);

        HttpRequestUtils.getmInstance().send(CostTypeActivity.this, Constant.GET_PUBLIC_TYPE, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBeans<CostType> appBeans = JSONObject.parseObject(result, new TypeReference<AppBeans<CostType>>() {
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
        Intent intent = new Intent(this, CostTypeDetailsActivity.class);
        intent.putExtra("OID", mDatas.get(position).getID());
        startActivityForResult(intent, 1002);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1002) {
            String codeName = data.getStringExtra("CodeName");
            int typeId = data.getIntExtra("OID", -1);
            int ExpID = data.getIntExtra("ExpID", -1);
            Intent intent = new Intent();
            intent.putExtra("OID", typeId);
            intent.putExtra("ExpID", ExpID);
            intent.putExtra("CodeName", codeName);
            setResult(1002, intent);
            finish();
        }
    }
}
