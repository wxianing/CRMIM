package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 公告详情
 */
@ContentView(R.layout.activity_announ_details)
public class AnnounDetailsActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.news_title)
    private TextView newsTitleTv;
    @ViewInject(R.id.news_content)
    private TextView newsContentTv;
    @ViewInject(R.id.create_time)
    private TextView createTime;

    @Override
    public void onInit() {
        title.setText("公告详情");
        Intent intent = getIntent();
        String titleString = intent.getStringExtra("newsTitle");
        String content = intent.getStringExtra("newsContent");
        String createtime = intent.getStringExtra("createTime");
        newsTitleTv.setText(titleString);
        newsContentTv.setText(content);
        createTime.setText(createtime);
    }

    @Event(value = {R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
