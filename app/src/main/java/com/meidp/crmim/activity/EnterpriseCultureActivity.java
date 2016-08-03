package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_enterprise_culture)
public class EnterpriseCultureActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @Override
    public void onInit() {
        title.setText("企业文化");
    }

    @Event({R.id.back_arrows, R.id.information_sharing, R.id.rules_and_regulations, R.id.companys_magazine})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {

            case R.id.companys_magazine:
                intent = new Intent(this, MyKnowledgeActivity.class);
                intent.putExtra("sType", 9);
                startActivity(intent);
                break;
            case R.id.rules_and_regulations:
                intent = new Intent(this, MyKnowledgeActivity.class);
                intent.putExtra("sType", 10);
                startActivity(intent);
                break;
            case R.id.information_sharing:
                intent = new Intent(this, MyKnowledgeActivity.class);
                intent.putExtra("sType", 11);
                startActivity(intent);
                break;
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
