package com.meidp.crmim.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.Performances;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.widget.BudgetPieChart;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

/**
 * 我的业绩
 */
@ContentView(R.layout.activity_my_performance)
public class MyPerformanceActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;
//
//    @ViewInject(R.id.chart_layout)
//    private LinearLayout view;

    private String[] titles;

    private double[] values;

    @ViewInject(R.id.project_count)
    private TextView projectCount;
    @ViewInject(R.id.complete_project)
    private TextView completeProjectCount;
    @ViewInject(R.id.total_money)
    private TextView totalMoney;
    @ViewInject(R.id.projectGathering)
    private TextView projectGathering;
    @ViewInject(R.id.projectReimburse)
    private TextView projectReimburse;
    @ViewInject(R.id.start_end_date)
    private TextView startEndDate;

    @Override
    public void onInit() {
        title.setText("我的业绩");
//        titles = new String[]{"已收到金额", "未收到金额"};
//        values = new double[]{5588, 29994};
//        BudgetPieChart intent = new BudgetPieChart();
//        View viewChart = intent.createView(this, titles, values);
//        view.addView(viewChart);
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();

        HttpRequestUtils.getmInstance().send(MyPerformanceActivity.this, Constant.PERFORMANCE_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBean<Performances> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<Performances>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    bkndView(appBean);
                }
            }
        });
    }

    private void bkndView(AppBean<Performances> appBean) {
        projectCount.setText("申报项目数：" + appBean.getData().getPrjectTotalCount() + "个");
        completeProjectCount.setText("已结项目数：" + appBean.getData().getFinishProejct() + "个");
        totalMoney.setText("项目总费用：" + appBean.getData().getProjectTotalMoney());
        projectGathering.setText("项目已收款：" + appBean.getData().getProjectGathering());
        projectReimburse.setText("项目已报销：" + appBean.getData().getProjectReimburse());
        startEndDate.setText("项目起止时间：" + appBean.getData().getStartDate() + "~" + appBean.getData().getEndDate());
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
