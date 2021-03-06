package com.meidp.crmim.imkit;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.JPushNoReader;
import com.meidp.crmim.receiver.ConnectionChangeReceiver;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.CopyUtils;
import com.meidp.crmim.utils.DataUtils;
import com.meidp.crmim.utils.FileUtils;
import com.meidp.crmim.utils.IMkitConnectUtils;
import com.meidp.crmim.utils.MyFileInputProvider;
import com.meidp.crmim.utils.NetUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;

import org.xutils.view.annotation.Event;

import java.io.File;
import java.util.HashMap;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.FileMessage;
import io.rong.message.ImageMessage;
import io.rong.message.TextMessage;

/**
 * Package： com.meidp.crmim.imkit
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/11
 */
public class ConversationListStaticFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "KEY";
    private String mParams;

    private TextView title;
    private ImageView backImg;
    private LinearLayout layout;

    private ImageView rightImg;
    private TextView currTime;
    private PopupWindow mPopupWindow;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        if (NetUtils.isConnected(getActivity())) {
        View view = inflater.inflate(R.layout.conversationlist, container, false);
//        setInputProvider();
        onInit(view);
        initEvent(view);
        return view;
//        }
//        return null;
    }

    private void initEvent(View view) {
        view.findViewById(R.id.search_edittext).setOnClickListener(this);
        view.findViewById(R.id.right_add).setOnClickListener(this);
        view.findViewById(R.id.right_scan).setOnClickListener(this);
        view.findViewById(R.id.secretary_layout).setOnClickListener(this);
    }

    private ConnectionChangeReceiver myReceiver;

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        myReceiver = new ConnectionChangeReceiver();
        getActivity().registerReceiver(myReceiver, filter);
    }

    public void onInit(View view) {
        registerReceiver();
        title = (TextView) view.findViewById(R.id.title_tv);
        backImg = (ImageView) view.findViewById(R.id.back_arrows);
        layout = (LinearLayout) view.findViewById(R.id.layout);
        rightImg = (ImageView) view.findViewById(R.id.right_add);
        currTime = (TextView) view.findViewById(R.id.curr_time);
        unreader = (TextView) view.findViewById(R.id.unreader);


//        String token = (String) SPUtils.get(getActivity(), "TOEKN", "");
//        if (RongIM.getInstance() == null) {
//            IMkitConnectUtils.connect(token, getActivity());
//        }

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


    /*private class MyConnectionStatusListener implements RongIMClient.ConnectionStatusListener {
        String token = (String) SPUtils.get(getActivity(), "TOKEN", "");

        @Override
        public void onChanged(ConnectionStatus connectionStatus) {
            switch (connectionStatus) {

                case CONNECTED://连接成功。
                    break;
                case DISCONNECTED://断开连接。
                    if (NetUtils.isConnected(getActivity())) {
                       new IMkitConnectUtils().connect(token, getActivity());
                    }
                    break;
                case CONNECTING://连接中。
                    break;
                case NETWORK_UNAVAILABLE://网络不可用。
                    break;
                case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线

                    break;
            }
        }
    }*/

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
          //  RongIM.getInstance().getRongIMClient().setConnectionStatusListener(new MyConnectionStatusListener());
        }

        Log.e("ssssss", "sssssssssssss");

        RongIM.setConversationListBehaviorListener(new MyConversationListBehaviorListener());
        super.onResume();
    }

    /**
     * 监听融云会话列表
     */
    private class MyConversationListBehaviorListener implements RongIM.ConversationListBehaviorListener {
        @Override
        public boolean onConversationPortraitClick(Context context, Conversation.ConversationType conversationType, String s) {
            return false;
        }

        @Override
        public boolean onConversationPortraitLongClick(Context context, Conversation.ConversationType conversationType, String s) {
            return false;
        }

        /**
         * 长按会话列表中的 item 时执行。
         *
         * @param context        上下文。
         * @param view           触发点击的 View。
         * @param uiConversation 长按时的会话条目。
         * @return 如果用户自己处理了长按会话后的逻辑处理，则返回 true， 否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onConversationLongClick(Context context, View view, UIConversation uiConversation) {
            return false;
        }

        /**
         * 点击会话列表中的 item 时执行。
         *
         * @param context        上下文。
         * @param view           触发点击的 View。
         * @param uiConversation 会话条目。
         * @return 如果用户自己处理了点击会话后的逻辑处理，则返回 true， 否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onConversationClick(Context context, View view, final UIConversation uiConversation) {

            return false;
        }
    }

    private void showPopupWindow() {
//        mPopupWindow.showAsDropDown(titlebar );
        mPopupWindow.showAsDropDown(rightImg, 10, 10);
    }

    private void initPopupWindow() {
        View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_list_layout, null);
//        x.view().inject(this, popupView);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(popupView);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupView.findViewById(R.id.visit_client).setOnClickListener(this);
        popupView.findViewById(R.id.new_group).setOnClickListener(this);
        popupView.findViewById(R.id.submit_project).setOnClickListener(this);
        popupView.findViewById(R.id.apply_model).setOnClickListener(this);
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

            case R.id.search_edittext:
                intent = new Intent(getActivity(), SearchMsgActivity.class);
                startActivity(intent);
                break;
            case R.id.right_add://下拉对话框
                if (!mPopupWindow.isShowing()) {
                    showPopupWindow();
                } else {
                    mPopupWindow.dismiss();
                }
                break;
            case R.id.right_scan://二维码
                intent = new Intent(getActivity(), DimensionCodeActivity.class);
                startActivity(intent);
                break;
            case R.id.secretary_layout:
//                intent = new Intent(getActivity(), GroupActivity.class);
//                startActivity(intent);
                intent = new Intent(getActivity(), SecretaryActivity.class);
                startActivity(intent);
                break;

        }
    }
}
