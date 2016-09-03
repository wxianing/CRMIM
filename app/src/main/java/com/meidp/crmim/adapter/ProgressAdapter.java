package com.meidp.crmim.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.meidp.crmim.R;
import com.meidp.crmim.activity.DocumentListActivity;
import com.meidp.crmim.activity.PrototypeDetailsActivity;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.DocBean;
import com.meidp.crmim.model.ProjectDetails;
import com.meidp.crmim.utils.FileUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Package： com.meidp.crmim.adapter
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/29
 */
public class ProgressAdapter extends BasicAdapter<ProjectDetails.ProcessListBean> {
    private Activity activity;
    private int projectId;

    public ProgressAdapter(List<ProjectDetails.ProcessListBean> mDatas, Context context, Activity activity, int projectId) {
        super(mDatas, context);
        this.activity = activity;
        this.projectId = projectId;
    }

    @Override
    public View createView(final int position, View convertView, ViewGroup parent) {
        final ProjectDetails.ProcessListBean data = mDatas.get(position);
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_process_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        if (NullUtils.isNull(data.getProcessName())) {
            vh.submitProject.setText(data.getProcessName() + "：");
        } else {
            vh.submitProject.setText("");
        }
        if (NullUtils.isNull(data.getProcessTime())) {
            vh.submitTime.setText(data.getProcessTime());
        } else {
            vh.submitTime.setText("");
        }
        if (NullUtils.isNull(data.getMsg()) && !data.getMsg().equals("(当前状态)")) {
            vh.msg.setText(data.getMsg() + "  " + data.getFileNames());
        } else {
            vh.msg.setText("");
        }
        vh.linearLayout.removeAllViews();
        if (NullUtils.isNull(data.getFileNames())) {

            final String[] fileNames = data.getFileNames().split(";");
            final String[] filePaths = data.getFilePaths().split(";");

            for (int i = 0; i < fileNames.length; i++) {
                TextView textView = new TextView(context);
                textView.setTextColor(Color.rgb(255, 128, 0));
                textView.setText(fileNames[i]);
                Log.e("fileName", position + ">>>>>>>>>>" + fileNames[i]);

                final int finalI = i;

//            String html = "<a href=" + filePaths[i] + ">" + fileNames[i] + "</a>";
//            CharSequence charSequence = Html.fromHtml(html);
//            textView.setText(charSequence);
//            textView.setMovementMethod(LinkMovementMethod.getInstance());

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("filePaths", fileNames[finalI]);
                        String savePath = Environment.getExternalStorageDirectory()
                                .toString()
                                + File.separator
                                + fileNames[finalI];
                        String url = filePaths[finalI];
                        url = url.replace(" ", "");
                        savePath = savePath.replace(" ", "");

                        ToastUtils.shows(context, "正在下载");
                        HttpRequestUtils.getmInstance().downLoadFile(url, savePath, new HttpRequestCallBack<File>() {
                            @Override
                            public void onSuccess(String result) throws IOException {

                            }

                            @Override
                            public void onSuccess(File result) {
                                super.onSuccess(result);
                                Log.e("result", "下载成功");
                                ToastUtils.shows(context, "下载成功");
                                FileUtils.openFile(result, activity);//打开文档
                            }
                        });
                    }
                });
                vh.linearLayout.addView(textView);
            }
        }

        vh.msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        vh.addDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showFileChooser();
                queryFiles(position);
            }
        });
        vh.msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDatas.get(position).getFKId() != 0) {
                    Intent intent = new Intent(context, PrototypeDetailsActivity.class);
                    intent.putExtra("OID", mDatas.get(position).getFKId());
                    context.startActivity(intent);
                }
            }
        });

        return convertView;
    }

    private void queryFiles(int position) {
        List<DocBean> beanList = new ArrayList<>();
        String[] projection = new String[]{MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.SIZE
        };

        String[] type = new String[]{"%.doc", "%.docx", "%.xlsx", "%.xls", "%.ppt", "%.pptx"};
        Cursor cursor = null;
        for (int i = 0; i < type.length; i++) {
            cursor = getCursor(beanList, projection, type[i]);
        }

        Intent intent = new Intent(context, DocumentListActivity.class);
        intent.putExtra("DocBean", (Serializable) beanList);
        intent.putExtra("ProjectId", projectId);
        intent.putExtra("ProcessId", mDatas.get(position).getProcessId());
        activity.startActivityForResult(intent, 1030);
        cursor.close();
    }

    private Cursor getCursor(List<DocBean> beanList, String[] projection, String docType) {
        Cursor cursorDoc = activity.getContentResolver().query(
                Uri.parse("content://media/external/file"),
                projection,
                MediaStore.Files.FileColumns.DATA + " like ?",
                new String[]{docType},
                null);

        if (cursorDoc != null) {
            if (cursorDoc.moveToFirst()) {
                int idindex = cursorDoc.getColumnIndex(MediaStore.Files.FileColumns._ID);
                int dataindex = cursorDoc.getColumnIndex(MediaStore.Files.FileColumns.DATA);
                int sizeindex = cursorDoc.getColumnIndex(MediaStore.Files.FileColumns.SIZE);
                do {
                    DocBean docBean = new DocBean();
//                    ToastUtils.shows(context, "扫描到文件了");
                    String id = cursorDoc.getString(idindex);
                    String path = cursorDoc.getString(dataindex);
                    String size = cursorDoc.getString(sizeindex);
                    docBean.setId(id);
                    docBean.setPath(path);
                    docBean.setSize(size);
                    int dot = path.lastIndexOf("/");
                    String name = path.substring(dot + 1);
                    docBean.setFileName(name);
                    Log.e("test", name);
                    beanList.add(docBean);
                } while (cursorDoc.moveToNext());
            }
        }
        return cursorDoc;
    }

    public static class ViewHolder {
        @ViewInject(R.id.submit_project)
        public TextView submitProject;
        @ViewInject(R.id.submit_date)
        private TextView submitTime;
        @ViewInject(R.id.msg_tv)
        private TextView msg;
        @ViewInject(R.id.add_documents)
        private Button addDocuments;
        @ViewInject(R.id.linear_layout)
        private LinearLayout linearLayout;

        public ViewHolder(View view) {
            x.view().inject(this, view);
        }
    }
}
