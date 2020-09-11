package com.quy.alarmoclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Work at", Toast.LENGTH_SHORT).show();
        Intent intent_service = new Intent(context,RingtonePlayingService.class);
        intent_service.putExtra("status",intent.getExtras().getString("extra"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent_service);
        } else {
            context.startService(intent_service);
        }
    }
}
