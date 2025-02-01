package com.example.demoskins.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoskins.data.AshconService
import com.example.demoskins.data.skin.response.SkinResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel() {
    var searchUsername = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    var skinResponseFlow = MutableStateFlow<SkinResponse?>(null)

    var skinResponseUi = mutableStateOf<SkinResponse?>(null)

    private var service: AshconService

    init {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory()).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.ashcon.app/mojang/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        service = retrofit.create(AshconService::class.java)
    }

    fun getSkin() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.value = true
            val response = service.getSkin(searchUsername.value).execute()
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let { body ->
                    skinResponseFlow.value = body
                }
            }
            isLoading.value = false
        }
    }
}