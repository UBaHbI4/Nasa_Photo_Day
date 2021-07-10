package softing.ubah4ukdev.nasaphotoday.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.network

Created by Ivan Sheynmaer

2021.07.10
v1.0
 */
object Retrofit {

    private const val URL_BASE = "https://api.nasa.gov/"

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun retrofitInstance(): INasaApi {
        //Log.d("NasaDebug", "retrofitInstance Create")
        return retrofit.create(INasaApi::class.java)
    }
}