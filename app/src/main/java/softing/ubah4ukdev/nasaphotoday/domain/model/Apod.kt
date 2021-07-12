package softing.ubah4ukdev.nasaphotoday.domain.model

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.domain.model

Created by Ivan Sheynmaer

2021.07.05
v1.0
 */
//APOD - Astronomy Picture of the Day
data class Apod(
    val date: String?,
    val explanation: String?,
    val hdUrl: String?,
    val mediaType: String?,
    val serviceVersion: String?,
    val title: String?,
    val photoUrl: String?,
    val copyright: String?
)