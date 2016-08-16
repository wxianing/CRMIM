package com.meidp.crmim.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.ModelApplyAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppDatas;
import com.meidp.crmim.model.ModelApply;
import com.meidp.crmim.utils.Constant;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 样机管理
 */
@ContentView(R.layout.activity_my_prototype)
public class MyPrototypeActivity extends BaseActivity implements PullToRefreshBase.OnRefreshListener2<ListView>, AdapterView.OnItemClickListener {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.listview)
    private PullToRefreshListView mListView;

    private List<ModelApply> mDatas;
    private ModelApplyAdapter mAdapter;
    private int pageIndex = 1;
    private String keyword = "";
    @ViewInject(R.id.search_edittext)
    private EditText search;

    @Override
    public void onInit() {
        title.setText("样机管理");
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mDatas = new ArrayList<>();
        mAdapter = new ModelApplyAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setOnRefreshListener(this);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onInitData() {
        loadData(pageIndex, keyword);
    }

    private void loadData(int pageIndex, String keyword) {
        HashMap params = new HashMap();
        params.put("Keyword", keyword);
        params.put("sType", 0);
        params.put("PageIndex", pageIndex);
        params.put("PageSize", 8);
        HttpRequestUtils.getmInstance().send(MyPrototypeActivity.this, Constant.MY_MODEL_LIST, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                AppDatas<ModelApply> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<ModelApply>>() {
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
    protected void onResume() {
        super.onResume();
    }

    @Event(value = {R.id.back_arrows, R.id.search_btn, R.id.right_img})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.right_img:
                Intent intent = new Intent(this, ModelMachineApplyActivity.class);
                startActivityForResult(intent,1019);
                break;
            case R.id.search_btn:
                keyword = search.getText().toString().trim();
                mDatas.clear();
                pageIndex = 0;
                loadData(pageIndex, keyword);
                break;
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex = 1;
        mDatas.clear();
        loadData(pageIndex, keyword);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        pageIndex++;
        loadData(pageIndex, keyword);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, PrototypeDetailsActivity.class);
        intent.putExtra("OID", mDatas.get(position - 1).getID());
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1019){
            mDatas.clear();
            loadData(pageIndex, keyword);
        }
    }
}
