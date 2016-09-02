package com.meidp.crmim.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.Performances;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.view.InstrumentView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

@ContentView(R.layout.activity_my_credit)
public class MyCreditActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.iView)
    private InstrumentView iView;
    @ViewInject(R.id.txtView)
    private TextView txtView;


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
    @ViewInject(R.id.credit_rate)
    private TextView creditRate;

    @Override
    public void onInit() {
        title.setText("我的诚信度");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                iView.setReferValue(692, new InstrumentView.RotateListener() {
                    @Override
                    public void rotate(float sweepAngle, final float value) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtView.setText(Math.round(value) + "");
                            }
                        });
                    }
                });
            }
        }, 1000);
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        HttpRequestUtils.getmInstance().send(MyCreditActivity.this, Constant.PERFORMANCE_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {

                Log.e("result", result);
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

        if (appBean.getData().getPrjectTotalCount() != 0) {
            double rate = (double) appBean.getData().getFinishProejct() / (double) appBean.getData().getPrjectTotalCount() *100;
            creditRate.setText("我的诚信度：" +  Math.floor(rate) + "%");
        } else {
            creditRate.setText("我的诚信度：" + 0 + "%");
        }
    }

    @Event(value = {R.id.back_arrows, R.id.unscramble})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.unscramble:
                Intent intent = new Intent(this, CreditUnscrambleActivity.class);
                startActivity(intent);
                break;
        }
    }
}
