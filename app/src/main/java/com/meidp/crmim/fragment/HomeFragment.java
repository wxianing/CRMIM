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
import com.meidp.crmim.R;
import com.meidp.crmim.activity.AnnouncementActivity;
import com.meidp.crmim.activity.ApprovalProcessActivity;
import com.meidp.crmim.activity.ConventionApplyForActivity;
import com.meidp.crmim.activity.CostManagerActivity;
import com.meidp.crmim.activity.CustomerListActivity;
import com.meidp.crmim.activity.CustomerVisitActivity;
import com.meidp.crmim.activity.EnterpriseCultureActivity;
import com.meidp.crmim.activity.MyAchievementsActivity;
import com.meidp.crmim.activity.MyPerformanceActivity;
import com.meidp.crmim.activity.MyPrototypeActivity;
import com.meidp.crmim.activity.OpenSeaPoolActivity;
import com.meidp.crmim.activity.ProjectManagerActivity;
import com.meidp.crmim.activity.SubmitActivity;
import com.meidp.crmim.activity.VisitingClientsActivity;
import com.meidp.crmim.activity.WorkPlanActivity;
import com.meidp.crmim.adapter.HomeGvAdapter;
import com.meidp.crmim.adapter.ImagePagerAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBeans;
import com.meidp.crmim.model.Banner;
import com.meidp.crmim.model.HomeEntrity;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.widget.AutoScrollViewPager;

import org.xutils.view.annotation.ContentView;
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
    /**
     * 头部广告
     */
    @ViewInject(R.id.home_banner_viewpager)
    protected AutoScrollViewPager mViewPager;
    @ViewInject(R.id.home_dot_ll)
    protected LinearLayout dotLL;
    private List<Banner> imageUrls;
    private ImagePagerAdapter pagerAdapter;

    private List<HomeEntrity> entrities;

    @ViewInject(R.id.grid_view)
    private GridView mGridView;
    private HomeGvAdapter mGirdViewAdapter;

    public HomeFragment() {
    }

    @Override
    public void onInit() {
        backImg.setVisibility(View.GONE);
        title.setText(R.string.title_name);
        mGridView.setFocusable(false);
        imageUrls = new ArrayList<>();
        entrities = new ArrayList<>();
        entrities.add(new HomeEntrity(R.mipmap.home_announcement_icon, "公告"));
        entrities.add(new HomeEntrity(R.mipmap.home_opensea_icon, "公海池"));
        entrities.add(new HomeEntrity(R.mipmap.home_project_manager_icon, "项目管理"));
//        entrities.add(new HomeEntrity(R.mipmap.home_follow_project_icon, "项目跟进"));
        entrities.add(new HomeEntrity(R.mipmap.home_customer_visit_icon, "客户拜访"));
        entrities.add(new HomeEntrity(R.mipmap.home_expense_reimbursement_icon, "费用报销"));
        entrities.add(new HomeEntrity(R.mipmap.home_new_project_icon, "新建项目"));
        entrities.add(new HomeEntrity(R.mipmap.home_customer_archives_icon, "客户档案"));
        entrities.add(new HomeEntrity(R.mipmap.home_my_prototype_icon, "我的样机"));
        entrities.add(new HomeEntrity(R.mipmap.home_my_performance_icon, "我的业绩"));
        entrities.add(new HomeEntrity(R.mipmap.work_plan, "工作计划"));
        entrities.add(new HomeEntrity(R.mipmap.work_plan, "企业文化"));
        entrities.add(new HomeEntrity(R.mipmap.work_plan, "参展申请"));
        entrities.add(new HomeEntrity(R.mipmap.work_plan, "审批"));
        mGirdViewAdapter = new HomeGvAdapter(entrities, getActivity());
        mGridView.setAdapter(mGirdViewAdapter);
        mGridView.setOnItemClickListener(this);
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        HttpRequestUtils.getmInstance().send(getActivity(), Constant.BANNER_URL, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("HomeFragment", result);
                AppBeans<Banner> appBean = JSONObject.parseObject(result, new TypeReference<AppBeans<Banner>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    imageUrls.addAll(appBean.getData());
                    pagerAdapter = new ImagePagerAdapter(getActivity(), imageUrls, dotLL);
                    mViewPager.setAdapter(pagerAdapter);
                    mViewPager.setOnPageChangeListener(pagerAdapter);
                    pagerAdapter.refreshData(true);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewPager.startAutoScroll();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewPager.stopAutoScroll();
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
