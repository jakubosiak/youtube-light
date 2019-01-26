package com.example.android.youtubebardzo.fcm

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.android.youtubebardzo.MainActivity
import com.example.android.youtubebardzo.fcm.MyFirebaseMessagingService.Companion.REMOTE_MESSAGE
import com.example.android.youtubebardzo.utilities.JsonMapper.Companion.objectMapper
import com.example.android.youtubebardzo.utilities.NotificationsManager

class FcmWorker(private val ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {

        return try {
            Log.v("fcmWorker", "started")
            val remoteMessageString = inputData.getString(REMOTE_MESSAGE)
            val remoteMessage = objectMapper.readValue(remoteMessageString, FcmData::class.java)
            val notificationManager = NotificationsManager.getNotificationManager(ctx)
            val notificationBuilder =
                NotificationsManager
                    .getNotification(ctx, MainActivity::class.java)
                    .setContentText(remoteMessage.body)
                    .setContentTitle(remoteMessage.text)
            notificationManager.notify(0, notificationBuilder.build())
            Result.success()
        } catch (exception: Exception) {
            Result.failure()
        }
    }
}