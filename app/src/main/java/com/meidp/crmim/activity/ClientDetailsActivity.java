package com.meidp.crmim.activity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.ClientContacts;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NullUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

@ContentView(R.layout.activity_client_details)
public class ClientDetailsActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.name_tv)
    private TextView name;
    @ViewInject(R.id.phone_num)
    private TextView phone_num;
    @ViewInject(R.id.sex_tv)
    private TextView sex_tv;
    @ViewInject(R.id.company_name)
    private TextView company_name;
    @ViewInject(R.id.keshi)
    private TextView keshi;
    @ViewInject(R.id.positions)
    private TextView positions;
    private int oid;

    @Override
    public void onInit() {
        title.setText("客户详情");
        oid = getIntent().getIntExtra("OID", 0);
        ClientContacts contacts = (ClientContacts) getIntent().getSerializableExtra("ClientContacts");
//        if (contacts != null) {
//            name.setText("姓     名：" + contacts.getLinkManName());
//            phone_num.setText("工作电话：" + contacts.getWorkTel());
//            sex_tv.setText("性       别：" + contacts.getSex());
//            company_name.setText("医院/公司：" + contacts.getCustName());
//            keshi.setText("科        室：" + contacts.getDepartment());
//            positions.setText("职        位：" + contacts.getPosition());
//        }
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("Id", oid);
        HttpRequestUtils.getmInstance().send(this, Constant.LINKMAN_DETAILS_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("客户详情", result);
                AppBean<ClientContacts> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<ClientContacts>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    if (NullUtils.isNull(appBean.getData().getLinkManName())) {
                        name.setText("姓         名：" + appBean.getData().getLinkManName());
                    } else {
                        name.setText("姓         名：");
                    }
                    if (NullUtils.isNull(appBean.getData().getWorkTel())) {
                        phone_num.setText("工作电话：" + appBean.getData().getWorkTel());
                    } else {
                        phone_num.setText("工作电话：");
                    }
                    if (NullUtils.isNull(appBean.getData().getSex())) {
                        if (appBean.getData().getSex().equals("1")) {
                            sex_tv.setText("性       别：" + "男");
                        } else if (appBean.getData().getSex().equals("2")) {
                            sex_tv.setText("性       别：" + "女");
                        } else {
                            sex_tv.setText("性       别：" + "未填");
                        }
                    } else {
                        sex_tv.setText("性       别：");
                    }
                    if (NullUtils.isNull(appBean.getData().getCustName())) {
                        company_name.setText("医院/公司：" + appBean.getData().getCustName());
                    }
                    if (NullUtils.isNull(appBean.getData().getDepartment())) {
                        keshi.setText("科        室：" + appBean.getData().getDepartment());
                    }
                    if (NullUtils.isNull(appBean.getData().getPosition())) {
                        positions.setText("职        位：" + appBean.getData().getPosition());
                    }
                }
            }
        });
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
