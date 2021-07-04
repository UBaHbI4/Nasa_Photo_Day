package softing.ubah4ukdev.nasaphotoday.domain

import softing.ubah4ukdev.moviesinfosearcher.domain.RepositoryResult
import softing.ubah4ukdev.nasaphotoday.domain.model.Photo

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.domain

Created by Ivan Sheynmaer

2021.07.05
v1.0
 */
interface IRepository {

    fun photoDay(callback: (result: RepositoryResult<Photo>) -> Unit)
}