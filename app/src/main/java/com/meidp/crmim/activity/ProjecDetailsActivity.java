package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.ProgressAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.ProjectDetails;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;
import com.meidp.crmim.view.ListViewForScrollView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    @ViewInject(R.id.curr_status)
    private TextView currStatus;
    @ViewInject(R.id.related_personnel)
    private TextView relatedPersonnel;
    @ViewInject(R.id.follow_layout)
    private RelativeLayout follow_layout;
    @ViewInject(R.id.company_name)
    private TextView companyName;
    @ViewInject(R.id.department_name)
    private TextView departmentName;
    @ViewInject(R.id.positions_name)
    private TextView positionsName;
    @ViewInject(R.id.process_listview)
    private ListViewForScrollView processListview;
    private List<ProjectDetails.ProcessListBean> processListBeanList;

    private ProgressAdapter progressAdapter;

    @ViewInject(R.id.listview)
    private ListView mListView;
    private FollowAdapter mAdapter;
    @ViewInject(R.id.scrollView)
    private ScrollView scrollView;

    private ArrayList<ProjectDetails.ConstructionDetailsBean> mDatas;

    private String url;
    private String projectNames;

    @Override
    public void onInit() {
        scrollView.smoothScrollTo(0, 20);
        processListBeanList = new ArrayList<>();
        progressAdapter = new ProgressAdapter(processListBeanList, this);
        processListview.setAdapter(progressAdapter);

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
        mAdapter = new FollowAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
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
                    if (NullUtils.isNull(appBean.getData().getProjectName())) {
                        projectName.setText("项目名称：" + appBean.getData().getProjectName());
                        projectNames = appBean.getData().getProjectName();
                    } else {
                        projectName.setText("项目名称：");
                    }
                    if (NullUtils.isNull(appBean.getData().getProjectNo())) {
                        projectNum.setText("项目编号：" + appBean.getData().getProjectNo());
                    } else {
                        projectNum.setText("项目编号：");
                    }
                    if (NullUtils.isNull(appBean.getData().getCustLinkMan()) && !appBean.getData().getCustLinkMan().equals("待定")) {
                        projectLinkname.setText("联系人：" + appBean.getData().getCustLinkMan());
                    } else {
                        projectLinkname.setText("联系人：");
                    }
                    if (NullUtils.isNull(appBean.getData().getLinkTel())) {
                        linkmanPhone.setText("电话号码：" + appBean.getData().getLinkTel());
                    } else {
                        linkmanPhone.setText("电话号码：");
                    }
                    totalMoney.setText("￥" + appBean.getData().getInvestment());

                    String timeStr = appBean.getData().getCreateDate();
                    if (NullUtils.isNull(timeStr)) {
                        timeStr = timeStr.substring(0, timeStr.length() - 3);
                        registerDate.setText("项目登记时间: " + timeStr);
                    } else {
                        registerDate.setText("项目登记时间: ");
                    }

                    double rate = appBean.getData().getSuccessRate() * 100;//成功率
                    successRate.setText(rate + "%");
                    if (NullUtils.isNull(appBean.getData().getCanViewUser())) {
                        relatedPersonnel.setText(appBean.getData().getCanViewUser());
                    }
                    if (NullUtils.isNull(appBean.getData().getRemark())) {
                        remarkTv.setText("备注:" + appBean.getData().getRemark());
                    } else {
                        remarkTv.setText("备注:（无）");
                    }

                    currStatus.setText(appBean.getData().getStatusName());
                    mDatas.addAll(appBean.getData().getConstructionDetails());
                    if (mDatas != null && !mDatas.isEmpty()) {
                        follow_layout.setVisibility(View.GONE);
                        mAdapter.notifyDataSetChanged();
                    }
                    //医院
                    if (NullUtils.isNull(appBean.getData().getCustName())) {
                        companyName.setText(appBean.getData().getCustName());
                    }
                    //部门
                    if (NullUtils.isNull(appBean.getData().getDepartmentName())) {
                        departmentName.setText(appBean.getData().getDepartmentName());
                    }
                    //职位
                    if (NullUtils.isNull(appBean.getData().getZhiWu())) {
                        positionsName.setText(appBean.getData().getZhiWu());
                    }
                    //状态列表
                    processListBeanList.addAll(appBean.getData().getProcessList());
                    progressAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Event(value = {R.id.recode_visit, R.id.back_arrows, R.id.button, R.id.follow_btn, R.id.follow_layout, R.id.title_right, R.id.button})
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
                finish();
//                intent = new Intent();
//                intent.setClass(this, FollowProjectActivity.class);
//                intent.putExtra("OID", oid);
//                startActivity(intent);
                break;
//            case R.id.follow_layout:
//                if (mDatas != null && !mDatas.isEmpty()) {
//                    intent = new Intent(this, FollowListActivity.class);
//                    intent.putExtra("mDatas", (Serializable) mDatas);
//                    startActivity(intent);
//                }
//                break;
            case R.id.title_right:
                intent = new Intent(ProjecDetailsActivity.this, ModelMachineApplyActivity.class);
                startActivity(intent);
                break;
            case R.id.recode_visit:
                intent = new Intent(ProjecDetailsActivity.this, VisitRecordActivity.class);
                intent.putExtra("KeyWord", projectNames);
                startActivity(intent);
                break;
        }
    }
}
