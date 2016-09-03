package com.meidp.crmim.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.meidp.crmim.activity.DocumentListActivity;
import com.meidp.crmim.model.DocBean;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.FileInfo;
import io.rong.imkit.widget.provider.FileInputProvider;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.model.Message;
import io.rong.message.FileMessage;

/**
 * Package： com.meidp.crmim.utils
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/9/3
 */
public class MyFileInputProvider extends FileInputProvider {
    private List<DocBean> checkDocLists;

    public MyFileInputProvider(RongContext context) {
        super(context);
    }

    @Override
    public void onPluginClick(View view) {
        ToastUtils.shows(RongContext.getInstance(), "点击了文件");
        Intent intent = new Intent();
        intent.setClass(getContext(), DocumentListActivity.class);

        intent.putExtra("MSG", "adddocuments");
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        checkDocLists = (List<DocBean>) data.getSerializableExtra("CheckDocLists");
//        String docNames = "";
//        if (checkDocLists != null && checkDocLists.size() > 0) {
//            for (int i = 0; i < checkDocLists.size(); i++) {
//                docNames += checkDocLists.get(i).getFileName() + ";";
//            }
//            LogUtils.e(docNames);
//        }

//        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null) {
//            HashSet selectedFileInfos = (HashSet) data.getSerializableExtra("CheckDocLists");
//            Iterator i$ = selectedFileInfos.iterator();

            List<DocBean> checkDocLists = (List<DocBean>) data.getSerializableExtra("CheckDocLists");

            for (int i = 0; i < checkDocLists.size(); i++) {
                DocBean fileInfo = checkDocLists.get(i);
                Uri filePath = Uri.parse("file://" + fileInfo.getPath());
                FileMessage fileMessage = FileMessage.obtain(filePath);

                if (fileMessage != null) {
//                    fileMessage.setType(fileInfo.getSuffix());
                    Message message = Message.obtain(this.getCurrentConversation().getTargetId(), this.getCurrentConversation().getConversationType(), fileMessage);
                    RongIM.getInstance().sendMediaMessage(message, (String) null, (String) null, (IRongCallback.ISendMediaMessageCallback) null);
                }
            }
        }
    }
}
