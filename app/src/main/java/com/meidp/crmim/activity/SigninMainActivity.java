package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.DataUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class SigninMainActivity extends BaseActivity implements BDLocationListener, View.OnClickListener {

    private ImageView backImg;

    private TextView title;
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private LocationClient mLocalClient;
    private boolean isFirstLoc = true; // 是否首次定位

    private TextView address;

    @ViewInject(R.id.customer_layout)
    private RelativeLayout customerLayout;

    private BitmapDescriptor mCurrentMarker;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    private TextView addressTrim;
    private TextView currDate;
    private TextView currTime;

    private ImageView signIn;//签到按钮

    private TextView titleRight;

    private TextView customerName;
    private String customName;
    private int cusId;
    private EditText contentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_main);
        x.view().inject(this);
        mMapView = (MapView) findViewById(R.id.bmapView);
        initView();
    }

//    @Event(R.id.customer_layout)
//    private void click(View v) {
//        ToastUtils.shows(this, "点击了客户");
//    }

    private void initView() {
        title = (TextView) findViewById(R.id.title_tv);
        title.setText("拜访客户");
        address = (TextView) findViewById(R.id.address_tv);

        mBaiduMap = mMapView.getMap();
        mMapView.showZoomControls(false);

        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.marker_red);
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker));
        mBaiduMap.setMyLocationEnabled(true);
        mLocalClient = new LocationClient(SigninMainActivity.this);
        mLocalClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true);
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
                intent.setClass(SigninMainActivity.this, LocalTrimmingActivity.class);
                startActivityForResult(intent, Constant.RESULTCODE);
            }
        });
        currDate = (TextView) findViewById(R.id.date_tv);
        currTime = (TextView) findViewById(R.id.curr_time);
        signIn = (ImageView) findViewById(R.id.sign_in);

        currDate.setText(DataUtils.getDate() + "  " + DataUtils.getWeek());
        currTime.setText(DataUtils.getTime());
        signIn.setOnClickListener(this);
        backImg = (ImageView) findViewById(R.id.back_arrows);
        titleRight = (TextView) findViewById(R.id.title_right);
        customerName = (TextView) findViewById(R.id.customer_name);
        contentTv = (EditText) findViewById(R.id.content_tv);

        titleRight.setText("拜访记录");
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setOnClickListener(this);

        backImg.setOnClickListener(this);
        customerName.setOnClickListener(this);

        customName = getIntent().getStringExtra("customName");
        if (NullUtils.isNull(customName)) {
            customerName.setText(customName);
        }
        cusId = getIntent().getIntExtra("CustomerId", -1);
    }

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
        SPUtils.save(this, "Address", bdLocation.getAddrStr());
        if (NullUtils.isNull(bdLocation.getAddrStr())) {
            mLocalClient.stop();
        }
        address.setText(bdLocation.getAddrStr());

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
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in:
//                String addr = address.getText().toString().trim();
//                if (NullUtils.isNull(addr)) {
//                    Intent intent = new Intent(this, SigninTextActivity.class);
//                    intent.putExtra("ADDRESS", addr);
//                    startActivity(intent);
//
//                    double latitude = (double) SPUtils.get(this, "Latitude", 0.0);
//                    double longitude = (double) SPUtils.get(this, "Longitude", 0.0);
//                    String address = (String) SPUtils.get(this, "Address", "");
//                    String content = contentTv.getText().toString().trim();
//                    ToastUtils.shows(this, "正在提交");
//                    HashMap params = new HashMap();
//                    params.put("Contents", content);
//                    params.put("CustID", cusId);
//                    params.put("Title", "拜访" + customName);
//                    params.put("Lat", latitude);
//                    params.put("Lon", longitude);
//                    params.put("LocationAddress", address);
//                    HttpRequestUtils.getmInstance().send(SigninMainActivity.this, Constant.SAVE_VISIT_CUSTOMER, params, new HttpRequestCallBack<String>() {
//                        @Override
//                        public void onSuccess(String result) {
//                            Log.e("visit", result);
//                            AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
//                            });
//                            if (appMsg != null && appMsg.getEnumcode() == 0) {
//                                Intent intent = new Intent(SigninMainActivity.this, CustomerVisitActivity.class);
//                                ToastUtils.shows(SigninMainActivity.this, "拜访成功");
//                                startActivity(intent);
//                                finish();
//                            } else {
//                                ToastUtils.shows(SigninMainActivity.this, "拜访失败");
//                            }
//                        }
//                    });
//                } else {
//                    ToastUtils.shows(this, "定位失败");
//                }
                break;
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                Intent intent = new Intent(this, VisitRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.customer_name:
                ToastUtils.shows(this, "点击了客户");
                intent = new Intent();
                intent.setClass(this, CustomerListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
