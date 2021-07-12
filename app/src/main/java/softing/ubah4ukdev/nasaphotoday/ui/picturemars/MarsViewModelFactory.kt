package softing.ubah4ukdev.nasaphotoday.ui.picturemars

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import softing.ubah4ukdev.nasaphotoday.domain.IRepository

class MarsViewModelFactory(
    private val repository: IRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MarsViewModel(repository) as T
}