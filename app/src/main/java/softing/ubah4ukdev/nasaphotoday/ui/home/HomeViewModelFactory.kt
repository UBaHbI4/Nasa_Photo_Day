package softing.ubah4ukdev.nasaphotoday.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import softing.ubah4ukdev.nasaphotoday.domain.IRepository

class HomeViewModelFactory(
    private val repository: IRepository,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        HomeViewModel(repository) as T
}