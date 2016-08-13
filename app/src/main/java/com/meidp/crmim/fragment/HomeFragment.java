package com.meidp.crmim.fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.activity.AnnouncementActivity;
import com.meidp.crmim.activity.ApprovalProcessActivity;
import com.meidp.crmim.activity.ApprovalTypeActivity;
import com.meidp.crmim.activity.ConventionApplyForActivity;
import com.meidp.crmim.activity.CostManagerActivity;
import com.meidp.crmim.activity.CustomerListActivity;
import com.meidp.crmim.activity.EnterpriseCultureActivity;
import com.meidp.crmim.activity.ExhibitionManagerActivity;
import com.meidp.crmim.activity.ImportantActivity;
import com.meidp.crmim.activity.LifeNavigationActivity;
import com.meidp.crmim.activity.ModelMachineApplyActivity;
import com.meidp.crmim.activity.MyCreditActivity;
import com.meidp.crmim.activity.MyPerformanceActivity;
import com.meidp.crmim.activity.MyPrototypeActivity;
import com.meidp.crmim.activity.MyValuesActivity;
import com.meidp.crmim.activity.NewGroupActivity;
import com.meidp.crmim.activity.NewsActivity;
import com.meidp.crmim.activity.OpenSeaPoolActivity;
import com.meidp.crmim.activity.ProjectManagerActivity;
import com.meidp.crmim.activity.SubmitActivity;
import com.meidp.crmim.activity.VisitingClientsActivity;
import com.meidp.crmim.activity.WorkPlanActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.back_arrows)
    private ImageView backImg;

    @ViewInject(R.id.right_img)
    private ImageView rightImg;
    private PopupWindow mPopupWindow;

    @ViewInject(R.id.linear_layout)
    private LinearLayout linearLayout;



    public HomeFragment() {
    }

    @Override
    public void onInit() {
        backImg.setVisibility(View.GONE);
        title.setText("工作");
//        mGridView.setFocusable(false);
        linearLayout.setFocusable(true);
        linearLayout.setFocusableInTouchMode(true);
        linearLayout.requestFocus();
        initPopupWindow();
    }

    @Override
    public void onInitData() {

    }

    @Event({R.id.open_sea, R.id.exhibition_manager, R.id.approval_manager, R.id.apply_model, R.id.submit_project, R.id.new_group, R.id.visiting_customer, R.id.project_manager, R.id.cost_manager, R.id.prototype_manager, R.id.my_performance, R.id.my_values, R.id.my_integrity, R.id.life_navigation, R.id.improtment_thing, R.id.knowledge, R.id.right_img, R.id.visit_client})
    private void onclick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.visiting_customer://客户拜访
                intent = new Intent(getActivity(), CustomerListActivity.class);
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
            case R.id.approval_manager://审批管理
                intent = new Intent(getActivity(), ApprovalTypeActivity.class);
                startActivity(intent);
                break;
            case R.id.my_performance://业绩表现
                intent = new Intent(getActivity(), MyPerformanceActivity.class);
                startActivity(intent);
                break;
            case R.id.my_values://我的价值
                intent = new Intent(getActivity(), MyValuesActivity.class);
                startActivity(intent);
                break;
            case R.id.my_integrity://我的诚信度
                intent = new Intent(getActivity(), MyCreditActivity.class);
                startActivity(intent);
                break;
            case R.id.life_navigation://人生导航
                intent = new Intent(getActivity(), LifeNavigationActivity.class);
                startActivity(intent);
                break;
            case R.id.improtment_thing://重要事项
                intent = new Intent(getActivity(), ImportantActivity.class);
                startActivity(intent);
                break;
            case R.id.knowledge://专业知识
//                intent = new Intent(getActivity(), MyKnowledgeActivity.class);
//                startActivity(intent);
                intent = new Intent();
                intent.setClass(getActivity(), NewsActivity.class);
                intent.putExtra("sType", 7);
                intent.putExtra("title", "专业知识");
                startActivity(intent);
                break;
            case R.id.right_img:
                if (!mPopupWindow.isShowing()) {
                    showPopupWindow();
                } else {
                    mPopupWindow.dismiss();
                }
                break;
            case R.id.visit_client://客户拜访
                intent = new Intent(getActivity(), VisitingClientsActivity.class);
                startActivity(intent);
                mPopupWindow.dismiss();
                break;
            case R.id.new_group://新建群组
                intent = new Intent(getActivity(), NewGroupActivity.class);
                startActivity(intent);
                mPopupWindow.dismiss();
                break;
            case R.id.submit_project://申报项目
                intent = new Intent(getActivity(), SubmitActivity.class);
                startActivity(intent);
                mPopupWindow.dismiss();
                break;
            case R.id.apply_model:
                intent = new Intent(getActivity(), ModelMachineApplyActivity.class);
                startActivity(intent);
                mPopupWindow.dismiss();
                break;
            case R.id.exhibition_manager://展会管理
                intent = new Intent(getActivity(), ExhibitionManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.open_sea://公海池
                intent = new Intent(getActivity(), OpenSeaPoolActivity.class);
                startActivity(intent);
                break;
        }
    }


    @ViewInject(R.id.visit_client)
    private TextView visitClient;

    private void showPopupWindow() {
//        mPopupWindow.showAsDropDown(titlebar );
        mPopupWindow.showAsDropDown(rightImg, 0, 0);
    }

    private void initPopupWindow() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_list_layout, null);
        x.view().inject(this, contentView);
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
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
