package com.sf.gpslocation.gps;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

import com.sf.gpslocation.internet.ConnectionHelper;

public class GpsChangeService extends Service {
    static final String PROVIDERS_CHANGE_ACTION = "android.location.PROVIDERS_CHANGED";
    BroadcastReceiver receiver;

    public GpsChangeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.location.PROVIDERS_CHANGED");
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (PROVIDERS_CHANGE_ACTION.equals(action)) {
                    if (ConnectionHelper.isGpsEnabled(getApplicationContext())) {
                        Toast.makeText(context, "GPS Open", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "GPS Close", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        registerReceiver(receiver, filter);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
