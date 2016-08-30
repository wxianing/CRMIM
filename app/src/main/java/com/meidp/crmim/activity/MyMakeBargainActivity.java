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

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.MakeBargainAdapter;
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
 * 我的成交
 */
@ContentView(R.layout.activity_my_make_bargain)
public class MyMakeBargainActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<ListView>, AdapterView.OnItemClickListener, View.OnClickListener {

    private int pageIndex = 1;
    private int pageSize = 12;
    private int sType = 0;
    private int sType2 = 0;
    private int sType3 = -1;

    private PopupWindow mDatePopWindow;
    private PopupWindow mTypePopWindow;

    @ViewInject(R.id.listview)
    private PullToRefreshListView mListView;
    private List<Projects> mDatas;
    private MakeBargainAdapter mAdapter;
    @ViewInject(R.id.line)
    private LinearLayout layout;

    @Event({R.id.back_arrows, R.id.time_tv, R.id.type_tv, R.id.search_edittext, R.id.right_img})
    private void onclick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.time_tv://选择时间
                if (!mDatePopWindow.isShowing()) {
                    showDatePopupWindow();
                    dismissTypePopupWindow();
                } else {
                    dismissTypePopupWindow();
                }

                break;
            case R.id.type_tv://选择类型
                if (!mTypePopWindow.isShowing()) {
                    showTypePopupWindow();
                    dismissDataPopupWindow();
                } else {
                    dismissTypePopupWindow();
                }
                break;
            case R.id.search_edittext:
                intent = new Intent(this, ProjectSearchActivity.class);
                intent.putExtra("FLAG", 0);
                startActivity(intent);
                break;
            case R.id.right_img:
                intent = new Intent(this, SubmitActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onInit() {
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mDatas = new ArrayList<>();
        mAdapter = new MakeBargainAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnRefreshListener(this);
        mListView.setOnItemClickListener(this);
        initPopupWindow();
//        Log.e("FLAG", mark);
    }

    @Override
    public void onInitData() {
        super.onInitData();
        loadData(pageIndex);
    }

    private void loadData(int pageIndex) {
        HashMap params = new HashMap();
        params.put("sType", 5);//2公海池
//        params.put("sType2", sType2);//2公海池
        params.put("sType3", 5);//2公海池
        params.put("PageIndex", pageIndex);
        params.put("PageSize", pageSize);
        HttpRequestUtils.getmInstance().send(MyMakeBargainActivity.this, Constant.PROJECT_MANAGER, params, new HttpRequestCallBack<String>() {
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

    private void showTypePopupWindow() {
        //显示PopupWindow
//        typeImg.setImageResource(R.mipmap.arrow_up);
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_work_plan, null);
//        mTypePopWindow.showAtLocation(rootview, Gravity.TOP, 0, 240);
        mTypePopWindow.showAsDropDown(layout);
    }

    private void dismissTypePopupWindow() {
        mTypePopWindow.dismiss();
//        typeImg.setImageResource(R.mipmap.arrow_down);
    }

    private void dismissDataPopupWindow() {
        mDatePopWindow.dismiss();
//        timeImg.setImageResource(R.mipmap.arrow_down);
    }

    private void showDatePopupWindow() {
        //显示PopupWindow
//        timeImg.setImageResource(R.mipmap.arrow_up);
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_work_plan, null);
//        mDatePopWindow.showAtLocation(rootview, Gravity.TOP, 0, 240);
        mDatePopWindow.showAsDropDown(layout);
    }


    private void initPopupWindow() {
        View contentView = LayoutInflater.from(MyMakeBargainActivity.this).inflate(R.layout.popup_success_layout, null);
        mTypePopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mTypePopWindow.setContentView(contentView);
        mTypePopWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置各个控件的点击响应
        contentView.findViewById(R.id.wait_follow_up).setOnClickListener(this);//待跟进
        contentView.findViewById(R.id.in_the_paper).setOnClickListener(this);//报备中
        contentView.findViewById(R.id.all_type_tv).setOnClickListener(this);
        contentView.findViewById(R.id.stock_up).setOnClickListener(this);//备货中
        contentView.findViewById(R.id.shipment).setOnClickListener(this);//出货中
        contentView.findViewById(R.id.returned_money).setOnClickListener(this);//回款中
        contentView.findViewById(R.id.be_over).setOnClickListener(this);//完结
        contentView.findViewById(R.id.termination).setOnClickListener(this);//终止


        //设置contentView
        View contentView1 = LayoutInflater.from(MyMakeBargainActivity.this).inflate(R.layout.popup_time_layout, null);
        mDatePopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mDatePopWindow.setContentView(contentView1);
        mDatePopWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置各个控件的点击响应
        contentView1.findViewById(R.id.day_plan).setOnClickListener(this);
        contentView1.findViewById(R.id.week_plan).setOnClickListener(this);
        contentView1.findViewById(R.id.month_plan).setOnClickListener(this);
        contentView1.findViewById(R.id.quarter_plan).setOnClickListener(this);
        contentView1.findViewById(R.id.year_plan).setOnClickListener(this);
        contentView1.findViewById(R.id.all_date_tv).setOnClickListener(this);
        //显示PopupWindow
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ProjecDetailsActivity.class);
        intent.putExtra("TYPE", 0);//1公海池，0我的项目
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
//                timeImg.setImageResource(R.mipmap.arrow_down);
                mDatePopWindow.dismiss();
                break;
            case R.id.week_plan:
                sType = 2;
//                timeImg.setImageResource(R.mipmap.arrow_down);
                mDatePopWindow.dismiss();
                break;
            case R.id.month_plan:
                sType = 3;
//                timeImg.setImageResource(R.mipmap.arrow_down);
                mDatePopWindow.dismiss();
                break;
            case R.id.quarter_plan:
                sType = 4;
//                timeImg.setImageResource(R.mipmap.arrow_down);
                mDatePopWindow.dismiss();
                break;
            case R.id.year_plan:
                sType = 5;
//                timeImg.setImageResource(R.mipmap.arrow_down);
                mDatePopWindow.dismiss();
                break;
            case R.id.all_date_tv:
                sType = 0;
//                timeImg.setImageResource(R.mipmap.arrow_down);
                mDatePopWindow.dismiss();
                break;
            case R.id.wait_follow_up://待跟进
                sType2 = 1;
                sType3 = 0;
//                typeImg.setImageResource(R.mipmap.arrow_down);
                mTypePopWindow.dismiss();
                break;
            case R.id.in_the_paper://报备中
                sType2 = 2;
                sType3 = 1;
//                typeImg.setImageResource(R.mipmap.arrow_down);
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
            case R.id.all_type_tv:
                sType2 = 0;
//                typeImg.setImageResource(R.mipmap.arrow_down);
                mTypePopWindow.dismiss();
                break;
        }
        mDatas.clear();
        loadData(pageIndex);
    }
}
