package com.manjurulhoque.scheduler;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    String actionUriSMSSend = "com.scheduler.action.SMS_SEND";
    String actionUriEmailNotification = "com.scheduler.action.EMAIL_NOTIFICATION";
    public static final int NOTIFICATION_ID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if (intent.getAction().equals(actionUriSMSSend)) {

            Bundle bundle = intent.getExtras();
            String message1 = bundle.getString("message");
            System.out.println("mmm=" + message1);
            String number = bundle.getString("number");
            System.out.println("ppp" + number);
            NotificationManager mNotificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(number, null, message1, null, null);
                Toast.makeText(context, "SMS sent.", Toast.LENGTH_LONG).show();
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_sms)
                        .setContentTitle("Scheduler")
                        .setContentText("Sms sent to " + number + " successfully");
                mBuilder.setAutoCancel(true);
                //Vibration
                mBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});

                // set sound
                mBuilder.setSound(alarmSound);

                //LED
                mBuilder.setLights(Color.RED, 3000, 3000);
                mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
            } catch (Exception e) {
                Toast.makeText(context, "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                e.printStackTrace();
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_sms)
                        .setContentTitle("Scheduler")
                        .setContentText("Sms sent to " + number + " failed");
                mBuilder.setAutoCancel(true);
                //Vibration
                mBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});

                // set sound
                mBuilder.setSound(alarmSound);

                //LED
                mBuilder.setLights(Color.RED, 3000, 3000);
                mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
            }
        }
    }
}
