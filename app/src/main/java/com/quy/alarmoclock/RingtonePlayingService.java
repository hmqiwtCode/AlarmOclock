package com.quy.alarmoclock;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class RingtonePlayingService extends Service {
    private static final String NOTIFICATION_CHANNEL_ID = "10101";
    private static final CharSequence NOTIFICATION_CHANNEL_NAME = "APPOCLOCK" ;
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(11,new Notification());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String status = intent.getExtras().getString("status");
        Intent intent1 = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent1, 0);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            manager.createNotificationChannel(notificationChannel);
        }
        Notification notification = new Notification.Builder(this).setContentTitle("Hello").setContentText("Sao cung dc").setSmallIcon(R.drawable.ic_call).setChannelId("10101").build();

        if (status.equalsIgnoreCase("start")){
            manager.notify(1,notification);
            mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.iphone_ding_1);
            mediaPlayer.start();
        }else{
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        return START_STICKY;
    }
}
