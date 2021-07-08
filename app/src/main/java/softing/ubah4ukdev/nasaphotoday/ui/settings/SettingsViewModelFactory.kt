package softing.ubah4ukdev.nasaphotoday.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import softing.ubah4ukdev.nasaphotoday.domain.storage.AppThemeStorage

class SettingsViewModelFactory(
    private val themeStorage: AppThemeStorage
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        SettingsViewModel(themeStorage) as T
}