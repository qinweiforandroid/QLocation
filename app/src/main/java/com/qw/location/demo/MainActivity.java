package com.qw.location.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qw.framework.permission.OnPermissionCallback;
import com.qw.framework.permission.PermissionHelper;
import com.qw.framework.permission.PermissionScope;
import com.qw.location.amap.GDLocationClient;
import com.qw.location.core.QLocation;
import com.qw.location.core.LocationClientProxy;
import com.qw.location.core.QLocationListener;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements OnPermissionCallback {

    private LocationClientProxy locationClient;
    private TextView mLocationInfoLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocationInfoLabel = (TextView) findViewById(R.id.mLocationInfoLabel);
        findViewById(R.id.mLocationBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionHelper.INSTANCE.requestPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
            }
        });
        initLocation();
    }

    private void startLocation() {
        mLocationInfoLabel.setText("定位中...");
        locationClient.startLocation();
    }

    private void initLocation() {
        locationClient = LocationClientProxy.create(new GDLocationClient(this));
        locationClient.setLocationListener(new QLocationListener() {
            @Override
            public void onLocationChanged(QLocation location) {
                mLocationInfoLabel.setText(location.toString());
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationClient.stopLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationClient.onDestroy();
    }

    @Override
    public void onPermissionDenied(@NotNull String s, @NotNull PermissionScope permissionScope) {
        permissionScope.setTitle("提示");
        permissionScope.setMessage("需要相机权限");
        permissionScope.setLeftText("取消");
        permissionScope.setRightText("申请");
        permissionScope.show();
    }

    @Override
    public void onPermissionGranted(@NotNull String s) {
        startLocation();
    }

    @Override
    public void onRequestPermissionRationale(@NotNull String s, @NotNull PermissionScope permissionScope) {
        permissionScope.setTitle("提示");
        permissionScope.setMessage("需要相机权限");
        permissionScope.setLeftText("取消");
        permissionScope.setRightText("设置");
        permissionScope.show();
    }
}