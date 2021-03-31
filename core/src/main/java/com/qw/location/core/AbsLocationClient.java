package com.qw.location.core;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * Created by qinwei on 3/6/21 9:59 PM
 * email: qinwei_it@163.com
 */
public abstract class AbsLocationClient implements ILocationClient {
    protected Context context;

    public AbsLocationClient(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }
}