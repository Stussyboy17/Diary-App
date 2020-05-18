package com.example.diaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;

import com.github.orangegangsters.lollipin.lib.managers.LockManager;

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LockManager lockManager = LockManager.getInstance();
        lockManager.enableAppLock(this, CustomPinActivity.class);
        lockManager.getAppLock().setLogoId(R.drawable.security_lock);
        lockManager.getAppLock().setShouldShowForgot(false);
    }
}
