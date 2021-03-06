package com.meidp.crmim.activity;

import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

/**
 * 我的业绩
 */
@ContentView(R.layout.activity_my_achievements)
public class MyAchievementsActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;

    @Override
    public void onInit() {
        title.setText("我的成果");
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        HttpRequestUtils.getmInstance().send(MyAchievementsActivity.this, Constant.GET_MY_ACHIEVEMENT, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {

            }
        });
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
