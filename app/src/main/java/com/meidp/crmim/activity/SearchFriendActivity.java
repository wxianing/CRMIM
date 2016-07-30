package com.meidp.crmim.activity;

import android.view.View;
import android.widget.EditText;

import com.meidp.crmim.R;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_search_friend)
public class SearchFriendActivity extends BaseActivity {
    @ViewInject(R.id.search_edittext)
    private EditText searchEt;

    @Event(value = {R.id.search_btn})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_btn:
                String keyWord = searchEt.getText().toString().trim();
                if (NullUtils.isNull(keyWord)) {
                    ToastUtils.shows(this, keyWord);
                }
                break;
        }
    }
}
