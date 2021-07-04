package softing.ubah4ukdev.nasaphotoday.network

import retrofit2.Call
import retrofit2.http.GET
import softing.ubah4ukdev.nasaphotoday.BuildConfig

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.network

Created by Ivan Sheynmaer

2021.07.05
v1.0
 */
interface INasaApi {

    @GET("planetary/apod?api_key=${BuildConfig.API_KEY}")
    fun getPhoto(
    ): Call<PhotoResponse>
}