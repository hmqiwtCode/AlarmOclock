package com.quy.alarmoclock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    AlarmManager alarm_Manager;
    TimePicker alarm_timePicker;
    TextView update_textView;
    Context context;
    Calendar calendar;
    Button btnStart, btnStop;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarm_timePicker = findViewById(R.id.AlarmTimePicker);
        btnStart = findViewById(R.id.btnStartAlarm);
        btnStop = findViewById(R.id.btnStopAlarm);

        alarm_Manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        update_textView = findViewById(R.id.update_textView);
        calendar = Calendar.getInstance();
        this.context = this;
        final Intent myIntent = new Intent(MainActivity.this,Alarm_Receiver.class);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hourPick = alarm_timePicker.getHour();
                int minutePick = alarm_timePicker.getMinute();

                calendar.set(Calendar.HOUR_OF_DAY,hourPick);
                calendar.set(Calendar.MINUTE,minutePick);


                myIntent.putExtra("extra", "start");
                update_textView.setText("Alarm On " + hourPick + " : " + minutePick + "");

                pendingIntent = PendingIntent.getBroadcast(MainActivity.this,1,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                alarm_Manager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                // tam hieu la toi luc get time cua calendar.getTimeInMillis() thi no se wake up cai alarmManager va gui den pendingIntent(thi luc nay Alarm_Receiver se thuc thi)
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myIntent.putExtra("extra", "stop");
                sendBroadcast(myIntent);

                alarm_Manager.cancel(pendingIntent);

                update_textView.setText("Alarm Off");
            }
        });

    }


}