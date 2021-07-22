package softing.ubah4ukdev.nasaphotoday.ui.notes.adapter

import softing.ubah4ukdev.nasaphotoday.domain.model.notes.Note

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.ui.notes.adapter

Created by Ivan Sheynmaer

2021.07.22
v1.0
 */
interface INoteClickable {
    //Click по заметке
    fun onNoteClick(position: Int, note: Note)

    //Click по кнопке вверх внутри заметки
    fun onNoteUpClick(noteOne: Note, noteTwo: Note)

    //Click по кнопке ввниз внутри заметки
    fun onNoteDownClick(noteOne: Note, noteTwo: Note)
}