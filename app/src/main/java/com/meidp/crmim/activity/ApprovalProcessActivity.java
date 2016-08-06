package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.CheckforApply;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 审批
 */
@ContentView(R.layout.activity_approval_process)
public class ApprovalProcessActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    private String keyword = "";
    private int pageIndex = 1;
    @ViewInject(R.id.listview)
    private ListView mListView;
    private List<CheckforApply> mDatas;


    @Override
    public void onInit() {
        title.setText("审批");
        mDatas = new ArrayList<>();
    }

    @Event({R.id.right_img, R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_img:
                Intent intent = new Intent(this, NewApprovalProcessActivity.class);
                startActivity(intent);
                break;
            case R.id.back_arrows:
                finish();
                break;
        }
    }


    @Override
    public void onInitData() {
        loadData();
    }

    private void loadData() {
        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("sType", 0);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(ApprovalProcessActivity.this, Constant.FORCHECK_LIST, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppDatas<CheckforApply> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<CheckforApply>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {

                }
            }
        });
    }
}
