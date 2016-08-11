package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.NewsAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.Informations;
import com.meidp.crmim.model.Rules;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_news)
public class NewsActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.listview)
    private ListView mListView;
    private List<Informations> mDatas;
    private NewsAdapter mAdapter;
    private int sType;
    private int sType2;
    private String titleName;

    private int pageIndex = 1;

    @Override
    public void onInit() {
        titleName = getIntent().getStringExtra("title");
        title.setText(titleName);
        sType = getIntent().getIntExtra("sType", -1);
        sType2 = getIntent().getIntExtra("sType1", -1);
        mDatas = new ArrayList<>();
        mListView.setOnItemClickListener(this);
    }

    private void loadData(int pageIndex) {
        HashMap params = new HashMap();
        params.put("Keyword", "");
        params.put("sType", sType);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(NewsActivity.this, Constant.REGULATORY_FRAMEWORK_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppDatas<Informations> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<Informations>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    if (appDatas.getData().getDataList().size() > 0) {
                        mDatas.addAll(appDatas.getData().getDataList());
                        mAdapter = new NewsAdapter(mDatas, NewsActivity.this);
                        mListView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDatas.clear();
        loadData(pageIndex);
    }

    @Event({R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(NewsActivity.this, WebViewActivity.class);
        intent.putExtra("ClickUrl", mDatas.get(position).getClickUrl());
        intent.putExtra("OID", mDatas.get(position).getID());
        intent.putExtra("sType", sType2);
        intent.putExtra("TITLE", titleName);//标题
        startActivity(intent);
    }
}
