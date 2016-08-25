package com.meidp.crmim.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.LifeNavs;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

@ContentView(R.layout.activity_add_life_navigation)
public class AddLifeNavigationActivity extends BaseActivity {
    @ViewInject(R.id.title_right)
    private TextView titleRight;

    @ViewInject(R.id.title_tv)
    private TextView title;
    private TextView titleName;
    private View dialogView;
    @ViewInject(R.id.text11)
    private TextView text11;
    @ViewInject(R.id.text12)
    private TextView text12;
    @ViewInject(R.id.text13)
    private TextView text13;
    @ViewInject(R.id.text14)
    private TextView text14;

    @ViewInject(R.id.text31)
    private TextView text31;
    @ViewInject(R.id.text32)
    private TextView text32;
    @ViewInject(R.id.text33)
    private TextView text33;
    @ViewInject(R.id.text41)
    private TextView text41;
    @ViewInject(R.id.text42)
    private TextView text42;
    @ViewInject(R.id.text43)
    private TextView text43;

    @ViewInject(R.id.content21)
    private EditText editText21;
    @ViewInject(R.id.content22)
    private EditText editText22;
    @ViewInject(R.id.content23)
    private EditText editText23;
    @ViewInject(R.id.content24)
    private EditText editText24;
    @ViewInject(R.id.content25)
    private EditText editText25;

    private String content11;
    private String content12;
    private String content13;
    private String content14;
    private String content31;
    private String content32;
    private String content33;
    private String content41;
    private String content42;
    private String content43;


    @Override
    public void onInit() {
        title.setText("添加人生导航");
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setText("保存");
    }

