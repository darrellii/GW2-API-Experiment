package com.darrellii.gw2.gw2apiexperment.app;

import android.app.Application;

import com.darrellii.gw2.gw2apiexperment.R;

/**
 * Created by dj on 3/6/16.
 * For Use By GW2 API Experment
 */
public class GW2Application extends Application {

    private static GW2Application instance;
    public static final String[] REQUIRED_PERMS = {"account","characters","inventories","wallet"};

    public static String getAPIKey() {
        return instance
                .getSharedPreferences(instance.getString(R.string.prefs_name),MODE_PRIVATE)
                .getString(instance.getString(R.string.prefs_api_key),"failsnail");
    }

    @Override
    public void onCreate(){
        super.onCreate();

        instance = this;
    }
}
