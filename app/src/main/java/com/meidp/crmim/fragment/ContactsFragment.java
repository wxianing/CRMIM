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
import com.meidp.crmim.adapter.ExpandableAdapter;
import com.meidp.crmim.adapter.NearContactsAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBeans;
import com.meidp.crmim.model.Contact;
import com.meidp.crmim.model.Friends;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.IMkitConnectUtils;
import com.meidp.crmim.utils.NetUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.view.ExpListView;
import com.meidp.crmim.view.ListViewForScrollView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_friend_list)
public class ContactsFragment extends BaseFragment implements AdapterView.OnItemClickListener, ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupClickListener, View.OnClickListener {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.back_arrows)
    private ImageView backImg;
    private List<String> userIds;

    private List<Friends> mDatas;

    @ViewInject(R.id.expListView)
    protected ExpListView expListView;

    private List<Contact> contactList;

    private ExpandableAdapter expandableAdapter;

    @ViewInject(R.id.right_img)
    private ImageView rightImg;
    private PopupWindow mPopupWindow;
//    @ViewInject(R.id.scrollView)
//    private ScrollView scrollView;

    public ContactsFragment() {

    }

    @Override
    public void onInit() {
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.header_contacts_layout, null);

        headerView.findViewById(R.id.search_edittext).setOnClickListener(this);
        headerView.findViewById(R.id.recent_contacts).setOnClickListener(this);
        headerView.findViewById(R.id.group_layout).setOnClickListener(this);
        expListView.addHeaderView(headerView);
//        scrollView.smoothScrollTo(0, 20);
        rightImg.setImageResource(R.mipmap.more_icon);
        backImg.setVisibility(View.INVISIBLE);
        title.setText("通讯录");
        initPopupWindow();
        userIds = new ArrayList<>();
        userIds.add("1");
        userIds.add("3");
        mDatas = new ArrayList<>();
//        mAdapter = new NearContactsAdapter(userLists, getActivity());
        contactList = new ArrayList<>();
        expandableAdapter = new ExpandableAdapter(contactList, getActivity());
        expListView.setOnChildClickListener(this);
        expListView.setOnGroupClickListener(this);
        expListView.setAdapter(expandableAdapter);
//        expListView.setGroupIndicator(null);//去掉箭头
        expListView.setFocusable(false);
    }

    @Override
    public void onInitData() {
        /**
         * 保存通讯录
         */
        String contacts = (String) SPUtils.get(getActivity(), "Contacts", "");
        if (NullUtils.isNull(contacts)) {

            AppBeans<Contact> appBean = JSONObject.parseObject(contacts, new TypeReference<AppBeans<Contact>>() {
            });
            if (appBean != null && appBean.getEnumcode() == 0) {
                contactList.clear();
                contactList.addAll(appBean.getData());
                expandableAdapter.notifyDataSetChanged();
//                for (int i = 0; i < contactList.size(); i++) {
//                    expListView.expandGroup(i);//默认展开选项
//                }
            }
        } else {
            loadData();
        }

    }

    private void loadData() {
        HttpRequestUtils.getmInstance().send(getActivity(), Constant.GET_CONTACTS_URL, null, new HttpRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                SPUtils.save(getActivity(), "Contacts", result);
                AppBeans<Contact> appBean = JSONObject.parseObject(result, new TypeReference<AppBeans<Contact>>() {
                });
                if (appBean != null && appBean.getEnumcode() == 0) {
                    contactList.clear();
                    contactList.addAll(appBean.getData());
                    expandableAdapter.notifyDataSetChanged();
//                    for (int i = 0; i < contactList.size(); i++) {
//                        expListView.expandGroup(i);//默认展开选项
//                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        /**
         * 设置连接状态变化的监听器.
         */
        if (RongIM.getInstance() != null && RongIM.getInstance().getRongIMClient() != null) {
           // RongIM.getInstance().getRongIMClient().setConnectionStatusListener(new MyConnectionStatusListener());
        }
    }

    /**
     * 监测融云连接状态回调接口
     */
    /*private class MyConnectionStatusListener implements RongIMClient.ConnectionStatusListener {
        @Override
        public void onChanged(ConnectionStatus connectionStatus) {
            switch (connectionStatus) {

                case CONNECTED://连接成功。

                    break;
                case DISCONNECTED://断开连接。
                    if (NetUtils.isConnected(getActivity())) {
                        String token = (String) SPUtils.get(getActivity(), "TOKEN", "");
                        new IMkitConnectUtils().connect(token, getActivity());//如果连接断开重新连接
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
    }*/


    @Event(value = {R.id.right_img})
    private void click(View v) {
        Intent intent = null;
        switch (v.getId()) {

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
            case R.id.search_edittext:
                intent = new Intent(getActivity(), SearchMsgActivity.class);
                startActivity(intent);
                break;
            case R.id.recent_contacts:
                Intent intent1 = new Intent(getActivity(), NearContactsActivity.class);
                startActivity(intent1);

//                if (friendsList.getVisibility() == View.GONE) {
//                    friendsList.setVisibility(View.VISIBLE);
//                } else {
//                    friendsList.setVisibility(View.GONE);
//                }
                break;
            case R.id.group_layout:
                startActivity(new Intent(getActivity(), GroupActivity.class));
                break;
        }
    }


    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return false;
    }
}
