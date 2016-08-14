package com.meidp.crmim.activity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.ExhibitionDetails;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

@ContentView(R.layout.activity_exhibition_details)
public class ExhibitionDetailsActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;
    private int oid;

    @ViewInject(R.id.title_name)
    private TextView title_name;
    @ViewInject(R.id.link_man)
    private TextView link_man;
    @ViewInject(R.id.link_phone)
    private TextView link_phone;
    @ViewInject(R.id.start_date)
    private TextView start_date;
    @ViewInject(R.id.over_date)
    private TextView over_date;
    @ViewInject(R.id.count_tv)
    private TextView count_tv;
    @ViewInject(R.id.total_money)
    private TextView total_money;
    @ViewInject(R.id.aim_tv)
    private TextView aim_tv;
    @ViewInject(R.id.plan_tv)
    private TextView plan_tv;
    @ViewInject(R.id.partner)
    private TextView partner;
    @ViewInject(R.id.competitor)
    private TextView competitor;

    @Override
    public void onInit() {
        title.setText("展会详情");
        oid = getIntent().getIntExtra("OID", 0);
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("Id", oid);
        HttpRequestUtils.getmInstance().send(ExhibitionDetailsActivity.this, Constant.CONVERSATION_DETAILS_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("展会详情", result);
                AppBean<ExhibitionDetails> appDatas = JSONObject.parseObject(result, new TypeReference<AppBean<ExhibitionDetails>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
//                    Log.e("app", appDatas.getData().getTitle());/
//
//                    bindView(appBean);
                    title_name.setText("标题：" + appDatas.getData().getTitle());
                    link_man.setText("联系人：" + appDatas.getData().getLinkManName());
                    link_phone.setText("联系电话：" + appDatas.getData().getLinkTel());
                    start_date.setText("开始时间：" + appDatas.getData().getExhibitionStartDate());
                    over_date.setText("结束时间：" + appDatas.getData().getExhibitionEndDate());
                    count_tv.setText("人数规模：" + appDatas.getData().getAttendPersons());
                    total_money.setText("￥" + appDatas.getData().getPlanFee() + "万元");
                    aim_tv.setText("展会目的：" + appDatas.getData().getExhibitionAim());
                    plan_tv.setText("展会计划：" + appDatas.getData().getExhibitionPlan());
                    partner.setText("合作伙伴：" + appDatas.getData().getUnionPartner());
                    competitor.setText("竞争对手：" + appDatas.getData().getCompetitors());

                }
            }
        });
    }

    private void bindView(AppBean<ExhibitionDetails> appDatas) {

    }


    @Event({R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
