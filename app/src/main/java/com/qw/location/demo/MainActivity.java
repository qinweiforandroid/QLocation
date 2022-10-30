package com.qw.location.demo;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.qw.location.amap.GDLocationClient;
import com.qw.location.core.LocationClient;
import com.qw.location.core.LocOption;
import com.qw.permission.OnPermissionsResultListener;
import com.qw.permission.Permission;
import com.qw.permission.PermissionResult;


public class MainActivity extends AppCompatActivity {

    private LocationClient locationClient;
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
        String[] permissions;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permissions = new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            };
        } else {
            permissions = new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION
            };
        }
        Permission.Companion.init(this)
                .permissions(permissions)
                .setOnPermissionsResultListener(new OnPermissionsResultListener() {

                    @Override
                    public void onShowRequestPermissionRationale(@NonNull String s) {
                        Log.d("qinwei", "onShowRequestPermissionRationale " + s);
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
        locationClient.startLocation(LocOption.genOnceOption());
    }

    private void initLocation() {
        locationClient = LocationClient.create(new GDLocationClient(this));
        locationClient.setLocationListener(location ->
                mLocationInfoLabel.setText(location.toString()));
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