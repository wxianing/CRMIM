package com.meidp.crmim;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.baidu.mapapi.SDKInitializer;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.http.OkHttpStack;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.User;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NetUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.utils.ToastUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.xutils.x;

import java.util.HashMap;
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
    public static DisplayImageOptions optionsRounds;
    private List<Activity> activitys = new LinkedList<Activity>();
    public static final String TAG = "VolleyPatterns";
    private RequestQueue mRequestQueue;
    public static String userCode;
    private UserInfo users;
    private List<UserInfo> mDatas;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        initImageLoader(getApplicationContext());//初始化百度地图
        SDKInitializer.initialize(getApplicationContext());//百度
        JPushInterface.setDebugMode(true);//极光
        JPushInterface.init(this);//极光
        /**
         * 初始化融云
         * io.rong.push 为融云 push 进程名称，不可修改。
         */

        if (NetUtils.isConnected(getApplicationContext())) {

            if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                    "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
                RongIM.init(this);
//            String token = (String) SPUtils.get(getApplicationContext(), "TOKEN", "");
//            if (NullUtils.isNull(token)) {
//                IMkitConnectUtils.connect(token, getApplicationContext());
//                RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
//                    @Override
//                    public UserInfo getUserInfo(String userId) {
//                        Log.e("userInfo", "userInfo正在执行");
//                        for (int i = 0; i < mDatas.size(); i++) {
//                            RongIM.getInstance().refreshUserInfoCache(mDatas.get(i));//刷新用户数据
//                        }
//                        RongIM.getInstance().refreshUserInfoCache(users);//刷新用户数据
//                        return findUserById(userId);//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
//                    }
//                }, true);
//            }
            }
        } else {
            ToastUtils.shows(getApplicationContext(), "当前网络不可用，请检查网络");
        }
        userCode = (String) SPUtils.get(this, "CODE", "");
    }

    /**
     * 根据Id到本地服务器查找个人信息
     *
     * @param userId
     * @return
     */
    private UserInfo findUserById(final String userId) {
        HashMap params = new HashMap();
        params.put("Id", Integer.valueOf(userId));
        HttpRequestUtils.getmInstance().post(getApplicationContext(), Constant.GET_PERSON_INFORMATION, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBean<User> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<User>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    String avatar = appBean.getData().getPhotoURL();
                    String name = appBean.getData().getEmployeeName();
                    users = new UserInfo(userId, name, Uri.parse(avatar));
                    RongIM.getInstance().refreshUserInfoCache(users);//刷新用户数据
                }
            }
        });
        return users;
    }

    public static void initImageLoader(Context context) {
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.default_icon)//加载等待 时显示的图片
                .showImageForEmptyUri(R.mipmap.default_icon)//加载数据为空时显示的图片
                .showImageOnFail(R.mipmap.default_icon)//加载失败时显示的图片
                .cacheInMemory()
                .cacheOnDisc()
                .build();

        optionsRounds = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.headerphoto)
                .showImageForEmptyUri(R.mipmap.headerphoto)
                .showImageOnFail(R.mipmap.headerphoto)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.ARGB_8888)   //设置图片的解码类型
                .displayer(new RoundedBitmapDisplayer(10))
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
