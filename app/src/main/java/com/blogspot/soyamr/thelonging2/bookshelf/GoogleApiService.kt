package com.blogspot.soyamr.thelonging2.bookshelf

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface GoogleApiService {
    @GET("books/v1/volumes?")
    fun getBooksList(@Query("q") queryString: String): Call<JsonResponse>



    object ServiceBuilder {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        fun<T> buildService(service: Class<T>): T{
            return retrofit.create(service)
        }
    }

}