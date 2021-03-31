package com.qw.location.core;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * Created by qinwei on 3/6/21 9:59 PM
 * email: qinwei_it@163.com
 */
public abstract class AbsQLocationClient implements ILocationClient {
    protected Context context;

    public AbsQLocationClient(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }
}