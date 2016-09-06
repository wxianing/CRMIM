package com.meidp.crmim.utils;

import android.content.Context;
import android.util.Log;

import com.meidp.crmim.MyApplication;
import com.meidp.crmim.model.CostDetails;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Package： com.meidp.crmim.utils
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/9
 */
public class IMkitConnectUtils {

    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    public boolean connect(final String token, final Context mContext) {
        try {
            //        if (NetUtils.isConnected(mContext)) {
            if (mContext.getApplicationInfo().packageName.equals(MyApplication.getCurProcessName(mContext.getApplicationContext()))) {
                /**
                 * IMKit SDK调用第二步,建立与服务器的连接
                 */
                //  RongIM.getInstance().getRongIMClient().setConnectionStatusListener(new MyConnectionStatusListener());

                //RongIM rongIM = RongIM.getInstance();

                int IMkitconnectionStatus = (int) SPUtils.get(mContext, "IMkitconnectionStatus", 0);

//                ToastUtils.shows(mContext, "连接状态为：" + IMkitconnectionStatus);

                Log.e("IMkitconnectionStatus", "连接状态为：" + IMkitconnectionStatus);

                if (IMkitconnectionStatus == 0 || IMkitconnectionStatus == 1 || IMkitconnectionStatus == 2)

                    RongIM.connect(token, new RongIMClient.ConnectCallback() {
                        /**
                         * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                         */
                        @Override
                        public void onTokenIncorrect() {
                            Log.e("IMkitConnectUtils", "--Token不正确");
                        }

                        /**
                         * 连接融云成功
                         *
                         * @param userid 当前 token
                         */
                        @Override
                        public void onSuccess(String userid) {
                            Log.e("RongIMkit", "------userid=" + userid + "--连接融云成功");
                            RongIM.getInstance().getRongIMClient().getConversationList();

                            RongIM.getInstance().getRongIMClient().setConnectionStatusListener(new MyConnectionStatusListenerForJuFeng(mContext));
                        }

                        /**
                         * 连接融云失败
                         *
                         * @param errorCode 错误码，可到官网 查看错误码对应的注释
                         */
                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {
                            Log.e("errorCode", errorCode.getMessage());
                            if (NetUtils.isConnected(mContext)) {
                                connect(token, mContext);
                                Log.e("RondIMKit", "--融云服务器连接失败" + errorCode);
                            }
                        }

                        @Override
                        public void onFail(int errorCode) {
                            if (NetUtils.isConnected(mContext)) {
                                connect(token, mContext);
                            }
                            super.onFail(errorCode);
                        }
                    });

//            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //</editor-fold>
        return false;
    }

    public static int IMkitconnectionStatus = 0;

    /**
     * 监听融云连接状态
     */


    private class MyConnectionStatusListenerForJuFeng implements RongIMClient.ConnectionStatusListener {
        private Context mContext;

        public MyConnectionStatusListenerForJuFeng(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public void onChanged(ConnectionStatus connectionStatus) {
            Log.e("状态监听回调", ">>>>>>>>>>>>>>>11111111111111111111111");
            switch (connectionStatus) {

                case CONNECTED://连接成功。
                    Log.e("连接成功", "融云连接成功3");
                    IMkitconnectionStatus = 3;
                    SPUtils.save(mContext, "IMkitconnectionStatus", 3);
                    break;
                case DISCONNECTED://断开连接。
                    IMkitconnectionStatus = 1;
                    SPUtils.save(mContext, "IMkitconnectionStatus", 1);
                    break;
                case CONNECTING://连接中。
                    IMkitconnectionStatus = 10;
                    SPUtils.save(mContext, "IMkitconnectionStatus", 10);
                    break;
                case NETWORK_UNAVAILABLE://网络不可用。
                    IMkitconnectionStatus = 2;
                    SPUtils.save(mContext, "IMkitconnectionStatus", 2);
                    break;
                case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线

                    break;
            }
        }
    }

}
