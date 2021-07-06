package softing.ubah4ukdev.nasaphotoday.domain

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import softing.ubah4ukdev.moviesinfosearcher.domain.Error
import softing.ubah4ukdev.moviesinfosearcher.domain.RepositoryResult
import softing.ubah4ukdev.moviesinfosearcher.domain.Success
import softing.ubah4ukdev.nasaphotoday.domain.model.Photo
import softing.ubah4ukdev.nasaphotoday.network.INasaApi
import softing.ubah4ukdev.nasaphotoday.network.PhotoResponse

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.domain

Created by Ivan Sheynmaer

2021.07.05
v1.0
 */
object RepositoryImpl: IRepository {

    private const val URL_BASE = "https://api.nasa.gov/"

    override fun photoDay(callback: (result: RepositoryResult<Photo>) -> Unit) {
        val gson = Gson()
        val client = OkHttpClient.Builder().apply {

            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

        retrofit.create(INasaApi::class.java).getPhoto()
            .enqueue(
                object :retrofit2.Callback<PhotoResponse> {
                    override fun onResponse(
                        call: Call<PhotoResponse>,
                        response: Response<PhotoResponse>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                val photoDetail: Photo = it.let {
                                    val photoResult: Photo = Photo(
                                        date = it.date,
                                        explanation = it.explanation,
                                        hdUrl = it.hdUrl,
                                        mediaType = it.mediaType,
                                        serviceVersion = it.serviceVersion,
                                        title = it.title,
                                        photoUrl = it.photoUrl
                                    )
                                    photoResult
                                }
                                callback.invoke(Success(photoDetail))
                            }
                        }
                    }
                    override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
                        callback.invoke(Error(Exception("")))
                    }
                }
            )
    }
}