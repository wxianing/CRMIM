package com.meidp.crmim.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_companytructure)
public class CompanytructureActivity extends BasicActivity implements View.OnClickListener {
    private TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companytructure);
        onInit();
    }

    public void onInit() {
        title = (TextView) findViewById(R.id.title_tv);

        title.setText("组织架构");
        findViewById(R.id.back_arrows).setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
