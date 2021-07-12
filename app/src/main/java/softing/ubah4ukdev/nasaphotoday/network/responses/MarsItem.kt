package softing.ubah4ukdev.nasaphotoday.network.responses

import com.google.gson.annotations.SerializedName

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.network.responses

Created by Ivan Sheynmaer

2021.07.12
v1.0
 */
data class MarsItem(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("sol")
    val sol: Int?,

    @SerializedName("camera")
    val camera: Camera?,

    @SerializedName("img_src")
    val image: String?,

    @SerializedName("earth_date")
    val date: String?,

    @SerializedName("rover")
    val rover: Rover?
)
