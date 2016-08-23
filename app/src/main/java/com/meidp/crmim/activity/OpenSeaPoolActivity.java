package com.meidp.crmim.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.OpenProjectAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.Projects;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 公海池
 */
@ContentView(R.layout.activity_open_sea_pool)
public class OpenSeaPoolActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<ListView>, AdapterView.OnItemClickListener, View.OnClickListener {

    private int pageIndex = 1;
    private int pageSize = 8;
    private int sType = 0;
    @ViewInject(R.id.listview)
    private PullToRefreshListView mListView;
    private List<Projects> mDatas;
    private OpenProjectAdapter mAdapter;

    private PopupWindow mDatePopWindow;
    private PopupWindow mTypePopWindow;
    private int criticalType;
    @ViewInject(R.id.line)
    private LinearLayout layout;
    private int sType2 = 0;
    private int sType3 = 0;

    @Override
    public void onInit() {
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mDatas = new ArrayList<>();
        mAdapter = new OpenProjectAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnRefreshListener(this);
        mListView.setOnItemClickListener(this);
        initPopupWindow();
    }

    @Override
    public void onInitData() {
//        loadData(pageIndex);
    }

    private void loadData(int pageIndex) {
        HashMap params = new HashMap();
        params.put("sType", sType);//2公海池
        params.put("sType2", sType2);//2公海池
        params.put("sType3", sType3);//2公海池
        params.put("PageIndex", pageIndex);
        params.put("PageSize", pageSize);
        HttpRequestUtils.getmInstance().send(OpenSeaPoolActivity.this, Constant.OPEN_SEAR_PROJECT, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                AppDatas<Projects> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<Projects>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
                    mListView.onRefreshComplete();
                }
            }
        });
    }

    @Event(value = {R.id.back_arrows, R.id.time_tv, R.id.type_tv, R.id.search_edittext, R.id.right_img})
    private void click(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
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
            case R.id.search_edittext:
                intent = new Intent(this, ProjectSearchActivity.class);
                startActivity(intent);
                break;
            case R.id.right_img:
                intent = new Intent(this, SubmitActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void showTypePopupWindow() {
        //显示PopupWindow
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_work_plan, null);
//        mTypePopWindow.showAtLocation(rootview, Gravity.TOP, 0, 240);
        mTypePopWindow.showAsDropDown(layout);
    }

    private void showDatePopupWindow() {
        //显示PopupWindow
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_work_plan, null);
//        mDatePopWindow.showAtLocation(rootview, Gravity.TOP, 0, 240);
        mDatePopWindow.showAsDropDown(layout);
    }


    private void initPopupWindow() {
        View contentView = LayoutInflater.from(OpenSeaPoolActivity.this).inflate(R.layout.popup_success_layout, null);
        mTypePopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mTypePopWindow.setContentView(contentView);
        mTypePopWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置各个控件的点击响应
        //设置各个控件的点击响应
        contentView.findViewById(R.id.wait_follow_up).setOnClickListener(this);//待跟进
        contentView.findViewById(R.id.in_the_paper).setOnClickListener(this);//报备中
        contentView.findViewById(R.id.all_type_tv).setOnClickListener(this);
        contentView.findViewById(R.id.stock_up).setOnClickListener(this);//备货中
        contentView.findViewById(R.id.shipment).setOnClickListener(this);//出货中
        contentView.findViewById(R.id.returned_money).setOnClickListener(this);//回款中
        contentView.findViewById(R.id.be_over).setOnClickListener(this);//完结
        contentView.findViewById(R.id.termination).setOnClickListener(this);//终止
//        all_type.setOnClickListener(this);
//        tv11.setOnClickListener(this);
//        tv22.setOnClickListener(this);

        //设置contentView
        View contentView1 = LayoutInflater.from(OpenSeaPoolActivity.this).inflate(R.layout.popup_time_layout, null);
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

    @Override
    protected void onResume() {
        super.onResume();
        mDatas.clear();
        loadData(pageIndex);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ProjecDetailsActivity.class);
        intent.putExtra("TYPE", 1);//1公海池，0我的项目
        intent.putExtra("OID", mDatas.get(position - 1).getID());
        startActivity(intent);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex = 1;
        mDatas.clear();
        loadData(pageIndex);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex++;
        loadData(pageIndex);
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
                sType2 = 1;
                mTypePopWindow.dismiss();
                break;
            case R.id.importance_tv:
                sType2 = 2;
                mTypePopWindow.dismiss();
                break;
            case R.id.all_type_tv:
                sType2 = 0;
                mTypePopWindow.dismiss();
                break;
            case R.id.wait_follow_up://待跟进
                sType2 = 1;
                sType3 = 0;
                mTypePopWindow.dismiss();
                break;
            case R.id.in_the_paper://报备中
                sType2 = 2;
                sType3 = 1;
                mTypePopWindow.dismiss();
                break;
            case R.id.stock_up://备货中
                sType3 = 2;
                mTypePopWindow.dismiss();
                break;
            case R.id.shipment://出货中
                sType3 = 3;
                mTypePopWindow.dismiss();
                break;
            case R.id.returned_money://回款中
                sType3 = 4;
                mTypePopWindow.dismiss();
                break;
            case R.id.be_over://回款中
                sType3 = 5;
                mTypePopWindow.dismiss();
                break;
            case R.id.termination://回款中
                sType3 = 9;
                mTypePopWindow.dismiss();
                break;

        }
        mDatas.clear();
        loadData(pageIndex);
    }
}
