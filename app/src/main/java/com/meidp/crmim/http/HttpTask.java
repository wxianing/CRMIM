package com.meidp.crmim.http;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.Versions;
import com.meidp.crmim.utils.AppUtils;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.view.CustomDialog;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/2/27.
 */

public class HttpTask {

    public static void detectionNewAppVersion(final Context context, final boolean isUpdate, final boolean showLoading) {
        HashMap params = new HashMap();
        params.put("AppId", 102);

        HttpRequestUtils.getmInstance().send(context, Constant.VERSION_UPDATE, params, new HttpRequestCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("Versions", result);
                        final AppBean<Versions> v = JSONObject.parseObject(result, new TypeReference<AppBean<Versions>>() {
                        });

                        if (v == null && v.getEnumcode() != 0) {
                            return;
                        }
                        int versionCode = 0;
                        try {
                            versionCode = Integer.parseInt(v.getData().getVersionCode());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (versionCode > AppUtils.getAppVersionCode(context)) {
                            if (isUpdate) {
//                                new AlertDialog.Builder(context).setTitle("版本升级")
//                                        .setMessage("检测到有新版本，是否升级？")
//                                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog,
//                                                                int which) {
//                                                updateApp(context, v.getData());
//                                            }
//                                        }).setNegativeButton("否", null).create().show();

                                CustomDialog.Builder builder = new CustomDialog.Builder(context);
                                builder.setTitle("版本升级");
                                builder.setMessage("检测到有新版本，是否升级？");
                                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        updateApp(context, v.getData());
                                        dialog.dismiss();
                                    }
                                });

                                builder.setNegativeButton("否",
                                        new android.content.DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                builder.create().show();
                            } else {
                                //发送广播刷新设置界面的新版本显示
                                Intent intent = new Intent(Constant.ACTION_NEW_VERSION);
                                intent.putExtra(Constant.VERSION_NAME, v.getData().getVersionName());
                                context.sendBroadcast(intent);
                            }

                        } else {
                            if (showLoading) {
                                Toast.makeText(context, "已是最新版本", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );
    }

    public static void updateApp(final Context context, Versions app) {
        final String appName = context.getString(R.string.app_name);
        final String savePath = Environment.getExternalStorageDirectory()
                .toString()
                + File.separator
                + appName
                + File.separator
                + appName
                + app.getVersionName() + ".apk";

//        Log.e("")

        HttpRequestUtils.getmInstance().downLoadFile(app.getFilePath(), savePath, new HttpRequestCallBack<File>() {
            private NotificationManager updateNotificationManager;
            private Notification updateNotification;
            private NotificationCompat.Builder mBuilder;

            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onSuccess(File result) {
                updateNotificationManager.cancel(101);
                Uri uri = Uri.fromFile(new File(savePath));
                File file = new File(savePath);
                /**
                 * 下载完成后安装
                 */
                if (file.getName().endsWith(".apk")) {
                    Intent intent = new Intent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setAction(android.content.Intent.ACTION_VIEW);
                    intent.setDataAndType(uri, "application/vnd.android.package-archive");
                    context.startActivity(intent);
                }
            }

            @Override
            public void onStarted() {
                super.onStarted();
                // 创建文件
                updateNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                mBuilder = new NotificationCompat.Builder(context);
                mBuilder.setContentTitle("正在更新" + appName);
                mBuilder.setContentText("0%");
                mBuilder.setProgress(100, 0, false);
                mBuilder.setSmallIcon(android.R.drawable.stat_sys_download);
                updateNotification = mBuilder.build();
                updateNotification.flags = Notification.FLAG_AUTO_CANCEL;
                updateNotificationManager.notify(101, updateNotification);
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                super.onLoading(total, current, isDownloading);
                mBuilder.setContentText((int) current * 100 / total + "%");
                mBuilder.setProgress(100, (int) (current * 100 / total), false);
                updateNotification = mBuilder.build();
                updateNotificationManager.notify(101, updateNotification);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                mBuilder.setContentText("下载失败.请重新下载！");
                updateNotification = mBuilder.build();
                updateNotificationManager.notify(101, updateNotification);
            }
        });
    }
}
