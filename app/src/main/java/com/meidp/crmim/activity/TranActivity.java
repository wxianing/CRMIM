package com.meidp.crmim.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.meidp.crmim.R;
import com.meidp.crmim.imkit.ConversationListsFragment;

import org.xutils.view.annotation.ContentView;

public class TranActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tran);
//        Log.e("copy--message", CopyUtils.paste(this));
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.content, ConversationListsFragment.newInstance("转发"));
        ft.commit();
        finish();
    }
}
