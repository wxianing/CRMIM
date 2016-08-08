package com.meidp.crmim.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.activity.AnnouncementActivity;
import com.meidp.crmim.activity.NewsActivity;
import com.meidp.crmim.activity.RegulatoryFrameworkActivity;
import com.meidp.crmim.adapter.InformationAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBeans;
import com.meidp.crmim.model.InformationClassify;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Package： com.meidp.crmim.fragment
 * Author： wxianing
 * 作  用：公 司 页面
 * 时  间： 2016/8/6
 */
@ContentView(R.layout.fragment_company)
public class CompanyFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.back_arrows)
    private ImageView backImg;
    @ViewInject(R.id.listview)
    private ListView mListView;
    private List<InformationClassify> mDatas;
    private InformationAdapter mAdapter;

    @Override
    public void onInit() {
        backImg.setVisibility(View.GONE);
        title.setText("公司");
        mDatas = new ArrayList<>();
        mAdapter = new InformationAdapter(mDatas, getActivity());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onInitData() {
        HttpRequestUtils.getmInstance().send(getActivity(), Constant.CLASSIFY_URL, null, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBeans<InformationClassify> appBeans = JSONObject.parseObject(result, new TypeReference<AppBeans<InformationClassify>>() {
                });
                if (appBeans != null && appBeans.getEnumcode() == 0) {
                    mDatas.addAll(appBeans.getData());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        int sType = mDatas.get(position).getID();
        String titleName = mDatas.get(position).getTypeName();
        switch (position) {
            case 0:
                intent.setClass(getActivity(), NewsActivity.class);
                intent.putExtra("sType", sType);
                intent.putExtra("title",titleName);
                startActivity(intent);
                break;
            case 1:
                intent.setClass(getActivity(), RegulatoryFrameworkActivity.class);
                intent.putExtra("sType", sType);
                startActivity(intent);
                break;
            case 2:
                intent.setClass(getActivity(), NewsActivity.class);
                intent.putExtra("sType", sType);
                intent.putExtra("title",titleName);
                startActivity(intent);
                break;
            case 3:
                intent.setClass(getActivity(), NewsActivity.class);
                intent.putExtra("sType", sType);
                intent.putExtra("title",titleName);
                startActivity(intent);
                break;
            case 4:
                intent.setClass(getActivity(), AnnouncementActivity.class);
                intent.putExtra("sType", sType);
                startActivity(intent);
                break;
        }
    }
}
