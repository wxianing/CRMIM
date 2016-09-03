package com.meidp.crmim.activity;

import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

import io.rong.imkit.RongIM;

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

    /**
     * textview对话框
     */
    private void showDialog() {
        final Dialog dialog = new Dialog(ResetPwdActivity.this, R.style.Dialog);
        View contentView = LayoutInflater.from(ResetPwdActivity.this).inflate(R.layout.dialog_textview_layout, null);
        TextView titleName = (TextView) contentView.findViewById(R.id.title);
        TextView content = (TextView) contentView.findViewById(R.id.hint_content);
        titleName.setText("温馨提示");//标题
        content.setText("是否退出当前账户？");//提示你内容

        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(true);
        Button negativeButton = (Button) contentView.findViewById(R.id.negativeButton);
        negativeButton.setClickable(true);
        //取消按钮点击时间
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//点击取消结束掉当前页面
                dialog.dismiss();
            }
        });
        Button positiveButton = (Button) contentView.findViewById(R.id.positiveButton);
        positiveButton.setClickable(true);
        //确定按钮点击事件
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ResetPwdActivity.this, LoginActivity.class);
                if (MainActivity.mainActivity != null) {
                    MainActivity.mainActivity.finish();
                    MainActivity.mainActivity = null;
                }
                RongIM.getInstance().logout();
                SPUtils.setLoginTag(ResetPwdActivity.this, false);
                SPUtils.remove(ResetPwdActivity.this, "CODE");
//                        SPUtils.clear(getActivity());
                startActivity(intent);
                finish();

                dialog.dismiss();
            }
        });
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        WindowManager wm = getWindowManager();
        Display d = wm.getDefaultDisplay(); // 获取屏幕宽、高用
//        p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
        lp.width = (int) (d.getWidth() * 0.75); // 宽度设置为屏幕的0.65

        dialogWindow.setAttributes(lp);
        dialogWindow.setAttributes(lp);
        dialog.show();
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
//                        SPUtils.setLoginTag(ResetPwdActivity.this, false);
                        showDialog();
//                        finish();
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
