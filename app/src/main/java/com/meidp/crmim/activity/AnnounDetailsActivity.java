package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

/**
 * 公告详情
 */
public class AnnounDetailsActivity extends BasicActivity implements View.OnClickListener {

    private TextView title;
    private TextView newsTitleTv;
    private TextView newsContentTv;
    private TextView createTime;
    private int isRead;
    private int oid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announ_details);
        onInit();
        onInitData();
        initEvent();
    }

    private void initEvent() {
        findViewById(R.id.back_arrows).setOnClickListener(this);
    }


    public void onInit() {
        title = (TextView) findViewById(R.id.title_tv);
        newsTitleTv = (TextView) findViewById(R.id.news_title);
        newsContentTv = (TextView) findViewById(R.id.news_content);
        createTime = (TextView) findViewById(R.id.create_time);

        title.setText("公告详情");
        Intent intent = getIntent();
        String titleString = intent.getStringExtra("newsTitle");
        String content = intent.getStringExtra("newsContent");
        String createtime = intent.getStringExtra("createTime");
        newsTitleTv.setText(titleString);
        newsContentTv.setText(content);
        createTime.setText(createtime);
        isRead = getIntent().getIntExtra("IsRead", -1);
        oid = getIntent().getIntExtra("OID", -1);
    }

    public void onInitData() {
        if (isRead == 0) {
            sendMsg();
        }
    }

    /**
     * "IdTwo": 1,
     * "IdThree": 2,
     * "Id": 3
     */
    private void sendMsg() {
        HashMap params = new HashMap();

        params.put("IdTwo", 2);
        params.put("IdThree", 24);
        params.put("Id", oid);

        HttpRequestUtils.getmInstance().send(AnnounDetailsActivity.this, Constant.SAVE_UNREADER, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("未读标记result", result);
                Log.e("未读标记result", result);
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    Log.e("未读标记", "标记成功");
                } else {
                    Log.e("未读标记", "标记失败");
                }
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                Intent intent = new Intent(this, AnnouncementActivity.class);

                if (AnnouncementActivity.announcementActivity != null) {
                    AnnouncementActivity.announcementActivity.finish();
                    AnnouncementActivity.announcementActivity = null;
                }
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent(this, AnnouncementActivity.class);
            if (AnnouncementActivity.announcementActivity != null) {
                AnnouncementActivity.announcementActivity.finish();
                AnnouncementActivity.announcementActivity = null;
            }
            startActivity(intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
