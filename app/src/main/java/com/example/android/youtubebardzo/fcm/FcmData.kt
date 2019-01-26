package com.example.android.youtubebardzo.fcm

import com.fasterxml.jackson.annotation.JsonProperty

data class FcmData(
    @JsonProperty("body")
    val body: String?,
    @JsonProperty("text")
    val text: String?,
    @JsonProperty("category")
    val category: String?
)