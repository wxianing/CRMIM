package com.meidp.crmim.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.meidp.crmim.utils.DataUtils;
import com.meidp.crmim.utils.ToastUtils;
import com.meidp.crmim.view.WheelView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

/**
 * 项目跟进
 */
@ContentView(R.layout.activity_follow_project)
public class FollowProjectActivity extends BaseActivity {

    private static final String[] PLANETS = new String[]{"10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};

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
    private double rates;
    String dateSet;
    Calendar calendar;


    @Override
    public void onInit() {
        title.setText("项目跟进");
        titleRight.setText("保存");
        titleRight.setVisibility(View.VISIBLE);
        projectId = getIntent().getIntExtra("OID", -1);
        startDate.setText(DataUtils.getDate2());
        overDate.setText(DataUtils.getDate2());
        calendar = Calendar.getInstance();
        //初始化Calendar日历对象
        Calendar mycalendar = Calendar.getInstance();

        mYear = mycalendar.get(Calendar.YEAR); //获取Calendar对象中的年
        mMonth = mycalendar.get(Calendar.MONTH);//获取Calendar对象中的月
        mDay = mycalendar.get(Calendar.DAY_OF_MONTH);//获取这个月的第几天

    }

    DatePickerDialog.OnDateSetListener DateSet = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // 每次保存设置的日期
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String str = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            System.out.println("set is " + str);
        }
    };

    @Event(value = {R.id.back_arrows, R.id.start_date, R.id.over_date, R.id.title_right, R.id.rate})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.start_date:
//                DatePickerDialog datePickerDialog = new DatePickerDialog(
//                        FollowProjectActivity.this, DateSet, calendar
//                        .get(Calendar.YEAR), calendar
//                        .get(Calendar.MONTH), calendar
//                        .get(Calendar.DAY_OF_MONTH));
//                datePickerDialog.show();
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
            case R.id.rate:
                View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
                wv.setOffset(1);
                wv.setItems(Arrays.asList(PLANETS));
                wv.setSeletion(0);
                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {

                        rates = Double.valueOf(item) / 100;
                        Log.e(">>>>>>>>", "[Dialog]selectedIndex: " + rate + ", item: " + item);
                        rate.setText(rates * 100 + "%");
                    }
                });
                new AlertDialog.Builder(this)
                        .setTitle("请选择成功率(%)")
                        .setView(outerView)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(SubmitActivity.this, ">>>>>>>>", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
        }
    }

    private void sendMsg() {

        String startDates = startDate.getText().toString().trim();
        String overDates = overDate.getText().toString().trim();
        String budgetCost = budgetcost.getText().toString().trim();
        String personCounts = personCount.getText().toString().trim();
//        String rates = rate.getText().toString().trim();
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

            // 每次保存设置的日期
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

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
