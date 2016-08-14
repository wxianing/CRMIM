package com.meidp.crmim.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerialContext;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.ProduceAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.Product;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_produce_center)
public class ProduceCenterActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_right)
    private TextView titleRight;


    private int pageIndex = 1;
    private String keyword = "";

    @ViewInject(R.id.listview)
    private ListView mListView;

    private ArrayList<Product> mDatas;
    private ProduceAdapter mAdapter;
    private int checkNum;

    private ArrayList<Product> checkLists;

    public ProduceCenterActivity() {
    }

    @Override
    public void onInit() {
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setText("确定");
        title.setText("产品中心");
        mDatas = new ArrayList<>();
        checkLists = new ArrayList<>();
        mAdapter = new ProduceAdapter(mDatas, ProduceCenterActivity.this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("sType", 1);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(ProduceCenterActivity.this, Constant.PRODUCE_LIST, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppDatas<Product> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<Product>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Event({R.id.back_arrows, R.id.title_right})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                String produceNames = "";
                for (int i = 0; i < checkLists.size(); i++) {
                    produceNames += checkLists.get(i).getProductName() + "、";
                }
                Intent intent = new Intent();
                intent.putExtra("Product", (Serializable) checkLists);
                intent.putExtra("ProductName", produceNames);
                setResult(1009, intent);
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ProduceAdapter.ViewHolder vh = (ProduceAdapter.ViewHolder) view.getTag();
        vh.checkBox.toggle();
        ProduceAdapter.getIsSelected().put(position, vh.checkBox.isChecked());
        if (vh.checkBox.isChecked() == true) {
            checkNum++;
            checkLists.add(mDatas.get(position));
        } else {
            checkNum--;
            checkLists.remove(checkNum);
        }

//        Intent intent = new Intent();
//        intent.putExtra("ProductID", mDatas.get(position).getProductID());
//        intent.putExtra("ProductName", mDatas.get(position).getProductName());
//        setResult(1009, intent);
//        finish();

    }
}
