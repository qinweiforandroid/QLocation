package com.qw.location.core;

/**
 * Created by qinwei on 3/6/21 9:06 PM
 * email: qinwei_it@163.com
 */
public class Location {
    public int code;

    public String msg;

    /**
     * 获取纬度
     */
    public double latitude;

    /**
     * 获取经度
     */
    public double longitude;

    /**
     * 获取精度信息
     */
    public float accuracy;

    /**
     * 国家信息
     */
    public String country;

    /**
     * 省信息
     */
    public String province;

    /**
     * 城市信息
     */
    public String city;

    /**
     * 城区信息
     */
    public String district;

    /**
     * 街道信息
     */
    public String street;
    /**
     * 街道门牌号信息
     */
    public String streetNum;

    /**
     * 城市编码
     */
    public String cityCode;
    /**
     * 地区编码
     */
    public String adCode;
    /**
     * 地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
     */
    public String address;
    public long time;


    public boolean success() {
        return code == 0;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getAdCode() {
        return adCode;
    }

    public String getAddress() {
        return address;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "QLocation{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", accuracy=" + accuracy +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", streetNum='" + streetNum + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", adCode='" + adCode + '\'' +
                ", address='" + address + '\'' +
                ", time=" + time +
                '}';
    }

    //amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//amapLocation.getAoiName();//获取当前定位点的AOI信息
//amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
//amapLocation.getFloor();//获取当前室内定位的楼层
//amapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
}
