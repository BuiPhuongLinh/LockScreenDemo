package com.example.admin.lockscreendemo.object;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Admin on 19/04/2017.
 */

public class LookScreenSevice extends Service {

    BroadcastReceiver receiver;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        KeyguardManager.KeyguardLock key;
        KeyguardManager km = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        key= km.newKeyguardLock("IN");
        key.disableKeyguard();

        //Bắt đầu màn hình mới
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);

        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_BOOT_COMPLETED);

        receiver= new LookScreenReceiver();
        registerReceiver(receiver,intentFilter);

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
