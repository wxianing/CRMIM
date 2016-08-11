package com.meidp.crmim.activity;

import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.model.Feedbacks;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_feedback_details)
public class FeedbackDetailsActivity extends BaseActivity {

    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_name)
    private TextView titleName;
    @ViewInject(R.id.time_tv)
    private TextView time;
    @ViewInject(R.id.content_tv)
    private TextView content;

    @Override
    public void onInit() {
        title.setText("意见反馈");
        Feedbacks feedbacks = (Feedbacks) getIntent().getSerializableExtra("Feedbacks");

        if (feedbacks != null) {
            titleName.setText(feedbacks.getTitle());
            time.setText(feedbacks.getCreateDate());
            content.setText(feedbacks.getContent());
        }
    }

    @Event({R.id.back_arrows})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
