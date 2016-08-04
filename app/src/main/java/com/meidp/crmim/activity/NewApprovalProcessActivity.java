package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_new_approval_process)
public class NewApprovalProcessActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @Override
    public void onInit() {
        title.setText("审批");
    }

    @Event({R.id.back_arrows, R.id.model_machine, R.id.cost})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.model_machine:
                intent = new Intent(this, ModelMachineApplyActivity.class);
                startActivity(intent);
                break;
            case R.id.cost:
                intent = new Intent(this, CostReimbursementActivity.class);
                startActivity(intent);
                break;
        }
    }
}
