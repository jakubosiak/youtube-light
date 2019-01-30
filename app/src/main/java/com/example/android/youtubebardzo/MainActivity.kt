package com.example.android.youtubebardzo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.YouTube
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_search_input.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_layout)
        //initYoutubePlayer()
        val youtubeDataService = getYoutubeService()
        val searchQueryRequest = youtubeDataService.Search().list("snippet").setMaxResults(2).setQ("kuban").setType("")
            .setKey("AIzaSyCvvWDXdDv6q3MDg0HfKTyluboyscEaysA")

        Executors.newSingleThreadExecutor().execute {
            val searchQueryResponse = searchQueryRequest.execute()
            Log.v("ytSearchRespone", searchQueryResponse.toString())
/*            val jsonObjectMapper = ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            jsonObjectMapper.readValue(searchQueryResponse.toString(), YoutubeSearchResponse::class.java)*/
        }
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { result ->
            Log.v("onNewToken", result.result?.token)
        }

        searchClearIMG.setOnClickListener {

        }
    }

   /* private fun initYoutubePlayer() {
        ytPlayer.initialize({ player ->
            player.addListener(object : AbstractYouTubePlayerListener() {
                override fun onReady() {
                    super.onReady()
                    player.loadVideo("OaYL37l-DsQ", 0f)
                }
            })
        }, true)
        ytPlayer.enableBackgroundPlayback(true)
    }*/

    private fun getYoutubeService(): YouTube {
        val transport = AndroidHttp.newCompatibleTransport()
        val jsonFactory = JacksonFactory.getDefaultInstance()
        return YouTube
            .Builder(transport, jsonFactory, HttpRequestInitializer { request ->
                request.headers.set("X-Android-Package", packageName)
                request.headers.set("X-Android-Cert", "9F218A829E9A4E8571125D48C958A5999DE4C252")
            })
            .setApplicationName("YoutubeBardzo")
            .build()
    }
}
