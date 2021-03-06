package com.meidp.crmim.activity;

import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_work_plan_details)
public class WorkPlanDetailsActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;

    @Override
    public void onInit() {
        title.setText("计划详情");
    }

    @Event({R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                break;
        }
    }
}
