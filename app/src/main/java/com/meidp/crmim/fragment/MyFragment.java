package com.meidp.crmim.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meidp.crmim.MyApplication;
import com.meidp.crmim.R;
import com.meidp.crmim.activity.AboutActivity;
import com.meidp.crmim.activity.FeedbackActivity;
import com.meidp.crmim.activity.LoginActivity;
import com.meidp.crmim.activity.MainActivity;
import com.meidp.crmim.activity.MyTeamActivity;
import com.meidp.crmim.activity.PersonCentorActivity;
import com.meidp.crmim.utils.ImageUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.utils.ToastUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

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
    @ViewInject(R.id.header_img)
    private ImageView headerImg;

    public MyFragment() {
    }

    @Override
    public void onInit() {
        title.setText("个人中心");
        backImg.setVisibility(View.GONE);

    }

    @Event(value = {R.id.person_center, R.id.logout, R.id.my_group, R.id.about_layout, R.id.reset_password, R.id.header_img, R.id.feedbook})
    private void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.person_center://个人中心
                intent.setClass(getActivity(), PersonCentorActivity.class);
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
            case R.id.my_group:
                ToastUtils.shows(getActivity(), "正在开发");
                intent.setClass(getActivity(), MyTeamActivity.class);
                startActivity(intent);
                break;
            case R.id.about_layout:
                intent.setClass(getActivity(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.reset_password:
                ToastUtils.shows(getActivity(), "正在客户服务中心发送修改密码请求");
//                intent.setClass(getActivity(), ConventionApplyForActivity.class);
//                startActivity(intent);
                break;
            case R.id.header_img:
                intent.setClass(getActivity(), PersonCentorActivity.class);
                startActivity(intent);
                break;
            case R.id.feedbook:
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
        String niceName = (String) SPUtils.get(getActivity(), "EmployeeName", "");
        String zhiwu = (String) SPUtils.get(getActivity(), "ZHIWU", "");
        userName.setText(niceName + "(" + zhiwu + ")");
        String deptName = (String) SPUtils.get(getActivity(), "DeptName", "");
        jobTitle.setText("部门：" + deptName);
        String headerPhoto = (String) SPUtils.get(getActivity(), "PhotoURL", "");
        ImageLoader.getInstance().displayImage(headerPhoto, headerImg, MyApplication.optionsRounds);
        String phone = (String) SPUtils.get(getActivity(), "PHONE", "");
        phoneNum.setText("电话：" + phone);
    }
}
