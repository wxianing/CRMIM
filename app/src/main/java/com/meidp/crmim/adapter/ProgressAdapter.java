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

        final String[] fileNames = data.getFileNames().split(";");
        final String[] filePaths = data.getFilePaths().split(";");
        vh.linearLayout.removeAllViews();
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
//                    ToastUtils.shows(context, filePaths[finalI]);
                    Log.e("filePaths", fileNames[finalI]);
//                    ToastUtils.shows(context, fileNames[finalI]);
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
                            openFile(result);//打开文档
                        }
                    });
                }
            });
            vh.linearLayout.addView(textView);
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

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("doc/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            activity.startActivityForResult(Intent.createChooser(intent, "请选择一个要上传的文件"),
                    1029);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(context, "请安装文件管理器", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void openFile(File file) {
        try {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //设置intent的Action属性
            intent.setAction(Intent.ACTION_VIEW);
            //获取文件file的MIME类型
            String type = getMIMEType(file);
            //设置intent的data和Type属性。
            intent.setDataAndType(/*uri*/Uri.fromFile(file), type);
            //跳转
            activity.startActivity(intent); //这里最好try一下，有可能会报错。 //比如说你的MIME类型是打开邮箱，但是你手机里面没装邮箱客户端，就会报错。
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getMIMEType(File file) {

        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
            /* 获取文件的后缀名*/
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "") return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) { //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    private final String[][] MIME_MapTable = {
            //{后缀名，MIME类型}
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/x-zip-compressed"},
            {"", "*/*"}
    };

    private void queryFiles(int position) {
        List<DocBean> beanList = new ArrayList<>();
        String[] projection = new String[]{MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.SIZE
        };

        String[] type = new String[]{"%.doc", "%.docx", "%.xlsx", "%.jpeg", "%.zip", "%.ppt", "%.pptx"};
        Cursor cursor = null;
        for (int i = 0; i < type.length; i++) {
            cursor = getCursor(beanList, projection, type[i]);
        }

        for (int i = 0; i < beanList.size(); i++) {
            Log.e("tag", beanList.get(i).getFileName());
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
