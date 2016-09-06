package com.meidp.crmim.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.meidp.crmim.activity.DocumentListActivity;
import com.meidp.crmim.model.DocBean;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.FileInfo;
import io.rong.imkit.widget.provider.FileInputProvider;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.FileMessage;

/**
 * Package： com.meidp.crmim.utils
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/9/3
 */
public class MyFileInputProvider extends FileInputProvider {
    private List<DocBean> checkDocLists;

    private Context mContext;

    public MyFileInputProvider(RongContext context, Context mContext) {
        super(context);
        this.mContext = mContext;
    }

    @Override
    public void onPluginClick(View view) {
//        ToastUtils.shows(RongContext.getInstance(), "点击了文件");
        Intent intent = new Intent();
        intent.setClass(getContext(), DocumentListActivity.class);

        intent.putExtra("MSG", "adddocuments");
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        checkDocLists = (List<DocBean>) data.getSerializableExtra("CheckDocLists");
//        String docNames = "";
//        if (checkDocLists != null && checkDocLists.size() > 0) {
//            for (int i = 0; i < checkDocLists.size(); i++) {
//                docNames += checkDocLists.get(i).getFileName() + ";";
//            }
//            LogUtils.e(docNames);
//        }

//        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null) {
//            HashSet selectedFileInfos = (HashSet) data.getSerializableExtra("CheckDocLists");
//            Iterator i$ = selectedFileInfos.iterator();

            List<DocBean> checkDocLists = (List<DocBean>) data.getSerializableExtra("CheckDocLists");

            for (int i = 0; i < checkDocLists.size(); i++) {
                DocBean fileInfo = checkDocLists.get(i);
                Uri filePath = Uri.parse("file://" + fileInfo.getPath());
                FileMessage fileMessage = FileMessage.obtain(filePath);

//                RongIM.getInstance().setConnectionStatusListener(new MyConnectionStatusListener(fileMessage, this.getCurrentConversation().getTargetId(), this.getCurrentConversation().getConversationType()));
//                RongIM.getInstance().setConnectionStatusListener(new MyConnectionStatusListener());
//                if (RongIM.getInstance() != null && RongIM.getInstance().getRongIMClient() != null) {
//                }

                if (NetUtils.isConnected(mContext)) {
                    if (fileMessage != null) {
//                        IMkitConnectUtils.connect(Constant.getTOKEN(), mContext);
//                    fileMessage.setType(fileInfo.getSuffix());
                        Message message = Message.obtain(this.getCurrentConversation().getTargetId(), this.getCurrentConversation().getConversationType(), fileMessage);

                        RongIM.getInstance().sendMediaMessage(message, (String) null, (String) null, new MyFileIRongCallback());
                    }
                } else {
                    ToastUtils.shows(mContext, "当前网络不稳定，请检查网络是否通畅");
                    new IMkitConnectUtils().connect(Constant.getTOKEN(), mContext);
                }
            }
        }
    }

    /**
     * 监听融云连接状态
     */
    /*private class MyConnectionStatusListener implements RongIMClient.ConnectionStatusListener {
        private FileMessage fileMessage;

        private String targetId;

        private Conversation.ConversationType type;
//
//        public MyConnectionStatusListener(FileMessage fileMessage, String targetId, Conversation.ConversationType type) {
//            this.fileMessage = fileMessage;
//            this.targetId = targetId;
//            this.type = type;
//        }

        @Override
        public void onChanged(ConnectionStatus connectionStatus) {
            switch (connectionStatus) {
                case CONNECTED://连接融云成功。
                    Log.e("融云连接", "融云连接成功");
//                    if (fileMessage != null) {
////                    fileMessage.setType(fileInfo.getSuffix());
//                        Message message = Message.obtain(targetId, type, fileMessage);
//                        RongIM.getInstance().sendMediaMessage(message, (String) null, (String) null, new MyFileIRongCallback());
//                    }
                    break;
                case DISCONNECTED://断开连接。
                    if (NetUtils.isConnected(mContext)) {
                        new IMkitConnectUtils().connect(Constant.getTOKEN(), mContext);
                        Log.e("融云连接", "断开");
                    } else {
                        ToastUtils.shows(mContext, "网络连接不可用");
                    }
                    break;
                case CONNECTING://连接中。
                    Log.e("融云连接", "正在连接");
                    break;
                case NETWORK_UNAVAILABLE://网络不可用。
                    Log.e("融云连接", "网络不可用");
                    break;
                case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线
                    Log.e("融云连接", "用户账户在其他设备登录，本机会被踢掉线");
                    break;
            }
        }
    }*/


    private class MyFileIRongCallback implements IRongCallback.ISendMediaMessageCallback {

        @Override
        public void onProgress(Message message, int i) {

        }

        @Override
        public void onAttached(Message message) {

        }

        @Override
        public void onSuccess(Message message) {
//            ToastUtils.shows(mContext, "发送成功");
        }

        @Override
        public void onError(Message message, RongIMClient.ErrorCode errorCode) {
            ToastUtils.shows(mContext, "发送失败");
            new IMkitConnectUtils().connect(Constant.getTOKEN(), mContext);
        }
    }
}
