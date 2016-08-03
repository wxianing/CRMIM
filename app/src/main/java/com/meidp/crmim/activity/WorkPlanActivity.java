package com.meidp.crmim.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.WorkPlansAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.WorkPlans;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_work_plan)
public class WorkPlanActivity extends BaseActivity implements View.OnClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {

    @ViewInject(R.id.title_right)
    private TextView titleRight;
    @ViewInject(R.id.title_tv)
    private TextView title;

    private int pageIndex;
    private String keyWord;

    private PopupWindow mDatePopWindow;
    private PopupWindow mTypePopWindow;
    private int sType = -1;
    @ViewInject(R.id.listview)
    private PullToRefreshListView mListView;

    private List<WorkPlans> mDatas;
    private WorkPlansAdapter mAdapter;
    @ViewInject(R.id.line)
    private LinearLayout layout;

    private int criticalType;

    @Override
    public void onInit() {
        titleRight.setText("新建");
        titleRight.setVisibility(View.VISIBLE);
        title.setText("工作计划");
        mDatas = new ArrayList<>();
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mAdapter = new WorkPlansAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        initPopupWindow();
        mListView.setOnRefreshListener(this);
    }

    @Event({R.id.back_arrows, R.id.title_right, R.id.time_tv, R.id.type_tv})
    private void click(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                Intent intent = new Intent(this, NewWorkPlanActivity.class);
                startActivity(intent);
                break;
            case R.id.time_tv://选择时间
                if (!mDatePopWindow.isShowing()) {
//                    mDatePopWindow.dismiss();
                    showDatePopupWindow();
                    mTypePopWindow.dismiss();
                } else {
                    mDatePopWindow.dismiss();
                }
                break;
            case R.id.type_tv://选择类型
                if (!mTypePopWindow.isShowing()) {
                    showTypePopupWindow();
                    mDatePopWindow.dismiss();
                } else {
                    mTypePopWindow.dismiss();
                }
                break;
        }
    }

    private void initPopupWindow() {
        View contentView = LayoutInflater.from(WorkPlanActivity.this).inflate(R.layout.popup_type_layout, null);
        mTypePopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mTypePopWindow.setContentView(contentView);
        mTypePopWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置各个控件的点击响应
        TextView tv11 = (TextView) contentView.findViewById(R.id.ordinary_tv);
        TextView tv22 = (TextView) contentView.findViewById(R.id.importance_tv);
        TextView all_type = (TextView) contentView.findViewById(R.id.all_type_tv);
        all_type.setOnClickListener(this);
        tv11.setOnClickListener(this);
        tv22.setOnClickListener(this);

        //设置contentView
        View contentView1 = LayoutInflater.from(WorkPlanActivity.this).inflate(R.layout.popup_date_layout, null);
        mDatePopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mDatePopWindow.setContentView(contentView1);
        mDatePopWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置各个控件的点击响应
        TextView tv1 = (TextView) contentView1.findViewById(R.id.day_plan);
        TextView tv2 = (TextView) contentView1.findViewById(R.id.week_plan);
        TextView tv3 = (TextView) contentView1.findViewById(R.id.month_plan);
        TextView tv4 = (TextView) contentView1.findViewById(R.id.quarter_plan);
        TextView tv5 = (TextView) contentView1.findViewById(R.id.year_plan);
        TextView all_date = (TextView) contentView1.findViewById(R.id.all_date_tv);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        all_date.setOnClickListener(this);
        //显示PopupWindow
    }

    private void showTypePopupWindow() {
        //显示PopupWindow
        View rootview = LayoutInflater.from(WorkPlanActivity.this).inflate(R.layout.activity_work_plan, null);
//        mTypePopWindow.showAtLocation(rootview, Gravity.TOP, 0, 240);
        mTypePopWindow.showAsDropDown(layout);
    }

    private void showDatePopupWindow() {
        //显示PopupWindow
        View rootview = LayoutInflater.from(WorkPlanActivity.this).inflate(R.layout.activity_work_plan, null);
//        mDatePopWindow.showAtLocation(rootview, Gravity.TOP, 0, 240);
        mDatePopWindow.showAsDropDown(layout);
    }


    @Override
    public void onInitData() {
        loadData(pageIndex, keyWord, sType, criticalType);
    }

    /**
     * "Keyword": "sample string 1",
     * "sType": 2,
     * "PageIndex": 3,
     * "PageSize": 4
     */
    private void loadData(int pageIndex, String keyWord, int sType, int criticalType) {
        HashMap params = new HashMap();
        params.put("sType", sType);
        params.put("CriticalType", criticalType);
        params.put("Keyword", keyWord);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(WorkPlanActivity.this, Constant.GET_PERSONAL_PLAN, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppDatas<WorkPlans> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<WorkPlans>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
                    mListView.onRefreshComplete();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.day_plan:
                sType = 1;
                mDatePopWindow.dismiss();
                break;
            case R.id.week_plan:
                sType = 2;
                mDatePopWindow.dismiss();
                break;
            case R.id.month_plan:
                sType = 3;
                mDatePopWindow.dismiss();
                break;
            case R.id.quarter_plan:
                sType = 4;
                mDatePopWindow.dismiss();
                break;
            case R.id.year_plan:
                sType = 5;
                mDatePopWindow.dismiss();
                break;
            case R.id.all_date_tv:
                sType = 0;
                mDatePopWindow.dismiss();
                break;
            case R.id.ordinary_tv:
                criticalType = 2;
                mTypePopWindow.dismiss();
                break;
            case R.id.importance_tv:
                criticalType = 4;
                mTypePopWindow.dismiss();
                break;
            case R.id.all_type_tv:
                criticalType = 0;
                mTypePopWindow.dismiss();
                break;
        }
        mDatas.clear();
        loadData(pageIndex, keyWord, sType, criticalType);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex = 1;
        mDatas.clear();
        loadData(pageIndex, keyWord, sType, criticalType);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex++;
        loadData(pageIndex, keyWord, sType, criticalType);
    }
}
