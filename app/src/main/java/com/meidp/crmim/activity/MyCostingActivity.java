package com.meidp.crmim.activity;

import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 我的成本
 */

@ContentView(R.layout.activity_my_costing)
public class MyCostingActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @Override
    public void onInit() {
        title.setText("我的成本");
    }

    @Event({R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
