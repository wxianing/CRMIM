package com.meidp.crmim.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jauker.widget.BadgeView;
import com.meidp.crmim.R;
import com.meidp.crmim.fragment.CompanyFragment;
import com.meidp.crmim.fragment.HomeFragment;
import com.meidp.crmim.fragment.MyFragment;
import com.meidp.crmim.imkit.ContactsFragment;
import com.meidp.crmim.imkit.ConversationListStaticFragment;
import com.meidp.crmim.utils.SPUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @ViewInject(R.id.main_bottom_rg)
    private RadioGroup mRadioGroup;
    private List<Fragment> mFragments;
    private FragmentManager manager;
    private long exitTime = 0;
    public static MainActivity mainActivity;
//    @ViewInject(R.id.btn_msg)
//    private Button message;

    @Override
    public void onInit() {
        mainActivity = this;
        mFragments = new ArrayList<>();
        mFragments.add(new ConversationListStaticFragment());
        mFragments.add(new CompanyFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new ContactsFragment());
        mFragments.add(new MyFragment());

        ((RadioButton) mRadioGroup.getChildAt(0)).setChecked(true);
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_fragments_contents, mFragments.get(0));
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack("tag");
        transaction.commit();
        mRadioGroup.setOnCheckedChangeListener(this);
        System.out.println(SPUtils.get(this, "CODE", ""));

//        BadgeView badgeView = new BadgeView(this);
//        badgeView.setTargetView(message);
//        badgeView.setBadgeCount(3);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < group.getChildCount(); i++) {
            Fragment fragment = mFragments.get(i);
            if (checkedId == group.getChildAt(i).getId()) {
                addFragment(fragment);
            } else {
                FragmentTransaction t = manager.beginTransaction();
                t.hide(fragment);
                t.commit();
            }
        }
    }

    /**
     * 添加Fragment
     *
     * @param fragment
     */
    private void addFragment(Fragment fragment) {
        FragmentTransaction t = manager.beginTransaction();
        if (!fragment.isAdded()) {
            t.add(R.id.main_fragments_contents, fragment);
        }
        t.show(fragment);
        t.commit();
    }

    /**
     * 监听返回键, 退出应用程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitSystem();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出系统
     */
    private void exitSystem() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次返回键退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
