package com.meidp.crmim.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jauker.widget.BadgeView;
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
import com.meidp.crmim.activity.LoginActivity;
import com.meidp.crmim.activity.MainActivity;
import com.meidp.crmim.activity.ModelMachineApplyActivity;
import com.meidp.crmim.activity.MyCostingActivity;
import com.meidp.crmim.activity.MyCreditActivity;
import com.meidp.crmim.activity.MyCrowdActivity;
import com.meidp.crmim.activity.MyMakeBargainActivity;
import com.meidp.crmim.activity.MyPerformanceActivity;
import com.meidp.crmim.activity.MyPrototypeActivity;
import com.meidp.crmim.activity.MyValuesActivity;
import com.meidp.crmim.activity.NewGroupActivity;
import com.meidp.crmim.activity.NewsActivity;
import com.meidp.crmim.activity.OpenSeaPoolActivity;
import com.meidp.crmim.activity.ProjectManagerActivity;
import com.meidp.crmim.activity.SigninMainActivity;
import com.meidp.crmim.activity.SubmitActivity;
import com.meidp.crmim.activity.VisitingClientsActivity;
import com.meidp.crmim.activity.WebViewActivity;
import com.meidp.crmim.activity.WorkPlanActivity;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.NoReaders;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.IMkitConnectUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.back_arrows)
    private ImageView backImg;

    @ViewInject(R.id.right_img)
    private ImageView rightImg;
    private PopupWindow mPopupWindow;

    @ViewInject(R.id.linear_layout)
    private LinearLayout linearLayout;
    @ViewInject(R.id.unreader)
    private TextView uNreader;

    public HomeFragment() {

    }

    @Override
    public void onInit() {
        rightImg.setImageResource(R.mipmap.more_icon);
        backImg.setVisibility(View.INVISIBLE);
        title.setText("工作");
//        mGridView.setFocusable(false);
        linearLayout.setFocusable(true);
        linearLayout.setFocusableInTouchMode(true);
        linearLayout.requestFocus();
        initPopupWindow();

        getunReaderCount();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (RongIM.getInstance() != null && RongIM.getInstance().getRongIMClient() != null) {
            /**
             * 设置连接状态变化的监听器.
             */
            RongIM.getInstance().getRongIMClient().setConnectionStatusListener(new MyConnectionStatusListener());
        }
    }

    private void getunReaderCount() {
        HashMap params = new HashMap();
        HttpRequestUtils.getmInstance().send(getActivity(), Constant.GETNOCHECKTOTAL_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBean<NoReaders> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<NoReaders>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    BadgeView badgeView = new BadgeView(getActivity());
                    badgeView.setBadgeCount(appBean.getData().getNoCheckTotalCount());
                    badgeView.setTargetView(uNreader);
                }
            }
        });
    }

    /**
     * 监测融云连接状态回调接口
     */
    private class MyConnectionStatusListener implements RongIMClient.ConnectionStatusListener {
        @Override
        public void onChanged(ConnectionStatus connectionStatus) {
            switch (connectionStatus) {

                case CONNECTED://连接成功。

                    break;
                case DISCONNECTED://断开连接。
                    String token = (String) SPUtils.get(getActivity(), "TOKEN", "");
                    IMkitConnectUtils.connect(token, getActivity());
                    break;
                case CONNECTING://连接中。

                    break;
                case NETWORK_UNAVAILABLE://网络不可用。

                    break;
                case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线

                    break;
            }
        }
    }

    /**
     * 修改群名称
     */
    private void showDialog() {
        final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_textview_layout, null);
        TextView titleName = (TextView) contentView.findViewById(R.id.title);
        TextView content = (TextView) contentView.findViewById(R.id.hint_content);
        titleName.setText("温馨提示");//标题
        content.setText("正在开发中");//提示你内容

        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(true);
        Button negativeButton = (Button) contentView.findViewById(R.id.negativeButton);
        negativeButton.setClickable(true);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        Button positiveButton = (Button) contentView.findViewById(R.id.positiveButton);
        positiveButton.setClickable(true);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        WindowManager wm = getActivity().getWindowManager();
        Display d = wm.getDefaultDisplay(); // 获取屏幕宽、高用
//        p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
        lp.width = (int) (d.getWidth() * 0.75); // 宽度设置为屏幕的0.65

        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    @Event({R.id.level_expertise, R.id.loving_company, R.id.crowd_layout, R.id.department_flow, R.id.important_system, R.id.department_functions, R.id.department_duty_layout, R.id.my_hardworking_layout, R.id.my_costing, R.id.my_make_bargain, R.id.my_visiting, R.id.open_sea, R.id.exhibition_manager, R.id.approval_manager, R.id.apply_model, R.id.submit_project, R.id.new_group, R.id.visiting_customer, R.id.project_manager, R.id.cost_manager, R.id.prototype_manager, R.id.my_integrity, R.id.life_navigation, R.id.improtment_thing, R.id.knowledge, R.id.right_img, R.id.visit_client})
    private void click(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.level_expertise://我的专业水平
                showDialog();
                break;
            case R.id.my_hardworking_layout://我的勤奋度
                showDialog();
                break;
            case R.id.loving_company://对公司的热爱
                showDialog();
                break;
            case R.id.crowd_layout://我的一伙人
                intent = new Intent(getActivity(), MyCrowdActivity.class);
                startActivity(intent);
                break;
            case R.id.department_flow://部门流程
                intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("ClickUrl", Constant.DEPARTMENT_FLOW_URL);
                intent.putExtra("TITLE", "部门流程");//标题
                startActivity(intent);
                break;
            case R.id.important_system://部门重要制度
                intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("ClickUrl", Constant.DEPARTMENT_SYSTEM_URL);
                intent.putExtra("TITLE", "部门重要制度");//标题
                startActivity(intent);
                break;
            case R.id.department_functions://部门指标
                intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("ClickUrl", Constant.DEPARTMENT_FUNCTIONS_URL);
                intent.putExtra("TITLE", "部门指标");//标题
                startActivity(intent);
                break;
            case R.id.department_duty_layout://部门职责
                intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("ClickUrl", Constant.DEPARTMENT_DUTY_URL);
                intent.putExtra("TITLE", "部门职责");//标题
                startActivity(intent);
                break;
            case R.id.my_costing://我的成本
                intent = new Intent(getActivity(), CostManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.my_make_bargain://我的成交
                intent = new Intent(getActivity(), MyMakeBargainActivity.class);
                startActivity(intent);
                break;
            case R.id.my_visiting://我的拜访
                intent = new Intent(getActivity(), SigninMainActivity.class);
                startActivity(intent);
                break;
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
                intent = new Intent(getActivity(), ApprovalProcessActivity.class);
                startActivity(intent);
                break;
//            case R.id.my_performance://业绩表现
//                intent = new Intent(getActivity(), MyPerformanceActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.my_values://我的价值
//                intent = new Intent(getActivity(), MyValuesActivity.class);
//                startActivity(intent);
//                break;
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

    private void showPopupWindow() {
//        mPopupWindow.showAsDropDown(titlebar );
        mPopupWindow.showAsDropDown(rightImg, 0, 0);
    }

    private void initPopupWindow() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_list_layout, null);
//        x.view().inject(this, contentView);
        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        contentView.findViewById(R.id.visit_client).setOnClickListener(this);
        contentView.findViewById(R.id.new_group).setOnClickListener(this);
        contentView.findViewById(R.id.submit_project).setOnClickListener(this);
        contentView.findViewById(R.id.apply_model).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
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
