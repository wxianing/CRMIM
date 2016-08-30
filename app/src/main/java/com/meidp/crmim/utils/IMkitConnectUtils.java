package com.meidp.crmim.utils;

import android.content.Context;
import android.util.Log;

import com.meidp.crmim.MyApplication;

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
    public static void connect(final String token, final Context mContext) {

        if (NetUtils.isConnected(mContext)) {
            if (mContext.getApplicationInfo().packageName.equals(MyApplication.getCurProcessName(mContext.getApplicationContext()))) {
                /**
                 * IMKit SDK调用第二步,建立与服务器的连接
                 */
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
//                    mContext.startActivity(new Intent(mContext, ConversationActivity.class));
                    }

                    /**
                     * 连接融云失败
                     *
                     * @param errorCode 错误码，可到官网 查看错误码对应的注释
                     */
                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        IMkitConnectUtils.connect(token, mContext);
                        Log.e("RondIMKit", "--融云服务器连接成功" + errorCode);
                    }
                });
            }
        }
    }
}
