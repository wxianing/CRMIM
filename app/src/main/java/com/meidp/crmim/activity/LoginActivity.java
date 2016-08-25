package com.meidp.crmim.activity;

import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.User;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.IMkitConnectUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
    @ViewInject(R.id.username_edittext)
    private EditText usernameEt;
    @ViewInject(R.id.password_edittext)
    private EditText passwordEt;

    private String userName;
    private String passWord;
    private HttpCallBack mCallBack;

    private String user;
    private String pwd;
    @ViewInject(R.id.checked_img)
    private CheckBox checkBox;
    private boolean isRmb;
    private String headPhotoS;
    private String name;
    private UserInfo users = null;

    private List<UserInfo> mDatas;


    @Override
    public void onInit() {
        mCallBack = new HttpCallBack();
        //把光标移到最后
        Editable et = usernameEt.getText();
        usernameEt.setSelection(et.length());
        usernameEt.clearFocus();//默认不获取光标
        mDatas = new ArrayList<>();

        //记住密码
        isRmb = (boolean) SPUtils.get(LoginActivity.this, "isRmb", false);
        if (isRmb) {
            user = (String) SPUtils.get(this, "USERNAME", "");
            pwd = (String) SPUtils.get(this, "PASSWORD", "");
            checkBox.setChecked(true);
            if (NullUtils.isNull(user)) {
                usernameEt.setText(user);
            }
            if (NullUtils.isNull(pwd)) {
                passwordEt.setText(pwd);
            }
        }
        /**
         * 记住密码时间监听
         */
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SPUtils.save(LoginActivity.this, "isRmb", true);
                } else {
                    SPUtils.save(LoginActivity.this, "isRmb", false);
                }
            }
        });

        //自动登录
        boolean isLogin = SPUtils.getLoginTag(this);
        if (isLogin) {
            login();
        }
    }

    @Event(value = {R.id.login_btn})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                login();
                break;
        }
    }

    private void login() {
        userName = usernameEt.getText().toString().trim();
        passWord = passwordEt.getText().toString().trim();
        HashMap params = new HashMap();
        params.put("UserName", userName);
        params.put("Password", passWord);

        if (NullUtils.isNull(userName) && NullUtils.isNull(passWord)) {
            HttpRequestUtils.getmInstance().send(LoginActivity.this, Constant.LOGIN_URL, params, mCallBack);
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
                SPUtils.save(LoginActivity.this, "CODE", appBean.getData().getUserCode());
                saveData(appBean);

                headPhotoS = appBean.getData().getPhotoURL();
                name = appBean.getData().getEmployeeName();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

                IMkitConnectUtils.connect(appBean.getData().getRongcloudToken(), getApplicationContext());

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
                finish();
            } else {
                ToastUtils.shows(LoginActivity.this, appBean.getMsg());
            }
        }
    }

    private UserInfo findUserById(final String userId) {
        HashMap params = new HashMap();
        params.put("Id", Integer.valueOf(userId));
        HttpRequestUtils.getmInstance().post(LoginActivity.this, Constant.GET_PERSON_INFORMATION, params, new HttpRequestCallBack() {
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

    private void saveData(AppBean<User> appBean) {

        HttpRequestUtils.setUserCode(appBean.getData().getUserCode());
        Log.e("CODE", ">>>>>>>" + (String) SPUtils.get(this, "CODE", ""));
        SPUtils.save(this, "USERNAME", userName);
        SPUtils.save(this, "NICENAME", userName);
        SPUtils.save(this, "PASSWORD", passWord);
        SPUtils.save(this, "TOKEN", appBean.getData().getRongcloudToken());

        SPUtils.save(this, "Mobile", appBean.getData().getMobile());

        SPUtils.save(this, "EmployeeName", appBean.getData().getEmployeeName());
        SPUtils.save(this, "DeptName", appBean.getData().getDeptName());
        if (NullUtils.isNull(appBean.getData().getZhiWu())) {
            SPUtils.save(this, "ZHIWU", appBean.getData().getZhiWu());
        }
        SPUtils.save(this, "PhotoURL", appBean.getData().getPhotoURL());//保存头像
        SPUtils.setLoginTag(LoginActivity.this, true);//自动登录标志
        String JPushUserId = String.valueOf(appBean.getData().getUserID());
        JPushInterface.setAliasAndTags(this, JPushUserId, null);
    }
}
