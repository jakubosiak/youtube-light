package com.example.android.youtubebardzo.fcm

import android.util.Log
import androidx.work.*
import com.example.android.youtubebardzo.utilities.JsonMapper.Companion.objectMapper
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.time.Duration
import java.util.concurrent.TimeUnit

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
       const val REMOTE_MESSAGE = "remote_message"
    }
    private val workManager: WorkManager = WorkManager.getInstance()

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.v("remoteMesag5e", remoteMessage.toString())

        val fcmWork = PeriodicWorkRequest.Builder(FcmWorker::class.java, PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)
        val remoteMessageString = objectMapper.writeValueAsString(remoteMessage)
        val data = Data.Builder().putString(REMOTE_MESSAGE, remoteMessageString).build()
        fcmWork.setInputData(data)
        workManager.enqueue(fcmWork.build())
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.v("onNewToken", p0)
    }
}