package com.meidp.crmim.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.imkit.ConversationListStaticFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_near_contacts)
public class NearContactsActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;

    @Override
    public void onInit() {
        title.setText("常用联系人 ");
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ConversationListStaticFragment fragment = ConversationListStaticFragment.newInstance("PRIVATE");
        ft.replace(R.id.content_tv, fragment);
        ft.commit();
    }

    @Event({R.id.back_arrows})
    private void onClick(View v) {
        finish();
    }
}
