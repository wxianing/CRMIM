package com.meidp.crmim.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.MyCostAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.CostLists;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_cost_manager)
public class CostManagerActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.listview)
    private ListView mListView;
    private List<CostLists> mDatas;
    private MyCostAdapter mAdapter;


    @Override
    public void onInit() {
        title.setText("我的费用");
        mDatas = new ArrayList<>();
        mAdapter = new MyCostAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("PageIndex", 1);
        params.put("PageSize", 8);

        HttpRequestUtils.getmInstance().send(CostManagerActivity.this, Constant.GET_MY_COSTLIST_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("result", result);
                AppDatas<CostLists> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<CostLists>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Event(value = {R.id.back_arrows, R.id.right_img})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.right_img:
                Intent intent = new Intent();
                intent.setClass(this, CostReimbursementActivity.class);
                startActivity(intent);
                break;
        }
    }
}
