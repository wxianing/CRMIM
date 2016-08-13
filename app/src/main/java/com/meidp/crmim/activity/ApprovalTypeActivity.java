package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_approval_type)
public class ApprovalTypeActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;

    @Override
    public void onInit() {
        title.setText("审批");
    }

    @Event({R.id.back_arrows,R.id.cost,R.id.prototype})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.cost:
                intent = new Intent(this,ApprovalCostActivity.class);
                startActivity(intent);
                break;
            case R.id.prototype:
                intent = new Intent(this,ApprovalProcessActivity.class);
                startActivity(intent);
                break;

        }
    }
}
