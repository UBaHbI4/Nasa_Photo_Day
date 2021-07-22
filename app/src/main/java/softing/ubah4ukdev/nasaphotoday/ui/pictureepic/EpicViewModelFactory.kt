package softing.ubah4ukdev.nasaphotoday.ui.pictureepic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import softing.ubah4ukdev.nasaphotoday.domain.repository.nasa.INasaRepository

class EpicViewModelFactory(
    private val nasaRepository: INasaRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        EpicViewModel(nasaRepository) as T
}