package com.meidp.crmim.activity;

import android.view.View;
import android.widget.EditText;
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

@ContentView(R.layout.activity_add_feedback)
public class AddFeedbackActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.content_tv)
    private EditText contentEt;
    @ViewInject(R.id.title_name)
    private EditText titleNameEt;

    @Override
    public void onInit() {
        title.setText("意见反馈");
    }

    @Event({R.id.back_arrows, R.id.submit_btn})
    private void onClick(View v) {
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

                    HttpRequestUtils.getmInstance().send(this, Constant.SAVE_FEEDBACK_URL, params, new HttpRequestCallBack() {
                        @Override
                        public void onSuccess(String result) {
                            AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                            });
                            if (appMsg != null && appMsg.getEnumcode() == 0) {
                                ToastUtils.shows(AddFeedbackActivity.this, "保存成功");
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
}
