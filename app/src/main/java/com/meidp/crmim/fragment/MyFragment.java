package com.meidp.crmim.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.activity.CostManagerActivity;
import com.meidp.crmim.activity.FeedbackActivity;
import com.meidp.crmim.activity.LoginActivity;
import com.meidp.crmim.activity.MainActivity;
import com.meidp.crmim.activity.MyAchievementsActivity;
import com.meidp.crmim.activity.MyAssessActivity;
import com.meidp.crmim.activity.MyCreditActivity;
import com.meidp.crmim.activity.MyKnowledgeActivity;
import com.meidp.crmim.activity.MyPerformanceActivity;
import com.meidp.crmim.activity.MyPropertyActivity;
import com.meidp.crmim.activity.MyPrototypeActivity;
import com.meidp.crmim.activity.PersonCentorActivity;
import com.meidp.crmim.activity.ProjectManagerActivity;
import com.meidp.crmim.activity.VisitRecordActivity;
import com.meidp.crmim.utils.ImageUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_my)
public class MyFragment extends BaseFragment {

    @ViewInject(R.id.header_img)
    private ImageView header;
    @ViewInject(R.id.username)
    private TextView userName;
    @ViewInject(R.id.job_title)
    private TextView jobTitle;
    @ViewInject(R.id.phone_num)
    private TextView phoneNum;

    public MyFragment() {
    }

    @Override
    public void onInit() {
        String phone = (String) SPUtils.get(getActivity(), "PHONE", "");
        phoneNum.setText("电话：" + phone);
    }

    @Event(value = {R.id.person_center, R.id.my_performance, R.id.my_assess, R.id.my_projec, R.id.my_credit, R.id.logout, R.id.feedback_layout,  R.id.visit_record})
    private void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.person_center://个人中心
                intent.setClass(getActivity(), PersonCentorActivity.class);
                startActivity(intent);
                break;
            case R.id.my_projec://我的项目
                intent.setClass(getActivity(), ProjectManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.my_performance://我的业绩
                intent.setClass(getActivity(), MyPerformanceActivity.class);
                startActivity(intent);
                break;
            case R.id.my_credit://我的信用
                intent.setClass(getActivity(), MyCreditActivity.class);
                startActivity(intent);
                break;
//            case R.id.my_cost://我的费用
//                intent.setClass(getActivity(), CostManagerActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.my_achievements:
//                intent.setClass(getActivity(), MyAchievementsActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.my_property://我的财产
//                intent.setClass(getActivity(), MyPrototypeActivity.class);
//                startActivity(intent);
//                break;
            case R.id.logout://退出登录
                SPUtils.setLoginTag(getActivity(), false);
                intent.setClass(getActivity(), LoginActivity.class);
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.finish();
                    MainActivity.mainActivity = null;
                }
                startActivity(intent);
                break;
            case R.id.feedback_layout://意见反馈
                intent.setClass(getActivity(), FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.my_knowledge://
                intent.setClass(getActivity(), MyKnowledgeActivity.class);
                startActivity(intent);
                break;
            case R.id.my_assess:
                intent.setClass(getActivity(), MyAssessActivity.class);
                startActivity(intent);
                break;
            case R.id.visit_record:
                intent.setClass(getActivity(), VisitRecordActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        String headStr = (String) SPUtils.get(getActivity(), "headPortrait", "");
        if (NullUtils.isNull(headStr)) {
            Bitmap headerBitmap = ImageUtils.stringtoBitmap(headStr);
            header.setImageBitmap(headerBitmap);
        }
        String niceName = (String) SPUtils.get(getActivity(), "EmployeeName", "");
        userName.setText(niceName);
        String deptName = (String) SPUtils.get(getActivity(), "DeptName", "");
        jobTitle.setText("部门：" + deptName);
    }
}
