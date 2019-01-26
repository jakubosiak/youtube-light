package com.example.android.youtubebardzo.utilities

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.android.youtubebardzo.R

class NotificationsManager {
    companion object {
        fun <T> getNotification(context: Context, openClass: Class<T>): NotificationCompat.Builder {
            return NotificationCompat.Builder(context, "1")
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher))
                .setContentIntent(getPendingIntent(context, openClass))
        }

        fun getNotificationManager(context: Context): NotificationManager {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                val notificationChannel = getNotificationChannel()
                notificationManager.createNotificationChannel(notificationChannel)
            }
            return notificationManager
        }

        private fun <T> getPendingIntent(context: Context, clazz: Class<T>): PendingIntent {
            val intent = Intent(context, clazz)
            return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private fun getNotificationChannel(): NotificationChannel {
            return NotificationChannel("1", "FCM", NotificationManager.IMPORTANCE_DEFAULT)
        }
    }
}

/*
 private NotificationManager getNotificationManager() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = getNotificationChannel();
            notificationManager.createNotificationChannel(channel);
        }
        return notificationManager;
    }

    private Notification getNotification(String title, String message) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, getString(R.string.wayfarer_notification_channel_id))
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_notification_status_bar)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setContentIntent(getPendingIntent());
        return notificationBuilder.build();
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent();
        if (!app.isForeground()) {
            intent = new Intent(this, SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        return PendingIntent.getActivity(this, 0, intent, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotificationChannel getNotificationChannel() {
        CharSequence channelName = getString(R.string.wayfarer_notification_channel);
        String description = getString(R.string.wayfarer_notification_channel_description);
        NotificationChannel channel = new NotificationChannel(getString(R.string.wayfarer_notification_channel_id), channelName, NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(description);
        channel.enableVibration(true);
        return channel;
    }
 */