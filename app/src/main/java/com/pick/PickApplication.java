package com.pick;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.navercorp.volleyextensions.volleyer.Volleyer;
import com.navercorp.volleyextensions.volleyer.factory.DefaultRequestQueueFactory;

/**
 * Created by 29 on 2016-07-19.
 */
public class PickApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        RequestQueue rq = DefaultRequestQueueFactory.create(this);
        rq.start();
        Volleyer.volleyer(rq).settings().setAsDefault().done();
    }

    public static Context getItemContext() {
        return mContext;
    }
}
