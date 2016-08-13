package com.meidp.crmim.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.PrototypeAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.ProduceEntity;
import com.meidp.crmim.model.ProduceEntitys;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.DataUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_convention_apply_for)
public class ConventionApplyForActivity extends BaseActivity {

    private static final int SHOW_DATAPICK = 0;
    private static final int DATE_DIALOG_ID = 1;
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_right)
    private TextView titleRight;
    @ViewInject(R.id.title_name)
    private EditText titleNameEt;
    @ViewInject(R.id.linkman)
    private EditText linkmanEt;
    @ViewInject(R.id.link_phone)
    private EditText linkPhoneEt;
    @ViewInject(R.id.perosn_count)
    private EditText perosnCountEt;
    @ViewInject(R.id.start_date)
    private EditText startdateEt;
    @ViewInject(R.id.total_money)
    private EditText totalMoneyEt;
    @ViewInject(R.id.plan_content)
    private EditText planContentEt;
    @ViewInject(R.id.unionPartner)
    private EditText unionPartnerEt;
    @ViewInject(R.id.competitors)
    private EditText competitorsEt;
    @ViewInject(R.id.address_et)
    private EditText addressEt;
    @ViewInject(R.id.over_date)
    private EditText overDateEt;
    public int flag = 0;
    @ViewInject(R.id.pickdate_btn)
    private Button pickDate;
    @ViewInject(R.id.give_back_date)
    private EditText giveBackDateEt;

    @ViewInject(R.id.model_machine)
    private EditText modelMachine;
    private String productName;
    private int productID;

    private List<ProduceEntitys> entityList;

    @Override
    public void onInit() {
        title.setText("地方参展申请单");
        titleRight.setText("申请");
        titleRight.setVisibility(View.VISIBLE);
        startdateEt.setText(DataUtils.getDate2());
        overDateEt.setText(DataUtils.getDate2());
        giveBackDateEt.setText(DataUtils.getDate2());
        entityList = new ArrayList<>();
    }

    @Event({R.id.back_arrows, R.id.title_right, R.id.start_date, R.id.over_date, R.id.give_back_date, R.id.model_machine})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                sendMsg();
                break;

            case R.id.start_date://申请时间
                flag = 1;
                Message msg = new Message();
                if (pickDate.equals((EditText) v)) {
                    msg.what = ConventionApplyForActivity.SHOW_DATAPICK;
                }
                ConventionApplyForActivity.this.saleHandler.sendMessage(msg);
                break;
            case R.id.over_date://需要时间
                flag = 2;
                Message msg2 = new Message();
                if (pickDate.equals((EditText) v)) {
                    msg2.what = ConventionApplyForActivity.SHOW_DATAPICK;
                }
                ConventionApplyForActivity.this.saleHandler.sendMessage(msg2);
                break;
            case R.id.give_back_date:
                flag = 3;
                Message msg3 = new Message();
                if (pickDate.equals((EditText) v)) {
                    msg3.what = ConventionApplyForActivity.SHOW_DATAPICK;
                }
                ConventionApplyForActivity.this.saleHandler.sendMessage(msg3);
                break;
            case R.id.model_machine:
                Intent intent = new Intent(this, ProduceCenterActivity.class);
                startActivityForResult(intent, 1009);
                break;
        }
    }

    private void sendMsg() {
        String titleName = titleNameEt.getText().toString().trim();
        String linkman = linkmanEt.getText().toString().trim();
        String linkPhone = linkPhoneEt.getText().toString().trim();
        String perosnCount = perosnCountEt.getText().toString().trim();
        String startDate = startdateEt.getText().toString().trim();
        String totalMoney = totalMoneyEt.getText().toString().trim();
        String planContent = planContentEt.getText().toString().trim();
        String unionPartner = unionPartnerEt.getText().toString().trim();
        String competitors = competitorsEt.getText().toString().trim();
        String address = addressEt.getText().toString().trim();

        ProduceEntitys entity = new ProduceEntitys();
        entity.setProductID(productID);
        entity.setProductCount(2);
        entityList.add(entity);

        HashMap params = new HashMap();
        params.put("Title", titleName);//标题
        params.put("LinkManName", linkman);//联系人
        params.put("LinkTel", linkPhone);//联系人
        params.put("AttendPersons", Integer.valueOf(perosnCount));//人数
        params.put("TotalMoney", Integer.valueOf(totalMoney));//赞助费用
        params.put("ExhibitionStartDate", startDate);//开始时间
        params.put("ExhibitionEndDate", startDate);//开始时间
        params.put("ExhibitionPlan", planContent);//计划
        params.put("ExhibitionAim", planContent);//目标
        params.put("UnionPartner", unionPartner);//合作伙伴
        params.put("Competitors", competitors);//竞争对手
        params.put("Address", address);
        params.put("Details", entityList);

        HttpRequestUtils.getmInstance().send(ConventionApplyForActivity.this, Constant.SAVE_CONVERNTIONAPPLYFOR, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    ToastUtils.shows(ConventionApplyForActivity.this, "保存成功");
                    finish();
                } else {
                    ToastUtils.shows(ConventionApplyForActivity.this, appMsg.getMsg());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1009 && data != null) {
            productID = data.getIntExtra("ProductID", 0);
            productName = data.getStringExtra("ProductName");
            modelMachine.setText(productName);
        }
    }

    /**
     * 处理日期控件的Handler
     */

    Handler saleHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConventionApplyForActivity.SHOW_DATAPICK:
                    showDialog(DATE_DIALOG_ID);
                    break;
            }
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }

    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
                break;
        }
    }

    /**
     * 日期控件的事件
     */

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };


    /**
     * 更新日期
     */
    private void updateDisplay() {
        if (flag == 1) {
            startdateEt.setText(new StringBuilder()
                    .append(mYear)
                    .append("-")
                    .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                    .append("-")
                    .append((mDay < 10) ? "0" + mDay : mDay));
        }
        if (flag == 2) {
            overDateEt.setText(new StringBuilder()
                    .append(mYear)
                    .append("-")
                    .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                    .append("-")
                    .append((mDay < 10) ? "0" + mDay : mDay));
        }
        if (flag == 3) {
            giveBackDateEt.setText(new StringBuilder()
                    .append(mYear)
                    .append("-")
                    .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                    .append("-")
                    .append((mDay < 10) ? "0" + mDay : mDay));
        }
    }
}
