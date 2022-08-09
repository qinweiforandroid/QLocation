package com.qw.location.amap;

import android.content.Context;

import androidx.annotation.NonNull;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.qw.location.core.AbsLocationClient;
import com.qw.location.core.Location;
import com.qw.location.core.LocationListener;


/**
 * Created by qinwei on 3/6/21 9:00 PM
 * email: qinwei_it@163.com
 */
public class GDLocationClient extends AbsLocationClient {
    private AMapLocationClient mLocationClient;

    public GDLocationClient(@NonNull Context context) {
        super(context);
        try {
            mLocationClient = new AMapLocationClient(context.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setLocationListener(LocationListener listener) {
        mLocationClient.setLocationListener(aMapLocation -> {
            Location location = new Location();
            if (aMapLocation.getErrorCode() == 0) {
                location.latitude = aMapLocation.getLatitude();
                location.longitude = aMapLocation.getLongitude();
                location.accuracy = aMapLocation.getAccuracy();
                location.country = aMapLocation.getCountry();
                location.province = aMapLocation.getProvince();
                location.city = aMapLocation.getCity();
                location.district = aMapLocation.getDistrict();
                location.cityCode = aMapLocation.getCityCode();
                location.street = aMapLocation.getStreet();
                location.streetNum = aMapLocation.getStreetNum();
                location.adCode = aMapLocation.getAdCode();
                location.address = aMapLocation.getAddress();
                location.time = aMapLocation.getTime();
            } else {
                location.code = aMapLocation.getErrorCode();
                location.msg = aMapLocation.getErrorInfo();
            }
            listener.onLocationChanged(location);
        });
    }

    @Override
    public void startLocation() {
        //todo  构建定位配置
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //单次定位
        mLocationOption.setOnceLocation(true);
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(2000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    @Override
    public void stopLocation() {
        if (mLocationClient != null && mLocationClient.isStarted()) {
            mLocationClient.stopLocation();
        }
    }

    @Override
    public void onDestroy() {
        if (mLocationClient != null) {
            mLocationClient.onDestroy();
        }
    }
}