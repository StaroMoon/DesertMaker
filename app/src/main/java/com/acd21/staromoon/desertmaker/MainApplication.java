package com.acd21.staromoon.desertmaker;

import android.app.Application;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.crashlytics.android.Crashlytics;
import com.parse.Parse;
import com.parse.ParseInstallation;

import io.fabric.sdk.android.Fabric;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Parse.initialize(this, "ppgHWfEEXRreAJUDgrIMJV921fqLvcaJ2dIXx2PB", "6RPpaTPk6vsRDauMUa0f6CnO5i0qFmvJvqMRF0q7");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
