package com.meidp.crmim.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.DocBean;
import com.meidp.crmim.model.Product;
import com.meidp.crmim.model.Projects;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.FileUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.ToastUtils;
import com.meidp.crmim.view.WheelView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 申报项目
 */
@ContentView(R.layout.activity_submission)
public class SubmissionActivity extends BaseActivity {
    private static final String[] PLANETS = new String[]{"10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};

    @ViewInject(R.id.title_tv)
    private TextView title;

    @ViewInject(R.id.hospital)
    private EditText hospitalEt;
    private int hospitalId;
    private String hospitalNames;
    @ViewInject(R.id.project_area)
    private EditText projectArea;
    @ViewInject(R.id.edittext_total_price)
    private EditText projectTotalPriceEt;
    @ViewInject(R.id.department_name)
    private EditText departmentNameEt;
    @ViewInject(R.id.count_et)
    private EditText countEt;
    private ArrayList<Product> products;
    private int productID;
    @ViewInject(R.id.type_select)
    private EditText typeSelect;

    private String productName;
    private String projectName;

    private List<Product> prototypes;
    private String custContact,custName="";
    private Projects projects;
    private int projectDirectionId;


    @ViewInject(R.id.direction)
    private TextView directionEt;


    private String empolyeeId;
    private ArrayList<String> userIds;
    @ViewInject(R.id.related_personnel)
    private EditText relatedPersonnel;
    private double rate = 10;

    @ViewInject(R.id.edittext_success_rate)
    private EditText projectSuccessRateEt;
    @ViewInject(R.id.edittext_project_name)
    private EditText projectNameEt;
    private String contactPhone;
    private int custContactId,custId=0;
    @ViewInject(R.id.add_doc_img)
    private ImageView addDocImg;
    @ViewInject(R.id.document_name)
    private TextView documentName;
    private List<DocBean> checkDocLists;

    @Override
    public void onInit() {
        title.setText("申报项目");
        prototypes = new ArrayList<>();
            custId = getIntent().getIntExtra("custId", 0);
            hospitalId = custId;
            custContactId = getIntent().getIntExtra("custContactId", 0);
            contactPhone = getIntent().getStringExtra("contactPhone");
            custContact = getIntent().getStringExtra("custContact");
            custName = getIntent().getStringExtra("custName");
            hospitalEt.setText(custName);

        Projects projects = (Projects) getIntent().getSerializableExtra("Projects");
        if (projects != null) {
            if (NullUtils.isNull(projects.getProjectName())) {
                hospitalEt.setText(projects.getCustName());
            }
            if (NullUtils.isNull(projects.getDepartmentName())) {
                departmentNameEt.setText(projects.getDepartmentName());
            }
            if (NullUtils.isNull(projects.getProjectName())) {
                projectNameEt.setText(projects.getProjectName());
            }

            if (NullUtils.isNull(projects.getProjectDirectionName())) {
                directionEt.setText(projects.getProjectDirectionName());
            }
//            if (NullUtils.isNull(projects.getC))
        }
    }

    @Event({R.id.add_doc_img, R.id.save_btn, R.id.back_arrows, R.id.hospital, R.id.edittext_project_name, R.id.direction, R.id.type_select, R.id.related_personnel, R.id.edittext_success_rate})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.hospital:
                intent = new Intent(this, HospitalListActivity.class);
                startActivityForResult(intent, 1012);
                break;
            case R.id.edittext_project_name:
                intent = new Intent(this, ProjectManagerActivity.class);
                intent.putExtra("FLAG", "Apply");
                startActivityForResult(intent, 1004);
                break;
            case R.id.direction:
                intent = new Intent(this, ProjectDirectionActivity.class);
                startActivityForResult(intent, 1016);
                break;
            case R.id.type_select:
                intent = new Intent(this, ProduceCenterActivity.class);
                startActivityForResult(intent, 1009);
                break;
            case R.id.related_personnel:
                intent = new Intent(this, SelectEmpolyeeActivity.class);
                startActivityForResult(intent, 1013);
                break;
            case R.id.edittext_success_rate:
                View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
                WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
                wv.setOffset(1);
                wv.setItems(Arrays.asList(PLANETS));
                wv.setSeletion(0);
                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        rate = Double.valueOf(item) / 100;
                        projectSuccessRateEt.setText(item + "%");
                    }
                });
                new AlertDialog.Builder(this)
                        .setTitle("请选择成功率(%)")
                        .setView(outerView)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(SubmitActivity.this, ">>>>>>>>", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                break;
            case R.id.save_btn://保存项目
                sendMsg();

                break;
            case R.id.add_doc_img:
                intent = new Intent(this, DocumentListActivity.class);
                intent.putExtra("MSG", "adddocuments");
                startActivityForResult(intent, 100);
                break;
        }
    }

    private void sendDocMsg(DocBean docBean, int projectId, final Projects project) {
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
            params.put("ProcessId", 1);

            params.put("FileNames", docBean.getFileName());
//            params.put("FilePaths", mDagas.get(position).getPath());

            HttpRequestUtils.getmInstance().send(SubmissionActivity.this, Constant.UPDATE_DOCUMENTS_URL, params, new HttpRequestCallBack() {
                @Override
                public void onSuccess(String result) throws IOException {
                    AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                    });
                    if (appMsg != null && appMsg.getEnumcode() == 0) {
                        Intent intent = new Intent();

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Projects",project);
                        intent.putExtras(bundle);
                        setResult(1028, intent);
                        Log.e("保存成功", result);
                        finish();
                    } else {
                        ToastUtils.shows(SubmissionActivity.this, appMsg.getMsg());
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case 1012:
                String hospitalName = data.getStringExtra("CustName");
                String hospitalNO = data.getStringExtra("CustNo");
                hospitalId = data.getIntExtra("CustId", 0);
                hospitalEt.setText(hospitalName);
                break;
            case 1009:
                products = (ArrayList<Product>) data.getSerializableExtra("Product");
                productID = data.getIntExtra("ProductID", -1);
                productName = data.getStringExtra("ProductName");
                typeSelect.setText(productName);

                break;
            case 1016:
                projectDirectionId = data.getIntExtra("ProjectDirectionId", 0);
                String directionName = data.getStringExtra("ProjectDirectionName");
                Log.e("projectDirectionId", ">>>>>>>>>>>" + directionName);
                directionEt.setText(directionName);
                break;
            case 1013:
                empolyeeId = data.getStringExtra("EmpolyeeId");
                String empolyeeNames = data.getStringExtra("EmpolyeeName");
                relatedPersonnel.setText(empolyeeNames);
                userIds = data.getStringArrayListExtra("UserIds");
                Log.e("empolyeeId", empolyeeId);
                Log.e("empolyeeName", empolyeeNames);
                break;
            case 100:
                checkDocLists = (List<DocBean>) data.getSerializableExtra("CheckDocLists");
                String docNames = "";
                if (checkDocLists != null && checkDocLists.size() > 0) {
                    for (int i = 0; i < checkDocLists.size(); i++) {
                        docNames += checkDocLists.get(i).getFileName() + ";";
                    }
                    documentName.setText(docNames);
                }

                break;
        }
    }

    private void sendMsg() {
        projectName = projectNameEt.getText().toString().trim();
        String projectAddress = projectArea.getText().toString().trim();
        String projectTotalPrice = projectTotalPriceEt.getText().toString().trim();
        String departmentName = departmentNameEt.getText().toString().trim();
        String count = countEt.getText().toString().trim();

        if (!NullUtils.isNull(projectName)) {
            ToastUtils.shows(SubmissionActivity.this, "请输入项目名称");
            return;
        }

        if (!NullUtils.isNull(departmentName)) {
            ToastUtils.shows(SubmissionActivity.this, "请输入科室");
            return;
        }

        if (!NullUtils.isNull(count)) {
            ToastUtils.shows(SubmissionActivity.this, "请输入数量");
            return;
        }

        for (int i = 0; i < products.size(); i++) {
            Product product = new Product();
            product.setProductCount(Double.valueOf(count));
            product.setProductName(products.get(i).getProductName());
            product.setProductID(products.get(i).getProductID());
            prototypes.add(product);
        }
        HashMap params = new HashMap();

        params.put("ProjectName", projectName);//项目名称
        params.put("Address", projectAddress);//项目地址
        params.put("CustLinkMan", custContact);//客户联系人
        params.put("CustLinkTel", contactPhone);//客户号码
        params.put("Investment", 0);//总价钱
        params.put("SuccessRate", rate);//成功率
        params.put("CanViewUser", empolyeeId);//相关人员
        params.put("ProjectDirectionId", projectDirectionId);
        params.put("CustLinkManId", custContactId);
        params.put("CustID", hospitalId);//医院
        params.put("DepartmentName", departmentName);//科室
        params.put("Details", prototypes);//机型

        HttpRequestUtils.getmInstance().send(SubmissionActivity.this, Constant.SAVE_PROJECT, params, new HttpRequestCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        AppBean<Projects> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<Projects>>() {
                        });
                        if (appBean != null && appBean.getEnumcode() == 0) {
                            if (checkDocLists != null && checkDocLists.size() > 0) {//如果有文档就上传文档
                                for (int i = 0; i < checkDocLists.size(); i++) {
                                    sendDocMsg(checkDocLists.get(i), appBean.getData().getID(),appBean.getData());
                                }
                            } else {//没有文档直接返回
                                if (appBean != null && appBean.getEnumcode() == 0) {
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("Projects", appBean.getData());
                                    intent.putExtras(bundle);
                                    setResult(1028, intent);
                                    Log.e("保存成功", result);
                                    finish();
                                } else {
                                    ToastUtils.shows(SubmissionActivity.this, appBean.getMsg());
                                }
                            }
                        }
                    }
                }
        );
    }
}
