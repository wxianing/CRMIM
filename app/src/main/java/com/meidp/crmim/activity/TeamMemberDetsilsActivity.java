package com.meidp.crmim.activity;

import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.TeamMembers;
import com.meidp.crmim.utils.NullUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_team_member_detsils)
public class TeamMemberDetsilsActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    private TeamMembers data;
    @ViewInject(R.id.name_tv)
    private TextView name_tv;
    @ViewInject(R.id.phone_num)
    private TextView phone_num;
    @ViewInject(R.id.sex_tv)
    private TextView sex_tv;
    @ViewInject(R.id.company_name)
    private TextView company_name;
    @ViewInject(R.id.positions)
    private TextView positions;

    @Override
    public void onInit() {
        title.setText("详情");
        data = (TeamMembers) getIntent().getSerializableExtra("TeamMembers");
        if (data != null) {
            if (NullUtils.isNull(data.getLinkmanName())) {
                name_tv.setText("姓      名：" + data.getLinkmanName());
            } else {
                name_tv.setText("姓      名：");
            }
            if (NullUtils.isNull(data.getCompanyPhone())) {
                phone_num.setText("公司电话:" + data.getCompanyPhone());
            } else {
                phone_num.setText("公司电话:");
            }

            int sexFlag = Integer.valueOf(data.getSex());

            if (sexFlag == 1) {
                sex_tv.setText("性      别：" + "男");
            } else if (sexFlag == 2) {
                sex_tv.setText("性      别：" + "女");
            } else {
                sex_tv.setText("性      别：" + "未填写");
            }
            if (NullUtils.isNull(data.getCompanyName())) {
                company_name.setText("公      司：" + data.getCompanyName());
            } else {
                company_name.setText("公      司：");
            }
            if (NullUtils.isNull(data.getPosition())) {
                positions.setText("职      位：" + data.getPosition());
            }
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
