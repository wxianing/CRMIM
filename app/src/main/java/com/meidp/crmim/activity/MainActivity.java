package com.meidp.crmim.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.fragment.CompanyFragment;
import com.meidp.crmim.fragment.ContactsFragment;
import com.meidp.crmim.fragment.HomeFragment;
import com.meidp.crmim.fragment.MyFragment;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.http.HttpTask;
import com.meidp.crmim.imkit.ConversationListStaticFragment;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.User;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.IMkitConnectUtils;
import com.meidp.crmim.utils.NetUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.PermissionUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @ViewInject(R.id.main_bottom_rg)
    private RadioGroup mRadioGroup;
    private List<Fragment> mFragments;
    private FragmentManager manager;
    private long exitTime = 0;
    public static MainActivity mainActivity;
    //    @ViewInject(R.id.btn_msg)
//    private Button message;
    public static String userCode;
    private int REQUEST_CONTACTS = 100;

    Handler handler = new Handler();

    private int DELYED = 30 * 1000;

    private UserInfo users = null;
    private List<UserInfo> mDatas;

    @Override
    public void onInit() {
        if (NetUtils.isConnected(this)) {
            login();
        } else {
            ToastUtils.shows(this, "网络异常");
            startActivity(new Intent(this, LoginActivity.class));
            SPUtils.setLoginTag(this, false);
            finish();
        }

        mDatas = new ArrayList<>();
        String token = (String) SPUtils.get(this, "TOKEN", "");
        mainActivity = this;
        mFragments = new ArrayList<>();
        mFragments.add(ConversationListStaticFragment.newInstance("PUBLIC"));
        mFragments.add(new CompanyFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new ContactsFragment());
        mFragments.add(new MyFragment());
        userCode = (String) SPUtils.get(this, "CODE", "");
        ((RadioButton) mRadioGroup.getChildAt(0)).setChecked(true);

        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_fragments_contents, mFragments.get(0));
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack("tag");
        transaction.commit();
        mRadioGroup.setOnCheckedChangeListener(this);//设置监听

        if (Build.VERSION.SDK_INT >= 23) {
            showContacts();
        } else {
            HttpTask.detectionNewAppVersion(this, true, false);//检查版本更新
        }
        handler.postDelayed(runnable, DELYED); //每隔30s执行

        /**
         * 融云登录
         */
        IMkitConnectUtils.connect(token, getApplicationContext());//登录融云

        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String userId) {
                Log.e("userInfo", "userInfo正在执行");
                for (int i = 0; i < mDatas.size(); i++) {
                    RongIM.getInstance().refreshUserInfoCache(mDatas.get(i));//刷新用户数据
                }
                RongIM.getInstance().refreshUserInfoCache(users);//刷新用户数据
                return findUserById(userId);//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
            }
        }, true);
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
        HttpRequestUtils.getmInstance().post(MainActivity.this, Constant.GET_PERSON_INFORMATION, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBean<User> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<User>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    String avatar = appBean.getData().getPhotoURL();
                    String name = appBean.getData().getEmployeeName();
                    users = new UserInfo(userId, name, Uri.parse(avatar));
                    mDatas.add(users);
                    RongIM.getInstance().refreshUserInfoCache(users);//刷新用户数据
                }
            }
        });
        return users;
    }


    int i = 0;
    //定时向融云发送数据
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // handler自带方法实现定时器
            try {
                handler.postDelayed(this, DELYED);
                System.out.println("do..." + i++);
                if (RongIM.getInstance() != null && RongIM.getInstance().getRongIMClient() != null) {
                    /**
                     * 设置连接状态变化的监听器.
                     */
                    RongIM.getInstance().getRongIMClient().setConnectionStatusListener(new MyConnectionStatusListener());
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("exception...");
            }
        }
    };

    /**
     * 监听融云连接状态
     */
    private class MyConnectionStatusListener implements RongIMClient.ConnectionStatusListener {
        String token = (String) SPUtils.get(MainActivity.this, "TOKEN", "");

        @Override
        public void onChanged(ConnectionStatus connectionStatus) {
            switch (connectionStatus) {

                case CONNECTED://连接融云成功。
                    Log.e("融云连接", "融云连接成功");
                    break;
                case DISCONNECTED://断开连接。
                    IMkitConnectUtils.connect(token, MainActivity.this);
                    Log.e("融云连接", "断开");
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
    }

    public void showContacts() {
        // Verify that all required contact permissions have been granted.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestContactsPermissions();
        } else {
            HttpTask.detectionNewAppVersion(this, true, false);//检查版本更新
        }
    }

    private void requestContactsPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_PHONE_STATE)) {
//            Snackbar.make(v, "permission_contacts_rationale", Snackbar.LENGTH_INDEFINITE).setAction("ok", new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
//                }
//            }).show();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
        }
    }

    private String[] PERMISSIONS_CONTACT = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CONTACTS) {
            if (PermissionUtils.verifyPermissions(grantResults)) {
                HttpTask.detectionNewAppVersion(this, true, false);//检查版本更新
            } else {
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (RongIM.getInstance() != null && RongIM.getInstance().getRongIMClient() != null) {
            /**
             * 设置连接状态变化的监听器.
             */
            RongIM.getInstance().getRongIMClient().setConnectionStatusListener(new MyConnectionStatusListener());
            Log.e("监测融云连接状态", "监测融云连接状态");
        }
        for (int i = 0; i < group.getChildCount(); i++) {
            Fragment fragment = mFragments.get(i);
            if (checkedId == group.getChildAt(i).getId()) {
                addFragment(fragment);
            } else {
                FragmentTransaction t = manager.beginTransaction();
                t.hide(fragment);
                t.commit();
            }
        }
    }

    /**
     * 添加Fragment
     *
     * @param fragment
     */
    private void addFragment(Fragment fragment) {
        FragmentTransaction t = manager.beginTransaction();
        if (!fragment.isAdded()) {
            t.add(R.id.main_fragments_contents, fragment);
        }
        t.show(fragment);
        t.commit();
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
            exitSystem();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出系统
     */
    private void exitSystem() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            RongIM.getInstance().disconnect(true);
            finish();
            System.exit(0);
        }
    }

    private void login() {
        String userName = (String) SPUtils.get(this, "USERNAME", "");
        String passWord = (String) SPUtils.get(this, "PASSWORD", "");
        HashMap params = new HashMap();
        params.put("UserName", userName);
        params.put("Password", passWord);

        if (NullUtils.isNull(userName) && NullUtils.isNull(passWord)) {
            HttpRequestUtils.getmInstance().send(MainActivity.this, Constant.LOGIN_URL, params, new HttpCallBack());
        } else {
            ToastUtils.shows(this, "用户名或密码不能为空!");
        }
    }

    class HttpCallBack extends HttpRequestCallBack<String> {
        @Override
        public void onSuccess(String resutl) {
            final AppBean<User> appBean = JSONObject.parseObject(resutl, new TypeReference<AppBean<User>>() {
            });
            if (appBean != null && appBean.getEnumcode() == 0) {
                Constant.setCODE(appBean.getData().getUserCode());//保存userCode
                Constant.setTOKEN(appBean.getData().getRongcloudToken());
                saveData(appBean);
            } else {
                ToastUtils.showl(MainActivity.this, appBean.getMsg());
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                SPUtils.setLoginTag(MainActivity.this, false);
                finish();
            }
        }
    }

    /**
     * @param appBean
     */
    private void saveData(AppBean<User> appBean) {

        HttpRequestUtils.setUserCode(appBean.getData().getUserCode());
        SPUtils.save(this, "USERNAME", appBean.getData().getLoginName());
        SPUtils.save(this, "TOKEN", appBean.getData().getRongcloudToken());
        SPUtils.save(this, "Mobile", appBean.getData().getMobile());
        SPUtils.save(this, "EmployeeName", appBean.getData().getEmployeeName());
        SPUtils.save(this, "DeptName", appBean.getData().getDeptName());
        if (NullUtils.isNull(appBean.getData().getZhiWu())) {
            SPUtils.save(this, "ZHIWU", appBean.getData().getZhiWu());
        }
        SPUtils.save(this, "PhotoURL", appBean.getData().getPhotoURL());//保存头像
        SPUtils.setLoginTag(MainActivity.this, true);//自动登录标志
        String JPushUserId = String.valueOf(appBean.getData().getUserID());
        JPushInterface.setAliasAndTags(this, JPushUserId, null);
    }
}
