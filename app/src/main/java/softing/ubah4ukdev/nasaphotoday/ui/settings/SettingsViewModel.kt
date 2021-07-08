package softing.ubah4ukdev.nasaphotoday.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import softing.ubah4ukdev.nasaphotoday.domain.storage.AppThemeStorage

class SettingsViewModel(
    private val themeStorage: AppThemeStorage
) : ViewModel() {

    private val _theme = MutableStateFlow<Int>(0)
    val theme: Flow<Int> = _theme

    init {
        viewModelScope.launch {
            _theme.value = themeStorage.themeID
        }
    }

    fun setTheme(themeID: Int) {
        themeStorage.themeID = themeID
        _theme.value = themeID
    }

}