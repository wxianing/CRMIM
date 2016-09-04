package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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

public class AddFeedbackActivity extends BasicActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private TextView title;
    private EditText contentEt;
    private EditText titleNameEt;

    private RadioGroup mRadioGroup;
    private int type = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);
        onInit();
        onInitEvent();
    }

    private void onInitEvent() {
        findViewById(R.id.back_arrows).setOnClickListener(this);
        findViewById(R.id.submit_btn).setOnClickListener(this);
    }
    private void findById() {
        title = (TextView) findViewById(R.id.title_tv);
        contentEt = (EditText) findViewById(R.id.content_tv);
        titleNameEt = (EditText) findViewById(R.id.title_name);
        mRadioGroup = (RadioGroup) findViewById(R.id.main_bottom_rg);
    }

    public void onInit() {
        findById();
        title.setText("意见反馈");
        mRadioGroup.setOnCheckedChangeListener(this);
        ((RadioButton) mRadioGroup.getChildAt(0)).setChecked(true);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.submit_btn:
                String content = contentEt.getText().toString().trim();
                String titleName = titleNameEt.getText().toString().trim();
                if (NullUtils.isNull(content)) {
                    HashMap params = new HashMap();

                    params.put("Title", titleName);
                    params.put("Content", content);
                    params.put("AdviceType", type);

                    HttpRequestUtils.getmInstance().send(this, Constant.SAVE_FEEDBACK_URL, params, new HttpRequestCallBack() {
                        @Override
                        public void onSuccess(String result) {
                            AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                            });
                            if (appMsg != null && appMsg.getEnumcode() == 0) {
                                ToastUtils.shows(AddFeedbackActivity.this, "保存成功");
                                Intent intent = new Intent();
                                setResult(1032, intent);
                                finish();
                            } else {
                                ToastUtils.shows(AddFeedbackActivity.this, "保存失败");
                            }
                        }
                    });

                } else {
                    ToastUtils.shows(this, "请输入您的意见");
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < group.getChildCount(); i++) {
            if (checkedId == group.getChildAt(i).getId()) {
                type = i + 1;
            }
        }
    }
}
