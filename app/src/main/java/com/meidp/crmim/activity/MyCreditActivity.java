package com.meidp.crmim.activity;

import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.view.InstrumentView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.Timer;
import java.util.TimerTask;

@ContentView(R.layout.activity_my_credit)
public class MyCreditActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.iView)
    private InstrumentView iView;
    @ViewInject(R.id.txtView)
    private TextView txtView;

    @Override
    public void onInit() {
        title.setText("我的信用度");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                iView.setReferValue(692, new InstrumentView.RotateListener() {
                    @Override
                    public void rotate(float sweepAngle, final float value) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtView.setText(Math.round(value) + "");
                            }
                        });
                    }
                });
            }
        }, 1000);
    }

    @Event(value = {R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
