package com.meidp.crmim.imkit;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ScrollView;

import com.meidp.crmim.R;
import com.meidp.crmim.utils.CopyUtils;

/**
 * Package： com.meidp.crmim.imkit
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/9
 */
public class ConversationListActivity extends FragmentActivity {

    public static ConversationListActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_conversationlist);
        String msgType = getIntent().getStringExtra("TYPE");
//        Log.e("copy--message", CopyUtils.paste(this));
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.content, ConversationListsFragment.newInstance("转发", msgType));
        ft.commit();
//        finish();
    }
}
