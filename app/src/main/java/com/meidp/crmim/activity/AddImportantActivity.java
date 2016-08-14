package com.meidp.crmim.activity;

import android.content.Intent;
import android.util.Log;
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
import com.meidp.crmim.utils.LogUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

@ContentView(R.layout.activity_add_important)
public class AddImportantActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_name)
    private EditText titleEt;
    @ViewInject(R.id.content_tv)
    private EditText contentEt;
    @ViewInject(R.id.empolyee_name)
    private TextView empolyeeName;
    private String empolyeeId;

    @Override
    public void onInit() {
        title.setText("添加重要事项");
    }

    @Event({R.id.back_arrows, R.id.submit_btn,R.id.add_empolyee})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.submit_btn:
                String titleName = titleEt.getText().toString().trim();
                String content = contentEt.getText().toString().trim();
                HashMap hashMap = new HashMap();
                hashMap.put("ArrangeTItle", titleName);
                hashMap.put("content", content);
                hashMap.put("CanViewUser", empolyeeId);

                HttpRequestUtils.getmInstance().send(AddImportantActivity.this, Constant.ADD_IMPORTANT_URL, hashMap, new HttpRequestCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                        });
                        if (appMsg != null && appMsg.getEnumcode() == 0) {
                            ToastUtils.shows(AddImportantActivity.this, "保存成功");
                            finish();
                        } else {
                            ToastUtils.shows(AddImportantActivity.this, appMsg.getMsg());
                        }
                    }
                });

                break;
            case R.id.add_empolyee:
                Intent intent = new Intent(this, SelectEmpolyeeActivity.class);
                startActivityForResult(intent, 1013);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1013) {
            empolyeeId = data.getStringExtra("EmpolyeeId");
            String empolyeeNames = data.getStringExtra("EmpolyeeName");
            empolyeeName.setText(empolyeeNames);
            Log.e("empolyeeId",empolyeeId);
            Log.e("empolyeeName",empolyeeNames);
        }
    }
}
