package com.meidp.crmim.activity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

@ContentView(R.layout.activity_reset_pwd)
public class ResetPwdActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.old_pwd)
    private EditText oldPwd;
    @ViewInject(R.id.new_pwd)
    private EditText newPwd;
    @ViewInject(R.id.affirm_pwd)
    private EditText affirmPwd;

    @Override
    public void onInit() {
        title.setText("修改密码");
    }

    @Event({R.id.back_arrows, R.id.save_btn})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.save_btn:
                sendMsg();
                break;
        }
    }

    private void sendMsg() {
        String oldpwds = oldPwd.getText().toString().trim();
        String newpwds = newPwd.getText().toString().trim();
        String affirmpwds = affirmPwd.getText().toString().trim();
        if (!NullUtils.isNull(oldpwds)) {
            ToastUtils.shows(this, "请输入旧密码");
            return;
        }
        if (!NullUtils.isNull(newpwds)) {
            ToastUtils.shows(this, "请输入新密码");
            return;
        }

        if (!NullUtils.isNull(affirmpwds)) {
            ToastUtils.shows(this, "请输入确认密码");
            return;
        }

        if (newpwds.equals(affirmpwds)) {
            HashMap params = new HashMap();
            params.put("OldPassword", oldpwds);
            params.put("NewPassword", newpwds);
//            Log.e("pwd", JSON.toJSONString(params));
            HttpRequestUtils.getmInstance().send(ResetPwdActivity.this, Constant.RESET_PWD_URL, params, new HttpRequestCallBack() {
                @Override
                public void onSuccess(String result) {
                    Log.e("pwd", result);
                    AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                    });
                    if (appMsg != null && appMsg.getEnumcode() == 0) {
                        ToastUtils.shows(ResetPwdActivity.this, "修改成功");
                        finish();
                    } else {
                        ToastUtils.shows(ResetPwdActivity.this, appMsg.getMsg());
                    }
                }
            });
        } else {
            ToastUtils.shows(this, "您新密码和确认密码不一样");
        }
    }
}
