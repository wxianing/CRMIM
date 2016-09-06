package com.meidp.crmim.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.CheckedPrototypeAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.PrototypeDetails;
import com.meidp.crmim.model.PrototypePrepares;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;
import com.meidp.crmim.view.ListViewForScrollView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.rong.imkit.RongIM;

/**
 * 样机详情
 */
@ContentView(R.layout.activity_prototype_details)
public class PrototypeDetailsActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;
    private int id;

    @ViewInject(R.id.title_name)
    private TextView titleName;
    @ViewInject(R.id.prototype_name)
    private TextView prototypeName;
    @ViewInject(R.id.count_tv)
    private TextView count;
    @ViewInject(R.id.apply_time)
    private TextView applyTime;
    @ViewInject(R.id.apply_person)
    private TextView applyPerson;
    @ViewInject(R.id.phone_num)
    private TextView phoneNum;

    private String countString;

    private AppBean<PrototypeDetails> appBean;

    @ViewInject(R.id.relevant_project)
    private TextView relevantProject;
    @ViewInject(R.id.save_layout)
    private LinearLayout saveLayout;
    @ViewInject(R.id.listview)
    private ListViewForScrollView mListView;
    private List<PrototypeDetails.FlowStepsBean> mDatas;
    private CheckedPrototypeAdapter mAdapter;

    private Button button;

    @Override
    public void onInit() {
        title.setText("详情");
        id = getIntent().getIntExtra("OID", -1);
        mDatas = new ArrayList<>();
        mAdapter = new CheckedPrototypeAdapter(mDatas, this);
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        params.put("Id", id);
        HttpRequestUtils.getmInstance().send(PrototypeDetailsActivity.this, Constant.PROTOTYPE_DETAILS_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", result);
                appBean = JSONObject.parseObject(result, new TypeReference<AppBean<PrototypeDetails>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    bindView(appBean);
                }
            }
        });
    }

    private String maxCount = "0";


    private void bindView(AppBean<PrototypeDetails> appBean) {
        titleName.setText("标题：" + appBean.getData().getTitle());
        String prototypeNameString = "";
        String countString = "";
        for (int i = 0; i < appBean.getData().getDetails().size(); i++) {
            prototypeNameString += appBean.getData().getDetails().get(i).getProductName() + "、";
            countString += appBean.getData().getDetails().get(i).getProductCount() + "、";
        }
        if (NullUtils.isNull(prototypeNameString)) {
            prototypeNameString = prototypeNameString.substring(0, prototypeNameString.length() - 1);
        } else {
            prototypeNameString = "";
        }
        if (NullUtils.isNull(countString) && countString.length() > 0) {
            countString = countString.substring(0, countString.length() - 1);
            maxCount = countString;
        }

        prototypeName.setText("样机名称：" + prototypeNameString);


        count.setText("数量：" + countString);


        applyTime.setText("申请时间：" + appBean.getData().getCreateDate());
        if (NullUtils.isNull(appBean.getData().getCreatorName())) {
            applyPerson.setText("申请人：" + appBean.getData().getCreatorName());
        } else {
            applyPerson.setText("申请人：");
        }

        if (NullUtils.isNull(appBean.getData().getCustTel())) {
            phoneNum.setText("联系电话：" + appBean.getData().getCustTel());
        } else {
            phoneNum.setText("联系电话：");
        }
        if (NullUtils.isNull(appBean.getData().getProjectName())) {
            relevantProject.setText("相关项目：" + appBean.getData().getProjectName());
        } else {
            relevantProject.setText("相关项目：");
        }

        String statusNames = appBean.getData().getFlowStatusName();
        if (statusNames.equals("通过审批")) {
            saveLayout.setVisibility(View.VISIBLE);
        }

        mDatas.addAll(appBean.getData().getFlowSteps());
        mListView.setAdapter(mAdapter);

    }

    @Event({R.id.back_arrows, R.id.button})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.button:
                showDialog();
//                final View dialogView = LayoutInflater.from(this).inflate(R.layout.edittext_layout, null);
//                TextView titleName = (TextView) dialogView.findViewById(R.id.title_name);
//                titleName.setText("请输入备货数量");
//                new AlertDialog.Builder(this).setView(
//                        dialogView).setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        EditText editText = (EditText) dialogView.findViewById(R.id.edit_text);
//                        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
//                        countString = editText.getText().toString().trim();
//                        sendMsg(countString);
//
//                    }
//                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                }).show();
                break;
        }
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this, R.style.Dialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.edittext_dialog, null);
        TextView titleName = (TextView) contentView.findViewById(R.id.title);

        final EditText editText = (EditText) contentView.findViewById(R.id.message);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        final TextView msg = (TextView) contentView.findViewById(R.id.msg_tv);
        editText.setHint("");
        titleName.setText("请输入备货数量");
        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(true);
        Button negativeButton = (Button) contentView.findViewById(R.id.negativeButton);
        negativeButton.setClickable(true);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button positiveButton = (Button) contentView.findViewById(R.id.positiveButton);
        positiveButton.setClickable(true);


        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countStr = editText.getText().toString().trim();
                if (NullUtils.isNull(countStr) && NullUtils.isNull(maxCount)) {
                    if (Integer.valueOf(countStr) > Integer.valueOf(maxCount)) {
                        msg.setText("申请的数量不能大于" + maxCount);
                        msg.setVisibility(View.VISIBLE);
                        return;
                    }
                    sendMsg(countStr);
                    dialog.dismiss();
                } else {
                    ToastUtils.shows(PrototypeDetailsActivity.this, "数量不能为空");
                }

            }
        });

        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        WindowManager wm = this.getWindowManager();
        Display d = wm.getDefaultDisplay(); // 获取屏幕宽、高用
//        p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
        lp.width = (int) (d.getWidth() * 0.75); // 宽度设置为屏幕的0.65

        dialogWindow.setAttributes(lp);


        dialog.show();

    }

    private List<PrototypePrepares> prototypePrepares = new ArrayList<>();

    private void sendMsg(String count) {
        for (int i = 0; i < appBean.getData().getDetails().size(); i++) {
            PrototypePrepares prepares = new PrototypePrepares();
            prepares.setProductID(appBean.getData().getDetails().get(i).getProductID());
//            Log.e("")
            prepares.setProductCount(Double.valueOf(count));
            prototypePrepares.add(prepares);
        }
        Log.e("prototypePrepares", "prototypePrepares" + prototypePrepares.size());
        HashMap params = new HashMap();
        params.put("FromType", 4);
        params.put("FromBillID", id);
        params.put("Details", prototypePrepares);

        HttpRequestUtils.getmInstance().send(PrototypeDetailsActivity.this, Constant.STOCK_UP_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    ToastUtils.shows(PrototypeDetailsActivity.this, "申请成功");
                } else {
                    ToastUtils.showl(PrototypeDetailsActivity.this, appMsg.getMsg());
                }
            }
        });
    }
}
