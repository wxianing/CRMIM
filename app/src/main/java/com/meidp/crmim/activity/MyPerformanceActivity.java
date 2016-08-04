package com.meidp.crmim.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.widget.BudgetPieChart;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 我的业绩
 */
@ContentView(R.layout.activity_my_performance)
public class MyPerformanceActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.chart_layout)
    private LinearLayout view;

    private String[] titles;

    private double[] values;

    @Override
    public void onInit() {
        title.setText("我的业绩");
        titles = new String[]{"已收到金额", "未收到金额"};
        values = new double[]{5588, 29994};

        BudgetPieChart intent = new BudgetPieChart();
        View viewChart = intent.createView(this, titles, values);
        view.addView(viewChart);
    }

    @Override
    public void onInitData() {

    }

    @Event(value = {R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
