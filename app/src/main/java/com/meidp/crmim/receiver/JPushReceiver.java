package com.meidp.crmim.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.meidp.crmim.activity.JPushReceiverActivity;
import com.meidp.crmim.utils.LogUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * Package： com.meidp.crmim.receiver
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/2
 */
public class JPushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String content = bundle.getString(JPushInterface.EXTRA_ALERT);

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent
                .getAction())) {
            // 这里面能接受自定义消息内容
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent
                .getAction())) {
            // 接收通知头部
            String title = bundle
                    .getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            // 接收通知内容
            content = bundle.getString(JPushInterface.EXTRA_ALERT);
            LogUtils.e(content);
            // 在这里可以做些统计，或者做些其他工作
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent
                .getAction())) {
            System.out.println("用户点击打开了通知");
            // 在这里可以自己写代码去定义用户点击后的行为
            Intent in = new Intent(context, JPushReceiverActivity.class); // 自定义打开的界面
            in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            in.putExtra("content", content);
            context.startActivity(in);
        } else {
        }
    }
}
