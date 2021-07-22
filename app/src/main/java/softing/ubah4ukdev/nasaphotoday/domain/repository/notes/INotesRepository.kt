package softing.ubah4ukdev.nasaphotoday.domain.repository.notes

import kotlinx.coroutines.flow.Flow
import softing.ubah4ukdev.nasaphotoday.domain.model.notes.Note
import softing.ubah4ukdev.nasaphotoday.domain.repository.nasa.RepositoryResult

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.domain.repository.notes

Created by Ivan Sheynmaer

2021.07.21
v1.0
 */
interface INotesRepository {

    //Получение заметок
    suspend fun notes(): Flow<RepositoryResult<ArrayList<Note>>>

    //Генерация заметок, пока нет настоящих, загруженных откуда-либо
    fun notesExample(countGenerateNotes: Int)

    //Изменение позиции заметки
    suspend fun noteSetPosition(note: Note, newPosition: Int): Flow<Boolean>

    //Удаление заметки
    suspend fun noteDelete(target: Note): Flow<Boolean>

    //Добавление заметки
    suspend fun noteAdd(noteNew: Note): Flow<RepositoryResult<Note>>
}