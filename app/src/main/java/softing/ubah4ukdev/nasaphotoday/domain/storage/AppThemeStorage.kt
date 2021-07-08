package softing.ubah4ukdev.nasaphotoday.domain.storage

import android.content.Context
import softing.ubah4ukdev.nasaphotoday.R

/****
Project Nasa Photo Day
Package softing.ubah4ukdev.nasaphotoday.domain.storage

Created by Ivan Sheynmaer

2021.07.08
v1.0
 */
//Класс для чтения/записи ID темы в sharedPreferences
class AppThemeStorage(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences(
        context.getString(R.string.shared_preference_theme),
        Context.MODE_PRIVATE
    )

    var themeID: Int
        get() = sharedPreferences.getInt(context.getString(R.string.key_theme_id), 0)
        set(value) {
            sharedPreferences
                .edit()
                .putInt(context.getString(R.string.key_theme_id), value)
                .apply()
        }
}