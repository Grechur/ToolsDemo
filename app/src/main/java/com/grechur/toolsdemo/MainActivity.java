package com.grechur.toolsdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private TextView battery;
    private String[] permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.boot_time);
        battery = findViewById(R.id.battery);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions,100001);
        }
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onClick(View view) {
//        StringBuffer time = new StringBuffer();
//        time.append("bootTime:"+DevicesTools.bootTime()+"\n");
//        time.append("timeZone:"+DevicesTools.timeZone()+"\n");
//        time.append("languages:"+DevicesTools.languages(this)+"\n");
//        time.append("brightness:"+DevicesTools.brightness(this)+"\n");
//        time.append("batteryStatus:"+DevicesTools.batteryStatus(this)+"\n");
//        time.append("batteryTemp:"+DevicesTools.batteryTemp(this)+"\n");
//        time.append("cpuType:"+DevicesTools.cpuType()+"\n");
//        time.append("basebandVersion:"+DevicesTools.basebandVersion()+"\n");
//        time.append("getLinuxCore:"+DevicesTools.getLinuxCore()+"\n");
//        time.append("getSsid:"+DevicesTools.getSsid(this)+"\n");
//        time.append("getBSsid:"+DevicesTools.getBSsid(this)+"\n");
//        time.append("wifiList:"+DevicesTools.wifiList(this)+"\n");
//        time.append("totalDiskSize:"+DevicesTools.totalDiskSize()+"\n");
//        time.append("availableDiskSize:"+DevicesTools.availableDiskSize()+"\n");
//        time.append("carrierName:"+DevicesTools.carrierName(this)+"\n");
//        time.append("isRooted:"+DevicesTools.isRooted()+"\n");
//        time.append("elapsedRealtime:"+DevicesTools.elapsedRealtime()+"\n");
//        time.append("modelName:"+DevicesTools.modelName()+"\n");
//        time.append("manufacturerName:"+DevicesTools.manufacturerName()+"\n");
//        time.append("systemtVersion:"+DevicesTools.systemtVersion()+"\n");
//        time.append("carrierIpAddress:"+DevicesTools.carrierIpAddress(this)+"\n");
//        time.append("isUsingProxyPort:"+DevicesTools.isUsingProxyPort(this)+"\n");
//        time.append("board:"+DevicesTools.board()+"\n");
//        time.append("batteryPercentage:"+DevicesTools.batteryPercentage(this)+"\n");
//        time.append("nearbyBaseStat:"+DevicesTools.nearbyBaseStat(this)+"\n");
//        time.append("phoneNumber:"+DevicesTools.phoneNumber(this)+"\n");
//        time.append("isSimulatorNew:"+DevicesTools.isSimulatorNew(this)+"\n");
//        time.append("picNum:"+DevicesTools.picNum(this)+"\n");
//        time.append("getRecords:"+DevicesTools.getRecords(getContentResolver())+"\n");
//        time.append("uptimeMillis:"+DevicesTools.uptimeMillis()+"\n");
//        time.append("systemActive:"+DevicesTools.systemActive()+"\n");
//        textView.setText(time.toString());

    }


}
