package com.meidp.crmim.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.meidp.crmim.R;
import com.meidp.crmim.http.HttpRequestCallBack;
import com.meidp.crmim.http.HttpRequestUtils;
import com.meidp.crmim.model.AppBean;
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.model.CustomerLists;
import com.meidp.crmim.model.Product;
import com.meidp.crmim.model.Projects;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.DataUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.PermissionUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.utils.ToastUtils;
import com.meidp.crmim.view.WheelView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SigninMainActivity extends BaseActivity implements BDLocationListener, View.OnClickListener {

    private static final String[] PLANETS = new String[]{"10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};

    private ImageView backImg;

    private TextView title;
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private LocationClient mLocalClient;
    private boolean isFirstLoc = true; // 是否首次定位

    private TextView address;

    private BitmapDescriptor mCurrentMarker;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    private TextView addressTrim;
    private TextView currDate;
    private TextView currTime;

    private ImageView signIn;//签到按钮

    private TextView customerName;
    private String customName;
    private int cusId;
    private EditText contentTv;

    private TextView userName;//名称

    private ImageView headerImg;//头像
    private double latitude;
    private double longitude;
    private String addressStr;
    @ViewInject(R.id.right_scan)
    private ImageView rightScan;//历史拜访
    @ViewInject(R.id.right_add)
    private ImageView custImg;
    private int projectDirectionId;

    @ViewInject(R.id.edittext_success_rate)
    private EditText projectSuccessRateEt;
    private String empolyeeId;
    private ArrayList<String> userIds;
    private double successRate;
    private String projectName;
    @ViewInject(R.id.save_btn)
    private Button saveBtn;
    private String custPhone;
    @ViewInject(R.id.add_img)
    private ImageView addImg;
    private Projects projects;
    private int REQUEST_CONTACTS = 100;
    @ViewInject(R.id.cust_related)
    private TextView custelated;
    @ViewInject(R.id.scrollView)
    private ScrollView scrollView;
    private String[] PERMISSIONS_CONTACT = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};
    private int hospitalId;
    private ArrayList<Product> products;
    private Projects project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_main);
        x.view().inject(this);
        mMapView = (MapView) findViewById(R.id.bmapView);
        if (Build.VERSION.SDK_INT >= 23) {
            showContacts(mMapView);
        } else {
            initView();
        }
    }

    public void showContacts(View v) {
        // Verify that all required contact permissions have been granted.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // Contacts permissions have not been granted.
            requestContactsPermissions(v);
        } else {
            initView();
        }
    }

    private void requestContactsPermissions(View v) {
        // BEGIN_INCLUDE(contacts_permission_request)
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_PHONE_STATE)
                ) {
            Snackbar.make(v, "permission_contacts_rationale",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions(SigninMainActivity.this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CONTACTS) {
            if (PermissionUtils.verifyPermissions(grantResults)) {
                initView();
            } else {
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
//            case BAIDU_READ_PHONE_STATE:
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
//                    mLocalClient.start();
//                } else {
//                    // 没有获取到权限，做特殊处理
//                }
//                break;
//            default:
//                break;
//        }
    }

    private List<Product> prototypes;

    private void initView() {
//        projectSuccessRateEt.setText("10");
        prototypes = new ArrayList<>();
        scrollView.smoothScrollTo(0, 20);//让ScrollView从顶端开始显示
        addImg = (ImageView) findViewById(R.id.add_img);
        addImg.setOnClickListener(this);
        custelated.setOnClickListener(this);
        custImg.setOnClickListener(this);
        rightScan.setOnClickListener(this);
        rightScan.setVisibility(View.VISIBLE);
        rightScan.setImageResource(R.mipmap.cus_history);
        custImg.setImageResource(R.mipmap.cus_info);
        title = (TextView) findViewById(R.id.title_tv);
        title.setText("我的拜访");
        address = (TextView) findViewById(R.id.address_tv);
        custImg.setOnClickListener(this);
        rightScan.setOnClickListener(this);
        mBaiduMap = mMapView.getMap();
        mMapView.showZoomControls(false);
        typeSelect.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.marker_red);
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker));
        mBaiduMap.setMyLocationEnabled(true);
        mLocalClient = new LocationClient(SigninMainActivity.this);
        mLocalClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
        option.setPriority(LocationClientOption.NetWorkFirst);//设置网络定位优先
        option.setOpenGps(true);
        option.setIsNeedAddress(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);

        mLocalClient.setLocOption(option);
        mLocalClient.start();

        addressTrim = (TextView) findViewById(R.id.address_trim);
        addressTrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SigninMainActivity.this, VisitRecordActivity.class);
                startActivityForResult(intent, Constant.RESULTCODE);
            }
        });
        currDate = (TextView) findViewById(R.id.date_tv);
        currTime = (TextView) findViewById(R.id.curr_time);
