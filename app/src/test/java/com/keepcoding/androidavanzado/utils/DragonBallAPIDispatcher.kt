package com.keepcoding.androidavanzado.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

internal class DragonBallAPIDispatcher: Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when(request.path){
            "/api/data/bootcamps" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/bootcamps.json"))
            }
            "/api/heros/all" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/heros.json"))
            }
            else -> MockResponse().throttleBody(1024,5, TimeUnit.SECONDS)
        }
    }
}