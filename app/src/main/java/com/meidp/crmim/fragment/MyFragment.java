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
import com.meidp.crmim.activity.ConventionApplyForActivity;
import com.meidp.crmim.activity.GroupActivity;
import com.meidp.crmim.activity.LoginActivity;
import com.meidp.crmim.activity.MainActivity;
import com.meidp.crmim.activity.PersonCentorActivity;
import com.meidp.crmim.utils.ImageUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.utils.ToastUtils;
import com.meidp.crmim.view.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

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
    @ViewInject(R.id.header_img)
    private CircleImageView headerImg;

    public MyFragment() {
    }

    @Override
    public void onInit() {
        String phone = (String) SPUtils.get(getActivity(), "PHONE", "");
        phoneNum.setText("电话：" + phone);

    }

    @Event(value = {R.id.person_center, R.id.logout, R.id.my_group, R.id.about_layout, R.id.reset_password, R.id.header_img})
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
//                intent.setClass(getActivity(), GroupActivity.class);
//                startActivity(intent);
                break;
            case R.id.about_layout:
                intent.setClass(getActivity(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.reset_password:
                ToastUtils.shows(getActivity(), "正在客户服务中心发送修改密码请求");
                intent.setClass(getActivity(), ConventionApplyForActivity.class);
                startActivity(intent);
                break;
            case R.id.header_img:
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
        String headerPhoto = (String) SPUtils.get(getActivity(), "PhotoURL", "");
        ImageLoader.getInstance().displayImage(headerPhoto, headerImg, MyApplication.options);
    }
}