//        signIn = (ImageView) findViewById(R.id.sign_in);

        currDate.setText(DataUtils.getDate() + "  " + DataUtils.getWeek());
        currTime.setText("当前时间：" + DataUtils.getTime());
//        signIn.setOnClickListener(this);
        backImg = (ImageView) findViewById(R.id.back_arrows);
//        titleRight = (TextView) findViewById(R.id.title_right);
        customerName = (TextView) findViewById(R.id.customer_name);
        contentTv = (EditText) findViewById(R.id.remark);


        backImg.setOnClickListener(this);

        customName = getIntent().getStringExtra("customName");
        if (NullUtils.isNull(customName)) {
            customerName.setText(customName);
        }
        cusId = getIntent().getIntExtra("CustomerId", -1);
        userName = (TextView) findViewById(R.id.username);
        String employeeName = (String) SPUtils.get(this, "EmployeeName", "");
        userName.setText(employeeName);

        headerImg = (ImageView) findViewById(R.id.header_img);
        String photoURL = (String) SPUtils.get(this, "PhotoURL", "");
        ImageLoader.getInstance().displayImage(photoURL, headerImg);

        customerName = (TextView) findViewById(R.id.customer_name);
        ///customerName.setOnClickListener(this);
        directionEt.setOnClickListener(this);
        projectSuccessRateEt.setOnClickListener(this);
        projectArea.setOnClickListener(this);
//        relatedPersonnel.setOnClickListener(this);//项目关键人
        projectNameEt.setOnClickListener(this);
        hospitalName.setOnClickListener(this);//选择医院

        String zhiwu = (String) SPUtils.get(this, "VZhiWu", "");
        custPhone = (String) SPUtils.get(this, "VContact", "");
        String custContact = (String) SPUtils.get(this, "VContactName", "");
        String departmentString = (String) SPUtils.get(this, "VDepartment", "");
        String custName = (String) SPUtils.get(this, "VHospitalName", "");

        zhiwuEt.setText(zhiwu);
        phoneNumEt.setText(custPhone);
        customerName.setText(custContact);
        hospitalName.setText(custName);
        department.setText(departmentString);

        customeMessage.setOnClickListener(this);
        projRelated.setOnClickListener(this);
//        hospitalEt.setOnClickListener(this);
        projectRelated.setOnClickListener(this);

    }


    private static final int BAIDU_READ_PHONE_STATE = 100;
//    private TextView customerName;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mMapView = null;
        mLocalClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation == null || mMapView == null) {
            return;
        }
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(0).latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();
        Log.e("location", bdLocation.getAddrStr() + ">>>>>" + bdLocation.getLatitude() + ">>>>" + bdLocation.getLongitude());
        SPUtils.save(this, "Latitude", bdLocation.getLatitude());
        SPUtils.save(this, "Longitude", bdLocation.getLongitude());
