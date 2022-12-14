package com.qw.location.amap;

import android.content.Context;

import androidx.annotation.NonNull;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.qw.location.core.AbsLocationClient;
import com.qw.location.core.LocationEntity;
import com.qw.location.core.LocOption;
import com.qw.location.core.OnLocationListener;


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
    public void agreePrivacy() {
        AMapLocationClient.updatePrivacyShow(context, true, true);
        AMapLocationClient.updatePrivacyAgree(context, true);
    }

    @Override
    public void setLocationListener(OnLocationListener listener) {
        mLocationClient.setLocationListener(loc -> {
            LocationEntity location = new LocationEntity();
            if (loc.getErrorCode() == 0) {
                location.success();
                location.setLatitude(loc.getLatitude());
                location.setLongitude(loc.getLongitude());
                location.setAccuracy(loc.getAccuracy());
                location.setCountry(loc.getCountry());
                location.setProvince(loc.getProvince());
                location.setCity(loc.getCity());
                location.setDistrict(loc.getDistrict());
                location.setCityCode(loc.getCityCode());
                location.setStreet(loc.getStreet());
                location.setStreetNum(loc.getStreetNum());
                location.setAdCode(loc.getAdCode());
                location.setAddress(loc.getAddress());
                location.setTime(loc.getTime());
            } else {
                location.fail(loc.getErrorCode(), loc.getErrorInfo());
            }
            listener.onLocationChanged(location);
        });
    }


    @Override
    public void startLocation(LocOption option) {
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //单次定位
        mLocationOption.setOnceLocation(option.isOnce());
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(option.isOnceLocationLatest());
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(option.getInterval());
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(option.isNeedAddress());
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