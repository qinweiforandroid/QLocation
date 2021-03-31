package com.qw.location.core;


/**
 * Created by qinwei on 3/6/21 8:47 PM
 * email: qinwei_it@163.com
 */
public interface ILocationClient {

    void setLocationListener(LocationListener listener);

    void startLocation();

    void stopLocation();

    void onDestroy();
}