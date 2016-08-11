package com.meidp.crmim.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.meidp.crmim.R;
import com.meidp.crmim.adapter.SectionsPagerAdapter;
import com.meidp.crmim.imkit.ConversationListStaticFragment;
import com.meidp.crmim.model.Fragments;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */

@ContentView(R.layout.fragment_message)
public class MessageFragment extends BaseFragment {

    @ViewInject(R.id.tab_layout)
    private TabLayout tabLayout;
    @ViewInject(R.id.viewpager_container)
    private ViewPager mViewPager;
    private List<Fragments> fragments;
    private SectionsPagerAdapter mAdapter;

    public MessageFragment() {
    }

    @Override
    public void onInit() {
        fragments = new ArrayList<>();
        fragments.add(new Fragments(new ConversationListStaticFragment(), "消息"));
        fragments.add(new Fragments(new ContactsFragment(), "联系人"));
        mAdapter = new SectionsPagerAdapter(fragments, getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(mViewPager);
    }
}
