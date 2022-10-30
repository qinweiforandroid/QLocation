package com.qw.location.core;

/**
 * Created by qinwei on 3/6/21 9:06 PM
 * email: qinwei_it@163.com
 */
public class LocationEntity {
    private static final int STATUS_SUCCESS = 395;
    private static final int STATUS_FAIL = 972;
    private static final int STATUS_ING = 928;
    private int status = STATUS_ING;
    private int code = 0;
    private String msg;

    /**
     * 获取纬度
     */
    private double latitude;

    /**
     * 获取经度
     */
    private double longitude;

    /**
     * 获取精度信息
     */
    private float accuracy;

    /**
     * 国家信息
     */
    private String country;

    /**
     * 省信息
     */
    private String province;

    /**
     * 城市信息
     */
    private String city;

    /**
     * 城区信息
     */
    private String district;

    /**
     * 街道信息
     */
    private String street;
    /**
     * 街道门牌号信息
     */
    private String streetNum;

    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 地区编码
     */
    private String adCode;
    /**
     * 地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
     */
    private String address;
    private long time;

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isSuccessful() {
        return status == STATUS_SUCCESS;
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

    public void success() {
        status = STATUS_SUCCESS;
    }

    public void fail(int errorCode, String errorInfo) {
        this.code = errorCode;
        this.msg = errorInfo;
        this.status = STATUS_FAIL;
    }

    //amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//amapLocation.getAoiName();//获取当前定位点的AOI信息
//amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
//amapLocation.getFloor();//获取当前室内定位的楼层
//amapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
}
