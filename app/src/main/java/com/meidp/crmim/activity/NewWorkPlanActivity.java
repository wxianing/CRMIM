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
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

@ContentView(R.layout.activity_new_work_plan)
public class NewWorkPlanActivity extends BaseActivity {

    private static final int SHOW_DATAPICK = 0;
    private static final int DATE_DIALOG_ID = 1;
    public int flag = 0;

    @ViewInject(R.id.title_right)
    private TextView titleRight;
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.plan_type)
    private EditText planTypeEt;
    @ViewInject(R.id.work_direction)
    private EditText workDirection;//工作方向
    @ViewInject(R.id.period_et)
    private EditText period_et;// 周期
    @ViewInject(R.id.start_date)
    private EditText startDate;
    @ViewInject(R.id.end_date)
    private EditText endDate;

    @ViewInject(R.id.pickdate_btn)
    private Button pickDate;
    @ViewInject(R.id.plan_title)
    private EditText planTitleEt;//标题
    @ViewInject(R.id.plan_content)
    private EditText planContentEt;


    private int mYear;
    private int mMonth;
    private int mDay;
    private int aimFlag;
    private int aimSortId;
    private int aimDirectionId;

    @Override
    public void onInit() {
        title.setText("新建客户档案");
        titleRight.setText("保存");
        titleRight.setVisibility(View.VISIBLE);
    }

    @Event({R.id.back_arrows, R.id.title_right, R.id.plan_type, R.id.work_direction, R.id.period_et, R.id.start_date, R.id.end_date})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows://返回
                finish();
                break;
            case R.id.title_right://
                sendMsg();
                break;
            case R.id.plan_type://类型
                intent = new Intent();
                intent.setClass(this, CostTypeActivity.class);
                intent.putExtra("ChildId", 6);
                startActivityForResult(intent, 1002);
                break;
            case R.id.work_direction://工作方向
                intent = new Intent();
                intent.setClass(this, CostTypeActivity.class);
                intent.putExtra("ChildId", 7);
                startActivityForResult(intent, 1002);
                break;
            case R.id.period_et://周期
                intent = new Intent(this, CycleActivity.class);
                startActivityForResult(intent, 1007);
                break;
            case R.id.start_date:
                flag = 1;
                Message msg = new Message();
                if (pickDate.equals((EditText) v)) {
                    msg.what = NewWorkPlanActivity.SHOW_DATAPICK;
                }
                NewWorkPlanActivity.this.saleHandler.sendMessage(msg);
                break;
            case R.id.end_date:
                flag = 2;
                Message msg2 = new Message();
                if (pickDate.equals((EditText) v)) {
                    msg2.what = NewWorkPlanActivity.SHOW_DATAPICK;
                }
                NewWorkPlanActivity.this.saleHandler.sendMessage(msg2);
                break;

        }
    }

    private void sendMsg() {
        ToastUtils.shows(this, "正在保存");

        String planTitle = planTitleEt.getText().toString().trim();
        String planCOntent = planContentEt.getText().toString().trim();
        String startDates = startDate.getText().toString().trim();
        String endDates = endDate.getText().toString().trim();

        HashMap params = new HashMap();
        params.put("AimTitle", planTitle);
        params.put("AimContent", planCOntent);
        params.put("StartDate", startDates);
        params.put("EndDate", endDates);
        params.put("AimSortId", aimSortId);//类型
        params.put("AimFlag", aimFlag);//计划周期
        params.put("AimDirectionId", aimDirectionId);//计划方向

        HttpRequestUtils.getmInstance().send(NewWorkPlanActivity.this, Constant.SAVA_WORK_PLAN, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    ToastUtils.shows(NewWorkPlanActivity.this, "保存成功");
                    finish();
                } else {
                    ToastUtils.shows(NewWorkPlanActivity.this, appMsg.getMsg());
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1002) {
            int childId = data.getIntExtra("ChildId", -1);
            String typeName = data.getStringExtra("TypeName");
            int oid = data.getIntExtra("OID", -1);
            switch (childId) {
                case 6:
                    planTypeEt.setText(typeName);
                    aimSortId = oid;
                    break;
                case 7:
                    workDirection.setText(typeName);
                    aimDirectionId = oid;
                    break;
            }
        } else if (requestCode == 1007) {
            aimFlag = data.getIntExtra("AimFlag", -1);
            String cycleType = data.getStringExtra("CycleType");
            period_et.setText(cycleType);
        }
    }

    /**
     * 处理日期控件的Handler
     */

    Handler saleHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NewWorkPlanActivity.SHOW_DATAPICK:
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
            startDate.setText(new StringBuilder()
                    .append(mYear)
                    .append("-")
                    .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                    .append("-")
                    .append((mDay < 10) ? "0" + mDay : mDay));
        }
        if (flag == 2) {
            endDate.setText(new StringBuilder()
                    .append(mYear)
                    .append("-")
                    .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                    .append("-")
                    .append((mDay < 10) ? "0" + mDay : mDay));
        }
    }

}
