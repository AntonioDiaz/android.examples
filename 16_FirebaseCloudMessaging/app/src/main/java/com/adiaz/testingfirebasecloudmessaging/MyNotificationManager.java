package com.adiaz.testingfirebasecloudmessaging;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by adiaz on 4/1/18.
 */

public class MyNotificationManager {

    private Context mContext;
    private static final int NOTIFICATION_ID = 123;
    /**
     * This notification channel id is used to link notifications to this channel
     */
    private static final String UPDATE_SPORT_NOTIFICATION_CHANNEL_ID = "reminder_notification_channel";

    public MyNotificationManager(Context mContext) {
        this.mContext = mContext;
    }

    public void showNotification(String from, String notificationStr, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    UPDATE_SPORT_NOTIFICATION_CHANNEL_ID,
                    mContext.getString(R.string.main_notification_channel_name),
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(
                mContext,
                NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, UPDATE_SPORT_NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_trophy_black_24px))
                .setContentIntent(pendingIntent)
                .setContentTitle(from)
                .setContentText(notificationStr)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(notificationStr))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
