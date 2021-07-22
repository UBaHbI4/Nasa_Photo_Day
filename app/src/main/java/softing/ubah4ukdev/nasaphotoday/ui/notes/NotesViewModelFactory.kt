package softing.ubah4ukdev.nasaphotoday.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import softing.ubah4ukdev.nasaphotoday.domain.repository.nasa.INasaRepository
import softing.ubah4ukdev.nasaphotoday.domain.repository.notes.INotesRepository

class NotesViewModelFactory(
    private val notesRepository: INotesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        NotesViewModel(notesRepository) as T
}