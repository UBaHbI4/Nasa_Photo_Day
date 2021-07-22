package softing.ubah4ukdev.nasaphotoday.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import softing.ubah4ukdev.nasaphotoday.domain.model.nasa.Epic
import softing.ubah4ukdev.nasaphotoday.domain.model.notes.Note
import softing.ubah4ukdev.nasaphotoday.domain.repository.nasa.Success
import softing.ubah4ukdev.nasaphotoday.domain.repository.notes.INotesRepository

class NotesViewModel(private val notesRepository: INotesRepository) : ViewModel() {

    private val _loading = MutableStateFlow<Boolean>(false)
    private val _error = MutableStateFlow<String?>(null)
    private val _notes = MutableStateFlow<ArrayList<Note>>(arrayListOf())

    private val _note = MutableStateFlow<Note?>(null)

    val loading: StateFlow<Boolean> = _loading
    val error: StateFlow<String?> = _error
    val notes: StateFlow<ArrayList<Note>> = _notes
    val note: StateFlow<Note?> = _note

    fun fetchNotes() {
        viewModelScope.launch {
            notesRepository.notes()
                .flowOn(Dispatchers.IO)
                .onStart {
                    _loading.value = true
                }
                .collect { result ->
                    _loading.value = false

                    when (result) {
                        is Success -> {
                            _notes.value = result.value
                            _error.value = null
                        }

                        is Error -> {
                            _error.value = "Ошибка при получении заметок"
                            _notes.value = arrayListOf()
                        }
                    }
                }
        }
    }

    fun add_Note(newNote: Note) {
        viewModelScope.launch {
            notesRepository.noteAdd(newNote)
                .flowOn(Dispatchers.IO)
                .onStart {
                    _loading.value = true
                }
                .collect { result ->
                    _loading.value = false
                    when (result) {
                        is Success -> {
                            _note.value = result.value
                            _error.value = null
                        }

                        is Error -> {
                            _error.value = "Ошибка при добавлении заметки"
                            _note.value = null
                        }
                    }
                }
        }
    }

    fun noteUpdatePosition(note: Note, pos: Int) {
        viewModelScope.launch {
            notesRepository.noteSetPosition(note, pos)
                .flowOn(Dispatchers.IO)
                .onStart {
                    _loading.value = true
                }
                .collect { result ->
                    _loading.value = false
                    when (result) {
                        true -> {
                            _error.value = null
                        }

                        false -> {
                            _error.value = "Ошибка при обновлении позиции заметки"
                        }
                    }
                }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            notesRepository.noteDelete(note)
                .flowOn(Dispatchers.IO)
                .onStart {
                    _loading.value = true
                }
                .collect { result ->
                    _loading.value = false

                    when (result) {
                        true -> {
                            _error.value = null
                        }
                        false -> {
                            _error.value = "Ошибка при удалении заметки"
                        }
                    }
                }
        }
    }
}