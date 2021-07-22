package softing.ubah4ukdev.nasaphotoday.domain.model.nasa

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.domain.model

Created by Ivan Sheynmaer

2021.07.12
v1.0
 */
data class Mars(
    val id: Int?,
    val sol: Int?,
    val cameraId: Int?,
    val cameraName: String?,
    val cameraRoverId: Int?,
    val cameraFullName: String?,
    val imgSrc: String?,
    val earth_date: String?,
    val roverId: Int?,
    val roverName: String?,
    val roverLandingDate: String?,
    val roverLaunchDate: String?,
    val roverStatus: String?,
)