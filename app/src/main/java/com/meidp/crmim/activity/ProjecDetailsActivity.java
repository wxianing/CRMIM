package com.meidp.crmim.activity;

import android.content.Intent;
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

import java.util.HashMap;

/**
 * 项目详情
 */

@ContentView(R.layout.activity_projec_details)
public class ProjecDetailsActivity extends BaseActivity {
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

    private String url;

    @Override
    public void onInit() {
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
                    totalMoney.setText("项目金额（￥）: " + appBean.getData().getInvestment());
                    registerDate.setText("项目登记时间: " + appBean.getData().getCreateDate());

                    double rate = appBean.getData().getSuccessRate() * 100;//成功率

                    successRate.setText("项目成功率：" + rate + "%");
                    if (NullUtils.isNull(appBean.getData().getRemark())) {
                        remarkTv.setText("备注:" + appBean.getData().getRemark());
                    } else {
                        remarkTv.setText("备注:（无）");
                    }
                }
            }
        });
    }

    @Event(value = {R.id.back_arrows, R.id.button, R.id.follow_btn})
    private void onClick(View v) {
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
                            ToastUtils.shows(ProjecDetailsActivity.this, "操作失败");
                        }
                    }
                });
                break;
            case R.id.follow_btn:
                Intent intent = new Intent();
                intent.setClass(this, FollowProjectActivity.class);
                intent.putExtra("OID", oid);
                startActivity(intent);
                break;
        }
    }
}
