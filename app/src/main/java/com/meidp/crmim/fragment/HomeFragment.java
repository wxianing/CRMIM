package com.meidp.crmim.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jauker.widget.BadgeView;
import com.meidp.crmim.R;
import com.meidp.crmim.activity.AnnouncementActivity;
import com.meidp.crmim.activity.ApprovalProcessActivity;
import com.meidp.crmim.activity.ConventionApplyForActivity;
import com.meidp.crmim.activity.CostManagerActivity;
import com.meidp.crmim.activity.CustomerListActivity;
import com.meidp.crmim.activity.CustomerVisitActivity;
import com.meidp.crmim.activity.EnterpriseCultureActivity;
import com.meidp.crmim.activity.ImportantActivity;
import com.meidp.crmim.activity.LifeNavigationActivity;
import com.meidp.crmim.activity.MyAchievementsActivity;
import com.meidp.crmim.activity.MyCreditActivity;
import com.meidp.crmim.activity.MyKnowledgeActivity;
import com.meidp.crmim.activity.MyPerformanceActivity;
import com.meidp.crmim.activity.MyPrototypeActivity;
import com.meidp.crmim.activity.MyValuesActivity;
import com.meidp.crmim.activity.OpenSeaPoolActivity;
import com.meidp.crmim.activity.ProjectManagerActivity;
import com.meidp.crmim.activity.SubmitActivity;
import com.meidp.crmim.activity.VisitingClientsActivity;
import com.meidp.crmim.activity.WorkPlanActivity;
import com.meidp.crmim.adapter.HomeGvAdapter;
import com.meidp.crmim.adapter.ImagePagerAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.AppBeans;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.Banner;
import com.meidp.crmim.model.HomeEntrity;
import com.meidp.crmim.model.UnReaderMsg;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.widget.AutoScrollViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.back_arrows)
    private ImageView backImg;

    @ViewInject(R.id.linear_layout)
    private LinearLayout linearLayout;


    private int pubNoticeNewCount = 0;

    private int noCheckCount = 0;

    public HomeFragment() {
    }

    @Override
    public void onInit() {
        backImg.setVisibility(View.GONE);
        title.setText(R.string.title_name);
//        mGridView.setFocusable(false);
        linearLayout.setFocusable(true);
        linearLayout.setFocusableInTouchMode(true);
        linearLayout.requestFocus();
    }

    @Override
    public void onInitData() {

    }

    @Event({R.id.visiting_customer, R.id.project_manager, R.id.cost_manager, R.id.prototype_manager, R.id.my_project, R.id.my_performance, R.id.my_values, R.id.my_integrity, R.id.life_navigation, R.id.improtment_thing, R.id.knowledge})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.visiting_customer://客户拜访
                intent = new Intent(getActivity(), VisitingClientsActivity.class);
                startActivity(intent);
                break;
            case R.id.project_manager://项目管理
                intent = new Intent(getActivity(), ProjectManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.cost_manager://费用管理
                intent = new Intent(getActivity(), CostManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.prototype_manager://样机管理
                intent = new Intent(getActivity(), MyPrototypeActivity.class);
                startActivity(intent);
                break;
            case R.id.my_project:
                intent = new Intent(getActivity(), ProjectManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.my_performance:
                intent = new Intent(getActivity(), MyPerformanceActivity.class);
                startActivity(intent);
                break;
            case R.id.my_values:
                intent = new Intent(getActivity(), MyValuesActivity.class);
                startActivity(intent);
                break;
            case R.id.my_integrity:
                intent = new Intent(getActivity(), MyCreditActivity.class);
                startActivity(intent);
                break;
            case R.id.life_navigation:
                intent = new Intent(getActivity(), LifeNavigationActivity.class);
                startActivity(intent);
                break;
            case R.id.improtment_thing:
                intent = new Intent(getActivity(), ImportantActivity.class);
                startActivity(intent);
                break;
            case R.id.knowledge:
                intent = new Intent(getActivity(), MyKnowledgeActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        switch (position) {
            case 0://公告
                intent.setClass(getActivity(), AnnouncementActivity.class);
                startActivity(intent);
                break;
            case 1://公海池
                intent.setClass(getActivity(), OpenSeaPoolActivity.class);
                startActivity(intent);
                break;
            case 2://项目管理
                intent.setClass(getActivity(), ProjectManagerActivity.class);
                startActivity(intent);
                break;
//            case 3://项目跟进
//                intent.setClass(getActivity(), FollowProjectActivity.class);
//                startActivity(intent);
//                break;
            case 3://客户拜访
                intent.setClass(getActivity(), VisitingClientsActivity.class);
                startActivity(intent);
                break;
            case 4://费用报销
                intent.setClass(getActivity(), CostManagerActivity.class);
                startActivity(intent);
                break;
            case 5://新建项目
                intent.setClass(getActivity(), SubmitActivity.class);
                startActivity(intent);
                break;
            case 6://客户档案
                intent.setClass(getActivity(), CustomerListActivity.class);
                startActivity(intent);
                break;
            case 7://我的样机
                intent.setClass(getActivity(), MyPrototypeActivity.class);
                startActivity(intent);
                break;
            case 8://我的业绩
                intent.setClass(getActivity(), MyPerformanceActivity.class);
                startActivity(intent);
                break;
            case 9://工作计划
                intent.setClass(getActivity(), WorkPlanActivity.class);
                startActivity(intent);
                break;
            case 10://企业文化
                intent.setClass(getActivity(), EnterpriseCultureActivity.class);
                startActivity(intent);
                break;
            case 11://展会申请
                intent.setClass(getActivity(), ConventionApplyForActivity.class);
                startActivity(intent);
                break;
            case 12:
                intent.setClass(getActivity(), ApprovalProcessActivity.class);
                startActivity(intent);
                break;
        }
    }
}
