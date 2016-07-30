package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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

public class CustomerVisitActivity extends BaseActivity implements BDLocationListener, View.OnClickListener {

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

    private TextView titleRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_main);
        mMapView = (MapView) findViewById(R.id.bmapView);
        initView();
    }

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
        mLocalClient = new LocationClient(CustomerVisitActivity.this);
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
                intent.setClass(CustomerVisitActivity.this, LocalTrimmingActivity.class);
                startActivityForResult(intent, Constant.RESULTCODE);
            }
        });
        currDate = (TextView) findViewById(R.id.date_tv);
        currTime = (TextView) findViewById(R.id.curr_time);
        signIn = (ImageView) findViewById(R.id.sign_in);
        currDate.setText(DataUtils.getWeek() + ":" + DataUtils.getDate());
        currTime.setText("当前时间：" + DataUtils.getTime());
        signIn.setOnClickListener(this);
        backImg = (ImageView) findViewById(R.id.back_arrows);
        titleRight = (TextView) findViewById(R.id.title_right);
        titleRight.setText("拜访记录");
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setOnClickListener(this);

        backImg.setOnClickListener(this);
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
                String addr = address.getText().toString().trim();
                if (NullUtils.isNull(addr)) {
                    Intent intent = new Intent(this, SigninTextActivity.class);
                    intent.putExtra("ADDRESS", addr);
                    startActivity(intent);
                } else {
                    ToastUtils.shows(this, "定位失败");
                }
                break;
            case R.id.back_arrows:
                finish();
                break;
            case R.id.title_right:
                Intent intent = new Intent(this, VisitRecordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
