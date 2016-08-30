package com.meidp.crmim.imkit;


import android.content.Context;
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

import com.meidp.crmim.R;
import com.meidp.crmim.activity.DimensionCodeActivity;
import com.meidp.crmim.activity.ModelMachineApplyActivity;
import com.meidp.crmim.activity.NewGroupActivity;
import com.meidp.crmim.activity.SearchMsgActivity;
import com.meidp.crmim.activity.SecretaryActivity;
import com.meidp.crmim.activity.SigninMainActivity;
import com.meidp.crmim.activity.SubmitActivity;
import com.meidp.crmim.fragment.BaseFragment;
import com.meidp.crmim.utils.CopyUtils;
import com.meidp.crmim.utils.DataUtils;
import com.meidp.crmim.utils.FileUtils;
import com.meidp.crmim.utils.IMkitConnectUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.model.UIConversation;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.ImageMessage;
import io.rong.message.TextMessage;

/**
 * Package： com.meidp.crmim.imkit
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/11
 */
@ContentView(R.layout.conversationlist)
public class ConversationListsFragment extends BaseFragment {

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

    public static ConversationListsFragment newInstance(String params) {
        ConversationListsFragment fragment = new ConversationListsFragment();
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
        Log.e("mes", CopyUtils.paste(getActivity()));
        RongIM.setConversationListBehaviorListener(new RongIM.ConversationListBehaviorListener() {
            @Override
            public boolean onConversationPortraitClick(Context context, Conversation.ConversationType conversationType, String s) {
                return false;
            }

            @Override
            public boolean onConversationPortraitLongClick(Context context, Conversation.ConversationType conversationType, String s) {
                return false;
            }

            @Override
            public boolean onConversationLongClick(Context context, View view, UIConversation uiConversation) {
                return false;
            }

            @Override
            public boolean onConversationClick(Context context, View view, final UIConversation uiConversation) {
                if (getActivity() != null) {
                    String localUrl = (String) SPUtils.get(context, "LocalUri", "");
                    String thumUri = (String) SPUtils.get(context, "ThumUri", "");
                    String remodeUri = (String) SPUtils.get(getActivity(), "RemoteUri", "");
                    String message = CopyUtils.paste(getActivity());
                    if (NullUtils.isNull(message)) {
                        TextMessage textMessage = TextMessage.obtain(message);
                        Message messageContent = Message.obtain(uiConversation.getConversationTargetId(), uiConversation.getConversationType(), textMessage);
                        final Conversation.ConversationType type = uiConversation.getConversationType();

                        RongIM.getInstance().sendMessage(messageContent, null, null, new IRongCallback.ISendMessageCallback() {
                            @Override
                            public void onAttached(Message message) {
                                //消息本地数据库存储成功的回调
                            }

                            @Override
                            public void onSuccess(Message message) {
                                //消息通过网络发送成功的回调
                                if (type.getValue() == 1) {
                                    RongIM.getInstance().startPrivateChat(getActivity(), uiConversation.getConversationTargetId(), uiConversation.getUIConversationTitle());
                                } else if (type.getValue() == 2) {
                                    RongIM.getInstance().startDiscussionChat(getActivity(), uiConversation.getConversationTargetId(), uiConversation.getUIConversationTitle());
                                }
                                if (ConversationListActivity.activity != null) {
                                    ConversationListActivity.activity.finish();
                                    ConversationListActivity.activity = null;
                                }
                                CopyUtils.copy("", getActivity());
                            }

                            @Override
                            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                                //消息发送失败的回调
                            }
                        });
                        return true;
                    } else if (NullUtils.isNull(thumUri)) {

                        File imageFileSource = FileUtils.getFileByUri(Uri.parse(thumUri), getActivity());
                        File imageFileThumb = FileUtils.getFileByUri(Uri.parse(thumUri), getActivity());
                        File imageFileRemode = FileUtils.getFileByUri(Uri.parse(remodeUri), getActivity());

                        ImageMessage imgMsg = ImageMessage.obtain(Uri.fromFile(imageFileThumb), Uri.fromFile(imageFileThumb));

                        RongIM.getInstance().sendImageMessage(uiConversation.getConversationType(), uiConversation.getConversationTargetId(), imgMsg, null, null, new RongIMClient.SendImageMessageCallback() {

                            @Override
                            public void onAttached(Message message) {
                                //保存数据库成功
                            }

                            @Override
                            public void onError(Message message, RongIMClient.ErrorCode code) {
                                //发送失败
                                Log.e("code", code.getMessage());
                            }

                            @Override
                            public void onSuccess(Message message) {

                            }

                            @Override
                            public void onProgress(Message message, int progress) {
                                //发送进度
                            }
                        });
                    }
                }
                return false;
            }
        });
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
            /**
             * 设置会话列表界面操作的监听器。
             */
//            RongIM.setConversationListBehaviorListener(new MyConversationListBehaviorListener());
        }
    }

    private void sendImg(String localUrl, String thumUri) {

    }

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
        public boolean onConversationClick(Context context, View view, UIConversation uiConversation) {
            if (getActivity() != null) {
                String message = CopyUtils.paste(getActivity());
                if (NullUtils.isNull(message)) {
                    TextMessage textMessage = TextMessage.obtain(message);
                    Message messageContent = Message.obtain(uiConversation.getConversationTargetId(), uiConversation.getConversationType(), textMessage);
                    RongIM.getInstance().sendMessage(messageContent, null, null, new IRongCallback.ISendMessageCallback() {
                        @Override
                        public void onAttached(Message message) {
                            //消息本地数据库存储成功的回调
                        }

                        @Override
                        public void onSuccess(Message message) {
                            //消息通过网络发送成功的回调
                        }

                        @Override
                        public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                            //消息发送失败的回调
                        }
                    });
                }
            }
            return true;
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
//        HashMap params = new HashMap();
//        HttpRequestUtils.getmInstance().send(getActivity(), Constant.JPUSH_NORESDER_URL, params, new HttpRequestCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                JPushNoReader appBean = JSONObject.parseObject(result, new TypeReference<JPushNoReader>() {
//                });
//                if (appBean != null && appBean.getEnumcode() == 0) {
//                    BadgeView badgeView = new BadgeView(getActivity());
//                    badgeView.setBadgeCount(appBean.getData());
//                    badgeView.setTargetView(unreader);
//                }
//            }
//        });
    }

    @Override
    public void onResume() {
//        HashMap params = new HashMap();
//        params.put("Id", 1);
//        HttpRequestUtils.getmInstance().send(getActivity(), Constant.JPUSH_NORESDER_URL, params, new HttpRequestCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                Log.e("极光未读消息：", result);
//                JPushNoReader appBean = JSONObject.parseObject(result, new TypeReference<JPushNoReader>() {
//                });
//                if (appBean != null && appBean.getEnumcode() == 0) {
//                    BadgeView badgeView = new BadgeView(getActivity());
//                    badgeView.setBadgeCount(appBean.getData());
//                    badgeView.setTargetView(unreader);
//                }
//            }
//        });
//        if (RongIM.getInstance() != null && RongIM.getInstance().getRongIMClient() != null) {
//            /**
//             * 设置连接状态变化的监听器.
//             */
//            RongIM.getInstance().getRongIMClient().setConnectionStatusListener(new MyConnectionStatusListener());
//        }
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
//        x.view().inject(this, popupView);
        x.view().inject(this, popupView);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(popupView);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }
}
