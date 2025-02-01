package com.example.demoskins.data

import com.example.demoskins.data.skin.response.SkinResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AshconService {

    @GET("user/{username}")
    fun getSkin(@Path("username") usernameOrUuid: String): Call<SkinResponse>
}