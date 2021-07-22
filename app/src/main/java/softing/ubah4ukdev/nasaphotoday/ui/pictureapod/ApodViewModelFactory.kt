package softing.ubah4ukdev.nasaphotoday.ui.pictureapod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import softing.ubah4ukdev.nasaphotoday.domain.repository.nasa.INasaRepository

class ApodViewModelFactory(
    private val nasaRepository: INasaRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ApodViewModel(nasaRepository) as T
}