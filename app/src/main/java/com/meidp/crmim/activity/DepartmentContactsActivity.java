package com.meidp.crmim.activity;

import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_department_contacts)
public class DepartmentContactsActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;

    @Override
    public void onInit() {
        title.setText("我的部门");
    }

    @Event({R.id.back_arrows})
    private void click(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
