package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_cycle)
public class CycleActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    private int aimFlag = -1;
    private String cycleType = "";

    @Override
    public void onInit() {
        title.setText("选择计划周期");
    }

    @Event({R.id.day_plan, R.id.week_plan, R.id.month_plan, R.id.quarter_plan, R.id.year_plan})
    private void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.day_plan:
                aimFlag = 1;
                cycleType = "日计划";
                break;
            case R.id.week_plan:
                cycleType = "周计划";
                aimFlag = 2;
                break;
            case R.id.month_plan:
                cycleType = "月计划";
                aimFlag = 3;
                break;
            case R.id.quarter_plan:
                cycleType = "季计划";
                aimFlag = 4;
                break;
            case R.id.year_plan:
                cycleType = "年计划";
                aimFlag = 5;
                break;

        }
        intent.putExtra("AimFlag", aimFlag);
        intent.putExtra("CycleType", cycleType);
        setResult(1007, intent);
        finish();
    }
}
