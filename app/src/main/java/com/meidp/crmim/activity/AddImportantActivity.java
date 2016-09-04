package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class AddImportantActivity extends BasicActivity implements View.OnClickListener {
    private TextView title;
    private EditText titleEt;
    private EditText contentEt;
    private TextView empolyeeName;
    private String empolyeeId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_important);
        onInit();
        initEvent();
    }

    private void initEvent() {
        findViewById(R.id.back_arrows).setOnClickListener(this);
        findViewById(R.id.submit_btn).setOnClickListener(this);
        findViewById(R.id.add_empolyee).setOnClickListener(this);
    }


    public void onInit() {
        title = (TextView) findViewById(R.id.title_tv);
        titleEt = (EditText) findViewById(R.id.title_name);
        contentEt = (EditText) findViewById(R.id.content_tv);
        empolyeeName = (TextView) findViewById(R.id.empolyee_name);
        title.setText("添加重要事项");
    }

    public void onClick(View v) {
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
            Log.e("empolyeeId", empolyeeId);
            Log.e("empolyeeName", empolyeeNames);
        }
    }
}
