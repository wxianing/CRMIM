package com.meidp.crmim.imkit;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.android.gms.common.api.ResultCallback;
import com.meidp.crmim.R;
import com.meidp.crmim.activity.BaseActivity;
import com.meidp.crmim.activity.GroupMenberActivity;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.CopyUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.FileInputProvider;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.FileMessage;
import io.rong.message.ImageMessage;
import io.rong.message.RichContentMessage;
import io.rong.message.StickerMessage;
import io.rong.message.TextMessage;

/**
 * Package： com.meidp.crmim.imkit
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/9
 */
@ContentView(R.layout.conversation)
public class ConversationActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.right_img)
    private ImageView titleRight;

    private Activity activity;
    /**
     * 目标 Id
     */
    private String mTargetId;

    /**
     * 刚刚创建完讨论组后获得讨论组的id 为targetIds，需要根据 为targetIds 获取 targetId
     */
    private String mTargetIds;

    /**
     * 会话类型
     */
    private Conversation.ConversationType mConversationType;


    @Override
    public void onInit() {
        activity = this;
        //获取聊天类型
        mConversationType = Conversation.ConversationType.valueOf(getIntent().getData().getLastPathSegment().toUpperCase(Locale.getDefault()));

        titleRight.setVisibility(View.VISIBLE);
        titleRight.setImageResource(R.mipmap.three_dot);
        mTargetId = getIntent().getData().getQueryParameter("targetId");//获取聊天对象的Id
        String titleName = getIntent().getData().getQueryParameter("title");//获取聊天对象的Id

        title.setText(titleName);
        if (mConversationType.getName().equals("private")) {
            titleRight.setVisibility(View.GONE);
        }

        if (mConversationType.getName().equals("discussion")) {
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog();
                }
            });
        }

        RongIM.getInstance().getRongIMClient().getConversationList();
        /**
         * 设置会话界面操作的监听器。
         */
        RongIM.setConversationBehaviorListener(new MyConversationBehaviorListener());
