package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.StockUpAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.StockUps;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 备货审核
 */
@ContentView(R.layout.activity_stock_up)
public class StockUpActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;

    private String keyword = "";
    private int pageIndex = 1;

    @ViewInject(R.id.listview)
    private ListView mListView;
    private List<StockUps> mDatas;
    private StockUpAdapter mAdapter;

    @Override
    public void onInit() {
        title.setText("备货审核");
        mDatas = new ArrayList<>();
        mAdapter = new StockUpAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onInitData() {
        loadData(pageIndex, keyword);
    }

    private void loadData(int pageIndex, String keyword) {
        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("sType", 1);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(StockUpActivity.this, Constant.STOCK_LIST_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppDatas<StockUps> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<StockUps>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
//                    mListView.onRefreshComplete();
                }
            }
        });
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
        Intent intent = new Intent(this, StockUpDetailsActivity.class);
        intent.putExtra("OID", mDatas.get(position).getID());
        startActivityForResult(intent, 1019);
    }
}
