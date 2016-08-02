package com.meidp.crmim;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.baidu.mapapi.SDKInitializer;
import com.meidp.crmim.http.OkHttpStack;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.xutils.x;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

/**
 * Package：com.meidp.crmim
 * 作  用：
 * Author：wxianing
 * 时  间：2016/6/18
 */
public class MyApplication extends Application {

    public static DisplayImageOptions options;

    private static MyApplication mInstance;
    private List<Activity> activitys = new LinkedList<Activity>();
    public static final String TAG = "VolleyPatterns";
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        initImageLoader(getApplicationContext());//初始化百度地图
        SDKInitializer.initialize(getApplicationContext());
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);


        /**
         * 初始化融云
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
            RongIM.init(this);

//            RongIM.getInstance().setCurrentUserInfo(findUserById("10011"));
//            RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
//                @Override
//                public UserInfo getUserInfo(String userId) {
//                    Log.e("userId>>>>>>>>>", userId);
//                    return findUserById(userId);
//                }
//            }, true);
        }
    }

    /**
     * 设置头像、用户名等信息
     *
     * @param userId
     * @return
     */
    private UserInfo findUserById(String userId) {
        UserInfo userInfo = null;
        if (userInfo == null) {
            userInfo = new UserInfo(userId, userId, Uri.parse("http://www.qqbody.com/uploads/allimg/201411/18-200811_37.jpg"));
        }
        return userInfo;
    }

    public static void initImageLoader(Context context) {
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.default_icon)//加载等待 时显示的图片
                .showImageForEmptyUri(R.mipmap.default_icon)//加载数据为空时显示的图片
                .showImageOnFail(R.mipmap.default_icon)//加载失败时显示的图片
                .cacheInMemory()
                .cacheOnDisc()
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    public static synchronized MyApplication getmInstance() {
        return mInstance;
    }


    public void addActivity(Activity activity) {
        activitys.add(activity);
    }

    public void removeActivity(Activity activity) {
        activitys.remove(activity);
    }

    public void closeActivitys() {
        ListIterator<Activity> iterator = activitys.listIterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 获取当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    public RequestQueue getmRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext(), new OkHttpStack());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        VolleyLog.d("Adding request to queue: %s", req.getUrl());
        getmRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getmRequestQueue().add(req);
    }
}
