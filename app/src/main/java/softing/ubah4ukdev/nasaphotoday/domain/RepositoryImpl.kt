package softing.ubah4ukdev.nasaphotoday.domain

import retrofit2.Call
import retrofit2.Response
import softing.ubah4ukdev.moviesinfosearcher.domain.Error
import softing.ubah4ukdev.moviesinfosearcher.domain.RepositoryResult
import softing.ubah4ukdev.moviesinfosearcher.domain.Success
import softing.ubah4ukdev.nasaphotoday.domain.model.Photo
import softing.ubah4ukdev.nasaphotoday.network.PhotoResponse
import softing.ubah4ukdev.nasaphotoday.network.Retrofit

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.domain

Created by Ivan Sheynmaer

2021.07.05
v1.0
 */
object RepositoryImpl : IRepository {

    private val retrofitInstance = Retrofit.retrofitInstance()

    override fun photoDay(callback: (result: RepositoryResult<Photo>) -> Unit) {

        retrofitInstance.getPhoto()
            .enqueue(
                object : retrofit2.Callback<PhotoResponse> {
                    override fun onResponse(
                        call: Call<PhotoResponse>,
                        response: Response<PhotoResponse>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                val photoDetail: Photo = it.let {
                                    val photoResult = Photo(
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
                        callback.invoke(Error(Exception(t.message)))
                    }
                }
            )
    }
}