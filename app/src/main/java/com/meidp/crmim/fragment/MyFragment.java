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
import com.meidp.crmim.activity.MyCreditActivity;
import com.meidp.crmim.activity.MyPerformanceActivity;
import com.meidp.crmim.activity.MyPropertyActivity;
import com.meidp.crmim.activity.PersonCentorActivity;
import com.meidp.crmim.activity.ProjectManagerActivity;
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

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.back_arrows)
    private ImageView backImg;
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
        backImg.setVisibility(View.GONE);
        title.setText("个人信息");

        String phone = (String) SPUtils.get(getActivity(), "PHONE", "");
        phoneNum.setText("电话：" + phone);
    }

    @Event(value = {R.id.person_center, R.id.my_projec, R.id.my_credit, R.id.my_cost, R.id.my_property, R.id.my_achievements, R.id.logout, R.id.feedback_layout})
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
            case R.id.my_performance:
                intent.setClass(getActivity(), MyPerformanceActivity.class);
                startActivity(intent);
                break;
            case R.id.my_credit:
                intent.setClass(getActivity(), MyCreditActivity.class);
                startActivity(intent);
                break;
            case R.id.my_cost:
                intent.setClass(getActivity(), CostManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.my_achievements:
                intent.setClass(getActivity(), MyAchievementsActivity.class);
                startActivity(intent);
                break;
            case R.id.my_property://
                intent.setClass(getActivity(), MyPropertyActivity.class);
                startActivity(intent);
                break;
            case R.id.logout://退出登录
                SPUtils.setLoginTag(getActivity(), false);
                intent.setClass(getActivity(), LoginActivity.class);
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.finish();
                    MainActivity.mainActivity = null;
                }
                startActivity(intent);
                break;
            case R.id.feedback_layout:
                intent.setClass(getActivity(), FeedbackActivity.class);
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
        String employeeName = (String) SPUtils.get(getActivity(), "EmployeeName", "");
        userName.setText(employeeName);
        String deptName = (String) SPUtils.get(getActivity(), "DeptName", "");
        jobTitle.setText(deptName);

    }
}
