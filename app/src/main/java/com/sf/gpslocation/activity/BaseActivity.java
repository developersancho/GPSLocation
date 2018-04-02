package com.sf.gpslocation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sf.gpslocation.gps.GpsChangeService;
import com.sf.gpslocation.internet.CheckInternetService;
import com.sf.gpslocation.internet.ConnectionHelper;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(getBaseContext(), CheckInternetService.class));
        startService(new Intent(getBaseContext(), GpsChangeService.class));
        if (ConnectionHelper.isGpsEnabled(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "GPS Open", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "GPS Close", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(getBaseContext(), CheckInternetService.class));
        stopService(new Intent(getBaseContext(), GpsChangeService.class));
    }
}
