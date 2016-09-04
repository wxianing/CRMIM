package com.meidp.crmim.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.ApprovalAdapter;
import com.meidp.crmim.adapter.ApprovalCostAdapter;
import com.meidp.crmim.adapter.StockUpAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.ApprovalCosts;
import com.meidp.crmim.model.CheckforApply;
import com.meidp.crmim.model.StockUps;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.view.ListViewForScrollView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 审批
 */
public class ApprovalProcessActivity extends BasicActivity implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView>, View.OnClickListener {

    private TextView title;
    private String keyword = "";
    private int pageIndex = 1;

    private ListViewForScrollView prototypeListView;
    private ListViewForScrollView feeListView;
    private ListViewForScrollView stockupListview;

    private List<CheckforApply> mDatasPrototype;//样机
    private List<ApprovalCosts> mDatasCosts;//费用
    private List<StockUps> mDatasStockups;//备货

    private StockUpAdapter stockUpAdapter;//备货
    private ApprovalAdapter ApprovalAdapter;//样机审核
    private ApprovalCostAdapter costAdapter;//费用审核

    private EditText search;

    private PopupWindow mCheckStatusPopWindow;
    private PopupWindow mCheckTypePopWindow;
    private LinearLayout layout;
    private TextView typeName;
    private TextView statusName;
    private PullToRefreshScrollView scrollView;
    private TextView prototype_tv;
    private TextView fee_tv;
    private TextView stockup_tv;

    private String url = "";

