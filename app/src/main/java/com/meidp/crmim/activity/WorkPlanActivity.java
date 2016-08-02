package com.meidp.crmim.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

@ContentView(R.layout.activity_work_plan)
public class WorkPlanActivity extends BaseActivity {
    @ViewInject(R.id.title_right)
    private TextView titleRight;
    @ViewInject(R.id.title_tv)
    private TextView title;

    private int pageIndex;
    private String keyWord;

    @Override
    public void onInit() {
        titleRight.setText("新建");
        titleRight.setVisibility(View.VISIBLE);
        title.setText("工作计划");
    }

    @Event({R.id.back_arrows, R.id.title_right})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                Intent intent = new Intent(this, NewWorkPlanActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onInitData() {
        loadData(pageIndex, keyWord);
    }

    /**
     * "Keyword": "sample string 1",
     * "sType": 2,
     * "PageIndex": 3,
     * "PageSize": 4
     */
    private void loadData(int pageIndex, String keyWord) {
        HashMap params = new HashMap();
        params.put("sType", -1);
        params.put("Keyword", keyWord);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(WorkPlanActivity.this, Constant.GET_PERSONAL_PLAN, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("个人计划result", result);
            }
        });
    }
}
