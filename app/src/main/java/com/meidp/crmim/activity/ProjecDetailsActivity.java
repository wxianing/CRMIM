package com.meidp.crmim.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.ProjectDetails;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.meidp.crmim.R.color.textcolor_gray;

/**
 * 项目详情
 */

@ContentView(R.layout.activity_projec_details)
public class ProjecDetailsActivity extends BaseActivity {
    @ViewInject(R.id.title_right)
    private TextView titleRight;
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.project_name)
    private TextView projectName;
    @ViewInject(R.id.project_num)
    private TextView projectNum;
    @ViewInject(R.id.project_linkname)
    private TextView projectLinkname;
    @ViewInject(R.id.linkman_phone)
    private TextView linkmanPhone;
    private int oid;
    private int type = -1;
    @ViewInject(R.id.button)
    private Button button;
    @ViewInject(R.id.follow_btn)
    private Button followBtn;
    @ViewInject(R.id.total_money)
    private TextView totalMoney;
    @ViewInject(R.id.register_date)
    private TextView registerDate;
    @ViewInject(R.id.remark_tv)
    private TextView remarkTv;
    @ViewInject(R.id.success_rate)
    private TextView successRate;

    @ViewInject(R.id.follow_status)
    private TextView followStatus;

    private ArrayList<ProjectDetails.ConstructionDetailsBean> mDatas;

    private String url;

    @Override
    public void onInit() {
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setText("样机申请");
        title.setText("项目详情");
        oid = getIntent().getIntExtra("OID", -1);
        type = getIntent().getIntExtra("TYPE", -1);
        if (type == 1) {
            button.setText("我来跟进");
            followBtn.setVisibility(View.GONE);
            url = Constant.FOLLOW_OPENPOOL_PROJECT;
        }
        if (type == 0) {
            button.setText("丢进公海池");
            url = Constant.PUT_TO_OPENPOOL;
        }
        if (type == -1) {
            followBtn.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
            titleRight.setVisibility(View.GONE);
        }
        mDatas = new ArrayList<>();
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("Id", oid);
        HttpRequestUtils.getmInstance().send(ProjecDetailsActivity.this, Constant.GET_PROJECT_DETAILS, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                AppBean<ProjectDetails> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<ProjectDetails>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    projectName.setText("项目名称：" + appBean.getData().getProjectName());
                    projectNum.setText("项目编号：" + appBean.getData().getProjectNo());
                    projectLinkname.setText("联系人：" + appBean.getData().getCustLinkMan());
                    linkmanPhone.setText("电话号码：" + appBean.getData().getLinkTel());
                    totalMoney.setText("" + appBean.getData().getInvestment());
                    registerDate.setText("项目登记时间: " + appBean.getData().getCreateDate());
                    double rate = appBean.getData().getSuccessRate() * 100;//成功率
                    successRate.setText(rate + "%");
                    if (NullUtils.isNull(appBean.getData().getRemark())) {
                        remarkTv.setText("备注:" + appBean.getData().getRemark());
                    } else {
                        remarkTv.setText("备注:（无）");
                    }
                    mDatas.addAll(appBean.getData().getConstructionDetails());
                    if (mDatas != null && !mDatas.isEmpty()) {
                        followStatus.setText("最新跟进状态");
                        followStatus.setTextColor(Color.rgb(255, 163, 0));
                    }
                }
            }
        });
    }

    @Event(value = {R.id.back_arrows, R.id.button, R.id.follow_btn, R.id.follow_layout, R.id.title_right,R.id.button})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.button:
                HashMap params = new HashMap();
                params.put("Id", oid);
                HttpRequestUtils.getmInstance().send(ProjecDetailsActivity.this, url, params, new HttpRequestCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                        });
                        if (appMsg != null && appMsg.getEnumcode() == 0) {
                            if (type == 1) {
                                ToastUtils.shows(ProjecDetailsActivity.this, "跟进成功");
                                finish();
                            }
                            if (type == 0) {
                                ToastUtils.shows(ProjecDetailsActivity.this, "放入公海池成功");
                                finish();
                            }
                        } else {
                            ToastUtils.shows(ProjecDetailsActivity.this, appMsg.getMsg());
                        }
                    }
                });
                break;
            case R.id.follow_btn:
                intent = new Intent();
                intent.setClass(this, FollowProjectActivity.class);
                intent.putExtra("OID", oid);
                startActivity(intent);
                break;
            case R.id.follow_layout:
                if (mDatas != null && !mDatas.isEmpty()) {
                    intent = new Intent(this, FollowListActivity.class);
                    intent.putExtra("mDatas", (Serializable) mDatas);
                    startActivity(intent);
                }
                break;
            case R.id.title_right:
                intent = new Intent(ProjecDetailsActivity.this, ModelMachineApplyActivity.class);
                startActivity(intent);
                break;
        }
    }
}
