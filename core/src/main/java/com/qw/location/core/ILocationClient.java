package com.qw.location.core;


/**
 * Created by qinwei on 3/6/21 8:47 PM
 * email: qinwei_it@163.com
 */
interface ILocationClient {

    void setLocationListener(QLocationListener listener);

    void startLocation();

    void stopLocation();

    void onDestroy();
}