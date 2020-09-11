package com.quy.alarmoclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Work at", Toast.LENGTH_SHORT).show();
        Intent intent_service = new Intent(context,RingtonePlayingService.class);
        intent_service.putExtra("status",intent.getExtras().getString("extra"));
        context.startService(intent_service);
    }
}
