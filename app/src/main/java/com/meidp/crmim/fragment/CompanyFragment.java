package com.meidp.crmim.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.activity.AnnouncementActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Package： com.meidp.crmim.fragment
 * Author： wxianing
 * 作  用：公 司 页面
 * 时  间： 2016/8/6
 */
@ContentView(R.layout.fragment_company)
public class CompanyFragment extends BaseFragment {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.back_arrows)
    private ImageView backImg;

    @Override
    public void onInit() {
        backImg.setVisibility(View.GONE);
        title.setText("公司");
    }

    @Event({R.id.company_announcement_layout, R.id.company_institutional_layout, R.id.company_cultrue_layout, R.id.company_news_layout, R.id.company_activities_layout, R.id.company_structure_layout})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.company_announcement_layout://公告
                intent = new Intent(getActivity(), AnnouncementActivity.class);
                startActivity(intent);
                break;
            case R.id.company_institutional_layout://制度
                break;
            case R.id.company_cultrue_layout://文化
                break;
            case R.id.company_news_layout://新闻
                break;
            case R.id.company_activities_layout://活动
                break;
            case R.id.company_structure_layout://组织结构
                break;
        }
    }
}
