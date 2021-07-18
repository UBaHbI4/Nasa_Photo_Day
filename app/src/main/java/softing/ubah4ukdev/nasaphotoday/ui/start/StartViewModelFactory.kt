package softing.ubah4ukdev.nasaphotoday.ui.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import softing.ubah4ukdev.nasaphotoday.domain.storage.AppThemeStorage

class StartViewModelFactory(
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        StartViewModel() as T
}