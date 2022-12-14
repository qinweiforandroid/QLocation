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
        //????????????
        mLocationOption.setOnceLocation(option.isOnce());
        //??????setOnceLocationLatest(boolean b)?????????true??????????????????SDK???????????????3s?????????????????????????????????????????????????????????true???setOnceLocation(boolean b)????????????????????????true???????????????????????????false???
        mLocationOption.setOnceLocationLatest(option.isOnceLocationLatest());
        //??????????????????,????????????,?????????2000ms?????????1000ms???
        mLocationOption.setInterval(option.getInterval());
        //????????????????????????????????????????????????????????????
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