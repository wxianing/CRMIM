package com.meidp.crmim.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.meidp.crmim.R;
import com.meidp.crmim.activity.CompanytructureActivity;
import com.meidp.crmim.activity.NewsActivity;
import com.meidp.crmim.adapter.ImagePagerAdapter;
import com.meidp.crmim.adapter.InformationAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBeans;
import com.meidp.crmim.model.Banner;
import com.meidp.crmim.model.InformationClassify;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.widget.AutoScrollViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Package： com.meidp.crmim.fragment
 * Author： wxianing
 * 作  用：公 司 页面
 * 时  间： 2016/8/6
 */
@ContentView(R.layout.fragment_company)
public class CompanyFragment extends BaseFragment implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2<ListView> {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.back_arrows)
    private ImageView backImg;
    @ViewInject(R.id.listview)
    private ListView mListView;
    private List<InformationClassify> mDatas;

    private InformationAdapter mAdapter;

    /**
     * 头部广告
     */
    @ViewInject(R.id.home_banner_viewpager)
    protected AutoScrollViewPager mViewPager;
    @ViewInject(R.id.home_dot_ll)
    protected LinearLayout dotLL;
    private List<Banner> imageUrls;
    private ImagePagerAdapter pagerAdapter;

    @Override
    public void onInit() {
        backImg.setVisibility(View.GONE);
        title.setText(R.string.title_name);
        imageUrls = new ArrayList<>();

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
    }

    @Override
    public void onInitData() {
        HashMap params = new HashMap();
        HttpRequestUtils.getmInstance().send(getActivity(), Constant.BANNER_URL, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("HomeFragment", result);
                AppBeans<Banner> appBean = JSONObject.parseObject(result, new TypeReference<AppBeans<Banner>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    imageUrls.addAll(appBean.getData());
                    pagerAdapter = new ImagePagerAdapter(getActivity(), imageUrls, dotLL);
                    mViewPager.setAdapter(pagerAdapter);
                    mViewPager.setOnPageChangeListener(pagerAdapter);
                    pagerAdapter.refreshData(true);
                }
            }
        });
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
        mDatas.clear();
        loadData();
        mViewPager.startAutoScroll();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewPager.stopAutoScroll();
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
        startActivity(intent);
//        switch (position) {
//            case 0:
//                intent.setClass(getActivity(), NewsActivity.class);
//                intent.putExtra("sType", sType);
//                intent.putExtra("sType2", 25);
//                intent.putExtra("title", titleName);
//                startActivity(intent);
//                break;
//            case 1:
//                intent.setClass(getActivity(), NewsActivity.class);
//                intent.putExtra("sType", sType);
//                intent.putExtra("title", titleName);
//                startActivity(intent);
//                break;
//            case 2:
//                intent.setClass(getActivity(), NewsActivity.class);
//                intent.putExtra("sType", sType);
//                intent.putExtra("title", titleName);
//                startActivity(intent);
//                break;
//            case 3:
//                intent.setClass(getActivity(), NewsActivity.class);
//                intent.putExtra("sType", sType);
//                intent.putExtra("title", titleName);
//                startActivity(intent);
//                break;
//            case 4:
//                intent.setClass(getActivity(), NewsActivity.class);
//                intent.putExtra("sType", sType);
//                intent.putExtra("title", titleName);
//                startActivity(intent);
//                break;
//        }
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
}
