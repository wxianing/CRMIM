package com.meidp.crmim.activity;

import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.VisitRecords;
import com.meidp.crmim.utils.NullUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_visit_recode_details)
public class VisitRecodeDetailsActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_name)
    private TextView titleName;
    @ViewInject(R.id.cust_name)
    private TextView custName;
    @ViewInject(R.id.address_tv)
    private TextView address;
    @ViewInject(R.id.vist_time)
    private TextView vist_time;
    @ViewInject(R.id.content_tv)
    private TextView content;

    @Override
    public void onInit() {
        title.setText("拜访详情");
        VisitRecords records = (VisitRecords) getIntent().getSerializableExtra("VisitRecords");
        if (records != null) {
            titleName.setText(records.getTitle());
            if (NullUtils.isNull(records.getCustName())) {
                custName.setText("客户名称：" + records.getCustLinkManName());
            } else {
                custName.setText("客户名称：");
            }
            vist_time.setText("拜访时间：" + records.getModifiedDate());
            address.setText("地址：" + records.getLocationAddress());
            content.setText("内容：" + records.getContents());
        }
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
