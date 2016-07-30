package com.pick;

import android.content.Context;

/**
 * Created by 29 on 2016-07-19.
 */
public class PickApplication extends android.app.Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getItemContext() {
        return mContext;
    }
}
