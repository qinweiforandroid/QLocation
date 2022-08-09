package com.qw.location.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qw.location.amap.GDLocationClient;
import com.qw.location.core.Location;
import com.qw.location.core.LocationClientProxy;
import com.qw.location.core.LocationListener;
import com.qw.permission.OnPermissionsResultListener;
import com.qw.permission.Permission;
import com.qw.permission.PermissionResult;


public class MainActivity extends AppCompatActivity {

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
                requestLocation();
            }
        });
        initLocation();
    }

    private void requestLocation() {
        Permission.Companion.init(this)
                .permission(Manifest.permission.ACCESS_COARSE_LOCATION)
                .setOnPermissionsResultListener(new OnPermissionsResultListener() {

                    @Override
                    public void onShowRequestPermissionRationale(@NonNull String s) {

                    }

                    @Override
                    public void onRequestPermissionsResult(@NonNull PermissionResult permissionResult) {
                        if (permissionResult.isGrant()) {
                            startLocation();
                        }
                    }
                }).request();
    }

    private void startLocation() {
        mLocationInfoLabel.setText("定位中...");
        locationClient.startLocation();
    }

    private void initLocation() {
        locationClient = LocationClientProxy.create(new GDLocationClient(this));
        locationClient.setLocationListener(new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
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
}