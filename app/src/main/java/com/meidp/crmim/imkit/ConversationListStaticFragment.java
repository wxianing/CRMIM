package com.meidp.crmim.imkit;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jauker.widget.BadgeView;
import com.meidp.crmim.R;
import com.meidp.crmim.activity.DimensionCodeActivity;
import com.meidp.crmim.activity.ModelMachineApplyActivity;
import com.meidp.crmim.activity.NewGroupActivity;
import com.meidp.crmim.activity.SearchMsgActivity;
import com.meidp.crmim.activity.SecretaryActivity;
import com.meidp.crmim.activity.SigninMainActivity;
import com.meidp.crmim.activity.SubmitActivity;
import com.meidp.crmim.fragment.BaseFragment;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.JPushNoReader;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.DataUtils;
import com.meidp.crmim.utils.IMkitConnectUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Package： com.meidp.crmim.imkit
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/11
 */
@ContentView(R.layout.conversationlist)
public class ConversationListStaticFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "KEY";
    private String mParams;

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.back_arrows)
    private ImageView backImg;
    @ViewInject(R.id.layout)
    private LinearLayout layout;

    @ViewInject(R.id.right_add)
    private ImageView rightImg;
    @ViewInject(R.id.curr_time)
    private TextView currTime;
    private PopupWindow mPopupWindow;
    @ViewInject(R.id.unreader)
    private TextView unreader;

    public static ConversationListStaticFragment newInstance(String params) {
        ConversationListStaticFragment fragment = new ConversationListStaticFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, params);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParams = getArguments().getString(ARG_PARAM1);
            Log.e("mParam", mParams);
        }
    }

    @Override
    public void onInit() {
        rightImg.setImageResource(R.mipmap.more_icon);
        backImg.setVisibility(View.INVISIBLE);
        title.setText("消息");
        currTime.setText(DataUtils.getTime2());
        initPopupWindow();
        ConversationListFragment fragment = (ConversationListFragment) getChildFragmentManager().findFragmentById(R.id.conversationlist);

        if (NullUtils.isNull(mParams) && mParams.equals("PRIVATE")) {
            layout.setVisibility(View.GONE);
            Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
//                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//设置群组会话聚合显示
//                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
//                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                    .build();
            fragment.setUri(uri);
        } else {
            Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//设置群组会话聚合显示
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                    .build();
            fragment.setUri(uri);
        }


    }



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

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        HttpRequestUtils.getmInstance().send(getActivity(), Constant.JPUSH_NORESDER_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                JPushNoReader appBean = JSONObject.parseObject(result, new TypeReference<JPushNoReader>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    BadgeView badgeView = new BadgeView(getActivity());
                    badgeView.setBadgeCount(appBean.getData());
                    badgeView.setTargetView(unreader);
                }
            }
        });
    }

    @Override
    public void onResume() {
        Log.e(">>>>>>>>", "测试而已");
        HashMap params = new HashMap();
        params.put("Id", 1);
        HttpRequestUtils.getmInstance().send(getActivity(), Constant.JPUSH_NORESDER_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("极光未读消息：", result);
                JPushNoReader appBean = JSONObject.parseObject(result, new TypeReference<JPushNoReader>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    BadgeView badgeView = new BadgeView(getActivity());
                    badgeView.setBadgeCount(appBean.getData());
                    badgeView.setTargetView(unreader);
                }
            }
        });
        if (RongIM.getInstance() != null && RongIM.getInstance().getRongIMClient() != null) {
            /**
             * 设置连接状态变化的监听器.
             */
            RongIM.getInstance().getRongIMClient().setConnectionStatusListener(new MyConnectionStatusListener());
        }
        super.onResume();
    }

    @Event({R.id.search_edittext, R.id.right_add, R.id.right_scan, R.id.secretary_layout, R.id.visit_client, R.id.new_group, R.id.submit_project, R.id.apply_model})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.search_edittext:
                intent = new Intent(getActivity(), SearchMsgActivity.class);
                startActivity(intent);
                break;
            case R.id.right_add:
                if (!mPopupWindow.isShowing()) {
                    showPopupWindow();
                } else {
                    mPopupWindow.dismiss();
                }
                break;
            case R.id.right_scan:
                intent = new Intent(getActivity(), DimensionCodeActivity.class);
                startActivity(intent);
                break;
            case R.id.secretary_layout:
//                intent = new Intent(getActivity(), GroupActivity.class);
//                startActivity(intent);
                intent = new Intent(getActivity(), SecretaryActivity.class);
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

        }
    }

    private void showPopupWindow() {
//        mPopupWindow.showAsDropDown(titlebar );
        mPopupWindow.showAsDropDown(rightImg, 10, 10);
    }

    private void initPopupWindow() {
        View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_list_layout, null);
        x.view().inject(this, popupView);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(popupView);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

}
