# QLocation
QLocation 定位小工具。API简洁易上手，只需5分钟即可实现一个功能

支持功能如下：

* 获取设备位置
* 适配高德定位
* 使用代理模式，可扩展其他地图平台定位功能

## 1、如何使用

### 1.1、导入依赖

**Step 1.** Add it in your root build.gradle at the end of repositories

```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

**Step 2.** Add the dependency

```groovy
dependencies {
    //核心库
    implementation 'com.github.qinweiforandroid.QLocation:location-core:1.0.1030'
    //高德地图实现
    implementation 'com.github.qinweiforandroid.QLocation:location-amap:1.0.1030'
}
```



### 1.2、api使用

api如下

```java
    private void initLocation() {
        //创建定位客户端，这里用的高德地图
        locationClient = LocationClient.create(new GDLocationClient(this));
        locationClient.setLocationListener(location ->
                mLocationInfoLabel.setText(location.toString()));
    }
    private void startLocation() {
        mLocationInfoLabel.setText("定位中...");
        //发起定位请求
        locationClient.startLocation(LocOption.genOnceOption());
    }
    @Override
    protected void onStop() {
        super.onStop();
        //暂停定位服务
        locationClient.stopLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁定位服务
        locationClient.onDestroy();
    }
```

## 2、支持更多的定位平台

### 2.1、高德定位实现

添加高德定位依赖

```groovy
api 'com.amap.api:location:5.6.2'
```

实现高德定位客户端

```java
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
```





更多功能请参考demo中实现<img src="png\519C949C.png" alt="img" style="zoom:50%;" />

在对接过程中如有遇到问题也可以+我QQ：435231045