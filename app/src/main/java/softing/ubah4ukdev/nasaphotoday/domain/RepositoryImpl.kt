package softing.ubah4ukdev.nasaphotoday.domain

import retrofit2.Call
import retrofit2.Response
import softing.ubah4ukdev.moviesinfosearcher.domain.Error
import softing.ubah4ukdev.moviesinfosearcher.domain.RepositoryResult
import softing.ubah4ukdev.moviesinfosearcher.domain.Success
import softing.ubah4ukdev.nasaphotoday.BuildConfig
import softing.ubah4ukdev.nasaphotoday.domain.model.Apod
import softing.ubah4ukdev.nasaphotoday.domain.model.Epic
import softing.ubah4ukdev.nasaphotoday.domain.model.Mars
import softing.ubah4ukdev.nasaphotoday.network.Retrofit
import softing.ubah4ukdev.nasaphotoday.network.responses.ApodResponse
import softing.ubah4ukdev.nasaphotoday.network.responses.EpicResponse
import softing.ubah4ukdev.nasaphotoday.network.responses.MarsResponse


/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.domain

Created by Ivan Sheynmaer

2021.07.05
v1.0
 */
object RepositoryImpl : IRepository {

    private val retrofitInstance = Retrofit.retrofitInstance()
    const val EMPTY_RESULT_ERROR_TEXT = "За выбранную дату (%s) отсутсвуют данные."

    override fun getAstronomyPictureDay(callback: (result: RepositoryResult<Apod>) -> Unit) {
        retrofitInstance.getApod()
            .enqueue(
                object : retrofit2.Callback<ApodResponse> {
                    override fun onResponse(
                        call: Call<ApodResponse>,
                        dayResponse: Response<ApodResponse>
                    ) {
                        if (dayResponse.isSuccessful) {
                            dayResponse.body()?.let {
                                val apodDetail: Apod = it.let {
                                    val photoResult = Apod(
                                        date = it.date,
                                        explanation = it.explanation,
                                        hdUrl = it.hdUrl,
                                        mediaType = it.mediaType,
                                        serviceVersion = it.serviceVersion,
                                        title = it.title,
                                        photoUrl = it.photoUrl,
                                        copyright = it.copyright
                                    )
                                    photoResult
                                }
                                callback.invoke(Success(apodDetail))
                            }
                        }
                    }

                    override fun onFailure(call: Call<ApodResponse>, t: Throwable) {
                        callback.invoke(Error(Exception(t.message)))
                    }
                }
            )
    }

    override fun getEarthPictureDay(callback: (result: RepositoryResult<ArrayList<Epic>>) -> Unit) {
        retrofitInstance.getEarthPicture()
            .enqueue(
                object : retrofit2.Callback<ArrayList<EpicResponse>> {
                    override fun onResponse(
                        call: Call<ArrayList<EpicResponse>>,
                        dayResponse: Response<ArrayList<EpicResponse>>
                    ) {
                        if (dayResponse.isSuccessful) {
                            dayResponse.body()?.let {
                                val epicList: ArrayList<Epic> = arrayListOf()
                                it.forEach { response ->
                                    var dateRequest: String = ""
                                    response.date?.let {
                                        dateRequest =
                                            response.date.substring(0, 10).replace("-", "/")
                                    }
                                    val path: String =
                                        "https://api.nasa.gov/EPIC/archive/natural/" + dateRequest + "/png/${response.image}.png?api_key=${BuildConfig.API_KEY}"

                                    epicList.add(
                                        Epic(
                                            response.identifier,
                                            response.caption,
                                            response.version,
                                            path,
                                            response.date
                                        )
                                    )
                                }
                                callback.invoke(Success(epicList))
                            }
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<EpicResponse>>, t: Throwable) {
                        callback.invoke(Error(Exception(t.message)))
                    }
                }
            )
    }

    override fun getMarsPictureDay(
        earthDate: String,
        callback: (result: RepositoryResult<ArrayList<Mars>>) -> Unit
    ) {
        retrofitInstance.getMarsPicture(earthDate)
            .enqueue(
                object : retrofit2.Callback<MarsResponse> {
                    override fun onResponse(
                        call: Call<MarsResponse>,
                        dayResponse: Response<MarsResponse>
                    ) {
                        if (dayResponse.isSuccessful) {
                            dayResponse.body()?.let {
                                val marsList: ArrayList<Mars> = arrayListOf()
                                it.photoList?.forEachIndexed { index, response ->
                                    marsList.add(
                                        Mars(
                                            response.id,
                                            response.sol,
                                            response.camera?.id,
                                            response.camera?.name,
                                            response.camera?.roverId,
                                            response.camera?.fullName,
                                            response.image,
                                            response.date,
                                            response.rover?.id,
                                            response.rover?.name,
                                            response.rover?.landingDate,
                                            response.rover?.launchDate,
                                            response.rover?.status,
                                        )
                                    )
                                }

                                if (marsList.isEmpty()) {
                                    callback.invoke(
                                        Error(
                                            Exception(
                                                String.format(
                                                    EMPTY_RESULT_ERROR_TEXT,
                                                    earthDate
                                                )
                                            )
                                        )
                                    )
                                } else {
                                    callback.invoke(Success(marsList))
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<MarsResponse>, t: Throwable) {
                        callback.invoke(Error(Exception(t.message)))
                    }
                }
            )
    }
}