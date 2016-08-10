package com.meidp.crmim.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.ProjectDetails;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_follow_list)
public class FollowListActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.listview)
    private ListView mListView;

    private FollowAdapter mAdapter;

    @Override
    public void onInit() {
        title.setText("项目跟进");
        ArrayList<ProjectDetails.ConstructionDetailsBean> mDatas = (ArrayList<ProjectDetails.ConstructionDetailsBean>) getIntent().getSerializableExtra("listobj");
        mAdapter = new FollowAdapter(mDatas, this);
//        mListView.setAdapter(mAdapter);
    }

    @Event({R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
