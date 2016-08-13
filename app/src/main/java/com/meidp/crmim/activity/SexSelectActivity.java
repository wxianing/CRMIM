package com.meidp.crmim.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import io.rong.imkit.model.ConversationProviderTag;

@ContentView(R.layout.activity_sex_select)
public class SexSelectActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @Override
    public void onInit() {
        title.setText("请选择性别");
    }

    @Event({R.id.back_arrows, R.id.man, R.id.woman})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.man:
                intent = new Intent();
                intent.putExtra("SEX", 1);
                intent.putExtra("SEXTYPE", "男");
                setResult(1010, intent);
                finish();
                break;
            case R.id.woman:
                intent = new Intent();
                intent.putExtra("SEX", 2);
                intent.putExtra("SEXTYPE", "女");
                setResult(1010, intent);
                finish();
                break;
        }
    }
}
