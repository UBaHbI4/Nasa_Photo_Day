package softing.ubah4ukdev.nasaphotoday.ui.pictureepic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import softing.ubah4ukdev.nasaphotoday.domain.IRepository

class EpicViewModelFactory(
    private val repository: IRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        EpicViewModel(repository) as T
}