package com.meidp.crmim.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.meidp.crmim.MyApplication;
import com.meidp.crmim.R;
import com.meidp.crmim.activity.AboutActivity;
import com.meidp.crmim.activity.FeedbackActivity;
import com.meidp.crmim.activity.LoginActivity;
import com.meidp.crmim.activity.MainActivity;
import com.meidp.crmim.activity.ModelMachineApplyActivity;
import com.meidp.crmim.activity.MyTeamActivity;
import com.meidp.crmim.activity.NewGroupActivity;
import com.meidp.crmim.activity.PersonCentorActivity;
import com.meidp.crmim.activity.ResetPwdActivity;
import com.meidp.crmim.activity.SigninMainActivity;
import com.meidp.crmim.activity.SubmitActivity;
import com.meidp.crmim.utils.ImageUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.view.CustomDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

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
    private TextView phoneNum;//手机号码
    @ViewInject(R.id.header_img)
    private ImageView headerImg;//头像

    @ViewInject(R.id.right_img)
    private ImageView rightImg;
    private PopupWindow mPopupWindow;
    @ViewInject(R.id.department)
    private TextView department;//部门
    @ViewInject(R.id.zhiwu)
    private TextView zhiwu;//职务

    public MyFragment() {
    }

    @Override
    public void onInit() {
        title.setText("个人中心");
        rightImg.setImageResource(R.mipmap.more_icon);
        backImg.setVisibility(View.INVISIBLE);
        initPopupWindow();
    }

    @Event(value = {R.id.person_center, R.id.logout, R.id.my_group, R.id.about_layout, R.id.reset_password, R.id.header_img, R.id.feedbook, R.id.visit_client, R.id.new_group, R.id.submit_project, R.id.apply_model, R.id.right_img})
    private void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.person_center://个人中心
                intent.setClass(getActivity(), PersonCentorActivity.class);
                startActivity(intent);
                break;
            case R.id.logout://退出登录
                logoutAlertDialog();
                break;
            case R.id.my_group:
//                ToastUtils.shows(getActivity(), "正在开发");
                intent.setClass(getActivity(), MyTeamActivity.class);
                startActivity(intent);
                break;
            case R.id.about_layout:
                intent.setClass(getActivity(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.reset_password:
//                showAlertDialog();
                intent.setClass(getActivity(), ResetPwdActivity.class);
                startActivity(intent);
                break;
            case R.id.header_img:
                intent.setClass(getActivity(), PersonCentorActivity.class);
                startActivity(intent);
                break;
            case R.id.feedbook:
                intent.setClass(getActivity(), FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.visit_client://客户拜访
                intent = new Intent(getActivity(), SigninMainActivity.class);
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
            case R.id.right_img:
                if (!mPopupWindow.isShowing()) {
                    showPopupWindow();
                } else {
                    mPopupWindow.dismiss();
                }
                break;
        }
    }

    public void showAlertDialog() {

        CustomDialog.Builder builder = new CustomDialog.Builder(getActivity());
        builder.setMessage("请联系系统管理员修改密码");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    public void logoutAlertDialog() {

        CustomDialog.Builder builder = new CustomDialog.Builder(getActivity());
        builder.setMessage("是否退出当前登录账号？");
        builder.setTitle("温馨提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.finish();
                    MainActivity.mainActivity = null;
                }
                SPUtils.remove(getActivity(), "CODE");
//                        SPUtils.clear(getActivity());
                startActivity(intent);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
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
        String zhiwuString = (String) SPUtils.get(getActivity(), "ZHIWU", "");
        if (NullUtils.isNull(zhiwuString)) {
            zhiwu.setText("(" + zhiwuString + ")");
        }
        userName.setText(niceName);
        String deptName = (String) SPUtils.get(getActivity(), "DeptName", "");
//        jobTitle.setText("部门：" + deptName);
        department.setText("部门：" + deptName);
        String headerPhoto = (String) SPUtils.get(getActivity(), "PhotoURL", "");
        ImageLoader.getInstance().displayImage(headerPhoto, headerImg, MyApplication.optionsRounds);
        String phone = (String) SPUtils.get(getActivity(), "Mobile", "");
        phoneNum.setText("电话：" + phone);
    }

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
}