//        SPUtils.save(this, "Address", bdLocation.getAddrStr());
        latitude = bdLocation.getLatitude();
        longitude = bdLocation.getLongitude();
        addressStr = bdLocation.getAddrStr();

        if (NullUtils.isNull(bdLocation.getAddrStr())) {
            mLocalClient.stop();
        }
        if (NullUtils.isNull(bdLocation.getAddrStr())) {
            address.setText(bdLocation.getAddrStr());
        } else {
            address.setText("定位失败，请检查当前网络连接状态");
        }

        mBaiduMap.setMyLocationData(locData);
        if (isFirstLoc) {
            isFirstLoc = false;
            LatLng ll = new LatLng(bdLocation.getLatitude(),
                    bdLocation.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }
    }

    @ViewInject(R.id.phone_num)
    private EditText phoneNumEt;
    @ViewInject(R.id.hospital_name)
    private TextView hospitalName;

    @ViewInject(R.id.department)
    private EditText department;
    @ViewInject(R.id.direction)
    private TextView directionEt;

    private int custId;
    private String custName;
    private String contactPhone;
    private int custContactId;
    private String custContact;
    private double rate = 10;
    @ViewInject(R.id.project_area)
    private EditText projectArea;
    @ViewInject(R.id.related_personnel)
    private EditText relatedPersonnel;
    @ViewInject(R.id.zhiwu_et)
    private EditText zhiwuEt;
    @ViewInject(R.id.hospital_layout_cust)
    private LinearLayout hospital_layout_cust;

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            if (NullUtils.isNull(data.getStringExtra("ADDRESS"))) {
                address.setText(data.getStringExtra("ADDRESS"));
            }
        } else if (requestCode == 1001 && data != null) {
            custId = data.getIntExtra("OID", -1);
            custContactId = data.getIntExtra("CustContactId", -1);
            custName = data.getStringExtra("CustName");
            contactPhone = data.getStringExtra("ContactPhone");
            custContact = data.getStringExtra("CustContact");//联系人
            String departmentString = data.getStringExtra("Department");
            custPhone = data.getStringExtra("CustPhone");
            String zhiwu = data.getStringExtra("Position");

            SPUtils.save(SigninMainActivity.this, "VcustId", custId);
            SPUtils.save(SigninMainActivity.this, "VcustContactId", custContactId);
            if (NullUtils.isNull(zhiwu)) {
                SPUtils.save(SigninMainActivity.this, "VZhiWu", zhiwu);
            }
            if (NullUtils.isNull(custPhone)) {
                SPUtils.save(SigninMainActivity.this, "VContact", custPhone);
            }
            if (NullUtils.isNull(custContact)) {
                SPUtils.save(SigninMainActivity.this, "VContactName", custContact);
            }
            if (NullUtils.isNull(departmentString)) {
                SPUtils.save(SigninMainActivity.this, "VDepartment", departmentString);
            }
            if (NullUtils.isNull(custName)) {
                SPUtils.save(SigninMainActivity.this, "VHospitalName", custName);
            }

            zhiwuEt.setText(zhiwu);
            phoneNumEt.setText(custPhone);
            customerName.setText(custContact);
            hospitalName.setText(custName);
            department.setText(departmentString);

            int tag = data.getIntExtra("SELECTTAG", -1);
            if (tag == Constant.RESULT_OK) {
                Intent intent = new Intent(SigninMainActivity.this, ProjectSelectActivity.class);
                intent.putExtra("custContact", custContact);
                intent.putExtra("custContactId", custContactId);
                intent.putExtra("contactPhone", contactPhone);
                startActivityForResult(intent, 1025);
            }

        } else if (resultCode == 1016) {
            projectDirectionId = data.getIntExtra("ProjectDirectionId", 0);
            String directionName = data.getStringExtra("ProjectDirectionName");
            Log.e("projectDirectionId", ">>>>>>>>>>>" + projectDirectionId);
            directionEt.setText(directionName);
        } else if (resultCode == 1024) {
            String cityName = data.getStringExtra("cityName");
            Log.e("cityName>>>>", cityName);
            projectArea.setText(cityName);
        } else if (resultCode == 1013) {
            empolyeeId = data.getStringExtra("EmpolyeeId");
            String empolyeeNames = data.getStringExtra("EmpolyeeName");
            relatedPersonnel.setText(empolyeeNames);
            userIds = data.getStringArrayListExtra("UserIds");
            Log.e("empolyeeId", empolyeeId);
            Log.e("empolyeeName", empolyeeNames);
        } else if (resultCode == 1015) {
            String rate = data.getStringExtra("Reta");
            projectSuccessRateEt.setText(rate);
            successRate = Double.valueOf(rate) / 100;
        } else if (resultCode == 1004) {
            projects = (Projects) data.getSerializableExtra("Projects");
            if (projects != null) {
                projectId = projects.getID();
                projectNameEt.setText(projects.getProjectName());
                projectTotalPriceEt.setText("" + projects.getInvestment());
                directionEt.setText(projects.getProjectDirectionName());
                double d = projects.getSuccessRate() * 100;
                DecimalFormat df = new DecimalFormat("#");
                String result = df.format(d);
                double floor = Math.floor(d);
                projectSuccessRateEt.setText(result + "%");
//                projectArea.setText(projects.get);
            }
        } else if (resultCode == 1012) {//选择医院
/*            String hospitalName = data.getStringExtra("CustName");
            String hospitalNO = data.getStringExtra("CustNo");
            hospitalId = data.getIntExtra("CustId", 0);
            hospitalEt.setText(hospitalName);*/
            CustomerLists custModel = JSONObject.parseObject(data.getStringExtra("CustModel"), new TypeReference<CustomerLists>() {
            });
            if (custModel != null) {

                custId = custModel.getID();
                hospitalId = custModel.getID();
                hospitalEt.setText(custModel.getCustName());
                hospitalName.setText(custModel.getCustName());
                custName = hospitalName.getText().toString().trim();


                List<CustomerLists.LinMansBean> linMansList = custModel.getLinMans();
                SPUtils.save(SigninMainActivity.this, "VcustId", custId);
                SPUtils.save(SigninMainActivity.this, "VcustContactId", custContactId);

                if (NullUtils.isNull(custName)) {
                    SPUtils.save(SigninMainActivity.this, "VHospitalName", custName);
                }

                //关键联系人信息
                if (linMansList != null && linMansList.size() > 0) {
                    CustomerLists.LinMansBean linkmanModel = linMansList.get(0);
                    CustomerNameEt.setText(linkmanModel.getLinkManName() + "");
                    phoneNumEt.setText(linkmanModel.getWorkTel() + "");
                    DepartmentEt.setText(linkmanModel.getDepartment() + "");

                    String zhiwu = linkmanModel.getPosition();
                    custContact = linkmanModel.getLinkManName();
                    custPhone = linkmanModel.getWorkTel();
                    String departmentString = linkmanModel.getDepartment();

                    if (NullUtils.isNull(zhiwu)) {
                        SPUtils.save(SigninMainActivity.this, "VZhiWu", zhiwu);
                    }
                    if (NullUtils.isNull(custPhone)) {
                        SPUtils.save(SigninMainActivity.this, "VContact", custPhone);
                    }
                    if (NullUtils.isNull(custContact)) {
                        SPUtils.save(SigninMainActivity.this, "VContactName", custContact);
                    }
                    if (NullUtils.isNull(departmentString)) {
                        SPUtils.save(SigninMainActivity.this, "VDepartment", departmentString);
                    }

                } else {
                    CustomerNameEt.setText(custModel.getContactName() + "");
                    phoneNumEt.setText(custModel.getMobile() + "");
                    DepartmentEt.setText(custModel.getContactOperation() + "");
                }
                List<CustomerLists.ProjectsBean> ProjectList = custModel.getProjects();
                if (ProjectList != null && ProjectList.size() > 0) {
                    CustomerLists.ProjectsBean project = ProjectList.get(0);
                    projRelated.setTextColor(Color.rgb(255, 147, 58));
                    custelated.setTextColor(Color.rgb(40, 40, 40));
                    scrollView.smoothScrollTo(0, 20);//让ScrollView从顶端开始显示

                    projectId = project.getID();
                    if (NullUtils.isNull(custModel.getCustName())) {
                        hospitalEt.setText(custModel.getCustName());
                    }
                    if (NullUtils.isNull(project.getDepartmentName())) {
                        departmentNameEt.setText(project.getDepartmentName());
                    }
                    if (NullUtils.isNull(project.getProjectName())) {
                        projectNameEt.setText(project.getProjectName());
                    }

                 /*   if (NullUtils.isNull(project.getProjectDirectionName())) {
                        directionEt.setText(project.getProjectDirectionName());
                    }*/
                    projectSuccessRateEt.setText(project.getSuccessRate() * 100 + "%");
                } else {
                    hospitalEt.setText("");
                    departmentNameEt.setText("");
                    projectNameEt.setText("");
                    projectSuccessRateEt.setText("");
                    Intent intent = new Intent(SigninMainActivity.this, ProjectSelectActivity.class);
                    intent.putExtra("custName", custModel.getCustName());
                    intent.putExtra("custId", custModel.getID());
                    intent.putExtra("ContactPhone", custModel.getMobile());
                    intent.putExtra("IsCreate", 1);
                    startActivityForResult(intent, 1025);
                }

            }
        } else if (resultCode == 1009) {
            products = (ArrayList<Product>) data.getSerializableExtra("Product");

            productID = data.getIntExtra("ProductID", -1);
            productName = data.getStringExtra("ProductName");
            typeSelect.setText(productName);
        } else if (resultCode == 1025) {
            project = (Projects) data.getSerializableExtra("Projects");
            if (project != null) {
                // customeMessage.setVisibility(View.GONE);
                //  projectRelated.setVisibility(View.VISIBLE);
                projRelated.setTextColor(Color.rgb(255, 147, 58));
                custelated.setTextColor(Color.rgb(40, 40, 40));
                scrollView.smoothScrollTo(0, 20);//让ScrollView从顶端开始显示

                Log.e("projects", "" + project.getCustName());
                projectId = project.getID();
                if (NullUtils.isNull(project.getCustName())) {
                    hospitalEt.setText(project.getCustName());
                }
                if (NullUtils.isNull(project.getDepartmentName())) {
                    departmentNameEt.setText(project.getDepartmentName());
                }
                if (NullUtils.isNull(project.getProjectName())) {
                    projectNameEt.setText(project.getProjectName());
                }

                if (NullUtils.isNull(project.getProjectDirectionName())) {
                    directionEt.setText(project.getProjectDirectionName());
                }
                projectSuccessRateEt.setText(project.getSuccessRate() * 100 + "%");
            }
        }
    }

    private int productID;
    private String productName;
    @ViewInject(R.id.count_et)
    private EditText countEt;

    private int projectId = 0;

    @ViewInject(R.id.edittext_project_name)
    private EditText projectNameEt;

    @ViewInject(R.id.edittext_project_address)
    private EditText projectAddressEt;

    @ViewInject(R.id.customer_name)
    private EditText CustomerNameEt;
    @ViewInject(R.id.edittext_customer_phone)
    private EditText CustomerPhoneEt;

    @ViewInject(R.id.edittext_total_price)
    private EditText projectTotalPriceEt;
    @ViewInject(R.id.department_name)
    private EditText departmentNameEt;
    @ViewInject(R.id.department)
    private EditText DepartmentEt;


    private void sendMsg() {

        String projectAddress = projectArea.getText().toString().trim();
        String projectTotalPrice = projectTotalPriceEt.getText().toString().trim();
        String departmentName = departmentNameEt.getText().toString().trim();
        String count = countEt.getText().toString().trim();

        if (!NullUtils.isNull(departmentName)) {
            ToastUtils.shows(SigninMainActivity.this, "请输入科室");
            return;
        }
        if (!NullUtils.isNull(count)) {
            ToastUtils.shows(SigninMainActivity.this, "请输入数量");
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

        HttpRequestUtils.getmInstance().send(SigninMainActivity.this, Constant.SAVE_PROJECT, params, new HttpRequestCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("保存成功", result);
                        AppBean<Projects> appBean = JSONObject.parseObject(result, new TypeReference<AppBean<Projects>>() {
                        });
                        if (appBean != null && appBean.getEnumcode() == 0) {
                            if (appBean.getData().getID() != 0) {
                                sendVisitMsg(appBean.getData().getID());
                            }
                        }
                    }
                }
        );
    }

    private void sendVisitMsg(int projectId) {
        String content = contentTv.getText().toString().trim();
        String departmentString = department.getText().toString().trim();
        custName = (String) SPUtils.get(SigninMainActivity.this, "VHospitalName", "");
        custContactId = (int) SPUtils.get(SigninMainActivity.this, "VcustContactId", 0);
        custPhone = phoneNumEt.getText().toString().trim();
        if (custId <= 0) {
            ToastUtils.shows(this, "请选择客户您要拜访的客户");
            return;
        }
/*        if (!NullUtils.isNull(custName)) {
            ToastUtils.shows(this, "请选择客户您要拜访的客户");
            return;
        }*/
        //custId = (int) SPUtils.get(SigninMainActivity.this, "VcustId", 0);

        HashMap params = new HashMap();
        params.put("Contents", content);//拜访内容
        params.put("CustID", custId);//客户Id
        params.put("Title", custName);
        params.put("Lat", latitude);//维度
        params.put("Lon", longitude);//经度
        params.put("LocationAddress", addressStr);//地址
        params.put("Department", departmentString);
        params.put("CustLinkMan", custContactId);//联系人
        params.put("LinkTel", custPhone);//联系人电话
        params.put("ProjectID", projectId);//联系人电话

        HttpRequestUtils.getmInstance().send(SigninMainActivity.this, Constant.SAVE_VISIT_CUSTOMER, params, new HttpRequestCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("visit", result);
                AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                });
                if (appMsg != null && appMsg.getEnumcode() == 0) {
                    Intent intent = new Intent(SigninMainActivity.this, VisitRecordActivity.class);
                    ToastUtils.shows(SigninMainActivity.this, "拜访成功");
                    startActivity(intent);
                    finish();
                } else {
                    ToastUtils.shows(SigninMainActivity.this, "拜访失败");
                }
            }
        });
    }

    @ViewInject(R.id.custome_message_layout)
    private LinearLayout customeMessage;
    @ViewInject(R.id.proj_related)
    private TextView projRelated;
    @ViewInject(R.id.project_related)
    private LinearLayout projectRelated;
    @ViewInject(R.id.hospital)
    private TextView hospitalEt;
    @ViewInject(R.id.type_select)
    private EditText typeSelect;

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
/*            case R.id.cust_related:
                customeMessage.setVisibility(View.VISIBLE);
                projectRelated.setVisibility(View.GONE);
                custelated.setTextColor(Color.rgb(255, 147, 58));
                projRelated.setTextColor(Color.rgb(40, 40, 40));
                scrollView.smoothScrollTo(0, 20);//让ScrollView从顶端开始显示
                break;
            case R.id.proj_related:
                customeMessage.setVisibility(View.GONE);
                projectRelated.setVisibility(View.VISIBLE);
                projRelated.setTextColor(Color.rgb(255, 147, 58));
                custelated.setTextColor(Color.rgb(40, 40, 40));
                scrollView.smoothScrollTo(0, 20);//让ScrollView从顶端开始显示
                break;*/
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                intent = new Intent(this, VisitRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.address_trim:
                intent = new Intent();
                intent.setClass(this, VisitRecordActivity.class);
                startActivityForResult(intent, Constant.RESULTCODE);
                break;
            case R.id.right_scan:
                intent = new Intent();
                intent.setClass(this, VisitRecordActivity.class);
                startActivityForResult(intent, Constant.RESULTCODE);
                break;
            case R.id.right_add:
                intent = new Intent(this, CustomerListActivity.class);
                startActivity(intent);
                break;
            case R.id.customer_name:
                intent = new Intent();
                intent.setClass(this, ContactsActivity.class);
                intent.putExtra("FLAG", "Apply");
                startActivityForResult(intent, 1001);
                break;
            case R.id.direction://项目领域
//                intent = new Intent(this, ProjectDirectionActivity.class);
//                startActivityForResult(intent, 1016);
                intent = new Intent(this, SubmissionActivity.class);
//                startActivity(intent);
                break;
            case R.id.edittext_success_rate:
//                View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
//                WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
//                wv.setOffset(1);
//                wv.setItems(Arrays.asList(PLANETS));
//                wv.setSeletion(0);
//                wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
//                    @Override
//                    public void onSelected(int selectedIndex, String item) {
//                        rate = Double.valueOf(item) / 100;
//                        projectSuccessRateEt.setText(item + "%");
//                    }
//                });
//                new AlertDialog.Builder(this)
//                        .setTitle("请选择成功率(%)")
//                        .setView(outerView)
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
////                                Toast.makeText(SubmitActivity.this, ">>>>>>>>", Toast.LENGTH_SHORT).show();
//                            }
//                        }).show();
                intent = new Intent(this, SubmissionActivity.class);
//                startActivity(intent);
                break;
            case R.id.project_area:
                intent = new Intent(this, CityListActivity.class);
                startActivityForResult(intent, 1024);
                break;
            case R.id.related_personnel:
                intent = new Intent(this, SelectEmpolyeeActivity.class);
                startActivityForResult(intent, 1013);
                break;
            case R.id.save_btn:
                projectName = projectNameEt.getText().toString().trim();
                if (NullUtils.isNull(productName) && projectId == 0) {
                    sendMsg();
                } else if (projectId == 0) {
                    sendVisitMsg(0);
                } else {
                    sendVisitMsg(projectId);
                }
                break;
            case R.id.add_img:
                intent = new Intent(this, ProjectManagerActivity.class);
                intent.putExtra("FLAG", "Apply");
                startActivityForResult(intent, 1004);
                break;
            case R.id.edittext_project_name:
//                intent = new Intent(this, ProjectManagerActivity.class);
//                intent.putExtra("FLAG", "Apply");
//                startActivityForResult(intent, 1004);
                intent = new Intent(this, SubmissionActivity.class);
//                startActivity(intent);
                break;
            case R.id.hospital:
            case R.id.hospital_name:
                intent = new Intent(this, HospitalListActivity.class);
                startActivityForResult(intent, 1012);
                break;
            case R.id.type_select:
//                intent = new Intent(this, ProduceCenterActivity.class);
//                startActivityForResult(intent, 1009);
                intent = new Intent(this, SubmissionActivity.class);
//                startActivity(intent);
                break;
           /* case R.id.project_related:
                intent = new Intent(this, SubmissionActivity.class);
//                startActivity(intent);
                break;*/
        }
    }

    @Event({R.id.remark, R.id.edittext_success_rate, R.id.hospital_layout, R.id.hospital_layout_cust, R.id.hospital, R.id.department_name, R.id.edittext_project_name, R.id.edittext_project_content, R.id.type_select, R.id.count_et, R.id.date_time})
    private void click(View v) {
        Intent intent = new Intent(this, SubmissionActivity.class);
        Bundle bundle = new Bundle();
        if (project != null) {
            bundle.putSerializable("Projects", project);
        }
        intent.putExtras(bundle);
//        startActivity(intent);
    }
}
