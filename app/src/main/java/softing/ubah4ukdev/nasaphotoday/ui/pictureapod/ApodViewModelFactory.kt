package softing.ubah4ukdev.nasaphotoday.ui.pictureapod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import softing.ubah4ukdev.nasaphotoday.domain.IRepository

class ApodViewModelFactory(
    private val repository: IRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ApodViewModel(repository) as T
}