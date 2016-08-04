package com.meidp.crmim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.meidp.crmim.model.AppMsg;
import com.meidp.crmim.utils.Constant;
import com.meidp.crmim.utils.DataUtils;
import com.meidp.crmim.utils.NullUtils;
import com.meidp.crmim.utils.SPUtils;
import com.meidp.crmim.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;

/**
 * Package： com.meidp.crmim.activity
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/1
 */
@ContentView(R.layout.activity_signin_main)
public class VisitingClientsActivity extends BaseActivity implements BDLocationListener {

    @ViewInject(R.id.title_right)
    private TextView titleRight;
    @ViewInject(R.id.title_tv)
    private TextView title;
    @ViewInject(R.id.date_tv)
    private TextView currDate;
    @ViewInject(R.id.curr_time)
    private TextView currTime;
    @ViewInject(R.id.address_tv)
    private TextView addressTv;
    @ViewInject(R.id.customer_name)
    private TextView customerName;
    @ViewInject(R.id.content_tv)
    private EditText contentTv;


    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private LocationClient mLocalClient;
    private boolean isFirstLoc = true; // 是否首次定位
    private BitmapDescriptor mCurrentMarker;
    private MyLocationConfiguration.LocationMode mCurrentMode;
    private int custId;
    private String custName;
    private double latitude;
    private double longitude;
    private String address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onInit() {
        titleRight.setVisibility(View.VISIBLE);
        titleRight.setText("历史记录");
        title.setText("客户拜访");
        currDate.setText(DataUtils.getDate() + "  " + DataUtils.getWeek());
        currTime.setText(DataUtils.getTime());
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mMapView.showZoomControls(false);

        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.marker_red);
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker));
        mBaiduMap.setMyLocationEnabled(true);
        mLocalClient = new LocationClient(VisitingClientsActivity.this);
        mLocalClient.registerLocationListener(this);
        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true);//打开GPS定位
        option.setIsNeedAddress(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        mLocalClient.setLocOption(option);
        mLocalClient.start();
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

        latitude = bdLocation.getLatitude();
        longitude = bdLocation.getLongitude();
        address = bdLocation.getAddrStr();
        if (NullUtils.isNull(bdLocation.getAddrStr())) {
            mLocalClient.stop();
        }
        addressTv.setText(bdLocation.getAddrStr());

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
        if (requestCode == 1 && data != null) {
            if (NullUtils.isNull(data.getStringExtra("ADDRESS"))) {
                addressTv.setText(data.getStringExtra("ADDRESS"));
            }
        } else if (requestCode == 1003 && data != null) {
            custId = data.getIntExtra("OID", -1);
            custName = data.getStringExtra("CustName");
            customerName.setText(custName);
        }
    }

    @Event(value = {R.id.back_arrows, R.id.address_trim, R.id.customer_name, R.id.sign_in, R.id.title_right})
    private void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.back_arrows:
                finish();
                break;
            case R.id.address_trim:
                intent = new Intent();
                intent.setClass(VisitingClientsActivity.this, LocalTrimmingActivity.class);
                startActivityForResult(intent, Constant.RESULTCODE);
                break;
            case R.id.customer_name:
                intent = new Intent();
                intent.setClass(this, CustomerListActivity.class);
                intent.putExtra("FLAG", "Apply");
                startActivityForResult(intent, 1003);
                break;
            case R.id.sign_in:
                String addr = addressTv.getText().toString().trim();
                if (NullUtils.isNull(addr)) {
                    String content = contentTv.getText().toString().trim();
                    ToastUtils.shows(this, "正在提交");
                    HashMap params = new HashMap();
                    params.put("Contents", content);//拜访内容
                    params.put("CustID", custId);//客户Id
                    params.put("Title", "拜访" + custName);
                    params.put("Lat", latitude);//维度
                    params.put("Lon", longitude);//经度
                    params.put("LocationAddress", address);//地址
                    HttpRequestUtils.getmInstance().send(VisitingClientsActivity.this, Constant.SAVE_VISIT_CUSTOMER, params, new HttpRequestCallBack<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Log.e("visit", result);
                            AppMsg appMsg = JSONObject.parseObject(result, new TypeReference<AppMsg>() {
                            });
                            if (appMsg != null && appMsg.getEnumcode() == 0) {
                                Intent intent = new Intent(VisitingClientsActivity.this, VisitRecordActivity.class);
                                ToastUtils.shows(VisitingClientsActivity.this, "拜访成功");
                                startActivity(intent);
                                finish();
                            } else {
                                ToastUtils.shows(VisitingClientsActivity.this, "拜访失败");
                            }
                        }
                    });
                } else {
                    ToastUtils.shows(this, "定位失败");
                }
                break;
            case R.id.title_right:
                intent = new Intent(VisitingClientsActivity.this, VisitRecordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
