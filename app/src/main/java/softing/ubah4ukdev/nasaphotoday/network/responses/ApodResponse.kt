package softing.ubah4ukdev.nasaphotoday.network.responses

import com.google.gson.annotations.SerializedName

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.network

Created by Ivan Sheynmaer

2021.07.05
v1.0
 */

//Astronomy Picture of the Day
data class ApodResponse(
    @SerializedName("date")
    val date: String?,

    @SerializedName("explanation")
    val explanation: String?,

    @SerializedName("hdurl")
    val hdUrl: String?,

    @SerializedName("media_type")
    val mediaType: String?,

    @SerializedName("service_version")
    val serviceVersion: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("url")
    val photoUrl: String?,

    @SerializedName("copyright")
    val copyright: String?,
)