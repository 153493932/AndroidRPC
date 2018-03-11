package io.smarttangle.rpcdemo.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import io.smarttangle.rpcdemo.R;
import io.smarttangle.rpcdemo.Utils.NetUtils;
import io.smarttangle.rpcdemo.Utils.StorageKey;
import io.smarttangle.rpcdemo.model.BCRequest;
import io.smarttangle.rpcdemo.model.PeerEntity;
import io.smarttangle.rpcdemo.model.RPCEntity;
import io.smarttangle.rpcdemo.model.ReceiptEntity;
import io.smarttangle.rpcdemo.model.UserStorageUtils;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        //    System.loadLibrary("native-lib");
    }

    public String address;
    public boolean isRegistProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        address = UserStorageUtils.getObject(MainActivity.this, StorageKey.USER_ADDRESS);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                if (TextUtils.isEmpty(address)) {
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                } else {
                    intent = new Intent(MainActivity.this, ProxyZonesActivity.class);
                }
                startActivity(intent);
            }
        });

        FloatingActionButton fabSettings = (FloatingActionButton) findViewById(R.id.fabSettings);
        fabSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                if (TextUtils.isEmpty(address)) {
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                } else {
                    intent = new Intent(MainActivity.this, MineActivity.class);
                }
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        address = UserStorageUtils.getObject(MainActivity.this, StorageKey.USER_ADDRESS);

        if (!isRegistProxy) {
            String ip = NetUtils.getLocalIp(this);

            if (ip != null && address != null) {
                BCRequest.registerProxy(this, ip, address, new BCRequest.Listener<RPCEntity>() {
                    @Override
                    public void onResponse(RPCEntity response) {
                        isRegistProxy = true;
                    }
                }, null);
            }
        }

        String txHash = UserStorageUtils.getObject(this, StorageKey.TX_HASH);

        if (!TextUtils.isEmpty(txHash)) {
            BCRequest.getReceiptInfo(this, txHash, new BCRequest.Listener<ReceiptEntity>() {
                @Override
                public void onResponse(ReceiptEntity receiptEntity) {
                    if (receiptEntity.getResult().getTo().equals(address)) {
                        //transaction success
                        Snackbar.make(findViewById(R.id.parentLayout), "Proxy for " + receiptEntity.getResult().getFrom(), Snackbar.LENGTH_LONG).show();
                    } else {
                        //error
                    }
                }
            }, null);
        }
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //    public native String stringFromJNI();
}
