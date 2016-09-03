package com.meidp.crmim.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.adapter.DocumentAdapter;
import com.meidp.crmim.adapter.MyExpandableAdapter;
import com.meidp.crmim.adapter.SelectFriendAdapter;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.Child;
import com.meidp.crmim.model.DocBean;
import com.meidp.crmim.model.Friends;
import com.meidp.crmim.model.Group;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.FileUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DocumentListActivity extends BasicActivity implements AdapterView.OnItemClickListener, ExpandableListView.OnChildClickListener, View.OnClickListener {

    private TextView title;

    private List<DocBean> mDagas;

    private DocumentAdapter mAdapter;

    private ListView mListView;
    private int projectId;
    private int processId;
    private ImageView backImg;

    protected ExpandableListView expListView;

    private List<Group> list = new ArrayList<Group>();
    private int checkNum = 0;

    private List<DocBean> checkLists;

    private TextView titleRight;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_list);
        initView();
        initEvent();
    }

    private void initEvent() {
        mListView.setOnItemClickListener(this);
    }

    private void initView() {
        mDagas = new ArrayList<>();
        queryFiles();
        msg = getIntent().getStringExtra("MSG");
        checkLists = new ArrayList<>();
        checkLists.clear();
        projectId = getIntent().getIntExtra("ProjectId", 0);
        processId = getIntent().getIntExtra("ProcessId", 0);
        title = (TextView) findViewById(R.id.title_tv);
        title.setText("文件列表");
        mListView = (ListView) findViewById(R.id.listview);
        expListView = (ExpandableListView) findViewById(R.id.expListView);
        titleRight = (TextView) findViewById(R.id.title_right);
        titleRight.setText("确定");
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setOnClickListener(this);
        backImg = (ImageView) findViewById(R.id.back_arrows);
        backImg.setOnClickListener(this);
//        expListView.setGroupIndicator(null);//去掉默认箭头

        List<DocBean> docBeans = (List<DocBean>) getIntent().getSerializableExtra("DocBean");
        if (docBeans != null && docBeans.size() > 0) {
//            mDagas.addAll(docBeans);
        }

        Group g = new Group();
        g.setTitle("WORD");
        Group g1 = new Group();
        g1.setTitle("EXECL");
        Group g2 = new Group();
        g2.setTitle("PPT");

        ArrayList<Child> childList = new ArrayList<Child>();
        ArrayList<DocBean> docList = new ArrayList<>();
        ArrayList<DocBean> docList1 = new ArrayList<>();
        ArrayList<Child> childList1 = new ArrayList<Child>();
        ArrayList<DocBean> docList2 = new ArrayList<>();
        ArrayList<Child> childList2 = new ArrayList<Child>();
        for (int i = 0; i < mDagas.size(); i++) {
            int len = mDagas.get(i).getPath().lastIndexOf(".");
            String filType = mDagas.get(i).getPath().substring(len + 1);
            if (filType.equals("doc") || filType.equals("docx")) {
                docList.add(mDagas.get(i));
                childList.add(new Child(R.mipmap.headerphoto, mDagas.get(i).getFileName()));
            } else if (filType.equals("xlsx") || filType.equals("xls")) {
                docList1.add(mDagas.get(i));
                childList1.add(new Child(R.mipmap.headerphoto, mDagas.get(i).getFileName()));
            } else if (filType.equals("ppt") || filType.equals("pptx")) {
                docList2.add(mDagas.get(i));
                childList2.add(new Child(R.mipmap.headerphoto, mDagas.get(i).getFileName()));
            }
        }
        g.setChildList(childList);
        g.setDocBeen(docList);

        g1.setChildList(childList1);
        g1.setDocBeen(docList1);
        g2.setDocBeen(docList2);
        g2.setChildList(childList2);

        list.add(g);
        list.add(g1);
        list.add(g2);

        expListView.setOnChildClickListener(this);
        expListView.setAdapter(new MyExpandableAdapter(list, this));
        //默认全部展开
        for (int i = 0; i < list.size(); i++) {
            expListView.expandGroup(i);
        }

        mAdapter = new DocumentAdapter(mDagas, this);
//        mListView.setAdapter(mAdapter);
    }

    private void queryFiles() {
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

        mDagas.addAll(beanList);

        cursor.close();

    }

    private Cursor getCursor(List<DocBean> beanList, String[] projection, String docType) {
        Cursor cursorDoc = getContentResolver().query(
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtils.shows(this, mDagas.get(position).getPath());

    }

    private void sendMsg(DocBean docBean, final boolean boo) {
        try {
            String fileString = FileUtils.encodeBase64File(docBean.getPath());

            int len = docBean.getPath().lastIndexOf(".");
            String type = docBean.getPath().substring(len + 1);
            Log.e("fileString", fileString);
            Log.e("fileSize", docBean.getSize());
            Log.e("fileType", type);

            HashMap params = new HashMap();
            params.put("Base64Data", fileString);
            params.put("ProjectId", projectId);
            params.put("ProcessId", processId);
            params.put("FileNames", docBean.getFileName());
//            params.put("FilePaths", mDagas.get(position).getPath());

            HttpRequestUtils.getmInstance().send(DocumentListActivity.this, Constant.UPDATE_DOCUMENTS_URL, params, new HttpRequestCallBack() {
                @Override
                public void onSuccess(String result) throws IOException {
                    AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                    });
                    if (appMsg != null && appMsg.getEnumcode() == 0) {
                        ToastUtils.shows(DocumentListActivity.this, "上传成功");
                        Intent intent = new Intent();
                        setResult(1030, intent);
                        checkLists.clear();
                        finish();
                    } else {
                        ToastUtils.shows(DocumentListActivity.this, appMsg.getMsg());
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        MyExpandableAdapter.ChildViewHolder holder = (MyExpandableAdapter.ChildViewHolder) v.getTag();
        holder.mCheckBox.toggle();

        MyExpandableAdapter.getIsSelected().put(list.get(groupPosition).getDocBeen().get(childPosition).getFileName(), holder.mCheckBox.isChecked());
        if (holder.mCheckBox.isChecked() == true) {
            checkNum++;
            //如果选中，则添加到被选集合里面
            checkLists.add(list.get(groupPosition).getDocBeen().get(childPosition));
        } else {
            checkNum--;
            for (int i = 0; i < checkLists.size(); i++) {
                //如果取消选择则用当前的文件名到被选中的集合里面去匹配，寻找，然后remove掉它
                if (checkLists.get(i).getFileName().equals(list.get(groupPosition).getDocBeen().get(childPosition).getFileName())) {
                    checkLists.remove(i);
                }
            }
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_right:
                if (NullUtils.isNull(msg) && msg.equals("adddocuments")) {
                    Intent intent = new Intent();
                    intent.putExtra("CheckDocLists", (Serializable) checkLists);
                    setResult(100, intent);
                    finish();
                } else {
                    Log.e("checkList", ">>>>>" + checkLists.size());
                    for (int i = 0; i < checkLists.size(); i++) {
                        if (i == checkLists.size() - 1) {
                            sendMsg(checkLists.get(i), true);
                        } else {
                            sendMsg(checkLists.get(i), false);
                        }
                    }
                }
                break;
            case R.id.back_arrows:
                finish();
                break;
        }
    }
}
