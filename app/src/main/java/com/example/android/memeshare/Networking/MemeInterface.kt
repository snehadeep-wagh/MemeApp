package com.example.android.memeshare.Networking

import retrofit2.Response
import retrofit2.http.GET

interface MemeInterface {
    @GET("gimme")
    suspend fun getMeme(): Response<MemeData>
}