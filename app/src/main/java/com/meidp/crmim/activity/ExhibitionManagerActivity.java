package com.meidp.crmim.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.ExhibitionsAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.CostLists;
import com.meidp.crmim.model.Exhibitions;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 展会列表
 */
@ContentView(R.layout.activity_exhibition_manager)
public class ExhibitionManagerActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.listview)
    private ListView mListView;

    private String keyword = "";
    private int pageIndex = 1;

    private ExhibitionsAdapter mAdapter;

    private List<Exhibitions> mDatas;


    @Override
    public void onInit() {
        title.setText("展会管理");
        mDatas = new ArrayList<>();
        mAdapter = new ExhibitionsAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(ExhibitionManagerActivity.this, Constant.CONVERSATION_LIST, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("result", result);
                AppDatas<Exhibitions> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<Exhibitions>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Event({R.id.back_arrows, R.id.right_img})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.right_img:
                intent = new Intent(this, ConventionApplyForActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ExhibitionDetailsActivity.class);
        intent.putExtra("OID", mDatas.get(position).getID());
        startActivity(intent);
    }
}
