package softing.ubah4ukdev.nasaphotoday.domain.model.notes

import android.graphics.Color

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.domain.model

Created by Ivan Sheynmaer

2021.07.21
v1.0
 */
data class Note(
    val id: Int,
    var position: Int = 0,
    val priority: Int = 0,
    val title: String = "",
    val note: String = "",
    val dateCreated: Long = 0L,
    val dateModify: Long = 0L,
    val color: Int = Color.YELLOW
)