    @Override
    public void onInitData() {
        HttpRequestUtils.getmInstance().send(AddLifeNavigationActivity.this, Constant.GET_LIFE_NAV, null, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBean<LifeNavs> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<LifeNavs>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {

                }
            }
        });
    }

    @Event({R.id.back_arrows, R.id.text11, R.id.text12, R.id.text13, R.id.text14, R.id.text31, R.id.text32, R.id.text33, R.id.text41, R.id.text42, R.id.text43, R.id.title_right})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.text11:
                dialogView = LayoutInflater.from(this).inflate(R.layout.edittext_layout2, null);
                titleName = (TextView) dialogView.findViewById(R.id.title_name);
                titleName.setText("我2016年要在公司平台实现多少万元个人财富？");
                new AlertDialog.Builder(this).setView(
                        dialogView).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) dialogView.findViewById(R.id.edit_text);
                        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                        content11 = editText.getText().toString().trim();
                        String str = "2、我2016年要在公司平台实现" + content11 + "万元个人财富";
                        int fstart = str.indexOf(content11);
                        int fend = fstart + content11.length();
                        SpannableStringBuilder style = new SpannableStringBuilder(str);
                        style.setSpan(new ForegroundColorSpan(Color.RED), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        text11.setText(style);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

                break;
            case R.id.text12:
                dialogView = LayoutInflater.from(this).inflate(R.layout.edittext_layout2, null);
                titleName = (TextView) dialogView.findViewById(R.id.title_name);
                titleName.setText("我未来5年要在公司平台实现多少万元个人财富？");
                new AlertDialog.Builder(this).setView(
                        dialogView).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) dialogView.findViewById(R.id.edit_text);
                        content12 = editText.getText().toString().trim();
                        String str = "2、我未来5年要在公司平台实现" + content12 + "万元个人财富";
                        int fstart = str.indexOf(content12);
                        int fend = fstart + content12.length();
                        SpannableStringBuilder style = new SpannableStringBuilder(str);
                        style.setSpan(new ForegroundColorSpan(Color.RED), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        text12.setText(style);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.text13:
                dialogView = LayoutInflater.from(this).inflate(R.layout.edittext_layout2, null);
                titleName = (TextView) dialogView.findViewById(R.id.title_name);
                titleName.setText("我2016年要发展多大的团队？成就多少人?");
                new AlertDialog.Builder(this).setView(
                        dialogView).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) dialogView.findViewById(R.id.edit_text);
                        content13 = editText.getText().toString().trim();
                        String str = "2、我2016年要发展多大的团队？成就" + content13 + "人";
                        int fstart = str.indexOf(content13);
                        int fend = fstart + content13.length();
                        SpannableStringBuilder style = new SpannableStringBuilder(str);
                        style.setSpan(new ForegroundColorSpan(Color.RED), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        text13.setText(style);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.text14:
                dialogView = LayoutInflater.from(this).inflate(R.layout.edittext_layout2, null);
                titleName = (TextView) dialogView.findViewById(R.id.title_name);
                titleName.setText("我未来5年要在公司平台发展多大的团队?成就多少人？");
                new AlertDialog.Builder(this).setView(
                        dialogView).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) dialogView.findViewById(R.id.edit_text);
                        content14 = editText.getText().toString().trim();
                        String str = "2、我未来5年要在公司平台发展多大的团队?成就" + content14 + "人";
                        int fstart = str.indexOf(content14);
                        int fend = fstart + content14.length();
                        SpannableStringBuilder style = new SpannableStringBuilder(str);
                        style.setSpan(new ForegroundColorSpan(Color.RED), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        text14.setText(style);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.text31:
                dialogView = LayoutInflater.from(this).inflate(R.layout.edittext_layout2, null);
                titleName = (TextView) dialogView.findViewById(R.id.title_name);
                titleName.setText("我每天拜访多少家客户");
                new AlertDialog.Builder(this).setView(
                        dialogView).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) dialogView.findViewById(R.id.edit_text);
                        content31 = editText.getText().toString().trim();
                        String str = "1、我每天拜访" + content31 + "家客户";
                        int fstart = str.indexOf(content31);
                        int fend = fstart + content31.length();
                        SpannableStringBuilder style = new SpannableStringBuilder(str);
                        style.setSpan(new ForegroundColorSpan(Color.RED), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        text31.setText(style);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.text32:
                dialogView = LayoutInflater.from(this).inflate(R.layout.edittext_layout2, null);
                titleName = (TextView) dialogView.findViewById(R.id.title_name);
                titleName.setText("我2016年总共要达到多少家客户？");
                new AlertDialog.Builder(this).setView(
                        dialogView).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) dialogView.findViewById(R.id.edit_text);
                        content32 = editText.getText().toString().trim();
                        String str = "2、我2016年总共要达到" + content32 + "家客户";
                        int fstart = str.indexOf(content32);
                        int fend = fstart + content32.length();
                        SpannableStringBuilder style = new SpannableStringBuilder(str);
                        style.setSpan(new ForegroundColorSpan(Color.RED), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        text32.setText(style);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.text33:
                dialogView = LayoutInflater.from(this).inflate(R.layout.edittext_layout2, null);
                titleName = (TextView) dialogView.findViewById(R.id.title_name);
                titleName.setText("我2016年要让多少家客户成为我的一伙人合作伙伴");
                new AlertDialog.Builder(this).setView(
                        dialogView).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) dialogView.findViewById(R.id.edit_text);
                        content33 = editText.getText().toString().trim();
                        String str = "3、我2016年要让" + content33 + "家客户成为我的一伙人合作伙伴";
                        int fstart = str.indexOf(content33);
                        int fend = fstart + content33.length();
                        SpannableStringBuilder style = new SpannableStringBuilder(str);
                        style.setSpan(new ForegroundColorSpan(Color.RED), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        text33.setText(style);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.text41:
                dialogView = LayoutInflater.from(this).inflate(R.layout.edittext_layout2, null);
                titleName = (TextView) dialogView.findViewById(R.id.title_name);
                titleName.setText("我2016年总共可以完成多少个项目");
                new AlertDialog.Builder(this).setView(
                        dialogView).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) dialogView.findViewById(R.id.edit_text);
                        content41 = editText.getText().toString().trim();
                        String str = "1、我未来5年要在公司平台发展多大的团队?成就" + content41 + "人";
                        int fstart = str.indexOf(content41);
                        int fend = fstart + content41.length();
                        SpannableStringBuilder style = new SpannableStringBuilder(str);
                        style.setSpan(new ForegroundColorSpan(Color.RED), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        text41.setText(style);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.text42:
                dialogView = LayoutInflater.from(this).inflate(R.layout.edittext_layout2, null);
                titleName = (TextView) dialogView.findViewById(R.id.title_name);
                titleName.setText("这些项目总共可以实现多少万元销售额？");
                new AlertDialog.Builder(this).setView(
                        dialogView).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) dialogView.findViewById(R.id.edit_text);
                        content42 = editText.getText().toString().trim();
                        String str = "2、这些项目总共可以实现" + content42 + "万元销售额";
                        int fstart = str.indexOf(content42);
                        int fend = fstart + content42.length();
                        SpannableStringBuilder style = new SpannableStringBuilder(str);
                        style.setSpan(new ForegroundColorSpan(Color.RED), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        text42.setText(style);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.text43:
                dialogView = LayoutInflater.from(this).inflate(R.layout.edittext_layout2, null);
                titleName = (TextView) dialogView.findViewById(R.id.title_name);
                titleName.setText("我预计可以实现____万元个人成果？");
                new AlertDialog.Builder(this).setView(
                        dialogView).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) dialogView.findViewById(R.id.edit_text);
                        content43 = editText.getText().toString().trim();
                        String str = "3、我预计可以实现" + content43 + "万元个人成果？";
                        int fstart = str.indexOf(content43);
                        int fend = fstart + content43.length();
                        SpannableStringBuilder style = new SpannableStringBuilder(str);
                        style.setSpan(new ForegroundColorSpan(Color.RED), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        text43.setText(style);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.title_right:
                sendMsg();
                break;
        }
    }

    private void sendMsg() {


        HashMap params = new HashMap();
        params.put("YearNav", 2016);
        params.put("TotalMineMoneyByYear", content11);
        params.put("TotalMineMoneyByFuture", content12);
        params.put("TeamCountByYear", content13);
        params.put("TeamCountByFuture", content14);
        params.put("VisitCountByDay", content31);
        params.put("VisitCountByYear", content32);
        params.put("CustomCountByYear", content33);
        params.put("ProjectCountByYear", content41);
        params.put("ProjectMoneyByYear", content42);
        params.put("SaleMoneyByYear", content43);


        HttpRequestUtils.getmInstance().send(AddLifeNavigationActivity.this, Constant.SAVE_NAV_URL, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    ToastUtils.shows(AddLifeNavigationActivity.this, "保存成功");
                    finish();
                } else {
                    ToastUtils.shows(AddLifeNavigationActivity.this, appMsg.getMsg());
                }
            }
        });
    }
}
