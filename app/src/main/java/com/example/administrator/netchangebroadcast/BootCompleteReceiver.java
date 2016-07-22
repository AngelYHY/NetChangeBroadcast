package com.example.administrator.netchangebroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class BootCompleteReceiver extends BroadcastReceiver {
    //静态注册广播，需要在清单文件中注册

    static final String ACTION_BOOT="android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Boot Complete", Toast.LENGTH_SHORT).show();
        Log.d("FreeStar", "onReceive→→→：Boot Complete");
        if (intent.getAction().equals(ACTION_BOOT)) {
            Intent intent1 = new Intent(context, MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }
    }
}
