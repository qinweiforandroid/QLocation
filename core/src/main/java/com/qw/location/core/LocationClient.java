package com.qw.location.core;

/**
 * Created by qinwei on 3/6/21 8:52 PM
 * email: qinwei_it@163.com
 */
public class LocationClient implements ILocationClient {
    private ILocationClient location;

    public static LocationClient create(ILocationClient location) {
        return new LocationClient(location);
    }

    public LocationClient(ILocationClient location) {
        this.location = location;
    }

    @Override
    public void agreePrivacy() {
        this.location.agreePrivacy();
    }

    @Override
    public void setLocationListener(OnLocationListener listener) {
        location.setLocationListener(listener);
    }

    @Override
    public void startLocation(LocOption option) {
        location.startLocation(option);
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