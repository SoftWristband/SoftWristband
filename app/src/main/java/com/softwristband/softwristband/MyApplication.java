package com.softwristband.softwristband;

import android.app.Application;
import android.content.Context;

/**
 * Created by XiaofeiLi on 2016/9/5.
 */
public class MyApplication extends Application
{
    private static Context context;
    @Override
    public void onCreate()
    {
        super.onCreate();
        context=getApplicationContext();
    }
    public static Context getContext()
    {
        return context;
    }
}
