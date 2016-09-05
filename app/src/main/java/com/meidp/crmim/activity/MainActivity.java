package com.meidp.crmim.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.meidp.crmim.R;
import com.meidp.crmim.fragment.CompanyFragment;
import com.meidp.crmim.fragment.HomeFragment;
import com.meidp.crmim.fragment.MyFragment;
import com.meidp.crmim.fragment.ContactsFragment;
import com.meidp.crmim.http.HttpTask;
import com.meidp.crmim.imkit.ConversationListStaticFragment;
import com.meidp.crmim.utils.PermissionUtils;
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
    public static String userCode;
    private int REQUEST_CONTACTS = 100;

    @Override
    public void onInit() {
        mainActivity = this;
        mFragments = new ArrayList<>();
        mFragments.add(ConversationListStaticFragment.newInstance("PUBLIC"));
        mFragments.add(new CompanyFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new ContactsFragment());
        mFragments.add(new MyFragment());
        userCode = (String) SPUtils.get(this, "CODE", "");
        ((RadioButton) mRadioGroup.getChildAt(0)).setChecked(true);

        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.main_fragments_contents, mFragments.get(0));
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack("tag");
        transaction.commit();
        mRadioGroup.setOnCheckedChangeListener(this);
        System.out.println(SPUtils.get(this, "CODE", ""));

        Log.e("MainActicity", userCode);

        if (Build.VERSION.SDK_INT >= 23) {
            showContacts();
        } else {
            HttpTask.detectionNewAppVersion(this, true, false);//检查版本更新
        }
    }

    public void showContacts() {
        // Verify that all required contact permissions have been granted.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestContactsPermissions();
        } else {
            HttpTask.detectionNewAppVersion(this, true, false);//检查版本更新
        }
    }


    private void requestContactsPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_PHONE_STATE)) {
//            Snackbar.make(v, "permission_contacts_rationale", Snackbar.LENGTH_INDEFINITE).setAction("ok", new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
//                }
//            }).show();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
        }
    }

    private String[] PERMISSIONS_CONTACT = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CONTACTS) {
            if (PermissionUtils.verifyPermissions(grantResults)) {
                HttpTask.detectionNewAppVersion(this, true, false);//检查版本更新
            } else {
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
