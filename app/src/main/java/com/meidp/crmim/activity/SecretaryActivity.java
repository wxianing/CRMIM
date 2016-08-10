package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.SecretaryAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.Secretary;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_secretary)
public class SecretaryActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.listview)
    private ListView mListView;
    private List<Secretary> mDatas;

    private SecretaryAdapter mAdapter;
    private String keyWord = "";

    @Override
    public void onInit() {
        title.setText("小秘书");
        mDatas = new ArrayList<>();
        mAdapter = new SecretaryAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onInitData() {
        loadData(keyWord);
    }

    @Event({R.id.back_arrows})
    private void onClcik(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }

    private void loadData(String keyWord) {
        HashMap params = new HashMap();
        params.put("Keyword", keyWord);
        params.put("sType", -1);
        params.put("PageIndex", 1);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(SecretaryActivity.this, Constant.GET_SYSTEM_MESSAGE, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppDatas<Secretary> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<Secretary>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int billId = mDatas.get(position).getBillId();
        int billTypeFlag = mDatas.get(position).getBillTypeFlag();
        int billTypeCode = mDatas.get(position).getBillTypeCode();
        if (billTypeFlag == 1 && billTypeCode == 4) {//费用
            Intent intent = new Intent(SecretaryActivity.this, CostDetailsActivity.class);
            intent.putExtra("OID", mDatas.get(position).getBillId());
            startActivity(intent);
        }
        if (billTypeFlag == 4 && billTypeCode == 4) {//项目拜访
            Intent intent = new Intent(SecretaryActivity.this, CostDetailsActivity.class);
            intent.putExtra("OID", mDatas.get(position).getBillId());
            startActivity(intent);
        }
        if (billTypeFlag == 4 && billTypeCode == 7) {//客户联系人
            Intent intent = new Intent(SecretaryActivity.this, CostDetailsActivity.class);
            intent.putExtra("OID", mDatas.get(position).getBillId());
            startActivity(intent);
        }
        if (billTypeFlag == 5 && billTypeCode == 11) {//样机申请
            Intent intent = new Intent(SecretaryActivity.this, PrototypeDetailsActivity.class);
            intent.putExtra("OID", mDatas.get(position).getBillId());
            startActivity(intent);
        }


    }
}
