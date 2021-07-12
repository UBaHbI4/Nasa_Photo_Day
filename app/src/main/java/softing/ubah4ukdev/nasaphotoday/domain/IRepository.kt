package softing.ubah4ukdev.nasaphotoday.domain

import softing.ubah4ukdev.moviesinfosearcher.domain.RepositoryResult
import softing.ubah4ukdev.nasaphotoday.domain.model.Apod
import softing.ubah4ukdev.nasaphotoday.domain.model.Epic
import softing.ubah4ukdev.nasaphotoday.domain.model.Mars

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.domain

Created by Ivan Sheynmaer

2021.07.05
v1.0
 */
interface IRepository {
    //Получить астрономическое фото дня
    fun getAstronomyPictureDay(callback: (result: RepositoryResult<Apod>) -> Unit)

    //Получить фото Земли
    fun getEarthPictureDay(callback: (result: RepositoryResult<ArrayList<Epic>>) -> Unit)

    //Получить фото Марса
    fun getMarsPictureDay(
        earthDate: String,
        callback: (result: RepositoryResult<ArrayList<Mars>>) -> Unit
    )
}