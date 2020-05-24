package com.example.diaryapp;

import android.app.Application;

import com.github.orangegangsters.lollipin.lib.managers.LockManager;

public class CustomApplication extends Application {

    @SuppressWarnings("incheckeed")
    @Override
    public void onCreate() {
        super.onCreate();

        LockManager<CustomPinActivity> lockManager = LockManager.getInstance();
        lockManager.enableAppLock(this, CustomPinActivity.class);
        lockManager.getAppLock().setLogoId(R.drawable.security_lock);
        //lockManager.getAppLock().setShouldShowForgot(false);
    }
}
