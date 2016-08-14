package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.adapter.RetaAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_rate_select)
public class RateSelectActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.listview)
    private ListView mListView;
    private List<String> mDatas;
    private RetaAdapter mAdapter;


    @Override
    public void onInit() {
        title.setText("选择成功率(%)");
        mDatas = new ArrayList<>();
        mDatas.add("10");
        mDatas.add("20");
        mDatas.add("30");
        mDatas.add("40");
        mDatas.add("50");
        mDatas.add("60");
        mDatas.add("70");
        mDatas.add("80");
        mDatas.add("90");
        mDatas.add("100");
        mAdapter = new RetaAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
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
        RetaAdapter.ViewHolder vh = (RetaAdapter.ViewHolder) view.getTag();
        String rate = (String) vh.reta.getText();
        Intent intent = new Intent();
        intent.putExtra("Reta", rate);
        setResult(1015);
        finish();
    }
}
