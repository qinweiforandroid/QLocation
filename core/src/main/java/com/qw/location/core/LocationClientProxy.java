package com.qw.location.core;

/**
 * Created by qinwei on 3/6/21 8:52 PM
 * email: qinwei_it@163.com
 */
public class LocationClientProxy implements ILocationClient {
    private ILocationClient location;

    public static LocationClientProxy create(ILocationClient location){
        return new LocationClientProxy(location);
    }
    public LocationClientProxy(ILocationClient location) {
        this.location = location;
    }

    @Override
    public void setLocationListener(LocationListener listener) {
        location.setLocationListener(listener);
    }

    @Override
    public void startLocation() {
        location.startLocation();
    }

    @Override
    public void stopLocation() {
        location.stopLocation();
    }

    @Override
    public void onDestroy() {
        location.onDestroy();
    }
}