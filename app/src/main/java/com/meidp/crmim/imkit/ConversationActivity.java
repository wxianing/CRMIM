package com.meidp.crmim.imkit;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.meidp.crmim.R;
import com.meidp.crmim.activity.BaseActivity;
import com.meidp.crmim.activity.ChattingRecordsActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import io.rong.imlib.model.Conversation;

/**
 * Package： com.meidp.crmim.imkit
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/7/9
 */
@ContentView(R.layout.conversation)
public class ConversationActivity extends BaseActivity {
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.title_right)
    private TextView titleRight;

    /**
     * 目标 Id
     */
    private String mTargetId;

    /**
     * 刚刚创建完讨论组后获得讨论组的id 为targetIds，需要根据 为targetIds 获取 targetId
     */
    private String mTargetIds;

    /**
     * 会话类型
     */
    private Conversation.ConversationType mConversationType;

    @Override
    public void onInit() {
        titleRight.setText("聊天记录");
        titleRight.setVisibility(View.VISIBLE);
        mTargetId = getIntent().getData().getQueryParameter("targetId");//获取聊天对象的Id
        String titleName = getIntent().getData().getQueryParameter("title");//获取聊天对象的Id
        title.setText(titleName);
    }

    @Event(value = {R.id.back_arrows, R.id.title_right})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                Intent intent = new Intent(this, ChattingRecordsActivity.class);
                intent.putExtra("userId", mTargetId);
                startActivity(intent);
                break;
        }
    }
}