    private int type = 0;
    private int sType = 1;
    private int sType2 = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_process);
        onInit();
        onInitData();
        initEvent();
    }

    private void initEvent() {
        findViewById(R.id.back_arrows).setOnClickListener(this);
        findViewById(R.id.search_btn).setOnClickListener(this);
        findViewById(R.id.checked_type).setOnClickListener(this);
        findViewById(R.id.checked_status).setOnClickListener(this);
    }


    public void onInit() {
        title = (TextView) findViewById(R.id.title_tv);
        prototypeListView = (ListViewForScrollView) findViewById(R.id.prototype_listview);
        feeListView = (ListViewForScrollView) findViewById(R.id.fee_listview);
        stockupListview = (ListViewForScrollView) findViewById(R.id.stockup_listview);
        search = (EditText) findViewById(R.id.search_edittext);
        layout = (LinearLayout) findViewById(R.id.layout);
        typeName = (TextView) findViewById(R.id.type_name);
        statusName = (TextView) findViewById(R.id.status_name);
        scrollView = (PullToRefreshScrollView) findViewById(R.id.scrollView);
        prototype_tv = (TextView) findViewById(R.id.prototype_tv);
        fee_tv = (TextView) findViewById(R.id.fee_tv);
        stockup_tv = (TextView) findViewById(R.id.stockup_tv);


        scrollView.setMode(PullToRefreshBase.Mode.BOTH);
        title.setText("我的审批");
        mDatasPrototype = new ArrayList<>();
        mDatasCosts = new ArrayList<>();
        mDatasStockups = new ArrayList<>();
        ApprovalAdapter = new ApprovalAdapter(mDatasPrototype, this);
        costAdapter = new ApprovalCostAdapter(mDatasCosts, this);
        stockUpAdapter = new StockUpAdapter(mDatasStockups, this);
        prototypeListView.setAdapter(ApprovalAdapter);
        feeListView.setAdapter(costAdapter);
        stockupListview.setAdapter(stockUpAdapter);
        prototypeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckforApply apply = mDatasPrototype.get(position);
                Intent intent = new Intent(ApprovalProcessActivity.this, ApprovalDetailsActivity.class);//审核详情
                Bundle bundle = new Bundle();
                bundle.putSerializable("CheckforApply", apply);
                intent.putExtra("OID", mDatasPrototype.get(position).getID());
                intent.putExtras(bundle);
                startActivityForResult(intent, 1019);
            }
        });
        feeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ApprovalCosts applys = mDatasCosts.get(position);
                Intent intent = new Intent(ApprovalProcessActivity.this, ApprovalCostDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ApprovalCosts", applys);
                intent.putExtras(bundle);
                intent.putExtra("OID", applys.getID());
                startActivityForResult(intent, 1019);
            }
        });
        stockupListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ApprovalProcessActivity.this, StockUpDetailsActivity.class);
                intent.putExtra("OID", mDatasStockups.get(position).getID());
                intent.putExtra("StatusName", mDatasStockups.get(position).getCheckStatusName());
                startActivityForResult(intent, 1019);
            }
        });

        initPopupWindow();
        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageIndex = 1;
                mDatasPrototype.clear();
                mDatasCosts.clear();
                mDatasStockups.clear();
                getPrototypeData(pageIndex, keyword);
                getFeeData(pageIndex, keyword);
                getStockupData(pageIndex, keyword);
                ApprovalAdapter.notifyDataSetChanged();
                costAdapter.notifyDataSetChanged();
                stockUpAdapter.notifyDataSetChanged();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pageIndex++;
                getPrototypeData(pageIndex, keyword);
                getFeeData(pageIndex, keyword);
                getStockupData(pageIndex, keyword);
            }
        });
    }

    public void onInitData() {
        getPrototypeData(pageIndex, keyword);
        getFeeData(pageIndex, keyword);
        getStockupData(pageIndex, keyword);
    }

    private void getPrototypeData(int pageIndex, String keyword) {
        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("sType", sType);
        params.put("sType2", sType2);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 5);
        HttpRequestUtils.getmInstance().send(ApprovalProcessActivity.this, Constant.FORCHECK_LIST, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {

                AppDatas<CheckforApply> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<CheckforApply>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    if (appDatas.getData().getDataList().size() > 0) {
                        prototype_tv.setVisibility(View.VISIBLE);
                    } else {
                        prototype_tv.setVisibility(View.GONE);
                    }
                    mDatasPrototype.addAll(appDatas.getData().getDataList());
                    if (mDatasPrototype != null && !mDatasPrototype.isEmpty()) {
                        ApprovalAdapter.notifyDataSetChanged();
                    } else {

                    }
//                    prototypeListView.onRefreshComplete();
                    scrollView.onRefreshComplete();
                }
                ApprovalAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getFeeData(int pageIndex, String keyword) {
        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("sType", sType);
        params.put("sType2", sType2);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 5);
        HttpRequestUtils.getmInstance().send(ApprovalProcessActivity.this, Constant.GET_COST_CHECK, params, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {

                AppDatas<ApprovalCosts> appData = JSONObject.parseObject(result, new TypeReference<AppDatas<ApprovalCosts>>() {
                });
                if (appData != null && appData.getEnumcode() == 0) {
                    if (appData.getData().getDataList().size() > 0) {
                        fee_tv.setVisibility(View.VISIBLE);
                    } else {
                        fee_tv.setVisibility(View.GONE);
                    }
                    mDatasCosts.addAll(appData.getData().getDataList());
                    if (mDatasCosts != null && !mDatasCosts.isEmpty()) {

                        costAdapter.notifyDataSetChanged();
                    } else {

                    }
//                    feeListView.onRefreshComplete();

                    scrollView.onRefreshComplete();
                }
                costAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getStockupData(int pageIndex, String keyword) {

        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("sType", sType);
        params.put("sType2", sType2);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 5);
        HttpRequestUtils.getmInstance().send(ApprovalProcessActivity.this, Constant.STOCK_LIST_URL, params, new HttpRequestCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        AppDatas<StockUps> stockUps = JSONObject.parseObject(result, new TypeReference<AppDatas<StockUps>>() {
                        });
                        if (stockUps != null && stockUps.getEnumcode() == 0) {
                            if (stockUps.getData().getDataList().size() > 0) {
                                stockup_tv.setVisibility(View.VISIBLE);
                            } else {
                                stockup_tv.setVisibility(View.GONE);
                            }
                            mDatasStockups.addAll(stockUps.getData().getDataList());
                            if (mDatasStockups != null && !mDatasStockups.isEmpty()) {
                                stockUpAdapter.notifyDataSetChanged();
//                            stockupListview.onRefreshComplete();
                            } else {
//                                stockup_tv.setVisibility(View.GONE);
                            }
                            scrollView.onRefreshComplete();
                        }
                        stockUpAdapter.notifyDataSetChanged();
                    }
                }

        );
    }

    @Override
    protected void onResume() {
        ApprovalAdapter.notifyDataSetChanged();
        stockUpAdapter.notifyDataSetChanged();
        costAdapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checked_prototype://样机审核
                sType2 = 2;
                typeName.setText("最近一个星期");
                mDatasPrototype.clear();
                mDatasCosts.clear();
                mDatasStockups.clear();
                ApprovalAdapter.notifyDataSetChanged();
                stockUpAdapter.notifyDataSetChanged();
                costAdapter.notifyDataSetChanged();
                getPrototypeData(pageIndex, keyword);
                getFeeData(pageIndex, keyword);
                getStockupData(pageIndex, keyword);
//                loadData(pageIndex, keyword);
                dismissCheckTypePopupWindow();
                break;
            case R.id.checked_fee:
                sType2 = 3;
                typeName.setText("最近一个月");
                mDatasPrototype.clear();
                mDatasCosts.clear();
                mDatasStockups.clear();
                ApprovalAdapter.notifyDataSetChanged();
                stockUpAdapter.notifyDataSetChanged();
                costAdapter.notifyDataSetChanged();
                getPrototypeData(pageIndex, keyword);
                getFeeData(pageIndex, keyword);
                getStockupData(pageIndex, keyword);
//                loadData(pageIndex, keyword);
                dismissCheckTypePopupWindow();
                break;
            case R.id.checked_stockup:
                sType2 = 5;
                typeName.setText("最近一年");
                mDatasPrototype.clear();
                mDatasCosts.clear();
                mDatasStockups.clear();
                ApprovalAdapter.notifyDataSetChanged();
                stockUpAdapter.notifyDataSetChanged();
                costAdapter.notifyDataSetChanged();
                getPrototypeData(pageIndex, keyword);
                getFeeData(pageIndex, keyword);
                getStockupData(pageIndex, keyword);
//                loadData(pageIndex, keyword);
                dismissCheckTypePopupWindow();
                break;
            case R.id.unlimited:
                sType2 = -1;
                mDatasPrototype.clear();
                mDatasCosts.clear();
                mDatasStockups.clear();
                ApprovalAdapter.notifyDataSetChanged();
                stockUpAdapter.notifyDataSetChanged();
                costAdapter.notifyDataSetChanged();
                getPrototypeData(pageIndex, keyword);
                getFeeData(pageIndex, keyword);
                getStockupData(pageIndex, keyword);
                typeName.setText("不限");
                dismissCheckTypePopupWindow();
                break;
            case R.id.not_checked://未审核
                sType = 1;
                mDatasPrototype.clear();
                mDatasCosts.clear();
                mDatasStockups.clear();
                ApprovalAdapter.notifyDataSetChanged();
                stockUpAdapter.notifyDataSetChanged();
                costAdapter.notifyDataSetChanged();
                getPrototypeData(pageIndex, keyword);
                getFeeData(pageIndex, keyword);
                getStockupData(pageIndex, keyword);
//                loadData(pageIndex, keyword);
                statusName.setText("未审核");
                dismissCheckStatusPopupWindow();
                break;
            case R.id.checked_already://已审核
                sType = 0;
                mDatasPrototype.clear();
                mDatasCosts.clear();
                mDatasStockups.clear();
                ApprovalAdapter.notifyDataSetChanged();
                stockUpAdapter.notifyDataSetChanged();
                costAdapter.notifyDataSetChanged();
                getPrototypeData(pageIndex, keyword);
                getFeeData(pageIndex, keyword);
                getStockupData(pageIndex, keyword);
//                loadData(pageIndex, keyword);
                statusName.setText("已审核");
                dismissCheckStatusPopupWindow();
                break;
            case R.id.checked_not_pass://已拒绝
                sType = 2;
                mDatasPrototype.clear();
                mDatasCosts.clear();
                mDatasStockups.clear();
                ApprovalAdapter.notifyDataSetChanged();
                stockUpAdapter.notifyDataSetChanged();
                costAdapter.notifyDataSetChanged();
                getPrototypeData(pageIndex, keyword);
                getFeeData(pageIndex, keyword);
                getStockupData(pageIndex, keyword);
//                loadData(pageIndex, keyword);
                statusName.setText("已拒绝");
                dismissCheckStatusPopupWindow();
                break;
            case R.id.back_arrows:
                finish();
                break;
            case R.id.search_btn:
                keyword = search.getText().toString().trim();
                mDatasPrototype.clear();
                mDatasCosts.clear();
                mDatasStockups.clear();
                getPrototypeData(pageIndex, keyword);
                getFeeData(pageIndex, keyword);
                break;
            case R.id.checked_type://审核类型
                if (!mCheckTypePopWindow.isShowing()) {
                    showCheckTypePopupWindow();
                    dismissCheckStatusPopupWindow();
                } else {
                    dismissCheckTypePopupWindow();
                }

                break;
            case R.id.checked_status://审核状态
                if (!mCheckStatusPopWindow.isShowing()) {
                    showCheckStatusPopupWindow();
                    dismissCheckTypePopupWindow();
                } else {
                    dismissCheckStatusPopupWindow();
                }
                break;
        }
    }

    private void initPopupWindow() {
        //审核类型PopupWindow
        View checkTypeView = LayoutInflater.from(ApprovalProcessActivity.this).inflate(R.layout.popup_check_type_layout, null);
        mCheckTypePopWindow = new PopupWindow(checkTypeView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mCheckTypePopWindow.setContentView(checkTypeView);
        mCheckTypePopWindow.setBackgroundDrawable(new BitmapDrawable());

        checkTypeView.findViewById(R.id.checked_prototype).setOnClickListener(this);
        checkTypeView.findViewById(R.id.checked_fee).setOnClickListener(this);
        checkTypeView.findViewById(R.id.checked_stockup).setOnClickListener(this);
        checkTypeView.findViewById(R.id.unlimited).setOnClickListener(this);

        // //审核状态PopupWindow
        View checkStatusView = LayoutInflater.from(ApprovalProcessActivity.this).inflate(R.layout.popup_check_status_layout, null);
        mCheckStatusPopWindow = new PopupWindow(checkStatusView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mCheckStatusPopWindow.setContentView(checkStatusView);
        mCheckStatusPopWindow.setBackgroundDrawable(new BitmapDrawable());
        checkStatusView.findViewById(R.id.not_checked).setOnClickListener(this);
        checkStatusView.findViewById(R.id.checked_already).setOnClickListener(this);
        checkStatusView.findViewById(R.id.checked_not_pass).setOnClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;
        Bundle bundle = null;
        switch (type) {
            case 0:

                break;
            case 1:
                ApprovalCosts applys = mDatasCosts.get(position - 1);
                intent = new Intent(this, ApprovalCostDetailActivity.class);
                bundle = new Bundle();
                bundle.putSerializable("ApprovalCosts", applys);
                intent.putExtras(bundle);
                intent.putExtra("OID", applys.getID());
                startActivityForResult(intent, 1019);
                break;
            case 2:
                intent = new Intent(this, StockUpDetailsActivity.class);
                intent.putExtra("OID", mDatasStockups.get(position - 1).getID());
                intent.putExtra("StatusNames", mDatasStockups.get(-1).getCheckStatusName());
                startActivityForResult(intent, 1019);
                break;
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex = 1;
        mDatasPrototype.clear();
        mDatasCosts.clear();
        mDatasStockups.clear();
//        loadData(pageIndex, keyword);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex++;
//        loadData(pageIndex, keyword);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1019) {
            mDatasPrototype.clear();
            mDatasCosts.clear();
            mDatasStockups.clear();
            getPrototypeData(pageIndex, keyword);
            getFeeData(pageIndex, keyword);
            getStockupData(pageIndex, keyword);
        }
    }

    private void showCheckTypePopupWindow() {
        mCheckTypePopWindow.showAsDropDown(layout);
    }

    private void dismissCheckTypePopupWindow() {
        mCheckTypePopWindow.dismiss();
    }

    private void dismissCheckStatusPopupWindow() {
        mCheckStatusPopWindow.dismiss();
    }

    private void showCheckStatusPopupWindow() {
        mCheckStatusPopWindow.showAsDropDown(layout);
    }
}
