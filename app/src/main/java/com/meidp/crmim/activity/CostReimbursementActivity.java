package com.meidp.crmim.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.CostEntrity;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 费用报销
 */
@ContentView(R.layout.activity_cost_reimbursement)
public class CostReimbursementActivity extends BaseActivity {

    private static final int SHOW_DATAPICK = 0;
    private static final int DATE_DIALOG_ID = 1;
    public int flag = 0;

    @ViewInject(R.id.title_right)
    private TextView titleRight;

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.customer_et)
    private EditText customerEt;//选这客户
    @ViewInject(R.id.cost_type)
    private EditText costType;//费用类型
    @ViewInject(R.id.apply_time)
    private EditText applyTimeEt;//申请时间
    @ViewInject(R.id.need_time)
    private EditText needTime;//需要时间
    @ViewInject(R.id.pickdate_btn)
    private Button pickDate;
    @ViewInject(R.id.title_et)
    private EditText titleEt;//费用标题
    @ViewInject(R.id.count_et)
    private EditText countEt;//数量
    @ViewInject(R.id.cause_et)
    private EditText causeEt;
    @ViewInject(R.id.remark_et)
    private EditText remarkEt;

    private int typeId;

    private int mYear;
    private int mMonth;
    private int mDay;
    private int custId;
    private int expId;

    @Override
    public void onInit() {
        title.setText("费用报销");
        titleRight.setText("保存");
        titleRight.setVisibility(View.VISIBLE);
    }

    @Event(value = {R.id.customer_et, R.id.cost_type, R.id.apply_time, R.id.need_time, R.id.title_right, R.id.back_arrows})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.customer_et:
                intent = new Intent();
                intent.setClass(this, CustomerListActivity.class);
                startActivityForResult(intent, 1001);
                break;
            case R.id.cost_type://类别
                intent = new Intent();
                intent.setClass(this, CostTypeActivity.class);
                startActivityForResult(intent, 1002);
                break;
            case R.id.apply_time://申请时间
                flag = 1;
                Message msg = new Message();
                if (pickDate.equals((EditText) v)) {
                    msg.what = CostReimbursementActivity.SHOW_DATAPICK;
                }
                CostReimbursementActivity.this.saleHandler.sendMessage(msg);
                break;
            case R.id.need_time://需要时间
                flag = 2;
                Message msg2 = new Message();
                if (pickDate.equals((EditText) v)) {
                    msg2.what = CostReimbursementActivity.SHOW_DATAPICK;
                }
                CostReimbursementActivity.this.saleHandler.sendMessage(msg2);
                break;
            case R.id.title_right:
                sendMsg();
                break;
            case R.id.back_arrows:
                finish();
                break;
        }
    }


    @Override
    public void onInitData() {
//        sendMsg();
    }

    private void sendMsg() {
        String titleStr = titleEt.getText().toString().trim();
        String ariseDate = applyTimeEt.getText().toString().trim();
        String needDate = needTime.getText().toString().trim();
        final String reason = causeEt.getText().toString().trim();
        String remark = remarkEt.getText().toString().trim();
        String count = countEt.getText().toString().trim();

        CostEntrity entrity = new CostEntrity();
        entrity.setExpType(typeId);
        entrity.setExpRemark(remark);
        if (NullUtils.isNull(count)) {
            entrity.setAmount(Double.valueOf(count));
        }
        List<CostEntrity> entrityList = new ArrayList<>();
        entrityList.add(entrity);

        HashMap params = new HashMap();

        params.put("Title", titleStr);
        params.put("CustID", custId);//客户Id
        params.put("ExpType", typeId);//公共分类类别

        params.put("AriseDate", ariseDate);//申请日期
        params.put("NeedDate", needDate);//使用日期
        params.put("Reason", reason);//原因
        params.put("details", entrityList);//原因

        HttpRequestUtils.getmInstance().send(CostReimbursementActivity.this, Constant.COST_SAVE_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("reslut", result);
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    ToastUtils.shows(CostReimbursementActivity.this, "保存成功");
                    finish();
                } else {
                    ToastUtils.shows(CostReimbursementActivity.this, "保存失败");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1001:
                String customName = data.getStringExtra("customName");
                custId = data.getIntExtra("CustomerId", 0);
                customerEt.setText(customName);
                break;
            case 1002:
                String codeName = data.getStringExtra("CodeName");
                typeId = data.getIntExtra("OID", -1);
                expId = data.getIntExtra("ExpID", -1);
                costType.setText(codeName);
                break;
        }
    }


    /**
     * 处理日期控件的Handler
     */

    Handler saleHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CostReimbursementActivity.SHOW_DATAPICK:
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
            applyTimeEt.setText(new StringBuilder()
                    .append(mYear)
                    .append("-")
                    .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                    .append("-")
                    .append((mDay < 10) ? "0" + mDay : mDay));
        }
        if (flag == 2) {
            needTime.setText(new StringBuilder()
                    .append(mYear)
                    .append("-")
                    .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                    .append("-")
                    .append((mDay < 10) ? "0" + mDay : mDay));
        }
    }

}