//        RongIM.getInstance().registerMessageType(FileMessage.class);
//        InputProvider.ExtendProvider[] singleProvider = {
//                new ImageInputProvider(RongContext.getInstance()),
//                new FileInputProvider(RongContext.getInstance())//文件消息
//        };
//        RongIM.resetInputExtensionProvider(Conversation.ConversationType.PRIVATE, singleProvider);
//        RongIM.resetInputExtensionProvider(Conversation.ConversationType.DISCUSSION, singleProvider);
    }

    /**
     * 修改群名称
     */
    private void showDialog() {
        final Dialog dialog = new Dialog(this, R.style.Dialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.edittext_dialog, null);
        TextView titleName = (TextView) contentView.findViewById(R.id.title);
        final EditText editText = (EditText) contentView.findViewById(R.id.message);
        titleName.setText("请输入群名称");
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
                final String groupName = editText.getText().toString().trim();
                if (NullUtils.isNull(groupName)) {
                    RongIM.getInstance().setDiscussionName(mTargetId, groupName, new RongIMClient.OperationCallback() {
                        @Override
                        public void onSuccess() {
                            title.setText(groupName);
                            sendMsg(mTargetId, groupName);
                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {
                            ToastUtils.shows(ConversationActivity.this, "修改失败");
                        }
                    });
                } else {
                    ToastUtils.shows(ConversationActivity.this, "请输入群名称");
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 修改群名
     *
     * @param s
     * @param groupName
     */
    private void sendMsg(String s, String groupName) {
        HashMap params = new HashMap();
        params.put("discussionId", s);
        params.put("discussionName", groupName);
        HttpRequestUtils.getmInstance().send(ConversationActivity.this, Constant.CREATE_GROUP_URL, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    ToastUtils.shows(ConversationActivity.this, "修改成功");
                } else {
                    ToastUtils.shows(ConversationActivity.this, appMsg.getMsg());
                }
            }
        });
    }


    @Event(value = {R.id.back_arrows, R.id.right_img})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.right_img:
                Intent intent = new Intent(this, GroupMenberActivity.class);
                intent.putExtra("mTargetId", mTargetId);
                startActivity(intent);
                break;
        }
    }

    private class MyConversationBehaviorListener implements RongIM.ConversationBehaviorListener {
        /**
         * 当点击用户头像后执行。
         *
         * @param context          上下文。
         * @param conversationType 会话类型。
         * @param userInfo         被点击的用户的信息。
         * @return 如果用户自己处理了点击后的逻辑，则返回 true，否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
            return false;
        }

        /**
         * 当长按用户头像后执行。
         *
         * @param context          上下文。
         * @param conversationType 会话类型。
         * @param userInfo         被点击的用户的信息。
         * @return 如果用户自己处理了点击后的逻辑，则返回 true，否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {

            return false;
        }

        /**
         * 当点击消息时执行。
         *
         * @param context 上下文。
         * @param view    触发点击的 View。
         * @param message 被点击的消息的实体信息。
         * @return 如果用户自己处理了点击后的逻辑，则返回 true， 否则返回 false, false 走融云默认处理方式。
         */
        @Override
        public boolean onMessageClick(Context context, View view, Message message) {
            return false;
        }

        /**
         * 当长按消息时执行。
         *
         * @param context 上下文。
         * @param view    触发点击的 View。
         * @param message 被长按的消息的实体信息。
         * @return 如果用户自己处理了长按后的逻辑，则返回 true，否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onMessageLongClick(Context context, View view, Message message) {
            alterDialog(view, message);

            return true;
        }

        /**
         * 当点击链接消息时执行。
         *
         * @param context 上下文。
         * @param link    被点击的链接。
         * @return 如果用户自己处理了点击后的逻辑处理，则返回 true， 否则返回 false, false 走融云默认处理方式。
         */
        @Override
        public boolean onMessageLinkClick(Context context, String link) {
            return false;
        }
    }

    /**
     * 获取会话列表。
     *
     * @param callback 获取会话列表的回调。
     */
    public void getConversationList(final RongIMClient.ResultCallback<List<Conversation>> callback) {

    }

    private class ResultCallBack extends RongIMClient.ResultCallback<List<Conversation>> {

        @Override
        public void onSuccess(List<Conversation> conversations) {

        }

        @Override
        public void onError(RongIMClient.ErrorCode errorCode) {

        }
    }

    /**
     * 点击消息对话框
     */
    private void alterDialog(View v, final Message message) {

        final Dialog dialog = new Dialog(ConversationActivity.this, R.style.Dialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.message_dialog, null);
        TextView titleName = (TextView) contentView.findViewById(R.id.title);
        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(true);
        Window w = dialog.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        //显示位置
        lp.x = (int) v.getX();
        lp.y = (int) v.getY();

        //复制
        TextView copy = (TextView) contentView.findViewById(R.id.copy);
        if (message.getObjectName().equals("RC:TxtMsg")) {
            copy.setVisibility(View.VISIBLE);
        }
        copy.setClickable(true);
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (message.getObjectName().equals("RC:TxtMsg")) {
                    TextMessage textMessage = (TextMessage) message.getContent();
                    CopyUtils.copy(textMessage.getContent(), ConversationActivity.this);

                } else if (message.getObjectName().equals("RC:ImgMsg")) {
                    ImageMessage imageMessage = (ImageMessage) message.getContent();
//                    CopyUtils.copy(imageMessage.getBase64(), ConversationActivity.this);
                } else if (message.getObjectName().equals("RC:ImgTextMsg")) {
                    //图文
                    RichContentMessage richContentMessage = (RichContentMessage) message.getContent();
//                    CopyUtils.copy(richContentMessage.getContent(), ConversationActivity.this);
                } else if (message.getObjectName().equals("RC:StkMsg")) {
                    //表情
                    StickerMessage stickerMessage = (io.rong.message.StickerMessage) message.getContent();
//                    CopyUtils.copy(stickerMessage.getCategory(), ConversationActivity.this);
                }
                dialog.dismiss();
            }
        });
        //转发
        TextView transpond = (TextView) contentView.findViewById(R.id.transpond);
        transpond.setClickable(true);
        transpond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (message.getObjectName().equals("RC:TxtMsg")) {
                    TextMessage textMessage = (TextMessage) message.getContent();
                    Log.e("textMessage.message", "" + textMessage.getContent());
                    CopyUtils.copy(textMessage.getContent(), ConversationActivity.this);
                    Intent intent = new Intent(ConversationActivity.this, ConversationListActivity.class);
                    startActivity(intent);
                    finish();
                } else if (message.getObjectName().equals("RC:ImgMsg")) {
                    //图片
                    ImageMessage imageMessage = (ImageMessage) message.getContent();
                    Log.e("imageMessage", "getRemoteUri" + imageMessage.getRemoteUri());
                    Log.e("imageMessage", "getLocalUri" + imageMessage.getLocalUri());
                    Log.e("imageMessage", "getThumUri" + imageMessage.getThumUri());
//                    Log.e("Base64", imageMessage.getBase64());
                    SPUtils.save(ConversationActivity.this, "RemoteUri", imageMessage.getRemoteUri());
                    SPUtils.save(ConversationActivity.this, "ThumUri", String.valueOf(imageMessage.getThumUri()));
                    SPUtils.save(ConversationActivity.this, "LocalUri", String.valueOf(imageMessage.getLocalUri()));
                    Intent intent = new Intent(ConversationActivity.this, ConversationListActivity.class);
                    startActivity(intent);
                    finish();
                } else if (message.getObjectName().equals("RC:ImgTextMsg")) {
                    //图文
                    RichContentMessage richContentMessage = (RichContentMessage) message.getContent();
                } else if (message.getObjectName().equals("RC:StkMsg")) {
                    //表情
                    StickerMessage StickerMessage = (io.rong.message.StickerMessage) message.getContent();
                }
                dialog.dismiss();
            }
        });
        TextView delete = (TextView) contentView.findViewById(R.id.delete);
        delete.setClickable(true);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RongIM.getInstance().deleteMessages(new int[]{message.getMessageId()}, new RongIMClient.ResultCallback<Boolean>() {

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        Log.e("删除成功", ">>>>>>>" + aBoolean);
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });


                dialog.dismiss();
            }
        });
        dialog.show();

    }

    /**
     * 监听返回键, 退出应用程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (ConversationListActivity.activity != null) {
                ConversationListActivity.activity.finish();
                ConversationListActivity.activity = null;
            }
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
