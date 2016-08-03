package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 紧急情况
 */
@ContentView(R.layout.activity_emergency)
public class EmergencyActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    private int emergency = 0;
    private String emergencyStr;

    @Override
    public void onInit() {
        title.setText("选择情况");
    }

    @Event({R.id.loose, R.id.general, R.id.anxious, R.id.urgency, R.id.extra_urgent, R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.loose:
                emergency = 1;
                emergencyStr = "宽松";
                break;
            case R.id.general:
                emergency = 2;
                emergencyStr = "一般";
                break;
            case R.id.anxious:
                emergency = 3;
                emergencyStr = "较急";
                break;
            case R.id.urgency:
                emergency = 4;
                emergencyStr = "紧急";
                break;
            case R.id.extra_urgent:
                emergency = 5;
                emergencyStr = "特级";
                break;
            case R.id.back_arrows:
                finish();
                return;
        }

        Intent intent = new Intent();
        intent.putExtra("emergency", emergency);
        intent.putExtra("emergencyStr",emergencyStr );
        setResult(1008, intent);
        finish();
    }
}
