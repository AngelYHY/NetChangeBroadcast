package com.example.administrator.netchangebroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private IntentFilter filter;
    private NetChangeReceive netChangeReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filter = new IntentFilter();
        //网络状态改变的监听
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netChangeReceive = new NetChangeReceive();
        registerReceiver(netChangeReceive, filter);
    }

    private class NetChangeReceive extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //查询系统的网络状态就是需要声明权限
            //android.permission.ACCESS_NETWORK_STATE
            //管理网络连接的系统服务类
            ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            NetworkInfo.State gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
            NetworkInfo.State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            //判断网络是否有网络
            //判断gprs或者wifi是否是已连接状态
            if (info == null || !info.isAvailable()) {
                Log.d("FreeStar", "onReceive→→→网络不可用");
            } else if (gprs == NetworkInfo.State.CONNECTED) {
                Log.d("FreeStar", "onReceive→→→：已连接gprs");
            } else if (wifi == NetworkInfo.State.CONNECTED) {
                Log.d("FreeStar", "onReceive→→→：已连接wifi");
            }
            //判断网络是否有网络
//            if (info != null && info.isAvailable()) {
////                Toast.makeText(context, "network is Available", Toast.LENGTH_SHORT).show();
//                Log.d("FreeStar", "onReceive→→→网络可用");
//            } else {
////                Toast.makeText(context, "network is unAvailable", Toast.LENGTH_SHORT).show();
//                Log.d("FreeStar", "onReceive→→→网络不可用");
//            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netChangeReceive);
    }
}
