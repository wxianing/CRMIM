package com.meidp.crmim.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.meidp.crmim.R;
import com.meidp.crmim.activity.CompanytructureActivity;
import com.meidp.crmim.activity.ModelMachineApplyActivity;
import com.meidp.crmim.activity.NewGroupActivity;
import com.meidp.crmim.activity.NewsActivity;
import com.meidp.crmim.activity.SigninMainActivity;
import com.meidp.crmim.activity.SubmitActivity;
import com.meidp.crmim.activity.VisitingClientsActivity;
import com.meidp.crmim.adapter.ImagePagerAdapter;
import com.meidp.crmim.adapter.InformationAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBeans;
import com.meidp.crmim.model.Banner;
import com.meidp.crmim.model.InformationClassify;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.IMkitConnectUtils;
import com.meidp.crmim.utils.NetUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.widget.AutoScrollViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Package： com.meidp.crmim.fragment
 * Author： wxianing
 * 作  用：公 司 页面
 * 时  间： 2016/8/6
 */
public class CompanyFragment extends Fragment implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView>, View.OnClickListener {

    private TextView title;
    private ImageView backImg;
    private ListView mListView;
    private List<InformationClassify> mDatas;

    private InformationAdapter mAdapter;

    /**
     * 头部广告
     */
//    @ViewInject(R.id.home_banner_viewpager)
//    protected AutoScrollViewPager mViewPager;
//    @ViewInject(R.id.home_dot_ll)
//    protected LinearLayout dotLL;
//    private List<Banner> imageUrls;

    private ImagePagerAdapter pagerAdapter;

    private ImageView rightImg;
    private PopupWindow mPopupWindow;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company, container, false);
        onInit(view);
        initEvent(view);
        onInitData();
        return view;
    }

    private void initEvent(View view) {

        view.findViewById(R.id.right_img).setOnClickListener(this);

    }

    public void onInit(View view) {
        title = (TextView) view.findViewById(R.id.title_tv);
        backImg = (ImageView) view.findViewById(R.id.back_arrows);
        mListView = (ListView) view.findViewById(R.id.listview);
        rightImg = (ImageView) view.findViewById(R.id.right_img);
        title = (TextView) view.findViewById(R.id.title_tv);
        rightImg.setImageResource(R.mipmap.more_icon);
        backImg.setVisibility(View.INVISIBLE);
        title.setText(R.string.title_name);
//        imageUrls = new ArrayList<>();
        initPopupWindow();

        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.auto_viewpager, null);
        View footerView = LayoutInflater.from(getActivity()).inflate(R.layout.company_list_footer, null);
        LinearLayout layout = (LinearLayout) footerView.findViewById(R.id.company_structure_layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CompanytructureActivity.class);
                startActivity(intent);
            }
        });
        mListView.addFooterView(footerView);
        mDatas = new ArrayList<>();
        mListView.setOnItemClickListener(this);
//        mListView.setOnRefreshListener(this);


        mAdapter = new InformationAdapter(mDatas, getActivity());
    }

    public void onInitData() {
        loadData();
    }

    private void loadData() {
        HttpRequestUtils.getmInstance().send(getActivity(), Constant.CLASSIFY_URL, null, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBeans<InformationClassify> appBeans = JSONObject.parseObject(result, new TypeReference<AppBeans<InformationClassify>>() {
                });
                if (appBeans != null && appBeans.getEnumcode() == 0) {
                    mDatas.addAll(appBeans.getData());
                    mAdapter = new InformationAdapter(mDatas, getActivity());
                    mListView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (RongIM.getInstance() != null && RongIM.getInstance().getRongIMClient() != null) {
            /**
             * 设置连接状态变化的监听器.
             */
           // RongIM.getInstance().getRongIMClient().setConnectionStatusListener(new MyConnectionStatusListener());
        }
    }

    /*private class MyConnectionStatusListener implements RongIMClient.ConnectionStatusListener {

        @Override
        public void onChanged(ConnectionStatus connectionStatus) {
            switch (connectionStatus) {
                case CONNECTED://连接成功。
                    break;
                case DISCONNECTED://断开连接。
                    if (NetUtils.isConnected(getActivity())) {
                       new IMkitConnectUtils().connect(Constant.getTOKEN(), getActivity());
                    }
                    break;
                case CONNECTING://连接中。
                    break;
                case NETWORK_UNAVAILABLE://网络不可用。
                    break;
                case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线
                    break;
            }
        }
    }
*/
    @Override
    public void onDestroy() {
        super.onDestroy();
//        mViewPager.stopAutoScroll();
    }

    /**
     * listView点击时间
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        int sType = mDatas.get(position).getID();
        String titleName = mDatas.get(position).getTypeName();
        intent.setClass(getActivity(), NewsActivity.class);
        intent.putExtra("sType", sType);
        intent.putExtra("title", titleName);
        startActivityForResult(intent, 101);
    }

    /**
     * 下拉刷新
     *
     * @param refreshView
     */
    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        mDatas.clear();
        loadData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        mDatas.clear();
        loadData();
    }

    private void showPopupWindow() {
//        mPopupWindow.showAsDropDown(titlebar );
        mPopupWindow.showAsDropDown(rightImg, 0, 0);
    }

    private void initPopupWindow() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_list_layout, null);
//        x.view().inject(this, contentView);
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        contentView.findViewById(R.id.visit_client).setOnClickListener(this);
        contentView.findViewById(R.id.new_group).setOnClickListener(this);
        contentView.findViewById(R.id.submit_project).setOnClickListener(this);
        contentView.findViewById(R.id.apply_model).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.visit_client://客户拜访
                intent = new Intent(getActivity(), SigninMainActivity.class);
                startActivity(intent);
                mPopupWindow.dismiss();
                break;
            case R.id.new_group://新建群组
                intent = new Intent(getActivity(), NewGroupActivity.class);
                startActivity(intent);
                mPopupWindow.dismiss();
                break;
            case R.id.submit_project://申报项目
                intent = new Intent(getActivity(), SubmitActivity.class);
                startActivity(intent);
                mPopupWindow.dismiss();
                break;
            case R.id.apply_model:
                intent = new Intent(getActivity(), ModelMachineApplyActivity.class);
                startActivity(intent);
                mPopupWindow.dismiss();
                break;
            case R.id.right_img:
                if (!mPopupWindow.isShowing()) {
                    showPopupWindow();
                } else {
                    mPopupWindow.dismiss();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==101){
            mDatas.clear();
            loadData();
        }
    }
}
