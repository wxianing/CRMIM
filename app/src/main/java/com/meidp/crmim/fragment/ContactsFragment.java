package com.meidp.crmim.fragment;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.activity.GroupActivity;
import com.meidp.crmim.activity.ModelMachineApplyActivity;
import com.meidp.crmim.activity.NearContactsActivity;
import com.meidp.crmim.activity.NewGroupActivity;
import com.meidp.crmim.activity.SearchMsgActivity;
import com.meidp.crmim.activity.SigninMainActivity;
import com.meidp.crmim.activity.SubmitActivity;
import com.meidp.crmim.activity.VisitingClientsActivity;
import com.meidp.crmim.adapter.ExpandableAdapter;
import com.meidp.crmim.adapter.NearContactsAdapter;
import com.meidp.crmim.fragment.BaseFragment;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBeans;
import com.meidp.crmim.model.Contact;
import com.meidp.crmim.model.Friends;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.view.ExpListView;
import com.meidp.crmim.view.ListViewForScrollView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_friend_list)
public class ContactsFragment extends BaseFragment implements AdapterView.OnItemClickListener, ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupClickListener {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.back_arrows)
    private ImageView backImg;
    private List<String> userIds;
    @ViewInject(R.id.listview)
    private ListViewForScrollView mListVeiw;
    private NearContactsAdapter mAdapter;
    private List<Friends> mDatas;

    @ViewInject(R.id.expListView)
    protected ExpListView expListView;

    private List<Contact> contactList;

    private ExpandableAdapter expandableAdapter;
    @ViewInject(R.id.friends_list)
    private FrameLayout friendsList;
    @ViewInject(R.id.right_img)
    private ImageView rightImg;
    private PopupWindow mPopupWindow;


    public ContactsFragment() {

    }

    @Override
    public void onInit() {
        rightImg.setImageResource(R.mipmap.more_icon);
        backImg.setVisibility(View.INVISIBLE);
        title.setText("通讯录");
        initPopupWindow();
        userIds = new ArrayList<>();
        userIds.add("1");
        userIds.add("3");
        mDatas = new ArrayList<>();
//        mAdapter = new NearContactsAdapter(userLists, getActivity());
        mListVeiw.setOnItemClickListener(this);
        contactList = new ArrayList<>();
        expandableAdapter = new ExpandableAdapter(contactList, getActivity());
        expListView.setOnChildClickListener(this);
        expListView.setOnGroupClickListener(this);
        expListView.setAdapter(expandableAdapter);
        expListView.setGroupIndicator(null);
    }

    @Override
    public void onInitData() {

    }

    private void loadData() {
        HttpRequestUtils.getmInstance().send(getActivity(), Constant.GET_CONTACTS_URL, null, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                AppBeans<Contact> appBean = JSONObject.parseObject(result, new TypeReference<AppBeans<Contact>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    contactList.clear();
                    contactList.addAll(appBean.getData());
                    expandableAdapter.notifyDataSetChanged();
                    for (int i = 0; i < contactList.size(); i++) {
                        expListView.expandGroup(i);//默认展开选项
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mDatas.clear();
        loadData();
    }

    @Event(value = {R.id.search_edittext, R.id.group_layout, R.id.recent_contacts, R.id.visit_client, R.id.new_group, R.id.submit_project, R.id.apply_model, R.id.right_img})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.search_edittext:
                intent = new Intent(getActivity(), SearchMsgActivity.class);
                startActivity(intent);
                break;
            case R.id.group_layout:
                startActivity(new Intent(getActivity(), GroupActivity.class));
                break;
            case R.id.recent_contacts:
                Intent intent1 = new Intent(getActivity(), NearContactsActivity.class);
                startActivity(intent1);

                if (friendsList.getVisibility() == View.GONE) {
                    friendsList.setVisibility(View.VISIBLE);
                } else {
                    friendsList.setVisibility(View.GONE);
                }
                break;
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RongIM.getInstance().startPrivateChat(getActivity(), Integer.toString(mDatas.get(position).getUserID()), mDatas.get(position).getEmployeeName());
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        String userId = Integer.toString(contactList.get(groupPosition).getUsers().get(childPosition).getUserID());
        Log.e("userId>>>>>>>>>", userId);
        String employeeName = contactList.get(groupPosition).getUsers().get(childPosition).getEmployeeName();
        RongIM.getInstance().startPrivateChat(getActivity(), userId, employeeName);
        return true;
    }

    private void showPopupWindow() {
        mPopupWindow.showAsDropDown(rightImg, 0, 0);
    }

    private void initPopupWindow() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_list_layout, null);
        x.view().inject(this, contentView);
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return true;
    }
}
