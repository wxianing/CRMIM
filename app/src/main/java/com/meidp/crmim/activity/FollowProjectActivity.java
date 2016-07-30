package com.meidp.crmim.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

/**
 * 项目跟进
 */
@ContentView(R.layout.activity_follow_project)
public class FollowProjectActivity extends BaseActivity {

    private static final int SHOW_DATAPICK = 0;
    private static final int DATE_DIALOG_ID = 1;
    @ViewInject(R.id.title_right)
    private TextView titleRight;

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.start_date)
    private EditText startDate;
    @ViewInject(R.id.over_date)
    private EditText overDate;
    @ViewInject(R.id.pickdate_btn)
    private Button pickDate;
    @ViewInject(R.id.budget_cost)
    private EditText budgetcost;
    @ViewInject(R.id.person_count)
    private EditText personCount;
    @ViewInject(R.id.rate)
    private EditText rate;
    @ViewInject(R.id.remark_et)
    private EditText remark;
    @ViewInject(R.id.abstract_message)
    private EditText abstractMessage;


    private int mYear;
    private int mMonth;
    private int mDay;
    public int flag = 0;
    private int projectId;

    @Override
    public void onInit() {
        title.setText("项目跟进");
        titleRight.setText("保存");
        titleRight.setVisibility(View.VISIBLE);
        projectId = getIntent().getIntExtra("OID", -1);
    }

    @Event(value = {R.id.back_arrows, R.id.start_date, R.id.over_date, R.id.title_right})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.start_date:
                flag = 1;
                Message msg = new Message();
                if (pickDate.equals((EditText) v)) {
                    msg.what = FollowProjectActivity.SHOW_DATAPICK;
                }
                FollowProjectActivity.this.saleHandler.sendMessage(msg);
                break;
            case R.id.over_date:
                flag = 2;
                Message msg2 = new Message();
                if (pickDate.equals((EditText) v)) {
                    msg2.what = FollowProjectActivity.SHOW_DATAPICK;
                }
                FollowProjectActivity.this.saleHandler.sendMessage(msg2);
                break;
            case R.id.title_right:
                sendMsg();
                break;
        }
    }

    private void sendMsg() {

        String startDates = startDate.getText().toString().trim();
        String overDates = overDate.getText().toString().trim();
        String budgetCost = budgetcost.getText().toString().trim();
        String personCounts = personCount.getText().toString().trim();
        String rates = rate.getText().toString().trim();
        String remarks = remark.getText().toString().trim();
        String abstractMessages = abstractMessage.getText().toString();
        HashMap params = new HashMap();

        params.put("SummaryName", abstractMessages);//摘要
        params.put("projectID", projectId);//进度预算
        params.put("ProcessScale", budgetCost);
        params.put("PersonNum", personCounts);//预算人工数
        params.put("Rate", rates);//进度占比
        params.put("BeginDate", startDates);//开始时间
        params.put("EndDate", overDates);//结束时间
        params.put("ProjectMemo", remarks);//备注

        HttpRequestUtils.getmInstance().send(FollowProjectActivity.this, Constant.SAVE_PROJECT_PROGRESS, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    ToastUtils.shows(FollowProjectActivity.this, "保存成功");
                    finish();
                } else {
                    ToastUtils.shows(FollowProjectActivity.this, "保存成功");
                }
            }
        });


    }

    /**
     * 处理日期控件的Handler
     */

    Handler saleHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FollowProjectActivity.SHOW_DATAPICK:
                    showDialog(DATE_DIALOG_ID);
                    break;
            }
        }
    };

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
            startDate.setText(new StringBuilder()
                    .append(mYear)
                    .append("-")
                    .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                    .append("-")
                    .append((mDay < 10) ? "0" + mDay : mDay));
        }
        if (flag == 2) {
            overDate.setText(new StringBuilder()
                    .append(mYear)
                    .append("-")
                    .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                    .append("-")
                    .append((mDay < 10) ? "0" + mDay : mDay));
        }
    }

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
}
