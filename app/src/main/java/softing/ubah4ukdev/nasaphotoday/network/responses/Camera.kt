package softing.ubah4ukdev.nasaphotoday.network.responses

import com.google.gson.annotations.SerializedName

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.network.responses

Created by Ivan Sheynmaer

2021.07.12
v1.0
 */
data class Camera(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("rover_id")
    val roverId: Int?,

    @SerializedName("full_name")
    val fullName: String?
)
