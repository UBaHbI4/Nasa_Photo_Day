package softing.ubah4ukdev.nasaphotoday.ui.picturemars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import softing.ubah4ukdev.nasaphotoday.domain.repository.nasa.INasaRepository

class MarsViewModelFactory(
    private val nasaRepository: INasaRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MarsViewModel(nasaRepository) as T
}