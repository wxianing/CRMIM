package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

@ContentView(R.layout.activity_project_select)
public class ProjectSelectActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.title_tv)
    private TextView title;

    private int pageIndex = 1;
    private int pageSize = 12;
    private int sType = -1;
    @ViewInject(R.id.listview)
    private ListView mListView;
    private List<Projects> mDatas;
    private OpenProjectAdapter mAdapter;

    @ViewInject(R.id.search_edittext)
    private EditText search;

    private String keyWord = "";
    @ViewInject(R.id.explain)
    private TextView explain;
    private int custContactId;
    private String contactPhone;

    @Override
    public void onInit() {
        mListView.setOnItemClickListener(this);
        keyWord = getIntent().getStringExtra("custContact");
        Log.e("keyWord", keyWord);
        custContactId = getIntent().getIntExtra("CustContactId", -1);
        contactPhone = getIntent().getStringExtra("ContactPhone");
        title.setText(keyWord + "与公司合作项目有");
//        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mDatas = new ArrayList<>();
        mAdapter = new OpenProjectAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onInitData() {
        loadData(pageIndex, keyWord);
    }

    private void loadData(int pageIndex, String keyWord) {
        HashMap params = new HashMap();
        params.put("Keyword", keyWord);//2公海池
        params.put("sType", sType);//2公海池
        params.put("sType2", -1);//2公海池
        params.put("PageIndex", pageIndex);
        params.put("PageSize", pageSize);
        HttpRequestUtils.getmInstance().send(ProjectSelectActivity.this, Constant.PROJECT_MANAGER, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                AppDatas<Projects> appDatas = JSONObject.parseObject(result, new TypeReference<AppDatas<Projects>>() {
                });
                if (appDatas != null && appDatas.getEnumcode() == 0) {
                    mDatas.addAll(appDatas.getData().getDataList());
                    mAdapter.notifyDataSetChanged();
//                    mListView.onRefreshComplete();
                    if (mDatas.size() > 0) {
                        explain.setText("以上是公司与该客户合作的所有项目，是否申报新的项目？");
                    } else {
                        explain.setText("系统未找到公司与该客户合作的项目，是否申报新项目？");
                    }
                }
            }
        });
    }

    @Event(value = {R.id.back_arrows, R.id.time_tv, R.id.type_tv, R.id.right_tv, R.id.save_btn})
    private void click(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.right_tv:
                keyWord = search.getText().toString().trim();
                mDatas.clear();
                pageIndex = 1;
                loadData(pageIndex, keyWord);
                break;
            case R.id.save_btn:
                Intent intent = new Intent(this, SubmissionActivity.class);
                intent.putExtra("custContactId", custContactId);
                intent.putExtra("contactPhone", contactPhone);
                intent.putExtra("custContact", keyWord);
                startActivityForResult(intent, 1028);
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Projects projects = mDatas.get(position);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Projects", projects);
        intent.putExtras(bundle);
        setResult(1025, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1028) {
            mDatas.clear();
            loadData(pageIndex, keyWord);
        }
    }
}
